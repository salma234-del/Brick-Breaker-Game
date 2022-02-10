package BrickBreakerGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Game extends JPanel implements ActionListener, KeyListener{
	private int score = 0;
	private int numOfBricks = 15;
	private boolean play = false;
	private int playerX = 300;
	
	// ball
	private int ballPosX = 300;
	private int ballPosY = 250;
	private int ballXdir = -1;
	private int ballYdir = -4;
	
	//time
	private Timer timer;
	
	private bricksGenerator bricks;
	
	// constructor
	public Game() {
		bricks = new bricksGenerator(3 , 5);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer = new Timer(1 , this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(1, 1, 599, 499);
		
		g.setColor(Color.white);
		g.fillRect(0 , 0 , 2 , 498);
		g.fillRect(0 , 0 , 599 , 2);
		g.fillRect(583 ,0 , 2 , 490);
		;
		
		// bricks
		bricks.draw((Graphics2D) g);
		
		//score
		g.setColor(Color.orange);
		g.setFont(new Font("serif" , Font.BOLD , 35));
		g.drawString("" + score , 300 , 30);
		
		if(numOfBricks == 0) {
			play = false;
			g.setColor(Color.red);
			g.setFont(new Font("serif" , Font.BOLD , 35));
			g.drawString("You Won Your Score Is " + score , 50 , 250);	
			g.setFont(new Font("serif" , Font.BOLD , 25));
			g.drawString("Press Enter To Play Again", 150, 300);
		}
		
		if(ballPosY > 450) {
			play = false;
			g.setColor(Color.red);
			g.setFont(new Font("serif" , Font.BOLD , 35));
			g.drawString("Game Over Your Score is: " + score , 50 , 250);	
			g.setFont(new Font("serif" , Font.BOLD , 25));
			g.drawString("Press Enter To Play Again", 150, 300);
		}
		
		//piddle
		g.setColor(Color.cyan);
		g.fillRect(playerX, 420 , 110 , 7);
		
		//ball
		g.setColor(Color.red);
		g.fillOval(ballPosX, ballPosY , 20 , 20);
		g.dispose();
	}
	
	public void moveRight() {
		play = true;
		playerX += 80;
	}
	public void moveLeft() {
		play = true;
		playerX -= 80;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 473) playerX = 473;
			else moveRight();
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX <= 0) playerX = 0;
			else moveLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!play) {
			    numOfBricks = 15;
				play = true;
			    playerX = 300;
				ballPosX = 300;
				ballPosY = 250;
			    ballXdir = -1;
				ballYdir = -4;
				score = 0;
				bricks = new bricksGenerator(3 , 5);
				repaint();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ballPosX , ballPosY , 20 , 20).intersects(new Rectangle(playerX, 420 , 110 , 7)))
				ballYdir *= -1;
			
			 for(int i = 0; i < bricks.bricks.length; i++) {
				for(int j = 0; j < bricks.bricks[i].length; j++) {
					if(bricks.bricks[i][j] == 1) {
						int brickY = i * bricks.brickHeight + 40;
						int brickX = j * bricks.brickWidth + 40;
						Rectangle rect = new Rectangle(brickX , brickY , bricks.brickWidth , bricks.brickHeight);
						Rectangle ballRect = new Rectangle(ballPosX , ballPosY , 20 , 20);
						Rectangle brickRect = rect;
						if(ballRect.intersects(brickRect)) {
							bricks.brickValue(0 , i , j);
							numOfBricks--;
							score += 1;	
							if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width){
								ballXdir *= -1;
							}else ballYdir *= -1;	
						}
					}
				}
			}
			
			ballPosX += ballXdir;
			ballPosY += ballYdir;
			if(ballPosX < 0) ballXdir *= -1; //left
			if(ballPosX > 565) ballXdir *= -1; // right
			if(ballPosY < 0) ballYdir *= -1; // top
				
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
