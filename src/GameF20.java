import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;



public class GameF20 extends GameBase
{

	TreePlayer TreePlayer = new TreePlayer (0,400);
	int currentXPositionTree;
	int currentYPositionTree;
	int TreeHp;
	confuse HaloPlayer1;
	
	OwlPlayer OwlPlayer = new OwlPlayer (800,400);
	int currentXPositionOwl;
	int currentYPositionOwl;
	int OwlHp;
	confuse HaloPlayer2;
	
	Image Backgroud;
	Image SkyColor;
	Image foreground_mountains;
	Image midground_mountains;
	Image farground_mountains;
	Image Sun;
	ImageLayer mid_ground_cloud_2;
	ImageLayer mid_ground_cloud_1;
	ImageLayer farground_cloud_2;
	ImageLayer farground_cloud_1;
	Camera camera1 = new Camera(300,0);
	Camera camera2 = new Camera(100000,0);
	
	Rect Tile1;
	Rect Tile2;
	Rect Tile3;
	
	Line WorldLeftLimit;
	Line WorldRightLimit;
	Line WorldTopLimit;
	
	
	Image TreeHealthBar;
	Image OwlHealthBar;
	
	Image [] Effects = new Image [3];
	Random rd = new Random();
	
	Image Bang;
	Image Kapow;
	Image Pow;
	

	
	//Game Over Images.
	Image Owl;
	Image Tree;
	Image OwlDie;
	Image TreeDie;
	
	public void SetScreen() {
		
		this.setSize(new Dimension(1024,740));
		
	}
	
	public void initialize()
	{
		Backgroud = Toolkit.getDefaultToolkit().getImage("FieldTransparent.png");
		SkyColor = Toolkit.getDefaultToolkit().getImage("sky_color_top.png");
		foreground_mountains = Toolkit.getDefaultToolkit().getImage("foreground_mountains.png");
		midground_mountains =  Toolkit.getDefaultToolkit().getImage("midground_mountains.png");
		farground_mountains = Toolkit.getDefaultToolkit().getImage("farground_mountains.png");
		Sun = Toolkit.getDefaultToolkit().getImage("sun.png");
		
		TreeHealthBar = Toolkit.getDefaultToolkit().getImage("TreeHealthBar.png");
		OwlHealthBar = Toolkit.getDefaultToolkit().getImage("OwlHealthBar.png");
		
		Bang = Toolkit.getDefaultToolkit().getImage("Bang.png");
		Kapow = Toolkit.getDefaultToolkit().getImage("Kapow.png");
		Pow = Toolkit.getDefaultToolkit().getImage("Pow.png");
		Effects[0] = Bang;
		Effects[1] = Kapow;
		Effects[2] = Pow;
		
	
		mid_ground_cloud_2 = new ImageLayer("mid_ground_cloud_2.png", 0, 280, 3, 1024, 250);
		mid_ground_cloud_2.setCamera(camera2);
		
		mid_ground_cloud_1 = new ImageLayer("mid_ground_cloud_1.png", 0, 100, 5, 1024, 370);
		mid_ground_cloud_1.setCamera(camera1);
		
		farground_cloud_2 = new ImageLayer("farground_cloud_2.png", 0, 150, 4 , 1024, 100);
		farground_cloud_2.setCamera(camera2);
		
		farground_cloud_1 = new ImageLayer("farground_cloud_1.png", 0, 50, 4 , 1024, 50);
		farground_cloud_1.setCamera(camera1);
		
		Owl = Toolkit.getDefaultToolkit().getImage("O_sr_0.png");
		Tree = Toolkit.getDefaultToolkit().getImage("t_sr_0.png");
		TreeDie = Toolkit.getDefaultToolkit().getImage("Treedie.png");
		OwlDie = Toolkit.getDefaultToolkit().getImage("Owldie.png");
		
		SetScreen();
		
		currentXPositionTree = -1;
		currentYPositionTree = 400;
		
		currentXPositionOwl = -1;
		currentYPositionOwl = 400;
		
		TreePlayer.setAccelaration(0, 0.1);
		TreePlayer.setVelocity(10*LookUp.cos[90],10*LookUp.sin[90]);
		
		OwlPlayer.setAccelaration(0, 0.1);
		OwlPlayer.setVelocity(10*LookUp.cos[90],10*LookUp.sin[90]);
		
		Tile1 = new Rect (98, 230, 190, 30);
		Tile2 = new Rect (409, 106, 190, 30);
		Tile3 = new Rect (727, 213, 190, 30);

		WorldLeftLimit = new Line (0, 600, 0, 0);
		WorldRightLimit = new Line (1023, 0 , 1023 , 400 );
		WorldTopLimit = new Line (-150, -150 , 1023, -150);
		
		OwlHp = 100;
		TreeHp = 100;
		
		HaloPlayer1 = new confuse (-1000,-1000);
		HaloPlayer2 = new confuse (-1000,-1000);
		
		
		
	}
	
	public void UpdatePlayerPositionTree() 
	
	{
		if(TreePlayer.x > currentXPositionTree ) 	
		{
			currentXPositionTree = TreePlayer.x;
			TreePlayer.StandingRight();
			
			
			
		}
		
		if(TreePlayer.x < currentXPositionTree ) 	
		{
			currentXPositionTree = TreePlayer.x;
			TreePlayer.StandingLeft();
			
		}
		
		
	}
	
	
	public void UpdatePlayerPositionOwl() 
	
	{
		
		if(OwlPlayer.x < currentXPositionOwl ) 	
		{
			currentXPositionOwl = OwlPlayer.x;
			OwlPlayer.StandingLeft();
			
		}
		
		
		if(OwlPlayer.x > currentXPositionOwl ) 	
		{
			currentXPositionOwl = OwlPlayer.x;
			OwlPlayer.StandingRight();
				
		}
		
		
	}
	
	
	public void  inTheGameLoop()
	{
//		BackgroundMusic.playSound("Silver_master");
//------------------------------------------------------------------------------------------------------------//
										// PLAYER 1 MOVEMENTS AND UPDATES.
		
		UpdatePlayerPositionTree();
		if(pressed[LT]) TreePlayer.moveLeft(4); 
	    if(pressed[RT])	TreePlayer.moveRight(4);
	    if(pressed[SHIFT]) TreePlayer.Attack();
	    if(pressed[UP]) TreePlayer.Jump(10);
	    if(pressed[DN]) TreePlayer.Block();
	    
	    
	    if(TreeHp <= 60) {
	    	HaloPlayer1.moveUp(0);
	    	HaloPlayer1.setPosition(TreePlayer.x + 120 , TreePlayer.y + 30);
	    } 
	   
	    								/*// COLLITION WITH TILES IN THE WORLD	 *///
	    
	    Circle TreeFeet = TreePlayer.getGroundBounds(); 
	    if(TreePlayer.y <= 400 && pressed[UP] == false && !Tile1.contains((int)TreeFeet.x, (int)TreeFeet.y)
	    		&& !Tile2.contains((int)TreeFeet.x, (int)TreeFeet.y) && !Tile3.contains((int)TreeFeet.x, (int)TreeFeet.y) ) {
	    	
	    	TreePlayer.update();
	    	TreePlayer.Fall(0);
	    }
	    
	    							/*// BOUNDERIES OF THE PLAYER	 *///
	    Circle TreeHead = TreePlayer.getTopBounds(); 
	    	if(TreeHead.overlaps(WorldLeftLimit)) {
	    		TreePlayer.x +=10;
	    	
	    }
	    	if(TreeHead.overlaps(WorldRightLimit)) {
	    		TreePlayer.x -=10;
	    	
	    }
	       
	    if(pressed[UP] == true && TreeHead.overlaps(WorldTopLimit) ) {
	    	
	    	TreePlayer.y += 10;
	   
	    }
	    
	                               /*// COLLITION WITH ATTACK PLAYER2	 *///
	    		
	    Rect TreeBody = TreePlayer.getBounds();
	    Rect TreeRightArm = TreePlayer.getRightDemage();
	    Rect TreeLeftArm = TreePlayer.getLeftDemage();
	    Rect OwlBody = OwlPlayer.getBounds();
	   
	    if(TreeRightArm.overlaps(OwlBody) 
	    		&& pressed[SHIFT] == true && pressed[_S] == false) {
	    	OwlHp = OwlHp - 1;
	    	
	    }else {
	    	if(TreeLeftArm.overlaps(OwlBody) && pressed[SHIFT] == true && pressed[_S] == false) {
	    		OwlHp = OwlHp - 1;
	    	}
	    	
	    	Rect OwlTopHead = OwlPlayer.getTopHead();
	    	
	    	if (OwlTopHead.contains((int)TreeFeet.x, (int)TreeFeet.y)) {
	    		TreePlayer.update();
	    		TreePlayer.bounceH();
	    		OwlHp = OwlHp - 1;
	    	}
	    	
	    	
	    
	    }
	    
//---------------------------------------------------------------------------------------------------------------//
	    									// 	WORLD CAMERA MOVEMENTS.
	    camera1.moveRight(2);
	    camera2.moveLeft(1);
		
//---------------------------------------------------------------------------------------------------------------//
											//PLAYER 2 MOVEMENTS AND UPDATES.
	    
	    UpdatePlayerPositionOwl(); 
	    if(pressed[_A]) OwlPlayer.moveLeft(4); 
	    if(pressed[_D])	OwlPlayer.moveRight(4);
	    if(pressed[_1]) OwlPlayer.Attack();
	    if(pressed[_W]) OwlPlayer.Jump(10);
	    if(pressed[_S]) OwlPlayer.Block();
	    
	    if(OwlHp <= 60) {
	    	HaloPlayer2.moveUp(0);
	    	HaloPlayer2.setPosition(OwlPlayer.x + 120 , OwlPlayer.y + 30);
	    }
	    //*HELPS PLAYER 2 STAND LEFT AT THE BEGINNIG OF THE GAME*//
	    if(OwlPlayer.x == 800 & OwlPlayer.y == 400) {
	    		OwlPlayer.StandingLeft();
	    }
	    
	    
	    								/*// COLLITION WITH TALES IN THE WORLD	 *///
	    
	    Circle OwlFeet = OwlPlayer.getGroundBounds(); 
	    if(OwlPlayer.y <= 400 && pressed[_W] == false && !Tile1.contains((int)OwlFeet.x, (int)OwlFeet.y)
	    		&& !Tile2.contains((int)OwlFeet.x, (int)OwlFeet.y) && !Tile3.contains((int)OwlFeet.x, (int)OwlFeet.y) ) {
	    	
	    	OwlPlayer.update();
	    	OwlPlayer.Fall(0);
	    }
	    
	    							/*// BOUNDERIES OF THE PLAYER	 *///
	    
	    Circle OwlHead = OwlPlayer.getTopBounds(); 
    	if(OwlHead.overlaps(WorldLeftLimit)) {
    		OwlPlayer.x +=10;
    	
    }
    	if(OwlHead.overlaps(WorldRightLimit)) {
    		OwlPlayer.x -=10;
    	
    }
    	
    	if(pressed[_W] == true && OwlHead.overlaps(WorldTopLimit) ) {
	    	
	    	OwlPlayer.y += 10;
	   
	    }
    	
    	
    									/*// COLLITION WITH ATTACK PLAYER2	 *///
    	
    	Rect OwlRightArm = OwlPlayer.getRightDemage();
		Rect OwlLeftArm = OwlPlayer.getLeftDemage();
		
	    if(OwlRightArm.overlaps(TreeBody) && pressed[_1] == true && pressed[DN] == false ) {
	    	TreeHp = TreeHp - 1;
	    	
	    }else {
	    	if(OwlLeftArm.overlaps(TreeBody) && pressed[_1] == true && pressed[DN] == false ) {
	    		TreeHp = TreeHp - 1;
	    	}
	    	
	    }
	    
	    Rect TreeTopHead = TreePlayer.getTopHead();
    	
    	if (TreeTopHead.contains((int)OwlFeet.x, (int)OwlFeet.y)) {
    		OwlPlayer.update();
    		OwlPlayer.bounceH();
    		TreeHp = TreeHp - 1;
    	}
    
	}
	
	
	public void paint(Graphics g)
	{
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(SkyColor, 0, 0, 1024, 768, null);
		//farground_cloud_1.draw(g);
		g.drawImage(Sun , 0 , -150, null);
		farground_cloud_2.draw(g);
		mid_ground_cloud_1.draw(g);
		mid_ground_cloud_2.draw(g);
		g.drawImage(farground_mountains, 0, 450, 1024, 100, null);
		g.drawImage(midground_mountains, 0, 510, 1024, 50, null);
		g.drawImage(foreground_mountains, 0, 530, 1024, 40, null);
		g.drawImage(Backgroud,0,0,null);
		
		TreePlayer.draw(g);
		HaloPlayer1.draw(g);
		
		OwlPlayer.draw(g);
		HaloPlayer2.draw(g);
		
		g.drawImage(TreeHealthBar, 5, 19, null);
		g.drawImage(OwlHealthBar, 700, 15, null);
		
		//* OWL HEALTH BAR*//
		g.setColor(Color.red);
		g.fillRect(750, 30, 215, 10);
		g.setColor(Color.GREEN);
		g.fillRect(750, 30, OwlHp * 2 + 15, 10);
		g.setColor(Color.black);
		g.drawRect(750, 30, 215, 10);
		
		//* TREE HEALTH BAR*//
		g.setColor(Color.red);
		g.fillRect(48, 30, 215, 10);
		g.setColor(Color.GREEN);
		g.fillRect(48, 30, TreeHp * 2 + 15, 10);
		g.setColor(Color.black);
		g.drawRect(48, 30, 215, 10);
		
										//*ATTACK EFFTECTS*//
		
		
		//*For Tree Player*//
		Rect TreeRightArm = TreePlayer.getRightDemage();
		Rect TreeLeftArm = TreePlayer.getLeftDemage();
		Rect OwlBody = OwlPlayer.getBounds();
		
		if(TreeRightArm.overlaps(OwlBody) && pressed[SHIFT] == true && pressed[_S] == false)
		{
			g.drawImage(Effects[rd.nextInt(3)], OwlBody.x, OwlBody.y, null);
			
		}else {
			if(TreeLeftArm.overlaps(OwlBody)&& pressed[SHIFT] == true && pressed[_S] == false) {
				
				g.drawImage(Effects[rd.nextInt(3)], OwlBody.x, OwlBody.y, null);
			}
			
			
		 }
		
		
		Rect TreeTopHead = TreePlayer.getTopHead();
		Circle OwlFeet = OwlPlayer.getGroundBounds();
    	if (TreeTopHead.contains((int)OwlFeet.x, (int)OwlFeet.y)) {
    		g.drawImage(Effects[rd.nextInt(3)], TreeTopHead.x + 10, TreeTopHead.y - 20, null);
    	}
		
		
		
		//*For OWl Player*//
		Rect OwlRightArm = OwlPlayer.getRightDemage();
		Rect OwlLeftArm = OwlPlayer.getLeftDemage();
		Rect TreeBody = TreePlayer.getBounds();
		
		if(OwlRightArm.overlaps(TreeBody) && pressed[_1] == true && pressed[DN] == false)
		{
			g.drawImage(Effects[rd.nextInt(3)], TreeBody.x, TreeBody.y, null);
			
		}else {
			
			if(OwlLeftArm.overlaps(TreeBody) && pressed[_1] == true && pressed[DN] == false) {
				
				g.drawImage(Effects[rd.nextInt(3)], TreeBody.x, TreeBody.y, null);
			}
			
		 }
		
		Rect OwlTopHead = OwlPlayer.getTopHead();
		Circle TreeFeet = TreePlayer.getGroundBounds();
    	if (OwlTopHead.contains((int)TreeFeet.x, (int)TreeFeet.y)) {
    		g.drawImage(Effects[rd.nextInt(3)], OwlTopHead.x + 10, OwlTopHead.y - 20, null);
    	}
		
		
		
		
		//*GAME OVER*//
		if(TreeHp <= -10 ||OwlHp <= -10 ) 
		{
			TreePlayer.setPosition(-1000, -1000);
			OwlPlayer.setPosition(-1000, -1000);
			
			g2d.setFont(new Font("Serif", Font.BOLD, 70));
	    	g2d.drawString("Game Over", 350, 250);
	    	 
	    }
		
		if(TreeHp <= -10) {
			g.drawImage(Owl , 300, 400, null);
			g.drawImage(TreeDie, 420, 400, null);
		}else {
			if(OwlHp < -10) {
				g.drawImage(Tree , 300, 400, null);
				g.drawImage(OwlDie, 420, 400, null);
			}
		}
		
		
		
		//-------------------------------------------------------------//
		// Drawing For Collation and World Interations.
		
		
////		Circle TreeFeet = TreePlayer.getGroundBounds();
//		TreeFeet.draw(g);
//		
//		Circle TreeHead = TreePlayer.getTopBounds();
//		TreeHead.draw(g);
//		
////		Rect TreeBody = TreePlayer.getBounds();
//		TreeBody.draw(g);
//		
////		Circle OwlFeet = OwlPlayer.getGroundBounds();
//		OwlFeet.draw(g);
//		
//		Circle OwlHead = OwlPlayer.getTopBounds();
//		OwlHead.draw(g);
//		
////		Rect OwlBody = OwlPlayer.getBounds();
//		OwlBody.draw(g);
		
//		Tile1.draw(g);
//		Tile2.draw(g);
//		Tile3.draw(g);
//		
//		WorldLeftLimit.draw(g);
//		WorldRightLimit.draw(g);
//		WorldTopLimit.draw(g);
		
		
////		Rect TreeRightArm = TreePlayer.getRightDemage();
//		TreeRightArm.draw(g);
//		
////		Rect TreeLeftArm = TreePlayer.getLeftDemage();
//		TreeLeftArm.draw(g);
//		
////		Rect OwlRightArm = OwlPlayer.getRightDemage();
//		OwlRightArm.draw(g);
//		
////		Rect OwlLeftArm = OwlPlayer.getLeftDemage();
//		OwlLeftArm.draw(g);
//		
////		Rect OwlTopHead = OwlPlayer.getTopHead();
//		OwlTopHead.draw(g);
//		
////		Rect TreeTopHead = TreePlayer.getTopHead();
//		TreeTopHead.draw(g);
		
		
		
		

		
	}
		
	

	}
	

