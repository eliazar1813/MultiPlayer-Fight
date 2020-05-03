
public class Camera
{
	int x; 
	int y;

	public Camera(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void moveLeft(int dx)
	{
		x -= dx;
	}
	
	public void moveRight(int dx)
	{
		x += dx;
	}
	
	public void moveUp(int dy)
	{
		y -= dy;
	}

	public void moveDown(int dy)
	{
		y += dy;
	}
}