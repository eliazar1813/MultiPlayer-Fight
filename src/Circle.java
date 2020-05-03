import java.awt.*;

public class Circle
{
	double x;
	double y;
	
	int r;

	int angle;
	
	public Circle(double x, double y, int r, int angle)
	{
		this.x = x;
		this.y = y;
		
		this.r = r;
		
		this.angle = angle;
	}
	
	public void chase(Circle c)
	{
		turnToward(c);
		
		double dx = c.x - x;
		double dy = c.y - y;
		
		double d2 = dx*dx + dy*dy;
		
		if (d2 > 40000)    moveForwardBy(4);
	}
	
	public void evade(Circle c)
	{
		turnAwayFrom(c);
		
		double dx = c.x - x;
		double dy = c.y - y;
		
		double d2 = dx*dx + dy*dy;
		
		if (d2 < 40000)    moveForwardBy(4);
	}
	
	public void turnToward(Circle c)
	{
		double d = signedDistance(c);
		
		if(d >  10)  rotateLeftBy(3);		
	
		if(d < -10)  rotateRightBy(3);
	}
	
	public void turnAwayFrom(Circle c)
	{
		double d = signedDistance(c);
		
		if(d <  -10)  rotateLeftBy(4);		
	
		if(d >   10)  rotateRightBy(4);
	}
	
	public double signedDistance(Circle c)
	{
		double cosA = LookUp.cos[angle];
		double sinA = LookUp.sin[angle];
		
		return (c.x - x)*sinA - (c.y - y)*cosA;
	}

	public boolean overlaps(Circle c)
	{
		double dx = x - c.x;
		double dy = y - c.y;
		
		double d2 = dx*dx + dy*dy;
		
		return d2 <= (r + c.r) * (r + c.r);
	}
	
	public boolean overlaps(Line L)
	{
		double d = L.signedDistanceTo(this);
		
		return d <= r;
	}
	
	public void moveBackFrom(Line L)
	{
		double d = L.signedDistanceTo(this);
		
		double mag = r - d;
		
		x += mag * L.xN;
		y += mag * L.yN;
	}
	
	
	public void rotateBy(int dangle)
	{
	   angle += dangle;
	   
	   if(angle >= 360)   angle -= 360;
	   
	   if(angle < 0)      angle += 360;
  	}

   
	
	public void rotateLeftBy(int dangle)
	{
	   angle -= dangle;
	   
	   if(angle < 0)      angle += 360;
  	}

   
	
	public void rotateRightBy(int dangle)
	{
	   angle += dangle;
	   
	   if(angle >= 360)   angle -= 360;
  	}

   
   public void moveForwardBy(int distance)
   {
   	double radians = angle*Math.PI/180;
   	double dx = distance * Math.cos(radians);
   	double dy = distance * Math.sin(radians);
   	
   	x += dx;
   	y += dy;
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
		g.setColor(Color.yellow);
		g.fillOval((int)(x-r), (int)(y-r), 2*r, 2*r);
		g.setColor(Color.black);
		g.drawOval((int)(x-r), (int)(y-r), 2*r, 2*r);
		
   	double cosA = LookUp.cos[angle];
   	double sinA = LookUp.sin[angle];
				
		g.drawLine((int)x, (int)y, (int)(x + r * cosA), (int)(y + r * sinA));
	}
	
}