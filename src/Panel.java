import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;

import javax.swing.JPanel;


public class Panel extends JPanel{
	public static int mousePos[] = new int[2];
	public void paintComponent(Graphics g){
		mousePos[0] = MouseInfo.getPointerInfo().getLocation().x-getLocationOnScreen().x;
		mousePos[1] = MouseInfo.getPointerInfo().getLocation().y-getLocationOnScreen().y;
		g.setColor(new Color(0, 0, 40));
		g.fillRect(0, 0, getSize().width, getSize().height);
		for(int i = 0; i < 256; i++){
			for(int j = 0; j < 144; j++){
				g.setColor(new Color(0, 0, 0));
				if(Frame.Game[i][j]) g.setColor(new Color(255, 255, 255));
				g.fillRect(1+i*5, 1+j*5, 4, 4);
			}
		}
		g.setColor(new Color(255, 255, 255, 100));
		if(!Frame.DrawStop) g.setColor(new Color(255, 255, 255, 200));
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Speed (Delay) = "+Frame.Speed, 5, 20);
		String Mode = "Game";
		if(Frame.DrawStop) Mode = "Draw";
		g.setColor(new Color(255, 255, 255, 200));
		g.drawString("Mode = "+Mode, 5, 40);
		g.drawString("Generation "+Frame.Generations, 1100, 20);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("Conway's Game of Life", 490, 30);
		g.setFont(new Font("Arial", Font.BOLD, 17));
		g.drawString("by Spectron", 610, 50);
	}
}

