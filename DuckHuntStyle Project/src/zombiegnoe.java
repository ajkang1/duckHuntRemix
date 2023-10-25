import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.lang.*;

public class zombiegnoe{
	private Image img; 	
	private AffineTransform tx;
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	int currentX, currentY;
	double scaleWidth = 1.2;		 //change to scale image
	double scaleHeight = 1.2; //change to scale image
	boolean boon = false;
	boolean walkCheck = true;

	public zombiegnoe(String filename) {
		img = getImage("/imgs/"+filename); //load the image for Tree

		//alter these
		width = 800;
		height = 600;
		x = -2000;
		y = 275;
		vx = 0;
		vy = 0;
		
		
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage("/imgs/" + newFileName);
		init(0, 0);
		
	}
	
	public void paint(Graphics g) {

		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		x+=vx;
		y+=vy;
		
		
		init(x,y);
		g2.drawImage(img, tx, null);

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = zombiegnoe.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
