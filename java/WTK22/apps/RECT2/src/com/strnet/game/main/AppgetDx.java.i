package com.strnet.game.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.microedition.io.Connector;

import com.nttdocomo.io.HttpConnection;
import com.nttdocomo.ui.Dialog;
import com.nttdocomo.ui.IApplication;

/**
 * DX����F�؃N���X
 */
public class AppgetDx {
	// �J�����g��IApplication
	private static IApplication curApp = IApplication.getCurrentApp();

	// �f�o�b�O���[�h
	private static boolean isDebug = false;

	/**
	 * ������: ����ς�(=1)
	 */
	private static int MEMBER_TYPE_REGIST = 1;

	/**
	 * ������: ������܂��͑މ�ς�(=2)
	 */
	private static int MEMBER_TYPE_UNREGIST = 2;

	/**
	 * �����ԁF HTTP�ʐM�Ɏ��s���擾�ł��Ȃ�����(=3)
	 */
	private static int MEMBER_TYPE_REQUEST_ERR = 3;

	// �F�؊m�F�t�q�k
	private static String urlCheck = curApp.getSourceURL() + "check";

	// �����L���O�\���t�q�k
	// private static String urlRankView =
	// "http://localhost:8080/appget-dx/page/viewRank/000001/?uid=NULLGWDOCOMO";
	private static String urlRankView = curApp.getSourceURL()
			+ "viewRank?uid=NULLGWDOCOMO";

	// �����L���O�o�^�t�q�k
	// private static String urlRankAdd =
	// "http://localhost:8080/appget-dx/page/addRank/000001/?uid=NULLGWDOCOMO";
	private static String urlRankAdd = curApp.getSourceURL()
			+ "addRank?uid=NULLGWDOCOMO";

	// DX�̃E�F�u�T�C�g
	private static String urlDx = "http://appget.com/dxi/";

	/**
	 * �f�o�b�O���[�h��ݒ肷��
	 * 
	 * @param debug
	 *            �f�o�b�O���[�h��ON�ɂ���ꍇ�^
	 */
	public static void setDebug(boolean debug) {
		isDebug = debug;
	}

	/**
	 * ����ς݉�����ǂ����𔻒�����ĕԂ�
	 * 
	 * @param userId
	 *            ���[�U�[�h�c
	 * 
	 * @return ������(MEMBER_TYPE_REGSIT�AMEMBER_TYPE_UNREGSIT�AMEMBER_TYPE_REQUEST_ERR�̂����ꂩ)
	 */
	private static int isMember(int userId) {
		HttpConnection conn = null;
		InputStream input = null;

		try {
			conn = (HttpConnection) Connector.open(
					urlCheck + "?user=" + userId, Connector.READ, true);
			conn.setRequestMethod("GET");
			conn.connect();

			if (conn.getResponseCode() != HttpConnection.HTTP_OK)
				return MEMBER_TYPE_REQUEST_ERR;

			input = conn.openInputStream();
			String res = String.valueOf((char) input.read());

			try {
				int ires = Integer.parseInt(res);
				debug("server res: " + ires);
				return ires;

			} catch (NumberFormatException e) {
				debug("[ERR]isMember: " + e.getMessage() + ": " + res);
				return MEMBER_TYPE_REQUEST_ERR;
			}

		} catch (IOException e) {
			debug("[ERR]isMember: " + e.getMessage());
			return MEMBER_TYPE_REQUEST_ERR;

		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				//
			}

			try {
				if (conn != null)
					conn.close();
			} catch (IOException e) {
				//
			}
		}
	}

	/**
	 * �����T�C�g�A�v���Q�b�gDX�֓���ς݂̃��[�U���ǂ������`�F�b�N���K��̏������s���B<br>
	 * <br>
	 * ����̏ꍇ�͉������Ȃ�<br>
	 * �����̏ꍇ�� "�A�v���Q�b�gDX�̉���̂ݗ��p�ł��܂��B"�Ƃ����_�C�A���O��\�����ċK���WEB�T�C�g�֗U�����ăA�v���I��<br>
	 * �ʐM���s�̏ꍇ��"�d�g�̗ǂ��Ƃ���ōēx�N�����Ă��������B"�Ƃ����_�C�A���O��\�����ăA�v���I��<br>
	 * <br>
	 * ������̏ꍇ�ł��N���������X�N���b�`�p�b�h�ɏ������ށB <br>
	 * 
	 * @param pos
	 *            �N���������������ރX�N���b�`�p�b�h�̊J�n�ʒu(�J�n�ʒu����8�o�C�g�g�p����)
	 */
	public static void check(int pos) {
		if (alreadyChecked(pos))
			return;
		if (!needCheck())
			return;

		debug("Start auth check");

		// AppParam��胆�[�U�[�h�c�̎󂯎��
		int userId = getUserId();
		if (userId == -1) {
			showErrDialog("�G���[���������܂���", "���萔�ł����ēx�A�v�����_�E�����[�h���Ă��������B");
			curApp.terminate();
		}

		int ret = isMember(userId);
		if (ret == MEMBER_TYPE_REGIST) {
			// ����
		} else if (ret == MEMBER_TYPE_UNREGIST) {
			showErrDialog("�N���G���[", "�A�v���Q�b�gDX�̉���̂ݗ��p�ł��܂��B");
			curApp.launch(IApplication.LAUNCH_BROWSER, new String[] { urlDx, });
			curApp.terminate();

		} else {
			showErrDialog("�N���G���[", "�d�g�̗ǂ��Ƃ���ōēx�N�����Ă��������B");
			curApp.terminate();
		}

		writeTime(pos, new Date());
	}

	/**
	 * �u���E�U���N�����āA�����L���O�\���p�t�q�k�����N�G�X�g����B<br>
	 * <br>
	 * �o�^�������_������͂���ƃ����L���O�o�^�p�t�q�k���쐬����B<br>
	 */
	public static void viewRank() {
		debug("Start viewRank");
		debug(urlRankView);

		curApp.launch(IApplication.LAUNCH_BROWSER,
						new String[] { urlRankView });
	}

	/**
	 * �u���E�U���N�����āA�����L���O�o�^�p�t�q�k�����N�G�X�g����B<br>
	 * <br>
	 * �o�^�������_������͂���ƃ����L���O�o�^�p�t�q�k���쐬����B<br>
	 * 
	 * @param score
	 *            �����L���O�ɓo�^�������_��
	 */
	public static void addRank(int score) {
		// �A�v���h�c�̎擾
		String path = urlRankAdd;
		path = path.trim();
		// 000001 �݂����ȃA�v���h�c�̕�����
		String strApid = path.substring(path.lastIndexOf('/', path
				.lastIndexOf('/') - 1) + 1, path.lastIndexOf('/'));
		// 000001 -> 1
		int apid = Integer.parseInt(strApid);

		debug("Start addRank");
		debug(urlRankAdd);
		debug("score " + String.valueOf(score));

		String key = makeChkKey(score, apid);
		String urlRankAddWithArg = urlRankAdd + "&score=" + score + "&key="
				+ key;
		debug(urlRankAddWithArg);

		curApp.launch(IApplication.LAUNCH_BROWSER,
				new String[] { urlRankAddWithArg });
	}

	/*
	 * �s���Ƀ����L���O�o�^�������Ȃ����߂ɁA�X�R�A�ȊO�ɓ��ʂȕ�������g���ă`�F�b�N����B<br>
	 * �o�^�̃T�[�u���b�g���ł��A�A�v���h�c�ƃX�R�A������ʂȕ�������쐬���āA�����ł���� ���K�̃f�[�^�ł���Ƃ���B
	 */
	private static String makeChkKey(int inScore, int inApId) {
		int intTmp = 0;
		final int maxScore = 1000000000;
		String strTmp = "";
		String strTmp2 = "";
		String strReturn = "";
		StringBuffer sb;
		
		if(inScore > maxScore){
			inScore = maxScore;
		}
		intTmp = inScore + inApId;
		while (intTmp <= 32768) {
			// 16�i���łS���ɂȂ�܂Ń��[�v
			intTmp = intTmp * intTmp + inScore;
		}
		strTmp = Integer.toHexString(intTmp);

		sb = new StringBuffer(strTmp);
		String rev = sb.reverse().toString().substring(0,4);
		int i = Integer.valueOf(rev, 16).intValue();
		strTmp2 = Integer.toHexString(i + intTmp);

		strReturn = strTmp.substring(0, 2) + strTmp2.substring(0, 2)
				+ strTmp.substring(2, 4) + strTmp2.substring(2, 4);

		return strReturn;
	}

	private static boolean needCheck() {
		String[] args = curApp.getArgs();

		if (args == null || args.length < 2) {
			return true;
		}

		String str = args[args.length - 1];
		return !str.equals("nocheck");
	}

	private static int getUserId() {
		String[] args = curApp.getArgs();

		if (args == null || args.length == 0) {
			debug("[ERR]getUserId: Param Error: " + curApp.getArgs());
			return -1;
		}

		try {
			return Integer.parseInt(args[0]);

		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private static Date readTime(int pos) {
		DataInputStream in = null;
		try {
			in = new DataInputStream(Connector
					.openInputStream("scratchpad:///0;pos=" + pos));
			long l;
			l = in.readLong();
			if (l == 0)
				return null;
			return new Date(l);

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());

		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				//
			}
		}
	}

	private static void writeTime(int pos, Date date) {
		// �N���������X�N���b�`�p�b�h�ɏ�������
		DataOutputStream o = null;

		try {
			o = new DataOutputStream(Connector
					.openOutputStream("scratchpad:///0;pos=" + pos));
			o.writeLong(date.getTime());

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());

		} finally {
			try {
				if (o != null)
					o.close();
			} catch (IOException e) {
				//
			}
		}
	}

	private static boolean alreadyChecked(int pos) {
		Date spDate = readTime(pos);
		debug("sp date: " + spDate);

		return spDate != null && getMonth(spDate) == getMonth(new Date());
	}

	private static Calendar cur = Calendar.getInstance();

	private static int getMonth(Date date) {
		cur.setTime(date);
		return cur.get(Calendar.MONTH);
	}

	private static void debug(String message) {
		if (isDebug)
			System.out.println(message);
	}

	/**
	 * �G���[�_�C�A���O��\������B
	 * 
	 * @param title
	 *            �_�C�A���O�̃^�C�g��
	 * @param txt
	 *            �_�C�A���O�ɕ\������e�L�X�g
	 */
	private static void showErrDialog(String title, String txt) {
		Dialog dlg = new Dialog(Dialog.DIALOG_ERROR, title);
		dlg.setText(txt);
		dlg.show();
	}
}
