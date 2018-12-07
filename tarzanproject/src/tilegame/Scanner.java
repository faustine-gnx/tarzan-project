package tilegame;
import java.io.InputStream;

public class Scanner {
	
		public static int endurance;
		public static int energy;
		public static int levelNumber;
		
		public static final void main(String[] args) {
			
			int energy;
			int endurance;
			int levelNumber; 

	        //Scanner reads the input provided by user
	        //using keyboard
	        //object Scanner created and System in 
	        Scanner scan = new Scanner(System.in);
	        
	        energy = scan.nextInt();
	        System.out.print("Enter your starting Energy:" + energy);

	        endurance = scan.nextInt();
	        System.out.print("Enter your starting Endurance:" + endurance);
	        
	        levelNumber = scan.nextInt();
	        System.out.print("Enter your starting levelNumber:" + levelNumber);
	        
	        // This method reads the number provided using keyboard
	        int num = scan.nextInt();

	        // Closing Scanner after the use
	        scan.close();
	        
	        // Displaying the number 
	        System.out.println("The number entered by user: "+num);
	    }

		private void close() {
			
		}

		private int nextInt() {
			return 0;
		}
		 public Scanner(InputStream in) {
			}
	}