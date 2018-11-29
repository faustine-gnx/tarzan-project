package display;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class DisplayOfGame {			
				// variables 
				private JFrame frame;
				//Canvas
				private Canvas canvas; 
				//title
				private String title; 
				// attention : width and height in pixels 
				private int width, height; 
				
				
				
				// constructor 
				public DisplayOfGame (String title, int width, int height){
					// set variables equal to the parameters. this since I name class variables equal to my parameters
					this.title = title; 
					this.width = width; 
					this.height = height; 
					// call method createDisplay 
					createDisplay (); 
				} 
				
				// set method 
				private void createDisplay () {
					frame = new JFrame (title); 
					frame.setSize(width, height);
				// Tells Java to completely close the program on the red x is pressed
					frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
				//Tells Java to disable the possibility for the user to Resize the window (False = boolean)
					frame.setResizable(true);
				//Tells Java to make the screen size the recommended size determined by the computer screen.
					frame.pack();
				 //Tells Java to either hide or show the window.
					frame.setVisible(true);
				//Tells Java I want my window in the center
					frame.setLocationRelativeTo(null);
					
					//initialize canvas with dimension, max and min. 
					canvas = new Canvas(); 
					canvas.setPreferredSize(new Dimension(width,height));
					canvas.setMaximumSize(new Dimension(width,height));	
					canvas.setMinimumSize(new Dimension(width,height));
					
					//add canvas to JFrame
					frame.add(canvas); 
					//see all the canvas
					frame.pack();
					
			}
				
			//getter to access canvas method from another class
			public  Canvas getCanvas () {
				return canvas; 
			}

	}

