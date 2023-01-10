package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;

class Triangle2DTest {
	static Point2D p1, p2, p3;
	static Triangle2D src;
	
	@BeforeEach
	void setUpTriangle() {
		p1 = new Point2D(3,2);
		p2 = new Point2D(9,2);
		p3 = new Point2D(6,5);
		src = new Triangle2D(p1, p2, p3);
	}

	@Test
	void testContains() {
		assertTrue(src.contains(new Point2D(6,3)));
		assertFalse(src.contains(new Point2D(4,4)));
	}

	@Test
	void testArea() {
		assertEquals(9, src.area(), Ex4_Const.EPS1);
	}

	@Test
	void testPerimeter() {
		assertEquals(14.48582, src.perimeter(), Ex4_Const.EPS1);
	}

	@Test
	void testMove() {
		src.move(new Point2D(2,3));
		Point2D p1Test = new Point2D(5,5);
		Point2D p2Test = new Point2D(11,5);
		Point2D p3Test = new Point2D(8,8);

		assertEquals(src.getPoints()[0], p1Test);
		assertEquals(src.getPoints()[1], p2Test);
		assertEquals(src.getPoints()[2], p3Test);
	}

	@Test
	void testCopy() {
		Triangle2D copy =(Triangle2D) src.copy();
		assertEquals(src.getPoints()[0], copy.getPoints()[0]);
		assertEquals(src.getPoints()[1], copy.getPoints()[1]);
		assertEquals(src.getPoints()[2], copy.getPoints()[2]);
		// check that changes affect only the source and not the copy
		copy.move(new Point2D(5,8));
		assertNotEquals(src.getPoints()[0], copy.getPoints()[0]);
		assertNotEquals(src.getPoints()[1], copy.getPoints()[1]);
		assertNotEquals(src.getPoints()[2], copy.getPoints()[2]);
	}

	@Test
	void testScale() {
		src.scale(new Point2D(0,0), 1.3);
		Point2D p1Test = new Point2D(3.9,2.6);
		Point2D p2Test = new Point2D(11.7,2.6);
		Point2D p3Test = new Point2D(7.8,6.5);
		assertTrue(p1Test.close2equals(src.getPoints()[0], Ex4_Const.EPS1));
		assertTrue(p2Test.close2equals(src.getPoints()[1], Ex4_Const.EPS1));
		assertTrue(p3Test.close2equals(src.getPoints()[2], Ex4_Const.EPS1));

	}

	@Test
	void testRotate() {
		src.rotate(new Point2D(p3), 45);
		Point2D p1Test = new Point2D(6.0,0.75735);
		Point2D p2Test = new Point2D(10.2426, 5);
		Point2D p3Test = new Point2D(6, 5);
		assertTrue(p1Test.close2equals(src.getPoints()[0], Ex4_Const.EPS1));
		assertTrue(p2Test.close2equals(src.getPoints()[1], Ex4_Const.EPS1));
		assertTrue(p3Test.close2equals(src.getPoints()[2], Ex4_Const.EPS1));

	}

}
