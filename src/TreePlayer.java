import java.awt.Rectangle;

public class TreePlayer extends Sprite {

	final static String[] action = {"dn", "up", "lt", "rt",
			"sr","sl","upL","dnL","pl","pr","bl","br"};

	final static int FallRight = 0;
	final static int JumpRight = 1;
	final static int LT = 2;
	final static int RT = 3;
	final static int STANDINGRIGHT = 4;
	final static int STANDINGLEFT = 5;
	final static int JumpLeft = 6;
	final static int FallLeft = 7;
	final static int AttackLeft = 8;
	final static int AttackRight = 9;
	final static int BlockAttackLeft = 10;
	final static int BlockAttackRight = 11;
	
	
	int CurrentPositionY = 400;
	int CurrentPositionX = -1;
	
	int imageWidth = 170;
	int imageHeight = 300;
	
	public TreePlayer(int x, int y) {
		
		super(x, y, "t", action, 11, ".png", 3);
		
	}
	
	
	public void moveLeft(int dx)
	{
		super.moveLeft(dx);
		
		pose = LT;
		
	}

	public void moveRight(int dx)
	{
		super.moveRight(dx);
		pose = RT;
		
	}
	
	public void StandingRight() {
		
		super.DefaultImageMove();
		
		pose = STANDINGRIGHT;
		
	}
	
	public void StandingLeft() 
	{
		super.DefaultImageMove();
		
		pose = STANDINGLEFT;
			
		
	}
	
	public void Jump(int dy) {
		super.moveUp(dy);
		
		if(y < CurrentPositionY && x > CurrentPositionX ) 	
		{
			CurrentPositionX = x;
			 
			pose = JumpRight;
		}else {
			if(y < CurrentPositionY && x < CurrentPositionX ) 	
			{
				CurrentPositionX = x;
				 
				pose = JumpLeft;
			}
		}
			
	}
	
	public void Fall(int dy) {
		
		super.moveDown(dy);
		
		if(y < CurrentPositionY && x > CurrentPositionX ) 	
		{
			CurrentPositionX = x;
			x ++;
			pose = FallRight;
		}else {
			if(y < CurrentPositionY && x < CurrentPositionX ) 	
			{
				CurrentPositionX = x;
				x --; 
				pose = FallLeft;
			}
		}
	}
		
	
	
	public void Attack() {
		
		super.DefaultImageMove();
		
		if(x > CurrentPositionX ) 	
		{
			CurrentPositionX = x;
			pose = AttackRight;		
			
		}else {
		
		if(x < CurrentPositionX ) 	
		{
			CurrentPositionX = x;
			pose = AttackLeft;
		}
		
		}
		
	}
	
	public void Block() {
		
		super.DefaultImageMove();
		
		if(x > CurrentPositionX ) 	
		{
			CurrentPositionX = x;
			pose = BlockAttackRight;		
			
		}else {
		
		if(x < CurrentPositionX ) 	
		{
			CurrentPositionX = x;
			pose = BlockAttackLeft;
		}
		
		}
		
	}
	
	
	public void setVelocity(double vx, double vy)
	{
		super.setVelocity(vx, vy);
	}
	
	public void setAccelaration(double ax, double ay)
	{
		super.setAccelaration(ax, ay);
	}
	
	
	public void move()
	{
		super.move();
		
//		pose = UP;
	}
	
	public void accelarate()
	{
		super.accelarate();
		
	}
	
	public void update()
	{
		move();
		
		accelarate();
	}
	
	
	public void bounceH()
	{
		super.bounceH();
		
		if(y < CurrentPositionY && x > CurrentPositionX ) 	
		{
			CurrentPositionX = x;
			
			pose = JumpRight;
		}else {
			if(y < CurrentPositionY && x < CurrentPositionX ) 	
			{
				CurrentPositionX = x;
				
				pose = JumpLeft;
			}
		}
		
	}
	
	
	
	public Rect getBounds() 
	  {

	      return new Rect((int) x + imageWidth / 2,
	              (int) y + imageHeight  / 4, imageWidth,
	              imageHeight  / 2);
	  }
	
	public Circle getGroundBounds() {
		
		return new Circle (x + 150 , y + 160, 10, 0);
	}
	
	public Circle getTopBounds() {
		
		return new Circle (x + 160 , y + 100, 30, 0);
	}

	public Rect getRightDemage() {
		
		return new Rect (x + 220, y + 120 , 10, 10);
		
	}
	
	public Rect getLeftDemage() {
		
		return new Rect (x + 80, y + 120 , 10, 10);
	}
	
	public Rect getTopHead() {
		
		return new Rect (x + 100 , y + 70, 150, 10);
	}
	
}
