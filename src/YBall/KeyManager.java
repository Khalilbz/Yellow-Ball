package YBall;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	public boolean up,down,left,right,enter;
	
	
	public KeyManager(){
		up = false;
		down = false;
		left = false;
		right = false;
		enter = false;
	}

	public void keyPressed(KeyEvent e) {
		//System.out.print(e.getKeyCode()+"\n");
		if(e.getKeyCode() == 38 || e.getKeyCode() == 90){ up = true; }
		if(e.getKeyCode() == 40 || e.getKeyCode() == 83){ down = true; }
		if(e.getKeyCode() == 39 || e.getKeyCode() == 68){ right = true; }
		if(e.getKeyCode() == 37 || e.getKeyCode() == 81){ left = true; }
		if(e.getKeyCode() == 10){ enter = true; }
		
		System.out.print(e.getKeyCode()+"\n");
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 38){ up = false; }
		if(e.getKeyCode() == 40){ down = false; }
		if(e.getKeyCode() == 39){ right = false; }
		if(e.getKeyCode() == 37){ left = false; }
		if(e.getKeyCode() == 10){ enter = false; }
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
