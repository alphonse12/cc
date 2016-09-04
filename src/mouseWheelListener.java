import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class mouseWheelListener implements MouseWheelListener{
	int wheel = 0;
	
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		wheel = arg0.getWheelRotation();
	}

}