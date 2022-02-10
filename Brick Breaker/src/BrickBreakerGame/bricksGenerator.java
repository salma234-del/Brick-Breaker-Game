package BrickBreakerGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class bricksGenerator {
	public int bricks[][];
	public int brickHeight;
	public int brickWidth;
	
	// constructor
	public bricksGenerator(int r , int c) {
		bricks = new int[r][c];
		for(int i = 0; i < bricks.length; i++) {
			for(int j = 0; j < bricks[i].length; j++) {
				bricks[i][j] = 1;
			}
		}
		
		brickHeight = 120 / r;
		brickWidth = 500 / c;
	}
	
	public void draw(Graphics2D g) {
		for(int i = 0; i < bricks.length; i++) {
			for(int j = 0; j < bricks[i].length; j++) {
				if(bricks[i][j] == 1) {
					if(i == 0) g.setColor(Color.white);
					else if(i == 1) g.setColor(Color.magenta);
					else if(i == 2) g.setColor(Color.orange);
					else g.setColor(Color.blue);
					
					g.fillRect(j * brickWidth + 40, i * brickHeight + 40 , brickWidth, brickHeight);
					g.setStroke(new BasicStroke(4));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 40, i * brickHeight + 40 , brickWidth, brickHeight);		
				}
			}
		}	
	}
	
	public void brickValue(int val , int r , int c) {
		bricks[r][c] = val;
	}
}
