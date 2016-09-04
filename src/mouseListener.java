import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouseListener implements MouseListener{
	public static boolean Clic;
	public static boolean Stop;
	public static boolean clic;
	public static boolean precise;

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		if(arg0.getButton() == 1){
			Clic = true;
			clic = true;
		}
		if(arg0.getButton() == 3) Stop = true;
		if(arg0.getButton() == 2) precise = true;
	}

	public void mouseReleased(MouseEvent arg0) {
		if(arg0.getButton() == 1) Clic = false;
	}

}

