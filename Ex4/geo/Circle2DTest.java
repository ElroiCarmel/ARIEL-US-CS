package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;

class Circle2DTest {
	static Point2D cen;
	double radius;
	Circle2D src;
	
	@BeforeEach
	void setUpCircle() {
		cen = new Point2D(5, 7);
		radius = 4;
		src = new Circle2D(cen, radius);
	}
	@Test
	void areaTest() {
		double area = 50.26548246;
		assertEquals(area, src.area(), Ex4_Const.EPS1);
	}
	@Test
	void perimeterTest() {
		double perimeter = 25.13274123;
		assertEquals(perimeter, src.perimeter(), Ex4_Const.EPS1);
	}
	@Test
	void testContains() {
		assertTrue(src.contains(new Point2D(7, 9)));
		assertFalse(src.contains(new Point2D(12,32)));
	}

	@Test
	void testMove() {
		src.move(new Point2D(7,9));
		Point2D cenTest = new Point2D(12, 16);
		assertTrue(src.get_center().close2equals(cenTest, Ex4_Const.EPS1));
	}

	@Test
	void testCopy() {
		Circle2D copy = (Circle2D) src.copy();
		assertEquals(src.get_center(), copy.get_center());
		assertEquals(src.getRadius(), copy.getRadius());
	}

	@Test
	void testScale() {
		src.scale(src.get_center(), 1.4);
		assertEquals(5.6, src.getRadius(), Ex4_Const.EPS1);
	}

	@Test
	void testRotate() {
		// in circle the rotate changes only the center point
		src.rotate(new Point2D(5, 12), 90);
		assertEquals(new Point2D(10, 12), src.get_center());
	}

}
