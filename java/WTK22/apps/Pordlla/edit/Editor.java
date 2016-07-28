import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Random;
import javax.imageio.*;
import javax.swing.*;

class MapChip extends JButton {
    public int gId = 0;
    public MapChip(Icon i) {
		super(i);
    }
}

public class Editor extends JFrame implements ActionListener
{
	static final String DAT_DIR = "./dat/";
	static final String OUT_DIR = "./out/";

    public ImageIcon[] chips;
    public MapChip[][] map;
    public MapChip[][] tmpMap;
    public MapChip[][] viewMap;
    public int w, h, imageWidth;
    public int selectId = 0;
    public int defaultId = 0;
    public MapChip selectedIcon = null;
	public JTextField rotateField;
	public JTextField stageField;

	public void clear()
	{
		for ( int i = 0; i < h; i++ ) {
			for ( int j = 0; j < w; j++ ) {
				map[i][j].gId = defaultId;
				map[i][j].setIcon(chips[map[i][j].gId]);
			}
		}
	}

    public void save()
    {
		StringBuffer sb = new StringBuffer();
		sb.append(rotateField.getText());
		sb.append("\n");
		for ( int i = 0; i < h; i++ ) {
			for ( int j = 0; j < w; j++ ) {
				sb.append(map[i][j].gId);
				if ( j < w - 1 ) {
					sb.append(", ");
				}
			}
			sb.append("\n");
		}
    	
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(DAT_DIR + getStage() + "_dat.txt"));
			out.write(sb.toString());
			out.close();
		} catch ( Exception e ){
			e.printStackTrace();
		}
    	
    	
    	
		sb = new StringBuffer();
		sb.append("m = new int[][]{");
		sb.append("\n");
		for ( int i = 0; i < h; i++ ) {
			sb.append("{");
			for ( int j = 0; j < w; j++ ) {
				sb.append(map[i][j].gId);
				if ( j < w - 1 ) {
					sb.append(", ");
				}
			}
			sb.append("},\n");
		}
		sb.append("};//" + stageField.getText());
		sb.append("\n");
		sb.append("maps.addElement(m);");
		sb.append("\n");
		sb.append("rotates.addElement(\"" + rotateField.getText() + "\");");
		sb.append("\n");
    	
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(OUT_DIR + getStage() + ".txt"));
			out.write(sb.toString());
			out.close();
		} catch ( Exception e ){
			e.printStackTrace();
		}
    }
	
	public void load()
	{
		try {
			BufferedReader in = new BufferedReader(new FileReader(DAT_DIR + getStage() + "_dat.txt"));
			String tmp = null;
			StringBuffer ret = new StringBuffer();
			int x = 0;
			boolean itigyou = false;
			while ((tmp = in.readLine()) != null ) {
				if ( !itigyou )
				{
					rotateField.setText(tmp.trim());
					itigyou = true;
				}
				else
				{
					String[] arr = tmp.split(", ");
					for ( int i = 0; i < arr.length; i++ )
					{
						map[x][i].gId = Integer.parseInt(arr[i]);
						map[x][i].setIcon(chips[map[x][i].gId]);
					}
					x++;
				}
			}
			in.close();
			preview();
		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}

    public void actionPerformed(ActionEvent ae)
    {
		MapChip mc = (MapChip)ae.getSource();
		mc.gId = selectId;
		mc.setIcon(chips[mc.gId]);
    }

    public Editor(File f, int w, int h, int imageWidth, int defaultId) throws Exception
    {
		this.defaultId = defaultId;
		rotateField = new JTextField();
		BufferedImage image = ImageIO.read(f);
		int chipX = image.getWidth() / imageWidth;
		int chipY = image.getHeight() / imageWidth;
		chips = new ImageIcon[chipX * chipY];
		int counter = 0;
		for ( int j = 0; j < chipY; j++ ) {
			for ( int i = 0; i < chipX; i++ ) {
				chips[counter++] = new ImageIcon(image.getSubimage(i * imageWidth, j * imageWidth, imageWidth, imageWidth));
			}
		}

		image = ImageIO.read(f);
		setSize(800, 600);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.w = w;
		this.h = h;
		this.imageWidth = imageWidth;
	
		Container pane = getContentPane();


		JPanel panel = new JPanel(new GridLayout(h, w, 0, 0));
		map = new MapChip[h][w];
		for ( int i = 0; i < h; i++ ) {
			for ( int j = 0; j < w; j++ ) {
				int id = defaultId;
				map[i][j] = new MapChip(chips[id]);
				map[i][j].gId = id;
				map[i][j].setBorderPainted(false);
				map[i][j].addActionListener(this);
				panel.add(map[i][j]);
			}
		}
		panel.setBounds(0, 0, w*imageWidth, h*imageWidth);
		pane.add(panel);


		JPanel tmpPanel = new JPanel(new GridLayout(h, w, 0, 0));
		tmpMap = new MapChip[h][w];
		for ( int i = 0; i < h; i++ ) {
			for ( int j = 0; j < w; j++ ) {
				int id = defaultId;
				tmpMap[i][j] = new MapChip(chips[id]);
				tmpMap[i][j].gId = id;
				tmpMap[i][j].setBorderPainted(false);
				tmpMap[i][j].addActionListener(this);
				tmpPanel.add(tmpMap[i][j]);
			}
		}
		tmpPanel.setBounds(500, 50, w*imageWidth, h*imageWidth);
		pane.add(tmpPanel);


		JPanel viewPanel = new JPanel(new GridLayout(h, w, 0, 0));
		viewMap = new MapChip[h][w];
		for ( int i = 0; i < h; i++ ) {
			for ( int j = 0; j < w; j++ ) {
				int id = defaultId;
				viewMap[i][j] = new MapChip(chips[id]);
				viewMap[i][j].gId = id;
				viewMap[i][j].setBorderPainted(false);
				viewPanel.add(viewMap[i][j]);
			}
		}
		viewPanel.setBounds(200, 300, w*imageWidth, h*imageWidth);
		pane.add(viewPanel);


		JPanel panel2 = new JPanel(new GridLayout(chipY, chipX, 0, 0));
		counter = 0;
		for ( int i = 0; i < chips.length; i++ ) {
			MapChip m = new MapChip(chips[i]);
			m.gId = i;
			//m.setBorderPainted(false);
			m.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						selectId = ((MapChip)ae.getSource()).gId;
						selectedIcon.setIcon(chips[selectId]);
					}
				});
			panel2.add(m);
		}
		panel2.setBounds(w*imageWidth + 10, 0, chipX * imageWidth, chipY*imageWidth);
		pane.add(panel2);

		JButton newb = new JButton("new");
		newb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					newb();
				}
			});
		newb.setBounds(0, h*imageWidth + 10, 80, 30);
		pane.add(newb);


		JButton load = new JButton("load");
		load.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					load();
				}
			});
		load.setBounds(0, h*imageWidth + 50, 80, 30);
		pane.add(load);


		JButton save = new JButton("save");
		save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					save();
				}
			});
		save.setBounds(0, h*imageWidth + 90, 80, 30);
		pane.add(save);

		JButton clear = new JButton("clear");
		clear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					clear();
				}
			});
		clear.setBounds(0, h*imageWidth + 290, 80, 30);
		pane.add(clear);

		JButton upup = new JButton("upup");
		upup.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					upup();
				}
			});
		upup.setBounds(200, 150, 80, 30);
		pane.add(upup);

		JButton build = new JButton("build");
		build.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					build();
				}
			});
		build.setBounds(280, 220, 80, 30);
		pane.add(build);


		JButton copy = new JButton("copy");
		copy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					copy();
				}
			});
		copy.setBounds(500, h*imageWidth + 130, 80, 30);
		pane.add(copy);


		
		JButton tmpClear = new JButton("tmp clear");
		tmpClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					tmpClear();
				}
			});
		tmpClear.setBounds(500, h*imageWidth + 170, 140, 30);
		pane.add(tmpClear);


		
		JButton preview = new JButton("preview");
		preview.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					preview();
				}
			});
		preview.setBounds(50, h*imageWidth + 170, 80, 30);
		pane.add(preview);
		
		rotateField = new JTextField();
		rotateField.setBounds(220, 250, 120, 20);
		pane.add(rotateField);

		stageField = new JTextField("0");
		stageField.setBounds(220, 200, 20, 20);
		pane.add(stageField);

		selectedIcon = new MapChip(chips[defaultId]);
		selectedIcon.setBounds(150, h*imageWidth + 10, imageWidth, imageWidth);
		pane.add(selectedIcon);
		newb();
		setVisible(true);
    }
	
	private static Random rand = new Random();
	public void newb()
	{
		stageField.setText((Integer.parseInt(stageField.getText()) + 1) + "");
		for ( int i = 0; i < h; i++ ) {
			for ( int j = 0; j < w; j++ ) {
				int id = defaultId;
				map[i][j].gId = id;
				map[i][j].setIcon(chips[map[i][j].gId]);
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for ( int i = 0; i < h; i++ )
		{
			sb.append((rand.nextInt() >>> 1) % 7);
		}
		rotateField.setText(sb.toString());
	}
	
	public void preview()
	{
		for ( int i = 0; i < h; i++ ) {
			for ( int j = 0; j < w; j++ ) {
				viewMap[i][j].gId = map[i][j].gId;
			}
		}
		String r = rotateField.getText();
		for ( int i = 0; i < r.length(); i++ )
		{
			int count = Integer.parseInt(r.charAt(i) + "");
			while ( --count >= 0 )
			{
				rotateLeft(i, viewMap);
			}
		}
		for ( int i = 0; i < h; i++ ) {
			for ( int j = 0; j < w; j++ ) {
				viewMap[i][j].setIcon(chips[viewMap[i][j].gId]);
			}
		}
	}


	public void tmpClear()
	{
		for ( int i = 0; i < h; i++ ) {
			for ( int j = 0; j < w; j++ ) {
				tmpMap[i][j].gId = defaultId;
				tmpMap[i][j].setIcon(chips[tmpMap[i][j].gId]);
			}
		}
	}


	public void copy()
	{
		for ( int i = 0; i < h; i++ ) {
			for ( int j = 0; j < w; j++ ) {
				map[i][j].gId = tmpMap[i][j].gId;
				map[i][j].setIcon(chips[map[i][j].gId]);
			}
		}
	}
	
	public void rotateLeft(int row, MapChip[][] map)
	{
		int left = map[row][0].gId;
		
		for ( int col = 0; col < w - 1; col++ )
		{
			map[row][col].gId = map[row][col+1].gId;
		}
		map[row][w-1].gId = left;
	}


	public void upup()
	{
		for ( int row = h - 1; row > 0; row-- )
		{
			for ( int col = 0; col < w; col++ )
			{
				if ( (map[row][col].gId > 0) && 
					 (map[row - 1][col].gId == 0) )
				{
					if ( ((rand.nextInt() >>> 1) % 2) == 0)
					{
						map[row - 1][col].gId = map[row][col].gId;
						map[row][col].gId = defaultId;
					}
				}
			}
		}

		for ( int i = 0; i < h; i++ ) {
			for ( int j = 0; j < w; j++ ) {
				map[i][j].setIcon(chips[map[i][j].gId]);
			}
		}
	}
	
	public void build()
	{
		try
		{
			
			try
			{
				for ( int i = 0; i< 100; i++ )
				{
					stageField.setText(i+"");
					load();
					save();
				}
			}
			catch ( Exception ignore ) {}
			
			File dir = new File("out");
			File[] files = dir.listFiles();
		
			StringBuffer sb = new StringBuffer();
			sb.append("package com.strnet.game.main;\n");
			sb.append("import java.util.Vector;\n");
			sb.append("public class EasyAutoMap\n");
			sb.append("{\n");
			sb.append("public static void init(Vector maps, Vector rotates){\n");
			sb.append("int[][] m;\n");
			
			
			String tmp;
			for ( int i=0;i < 30;i++ )
			{
				BufferedReader in = new BufferedReader(new FileReader(files[i]));
				while ((tmp = in.readLine()) != null ) {
					sb.append(tmp);
					sb.append("\n");
				}
				sb.append("\n");
				in.close();
			}
			sb.append("}}");
			
			BufferedWriter out = new BufferedWriter(new FileWriter("../src/com/strnet/game/main/EasyAutoMap.java"));
			out.write(sb.toString());
			out.close();





			sb = new StringBuffer();
			sb.append("package com.strnet.game.main;\n");
			sb.append("import java.util.Vector;\n");
			sb.append("public class NormalAutoMap\n");
			sb.append("{\n");
			sb.append("public static void init(Vector maps, Vector rotates){\n");
			sb.append("int[][] m;\n");
			
			
			for ( int i=30;i < 70;i++ )
			{
				BufferedReader in = new BufferedReader(new FileReader(files[i]));
				while ((tmp = in.readLine()) != null ) {
					sb.append(tmp);
					sb.append("\n");
				}
				sb.append("\n");
				in.close();
			}
			sb.append("}}");
			
			out = new BufferedWriter(new FileWriter("../src/com/strnet/game/main/NormalAutoMap.java"));
			out.write(sb.toString());
			out.close();





			sb = new StringBuffer();
			sb.append("package com.strnet.game.main;\n");
			sb.append("import java.util.Vector;\n");
			sb.append("public class HardAutoMap\n");
			sb.append("{\n");
			sb.append("public static void init(Vector maps, Vector rotates){\n");
			sb.append("int[][] m;\n");
			
			
			for ( int i=70;i < files.length;i++ )
			{
				BufferedReader in = new BufferedReader(new FileReader(files[i]));
				while ((tmp = in.readLine()) != null ) {
					sb.append(tmp);
					sb.append("\n");
				}
				sb.append("\n");
				in.close();
			}
			sb.append("}}");
			
			out = new BufferedWriter(new FileWriter("../src/com/strnet/game/main/HardAutoMap.java"));
			out.write(sb.toString());
			out.close();









		}
		catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public String getStage()
	{
		int s = Integer.parseInt(stageField.getText());
		StringBuffer ret = new StringBuffer();
		if ( s < 100 )
		{
			ret.append("0");
		}
		if ( s < 10 )
		{
			ret.append("0");
		}
		ret.append(s);
		return ret.toString();
	}
	
    public static void main(String[] args) throws Exception
    {
		Editor m = new Editor(new File("./map.png"),
							  7,
							  10,
							  22,
							  0);
    }
}
