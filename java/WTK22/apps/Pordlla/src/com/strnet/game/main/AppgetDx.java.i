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
 * DX会員認証クラス
 */
public class AppgetDx {
	// カレントのIApplication
	private static IApplication curApp = IApplication.getCurrentApp();

	// デバッグモード
	private static boolean isDebug = false;

	/**
	 * 入会状態: 入会済み(=1)
	 */
	private static int MEMBER_TYPE_REGIST = 1;

	/**
	 * 入会状態: 未入会または退会済み(=2)
	 */
	private static int MEMBER_TYPE_UNREGIST = 2;

	/**
	 * 入会状態： HTTP通信に失敗し取得できなかった(=3)
	 */
	private static int MEMBER_TYPE_REQUEST_ERR = 3;

	// 認証確認ＵＲＬ
	private static String urlCheck = curApp.getSourceURL() + "check";

	// ランキング表示ＵＲＬ
	// private static String urlRankView =
	// "http://localhost:8080/appget-dx/page/viewRank/000001/?uid=NULLGWDOCOMO";
	private static String urlRankView = curApp.getSourceURL()
			+ "viewRank?uid=NULLGWDOCOMO";

	// ランキング登録ＵＲＬ
	// private static String urlRankAdd =
	// "http://localhost:8080/appget-dx/page/addRank/000001/?uid=NULLGWDOCOMO";
	private static String urlRankAdd = curApp.getSourceURL()
			+ "addRank?uid=NULLGWDOCOMO";

	// DXのウェブサイト
	private static String urlDx = "http://appget.com/dxi/";

	/**
	 * デバッグモードを設定する
	 * 
	 * @param debug
	 *            デバッグモードをONにする場合真
	 */
	public static void setDebug(boolean debug) {
		isDebug = debug;
	}

	/**
	 * 入会済み会員かどうかを判定をして返す
	 * 
	 * @param userId
	 *            ユーザーＩＤ
	 * 
	 * @return 入会状態(MEMBER_TYPE_REGSIT、MEMBER_TYPE_UNREGSIT、MEMBER_TYPE_REQUEST_ERRのいずれか)
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
	 * 公式サイトアプリゲットDXへ入会済みのユーザかどうかをチェックし規定の処理を行う。<br>
	 * <br>
	 * 会員の場合は何もしない<br>
	 * 非会員の場合は "アプリゲットDXの会員のみ利用できます。"というダイアログを表示して規定のWEBサイトへ誘導してアプリ終了<br>
	 * 通信失敗の場合は"電波の良いところで再度起動してください。"というダイアログを表示してアプリ終了<br>
	 * <br>
	 * いずれの場合でも起動時刻をスクラッチパッドに書きこむ。 <br>
	 * 
	 * @param pos
	 *            起動時刻を書き込むスクラッチパッドの開始位置(開始位置から8バイト使用する)
	 */
	public static void check(int pos) {
		if (alreadyChecked(pos))
			return;
		if (!needCheck())
			return;

		debug("Start auth check");

		// AppParamよりユーザーＩＤの受け取り
		int userId = getUserId();
		if (userId == -1) {
			showErrDialog("エラーが発生しました", "お手数ですが再度アプリをダウンロードしてください。");
			curApp.terminate();
		}

		int ret = isMember(userId);
		if (ret == MEMBER_TYPE_REGIST) {
			// 正常
		} else if (ret == MEMBER_TYPE_UNREGIST) {
			showErrDialog("起動エラー", "アプリゲットDXの会員のみ利用できます。");
			curApp.launch(IApplication.LAUNCH_BROWSER, new String[] { urlDx, });
			curApp.terminate();

		} else {
			showErrDialog("起動エラー", "電波の良いところで再度起動してください。");
			curApp.terminate();
		}

		writeTime(pos, new Date());
	}

	/**
	 * ブラウザを起動して、ランキング表示用ＵＲＬをリクエストする。<br>
	 * <br>
	 * 登録したい点数を入力するとランキング登録用ＵＲＬを作成する。<br>
	 */
	public static void viewRank() {
		debug("Start viewRank");
		debug(urlRankView);

		curApp.launch(IApplication.LAUNCH_BROWSER,
						new String[] { urlRankView });
	}

	/**
	 * ブラウザを起動して、ランキング登録用ＵＲＬをリクエストする。<br>
	 * <br>
	 * 登録したい点数を入力するとランキング登録用ＵＲＬを作成する。<br>
	 * 
	 * @param score
	 *            ランキングに登録したい点数
	 */
	public static void addRank(int score) {
		// アプリＩＤの取得
		String path = urlRankAdd;
		path = path.trim();
		// 000001 みたいなアプリＩＤの文字列
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
	 * 不正にランキング登録をさせないために、スコア以外に特別な文字列を使ってチェックする。<br>
	 * 登録のサーブレット側でも、アプリＩＤとスコアから特別な文字列を作成して、同じであれば 正規のデータであるとする。
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
			// 16進数で４桁になるまでループ
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
		// 起動時刻をスクラッチパッドに書き込み
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
	 * エラーダイアログを表示する。
	 * 
	 * @param title
	 *            ダイアログのタイトル
	 * @param txt
	 *            ダイアログに表示するテキスト
	 */
	private static void showErrDialog(String title, String txt) {
		Dialog dlg = new Dialog(Dialog.DIALOG_ERROR, title);
		dlg.setText(txt);
		dlg.show();
	}
}
