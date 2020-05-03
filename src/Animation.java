import java.awt.*;

public class Animation
{
	private Image[] image;
	
	private int current = 0;
	
	int delay;
	int duration; 
	
	public Animation(String name, int count, String ext, int duration)
	{
		image = new Image[count];
		
		for(int i = 0; i < count; i++)
			
			image[i] = loadImage(name + i + ext);
	
		this.duration = duration;
		delay = duration;
	}
	
	
	public Image getImage()
	{
		if(delay == 0)
		{	
		   current++;
		
	   	if(current == image.length)  current = 1;
	   	
	   	delay = duration;
		}
		
		delay--;
		
	   return image[current];
	}
	
	public Image getStillImage()
	{
		return image[0];
	}
	
	
	public Image loadImage(String filename)
	{
		return Toolkit.getDefaultToolkit().getImage(filename);
	}

}