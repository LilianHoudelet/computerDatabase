package src.main.java.com.excilys.formation.service;
/**
 * Create an singleton instance
 * 
 * @author Lilian Houdelet
 *
 */
public class Singleton {
	private static final Singleton instance = new Singleton();
	
	private Singleton() {
		System.out.println("Construction singleton");
	}
	
	public static final Singleton getInstance() {
		return instance;
	}
}
