package puzzle;

import java.applet.Applet;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Applet applet = new puzzle.Puzzle();
		applet.init();
		applet.start();
		JFrame window = new JFrame ("Match Demo");
		window.setContentPane(applet);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setSize(380, 685);
		window.setVisible(true);
	}

}
