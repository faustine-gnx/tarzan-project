package animals;
public abstract class Animal {
	int[][] position; 
	int  strength ;
	String name ; 
	
	 Animal (int position, int strength, String name) {
		
		 String nameAnimal = name;  
		 int positionAnimal = position; 
		 int strengthAnimal = strength; 
			}
	 
	 //overriding finalize method 
	 @Override
	 protected void finalize () throws Throwable { 
		 System.out.println("Animal eliminated!");
} 
}