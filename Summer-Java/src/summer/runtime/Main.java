package summer.runtime;

/**
 * @author zhenzxie
 */
public class Main {

	public static void main(String[] args) {
		IController controller = IController.getInstance();
		controller.start();
	}
}
