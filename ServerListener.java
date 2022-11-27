package listener;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JButton;
import frame.Frame;
public class ServerListener implements MouseListener{
	
	Frame frame;

	public ServerListener( Frame frame ){
		this.setFrame( frame );
	}

	public void setFrame( Frame frame ){
		this.frame = frame;
	}
	public Frame getFrame(){
		return this.frame;
	}

	public void mousePressed( MouseEvent e ){}
	public void mouseClicked( MouseEvent e ){
		if( e.getSource() instanceof JButton ){
			if( ((JButton)e.getSource()).getText().equalsIgnoreCase("Start server") ){
				this.getFrame().getFramePanel().setStart(true);
				this.getFrame().getFramePanel().update();
			}else{
				this.getFrame().getFramePanel().setStart(false);
				this.getFrame().getFramePanel().update();
			}
		}
	}
	public void mouseEntered( MouseEvent e ){}
	public void mouseReleased( MouseEvent e ){}
	public void mouseExited( MouseEvent e ){}
}