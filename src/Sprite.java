import java.awt.*;

public class Sprite
{
	int x;
	int y;
	
	double vx = 0;
	double vy = 0;
	
	double ax = 0;
	double ay = 0;
	
	Camera camera;
	
	
	
	Animation[] animation = new Animation[12];
	
	
	int pose = 0;
	
	boolean moving = false;
	
	boolean alive = true;
	
	
	public Sprite(int x, int y, String name, String[] pose, int count, String exe, int duration)
	{
		this.x = x;
		this.y = y;
		
	
		
		for(int i = 0; i < pose.length; i++)
		{
			animation[i] = new Animation(name + "_" + pose[i] + "_", count, exe, duration);
		}
	}

	

	public void draw(Graphics g)
	{

		
		if(moving)

			g.drawImage(animation[pose].getImage(), x, y, null);
		
		else
			
			g.drawImage(animation[pose].getStillImage(), x, y, null);	
		
		
		moving = false;	
	}
	
	public void moveUp(int dy)
	{
		y -= dy;
			
		moving = true;
	}
	
	public void moveDown(int dy)
	{
		y += dy;
		
		moving = true;
	}
	
	public void moveLeft(int dx)
	{
		x -= dx;
		
		moving = true;
	}
	
	public void moveRight(int dx)
	{
		x += dx;
		
		moving = true;
	}
	
	
	public void setPosition(int px, int py)
	{
		x = px;
		y = py;
	}
	
	public void setVelocity(double vx, double vy)
	{
		this.vx = vx;
		this.vy = vy;
	}
	
	public void setAccelaration(double ax, double ay)
	{
		this.ax = ax;
		this.ay = ay;
	}
	
	public void move()
	{
		x += vx;
		y += vy;
	
		moving = false;
	}
	
	public void accelarate()
	{
		vx += ax;
		vy += ay;
		
	}
	
	public void bounceV()
	{
		vx = -.9*vx;
		
		moving = true;
	}
	
	public void bounceH()
	{
		vy = -.20 *vy;
		
		moving = true;
	}
	
	
	public void evadeRight (Sprite s)
	{
		double dx = s.x - x;
		double dy = s.y - y;
		
		double d2 = dx*dx + dy*dy;
		
		if (d2 < 40000)  moveRight(5);
		
		}
	
	
	public void evadeLeft (Sprite s)
	{
		double dx = s.x - x;
		double dy = s.y - y;
		
		double d2 = dx*dx + dy*dy;
		
		if (d2 < 40000)  moveLeft(5);
		
		}
	
	
	public void setCamera(Camera camera)
	{
		this.camera = camera;
	}
	
	public void DefaultImageMove() {
		
		moving = true;
	}
	

}
	
	
	
	
	
