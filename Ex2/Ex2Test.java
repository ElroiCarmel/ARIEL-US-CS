package Ex2;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * This JUnit class represents a very simple unit testing for Ex2.
 * This class should be improved and generalized significantly.
 * Make sure you add documentations to this Tesing class.
 * @author boaz.ben-moshe
 *
 */

class Ex2Test {
	static double[] po1={2,0,3, -1,0}, po2 = {0.1,0,1, 0.1,3};

	@Test
	void testEquals() {
		double[] p1 = {-1, 4, 3,1};
		double[] p2 = {-1, 2, 3,1};
		double[] p3 = {-1, 2.0005, 3,1};
		assertEquals(false, Ex2.equals(p1, p2));
		// Checks the "EPS" margin error.
		assertEquals(true, Ex2.equals(p2, p3));
	}
	
	@Test
	void testF() {
		double fx0 = Ex2.f(po1, 0);
		double fx1 = Ex2.f(po1, 1);
		double fx2 = Ex2.f(po1, 2);
		assertEquals(fx0,2);
		assertEquals(fx1,4);
		assertEquals(fx2,6);
	}
	
	@Test
	void testPoly() {
		double[] p1 = {3, 7, 0, -2};
		String text = "-2.0x^3 +7.0x^1 +3.0";
		assertEquals(text, Ex2.poly(p1));
	}
	
	@Test
	void testRoot() {
		double x12 = Ex2.root(po1, 0, 10, Ex2.EPS);
		assertEquals(x12, 3.1958, Ex2.EPS);
	}
	
	@Test
	void testRootRec() {
		double x12 = Ex2.root_rec(po1, 0, 10, Ex2.EPS);
		assertEquals(x12, 3.1958, Ex2.EPS);
	}
	
	@Test
	void testSameValue() {
		double[] poly1 = {6, 5, 2, 1};
		double[] poly2 = {1, 4, 3};
		double x12 = Ex2.sameValue(poly1, poly2, -5, 0, Ex2.EPS);
		//The answear "-1.27816" for these two polynoms
		//was taken from the web "www.symolab.com"
		assertEquals(x12, -1.27816, Ex2.EPS);
	}
	
	@Test
	void testArea() {
		double[] poly1 = {0, 3, 5, 2};
		double[] poly2 = {3, 0, 7, -2};
		double area = Ex2.area(poly1, poly2, 2, 6, 10000000);
		// The answear "1177.333" for the area between these
		// two polynoms was taken from the web "www.symolab.com"
		assertEquals(area, 1177.333, Ex2.EPS);
	}
	
	@Test
	void testAdd() {
		double[] p12 = Ex2.add(po1, po2);
		double[] minus1 = {-1};
		double[] pp2 = Ex2.mul(po2, minus1);
		double[] p1 = Ex2.add(p12, pp2);
		assertEquals(Ex2.poly(po1), Ex2.poly(p1));
	}
	@Test
	void testMul() {
		double[] p1 = {-7, 8, 2};
		double[] p2 = {4, 0, -5, 3};
		double[] ans1 = {-28, 32, 43, -61, 14, 6};
		double[] ans2 = Ex2.mul(p1, p2);
		assertArrayEquals(ans1, ans2);
	}
	
	@Test
	void testMulDoubleArrayDoubleArray() {
		double[] p12 = Ex2.add(po1, po2);
		double dd = Ex2.f(p12, 5);
		assertEquals(dd, 1864.6, Ex2.EPS);
	}
	@Test
	void testDerivativeArrayDoubleArray() {
		double[] p = {1,2,3}; // 3X^2+2x+1
		double[] dp1 = {2,6}; // 6x+2
		double[] dp2 = Ex2.derivative(p);
		assertEquals(dp1[0], dp2[0],Ex2.EPS);
		assertEquals(dp1[1], dp2[1],Ex2.EPS);
		assertEquals(dp1.length, dp2.length);
	}
	@Test
	public void testFromString() {
		double[] p = {-1.1,2.3,3.1}; // 3.1x^2+2.3x-1.1
		String sp = Ex2.poly(p);
		double[] p1 = Ex2.getPolynomFromString(sp);
		boolean isSame = Ex2.equals(p1, p);
		if(!isSame) {fail();}
		assertEquals(sp, Ex2.poly(p1));
	}
	
	@Test
	void testPolynomFromPoints() {
		double[] xx = {2, 5, 7};
		double[] yy = {24, 81, 139};
		double[] p = {6.0, 5.0, 2.0};
		assertArrayEquals(p, Ex2.PolynomFromPoints(xx, yy));
		
	}
}
