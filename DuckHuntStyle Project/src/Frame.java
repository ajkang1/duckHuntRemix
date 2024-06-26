import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.lang.Thread;
import java.util.Random;
public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	///
	long timer = 0;
	long time = 20;

	int level = 1;
	
	//song timer
	long timer2 = 0;
	long time2 = 77;
	
	//frame width/height
	int width = 900;
	int height = 600;
	int heart;
	int click = 0;
	int score = 0;
	boolean dead = false;
	boolean deathcheck = true;
	boolean youdied = false;
	boolean tpos = false;
	int wallhits = 0;
	int screenCheck = 0; //Counts how many times zombie gets out of screen
	//Add your object declaration and instantiations here
	Background b = new Background("Background.gif");
	Zombie z = new Zombie("Zombie2.gif");
	Lives l = new Lives("Lives.png");
	Lives l2 = new Lives("Lives.png");
	Music gunShot = new Music("Shoot8bit.wav", false);
	Music oof = new Music("oof.wav", false); //Death sound
	Music mainSong = new Music("DeadHunt.wav", true); //Theme song
	Music newWave = new Music("newWave.wav", false); //New wave
	Music youDied = new Music("youDied.wav", false); //You died
	Music taunt = new Music("taunt.wav", false); //Taunt sound
	Tombstone t = new Tombstone("tombstone.png");
	zombiegnoe zg = new zombiegnoe("pixil-frame-0 (4).png");
	Ghost gh = new Ghost("Ghost.gif");
	Font font = new Font("Verdana", Font.BOLD, 28);
	Restart r = new Restart("Restart.gif");
	newWave wave = new newWave("newWave.png");

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//Call the paint method of your objects here
		b.paint(g);
		z.paint(g);
		l.paint(g);
		gh.paint(g);
		t.paint(g);
		zg.paint(g);
		r.paint(g);
		wave.paint(g);
		
		////////////////////////////////////////////////////////
		if (t.y <= 240) {
			gh.x = z.x;
			gh.y = z.y + 300;
			gh.vy = -15;
			if (z.boon == true) {
			gh.vx = -5;
			z.wallhits = 0;
			gh.changePicture("output-onlinegiftools (2).gif");
			}else {
				gh.changePicture("Ghost.gif");
				gh.vx = 5;

			}
			z.x = -200;
			Random random = new Random();
			z.vx = random.nextInt(level*(10 + (score*5))) + 5;
			t.vy = 8;
			deathcheck = true;
			z.boon = false;
			tpos = true;
			z.changePicture("Zombie2.gif");
		}
		if (t.y >= 600 && tpos == true) {
			t.vy = 0;
	/////////////////////////////////////////////////////////////
			
		}
		timer += 20;
		if(timer%1000 == 0) {
			time--;
			}
		if (time == 0) {
			newWave.play();
			level++;
			time = 0;
			wave.vy = -15;
			restart();
			
		}
		
		//song timer loop
		timer2 += 20;
		if(timer2%1000 == 0) {
			time2--;
			}
		if (time2 == 0) {
			mainSong.play();
			time2 = 77;
			
		}
		//
		
		g.setColor(Color.WHITE);
		g.drawString("Time left till next wave: "+time, 200,75);
		if (youdied) {
			//g.drawString("YOU FAILED! LOSER! LOSER!", 400,300);
			//try again button
			r.y = 100;
			//zg.x = -80;
			//zg.y = -80;
			//System.out.println("hi");
			
			
		}
		g.drawString("Score: "+score, 200, 35);
		g.drawString("Wave: "+level, 200, 55);
		if (level >0) {
			
			//paint new values
		}
		
		////////////
		if (z.x < -300) { //If zombie walks off screen, player loses one of their lives
			//youdied = true;
			z.wallhits = 0;
			z.x = -100;
			z.boon = false;
			z.vx = 3;
			z.changePicture("Zombie2.gif");
			screenCheck++;
			if(screenCheck == 1) {
				oof.play();
				click = 1;
				l.changePicture("Lives2.png");
			}else if(screenCheck == 2) {
				oof.play();
				click = 2;
				l.changePicture("Lives3.png");
			}else if(screenCheck == 3) {
				oof.play();
				click = 3;
				l.changePicture("Lives4.png");
			}else if(screenCheck == 4) {
				oof.play();
				click = 4;
				l.changePicture("Lives6.png");
			}else if(screenCheck == 5) {
				oof.play();
				click = 5;
				l.changePicture("Lives7.png");
			}else if(screenCheck == 6) {
				oof.play();
				youDied.play();
				taunt.play();
				click = 6;
				l.changePicture("Lives8.png");
				r.y = 150;
				r.changePicture("Restart.gif");
				/////// Zombie laughs when player loses
				z.x = 250;
				z.vx = 0;
				z.changePicture("ZombieLaugh.gif");
				deathcheck = false;
				///////
				youdied = true;
				timer = 1; //time stop
			}
		}
		//////////////////////////////////////////////////////////
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
		mainSong.play();
	}
	
	@Override
	public void mouseClicked(MouseEvent m){
		// TODO Auto-generated method stub
		
		int xMouse = m.getX();
		int yMouse = m.getY();
		System.out.println(xMouse + ":" + yMouse);
		//Perform Rectangle collision for the two objects
		//
		Rectangle a = new Rectangle(xMouse, yMouse, 50,50); //Mouse representation
		
		//Represent the 2nd object as a Rectangle
		Rectangle b = new Rectangle(z.x, z.y, z.width, z.height); //x, y, width, height
		
		//Represent restart button
		Rectangle restart = new Rectangle(r.x, r.y, r.width, r.height);
		
		//Print the values of the Rectangle to confirm they're all sensical values!
		System.out.println("mouse: " + a);
		System.out.println("mouse: " + b);
		gunShot.play();
		if(a.intersects(b) && deathcheck == true) {
			System.out.println("ouch");
			z.vx = 0;
			tpos = false;
			score += 1;
			
			t.x = z.x - 230;
			t.y = z.y + 300;
			t.vy = -8;
			deathcheck = false;
			
		//Checks if mouse intersects restart button to restart game
			
			
		}else { //Checks if mouse is clicked outside of zombie
			System.out.println("You missed");
			click++;
			if(click == 1) {
				oof.play();
				screenCheck = 1;
				l.changePicture("Lives2.png");
			}else if(click == 2) {
				oof.play();
				screenCheck = 2;
				l.changePicture("Lives3.png");
			}else if(click == 3) {
				oof.play();
				screenCheck = 3;
				l.changePicture("Lives4.png");
			}else if(click == 4) {
				oof.play();
				screenCheck = 4;
				l.changePicture("Lives6.png");
			}else if(click == 5) {
				oof.play();
				screenCheck = 5;
				l.changePicture("Lives7.png");
			}else if(click == 6) {
				oof.play();
				taunt.play();
				youDied.play();
				screenCheck = 6;
				l.changePicture("Lives8.png");
				r.changePicture("Restart.gif");
				/////// Zombie laughs when player loses
				z.x = 250;
				z.vx = 0;
				z.changePicture("ZombieLaugh.gif");
				deathcheck = false;
				timer = 1; //time stops
				///////
				youdied = true;
			}else {
				if(a.intersects(restart)) { //Checks if mouse clicks restart button
					System.out.println("restart button pressed");
					if(r.y < 700) { //Takes restart button off of screen
						r.changePicture("Restart2.png");
						r.vy = 10;
					}
					restart();
					score = 0;
					z.boon = false;
					youdied = false;
					l.changePicture("Lives.png");
					level = 1; //starts player off from the beginning
					
					///////////////
					//if(z.boon == true) {
						//z.vx = -3;
					//}else { 					<---- DELETE SECTION IF NOT NEEDED
						//z.vx = 3;
					//}
					//////////////
				}
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
	public void restart() {
		timer = 0;
		time = 20;

		
		//frame width/height
		

		click = 0;
		screenCheck = 0;
		l.changePicture("Lives.png");
		//score = 0;
		dead = false;
		gh.x = z.x;
		gh.y = z.y + 300;
		gh.vy = -15;
		if (z.boon == true) {
		gh.vx = -6;
		z.wallhits = 0;
		gh.changePicture("output-onlinegiftools (2).gif");
		}else {
			gh.changePicture("Ghost.gif");
			gh.vx = 6;

		}
		z.x = -200;
		Random random = new Random();
		z.vx = random.nextInt(10 + (score*10)) + 5;
		deathcheck = true;
		z.boon = false;
		tpos = true;
		z.changePicture("Zombie2.gif");
		
	}

}
