package BrickBreakerGame;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Game game = new Game();
		frame.setTitle("Brick Breaker");
		frame.setBounds(20 , 20 , 600 , 500);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
	}
}
