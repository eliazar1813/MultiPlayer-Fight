import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.applet.Applet;

public abstract class GameBase extends Applet implements Runnable, KeyListener, MouseListener
{
	Image    offscreen;
	Graphics offscreen_g;

	Thread t;
		
	public static final int UP = KeyEvent.VK_UP;
	public static final int DN = KeyEvent.VK_DOWN;
	public static final int LT = KeyEvent.VK_LEFT;
	public static final int RT = KeyEvent.VK_RIGHT;

	public static final int _A = KeyEvent.VK_A;
	public static final int _S = KeyEvent.VK_S;
	public static final int _U = KeyEvent.VK_U;
	public static final int _D = KeyEvent.VK_D;
	public static final int _W = KeyEvent.VK_W;
	public static final int _L = KeyEvent.VK_L;
	public static final int _R = KeyEvent.VK_R;

	public static final int SPACE = KeyEvent.VK_SPACE;
	public static final int TAB = KeyEvent.VK_TAB;
	public static final int SHIFT = KeyEvent.VK_SHIFT;
	
	public static final int _1 = KeyEvent.VK_1;
	public static final int _2 = KeyEvent.VK_2;
	public static final int _3 = KeyEvent.VK_3;
	public static final int _4 = KeyEvent.VK_4;
	
	
	
	
	
	boolean[] pressed = new boolean[1024];
	
	
	public abstract void initialize();

	
	public void init()
	{
		initialize();

		offscreen   = createImage(1024, 768);
		offscreen_g = offscreen.getGraphics();
		
		requestFocus();
		addKeyListener(this);
		
		addMouseListener(this);
		
		t = new Thread(this);
		
		t.start();		
	}

	
	public abstract void inTheGameLoop();

	
	public void run()
	{
		while(true)
		{
			inTheGameLoop();
			
 			repaint();
			
			try
			{
		      t.sleep(16);
			}
			catch(Exception x) {};
		}
	}

	public void update(Graphics g)
	{
		offscreen_g.clearRect(0, 0, 1024, 768);
		
		paint(offscreen_g);
		
		g.drawImage(offscreen, 0, 0, null);
	}
	

	
	
	public void keyPressed(KeyEvent e)
	{  
		pressed[e.getKeyCode()] = true;
		
		
	}

	
	public void keyReleased(KeyEvent e)
	{
		pressed[e.getKeyCode()] = false;
		
		
		
	}

	
	public void keyTyped(KeyEvent e) {}
	
	public void mousePressed(MouseEvent e)  {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e)  {}
	public void mouseEntered(MouseEvent e)  {}
	public void mouseExited(MouseEvent e)   {}
	
}