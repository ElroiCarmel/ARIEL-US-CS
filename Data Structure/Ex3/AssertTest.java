package RBT;

public class AssertTest {

	/** Enable Assertions ECLIPSE
	 * Run->Run Configuration->Arguments->VM arguments: -ea
	 *
	 * Enable Assertions IntelliJ
	 * Choose Run → Edit Configurations... .
	 * Select the run configuration of interest.
	 * Click on Modify options link and choose Add VM options.
	 * Add -ea to the VM options box.
	 */
	public static void main(String[] args) {
		Integer h=null;
		assert h != null : "h = null !!!";
		System.out.println("h="+h);
	}

}
