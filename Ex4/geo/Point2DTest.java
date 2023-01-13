package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Exe.Ex4.*;

class Point2DTest {
	static Point2D src;
	
	@BeforeEach
	void clearPoint() {
	// Since the point's values change in the tests
	// we want to "reset" it's values.
	src = new Point2D(2,9);
	}
	
	@Test
	void testMove() {
		src.move(new Point2D(4,7));
		assertEquals(6, src.x(), Ex4_Const.EPS1);
		assertEquals(16, src.y(), Ex4_Const.EPS1);
	}

	@Test
	void testScale() {
		src.scale(new Point2D(8,12), 1.1);
		assertEquals(1.4, src.x(), Ex4_Const.EPS1);
		assertEquals(8.7, src.y(), Ex4_Const.EPS1);
		
	}

	@Test
	void testGetAngleFromPoints() {
		double angle = src.getAngleFromPoints(new Point2D(7.2, 14.7));
		assertEquals(0.8312, angle, Ex4_Const.EPS1);
	}

	@Test
	void testRotate() {
		// ROtate the point related to center(5.4, 13.8) by 33 degrees
		src.rotate(new Point2D(5.4,13.8), 33);
		assertEquals(5.1622, src.x(), Ex4_Const.EPS1);
	}

}
