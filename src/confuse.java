
public class confuse extends Sprite {
	
	final static String[] action = {"dn", "up", "lt", "rt"};

	final static int DN = 0;
	final static int UP = 1;
	final static int LT = 2;
	final static int RT = 3;

	public confuse(int x, int y) {
		super(x, y, "confuse", action, 5, ".png", 10);
		
	}
	
	public void moveUp(int dy)
	{
		super.moveUp(dy);
		
		pose = UP;
	}
	


}
