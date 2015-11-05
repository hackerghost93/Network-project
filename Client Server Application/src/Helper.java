import javax.swing.JTextArea;


public class Helper {
	
	public static synchronized void randomWait() {
		try {
			Thread.sleep((long) (3000 * Math.random()));
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void write(JTextArea place, String message) {
		place.append(message);
	}
}
