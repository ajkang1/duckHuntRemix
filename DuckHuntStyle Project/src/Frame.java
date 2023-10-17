import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//frame width/height
	int width = 900;
	int height = 600;
	int heart;
	int click = 0;
	
	
	
	//Add your object declaration and instantiations here
	Background b = new Background("Background.gif");
	Zombie z = new Zombie("Zombie2.gif");
	Lives l = new Lives("Lives.png");
	Lives l2 = new Lives("Lives.png");
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//Call the paint method of your objects here
		b.paint(g);
		z.paint(g);
		l.paint(g);

	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Zombie Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
		
		//cursor image must be outside of src folder
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("Scope.png").getImage(),
				new Point(0,0),"custom cursor"));
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		int xMouse = m.getX();
		int yMouse = m.getY();
		System.out.println(xMouse + ":" + yMouse);
		//Perform Rectangle collision for the two objects
		//
		Rectangle a = new Rectangle(xMouse, yMouse, 50,50); //Mouse representation
		
		//Represent the 2nd object as a Rectangle
		Rectangle b = new Rectangle(z.x, z.y, z.width, z.height); //x, y, width, height
		
		//Print the values of the Rectangle to confirm they're all sensical values!
		System.out.println("mouse: " + a);
		System.out.println("mouse: " + b);
		
		if(a.intersects(b)) {
			System.out.println("ouch");
			z.changePicture("Ghost.gif");
		}else {
			System.out.println("You missed");
			click++;
			if(click == 1) {
				l.changePicture("Lives2.png");
			}else if(click == 2) {
				l.changePicture("Lives3.png");
			}else if(click == 3) {
				l.changePicture("Lives4.png");
			}else if(click == 4) {
				l.changePicture("Lives6.png");
			}else if(click == 5) {
				l.changePicture("Lives7.png");
			}else if(click == 6) {
				l.changePicture("Lives8.png");
				
			}else {
				click = 0;
				l.changePicture("Lives.png");
			}
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
