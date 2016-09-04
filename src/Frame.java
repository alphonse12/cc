import java.awt.Color;

import javax.swing.JFrame;


public class Frame extends JFrame{
	public static Panel panel = new Panel();
	public static mouseListener ml = new mouseListener();
	public static mouseWheelListener mwl = new mouseWheelListener();
	public static int FrameRate = 12;
	public static int Speed = 4;
	public static int Buff = 0;
	public static int Generations = 0;
	public static int modify[][] = new int[40000][2];
	public static int a = 0;
	public static boolean Draw = true;
	public static boolean DrawStop = true;
	public static boolean ClicGame = true;
	public static boolean Game[][] = new boolean[256][144];
	public Frame(String str, int x, int y){
		this.setVisible(true);
		this.setSize(x, y);
		this.setTitle(str);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		this.addMouseListener(ml);
		this.addMouseWheelListener(mwl);
		this.setResizable(false);
		GameTrame();}
	
	public static void GameTrame(){
		while(true){
			int Buffer = TimerThread.TIME;
			
			if(Draw){
				Draw = false;
				a = 0;
				DrawTrame();
			}
			
			if(ml.Stop){
				ml.Stop = false;
				DrawStop = true;
				Draw = true;}
			
			Speed+=mwl.wheel;
			if(Speed < 1) Speed = 1;
			mwl.wheel = 0;
			
			Buff++;
			if(Buff >= Speed){
				Buff = 0;
				Generate();
			}
			
			panel.repaint();
			
			if((TimerThread.TIME-Buffer) < FrameRate){
				try {Thread.sleep(FrameRate-(TimerThread.TIME-Buffer));}
				catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}
	
	public static void DrawTrame(){
		while(DrawStop){
			int Buffer = TimerThread.TIME;
			
			if(panel.mousePos[0] > 0 && panel.mousePos[0] < 1280 && panel.mousePos[1] > 0 && panel.mousePos[1] < 720){
				if(ml.Clic){
					if(ml.clic){
						ml.clic = false;
						if(Game[(int)(((double)panel.mousePos[0]/5))][(int)(((double)panel.mousePos[1]/5))]) ClicGame = false;
						else ClicGame = true;
					}
					Game[(int)(((double)panel.mousePos[0]/5))][(int)(((double)panel.mousePos[1]/5))] = ClicGame;
				}
			}
			
			if(ml.Stop){
				ml.Stop = false;
				DrawStop = false;}
			
			panel.repaint();
			
			if((TimerThread.TIME-Buffer) < FrameRate){
				try {Thread.sleep(FrameRate-(TimerThread.TIME-Buffer));}
				catch (InterruptedException e) {e.printStackTrace();}
			}
		}
	}
	
	public static void Generate(){
		a = 0;
		int Light = 0;
		for(int i = 0; i < 256; i++){
			for(int j = 0; j < 144; j++){
				if(Game[i][j]) Light++;
				int nbre = 0;
				if(i == 0){
					if(j == 0){
						if(Game[i+1][j]) nbre++;
						if(Game[i][j+1]) nbre++;
						if(Game[i+1][j+1]) nbre++;
					}
					else if(j == 143){
						if(Game[i+1][j]) nbre++;
						if(Game[i][j-1]) nbre++;
						if(Game[i+1][j-1]) nbre++;
					}
					else{
						if(Game[i][j-1]) nbre++;
						if(Game[i+1][j-1]) nbre++;
						if(Game[i+1][j]) nbre++;
						if(Game[i+1][j+1]) nbre++;
						if(Game[i][j+1]) nbre++;
					}
				}
				else if(i == 255){
					if(j == 0){
						if(Game[i-1][j]) nbre++;
						if(Game[i][j+1]) nbre++;
						if(Game[i-1][j+1]) nbre++;
					}
					else if(j == 143){
						if(Game[i-1][j]) nbre++;
						if(Game[i][j-1]) nbre++;
						if(Game[i-1][j-1]) nbre++;
					}
					else{
						if(Game[i][j-1]) nbre++;
						if(Game[i-1][j-1]) nbre++;
						if(Game[i-1][j]) nbre++;
						if(Game[i-1][j+1]) nbre++;
						if(Game[i][j+1]) nbre++;
					}
				}
				else{
					if(j == 0){
						if(Game[i-1][j]) nbre++;
						if(Game[i-1][j+1]) nbre++;
						if(Game[i][j+1]) nbre++;
						if(Game[i+1][j+1]) nbre++;
						if(Game[i+1][j]) nbre++;
					}
					else if(j == 143){
						if(Game[i-1][j]) nbre++;
						if(Game[i-1][j-1]) nbre++;
						if(Game[i][j-1]) nbre++;
						if(Game[i+1][j-1]) nbre++;
						if(Game[i+1][j]) nbre++;
					}
					else{
						if(Game[i][j+1]) nbre++;
						if(Game[i][j-1]) nbre++;
						if(Game[i+1][j+1]) nbre++;
						if(Game[i+1][j]) nbre++;
						if(Game[i+1][j-1]) nbre++;
						if(Game[i-1][j+1]) nbre++;
						if(Game[i-1][j]) nbre++;
						if(Game[i-1][j-1]) nbre++;
					}
				}
				if(!Game[i][j] && nbre == 3){
					modify[a][0] = i;
					modify[a][1] = j;
					a++;
				}
				if(Game[i][j] && nbre != 2 && nbre != 3){
					modify[a][0] = i;
					modify[a][1] = j;
					a++;
				}
			}
		}
		
		if(Light > 0 && a != 0){
			Generations++;
			for(int i = 0; i < a; i++){
				if(Game[modify[i][0]][modify[i][1]]) Game[modify[i][0]][modify[i][1]] = false;
				else Game[modify[i][0]][modify[i][1]] = true;
			}
		}
		else{
			DrawStop = true;
			Draw = true;
		}
	}
}

