
import java.awt.Graphics;

public class Rect
{
	int x;
	int y;
	
	int w;
	int h;
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
				
		this.w = w;
		this.h = h;
	}
	
	public boolean overlaps(Rect r)
	{
		return (y+h >= r.y) && (r.y+r.h >= y) && (x+w >= r.x) && (r.x+r.w >= x);
	}
	
	public boolean contains(int mx, int my)
	{
		return (mx >= x)      &&
				 (mx <= x + w)  &&
				 (my >= y)      &&
				 (my <= y + h);
	}
	
	public void moveLeftBy(int dx)
	{
		x -= dx;
	}
	
	public void moveRightBy(int dx)
	{
		x += dx;
	}
	
	public void moveUpBy(int dy)
	{
		y -= dy;
	}
	
	public void moveDownBy(int dy)
	{
		y += dy;
	}
	
	public void moveBy(int dx, int dy)
	{
	   x += dx;
	   y += dy;
	}
	
	public void draw(Graphics g)
	{
		g.drawRect(x, y, w, h);
	}

}