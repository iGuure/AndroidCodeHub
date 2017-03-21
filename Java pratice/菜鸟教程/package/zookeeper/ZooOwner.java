package zookeeper;

/*
 * 2nd way to do so
 *
 * you can also use 'import animals.MammalInt'
 * for the same purpose
 *
 */
import animals.*;

public class ZooOwner {
	public static void main(String[] args) {
		
		/*
		 * 1st way to use a class in the different package
		 *
		 * have to use a full name
		 *	
		 */
		animals.MammalInt mi1 = new animals.MammalInt();

		MammalInt mi2 = new MammalInt();

		mi1.eat();
		mi1.travel();

		mi2.eat();
		mi2.travel();
	}
}