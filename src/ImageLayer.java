import java.awt.*;

public class ImageLayer
{
	Image image;
	
	int x;
	int y;
	int z;
	int width;
	int height;
	
	Camera camera;
	
	public ImageLayer(String filename, int x, int y, int z, int width, int height)
	{
		image = Toolkit.getDefaultToolkit().getImage(filename);
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
	}
	
	public void setCamera(Camera camera)
	{
		this.camera = camera;
	}
	
	public void draw(Graphics g)
	{
		for(int i = 0; i < 1000; i++)
		
			g.drawImage(image, (x + 720*i) - camera.x/z, y - camera.y/z, width, height, null);
	}

}