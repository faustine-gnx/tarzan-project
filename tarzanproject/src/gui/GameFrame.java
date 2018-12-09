package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.EventQueue;


// start our game
public class GameFrame extends JFrame {
	
	private JFrame frame;
	private String title;
	private int width,height;
	
	public void Display (String title, int width, int height) {
		this.title = title; 
		this.width = width; 
		this.height = height;
	}
	
	private void createDisplay() {
		frame = new JFrame(title); 
		frame.setSize(width,height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

	 private void setBackground() {

	       JLabel background = new JLabel("textures/tarzandanslajungle");
	       setContentPane(background);

	       pack();
	       repaint();

	       setResizable(false);
	       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setLocationRelativeTo(null);
	   }

	public static void main(String[] args) {

	    // Creates a new thread so the GUI can process itself
	    EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            JFrame frame = new JFrame();
	            frame.setVisible(true);
	        }
	    });
	}
	}
