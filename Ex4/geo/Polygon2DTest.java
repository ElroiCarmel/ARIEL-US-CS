package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;

class Polygon2DTest {
	static Polygon2D src;
//	static ArrayList<Point2D> points;
	static Point2D p1, p2, p3, p4, p5, p6;
	
	
	// This is also a test to the addPoint function
	@BeforeEach
	void setUpPolygon() {
		src = new Polygon2D();
//		points = new ArrayList<Point2D>();
		p1 = new Point2D(0,0);
		p2 = new Point2D(4,0);
		p3 = new Point2D(4,4);
		p4 = new Point2D(2,4);
		p5 = new Point2D(2,6);
		p6 = new Point2D(0,6);
		src.addPoint(p1);
		src.addPoint(p2);
		src.addPoint(p3);
		src.addPoint(p4);
		src.addPoint(p5);
		src.addPoint(p6);
	}

	@Test
	void testContains() {
		Point2D p1 = new Point2D(6,2);
		Point2D p2 = new Point2D(2,2);
		assertTrue(src.contains(p2));
		assertFalse(src.contains(p1));
	}

	@Test
	void testArea() {
		assertEquals(20, src.area(), Ex4_Const.EPS1);
	}

	@Test
	void testPerimeter() {
		assertEquals(20, src.perimeter(), Ex4_Const.EPS1);
	}

	@Test
	void testMove() {
		src.move(new Point2D(2,2));
		// check some of the points were moved
		Point2D[] pointsPoly = src.getPoints();
		Point2D p1Test = new Point2D(2,2);
		Point2D p3Test = new Point2D(6,6);
		Point2D p6Test = new Point2D(2,8);
		
		assertEquals(p1Test, pointsPoly[0]);
		assertEquals(p3Test, pointsPoly[2]);
		assertEquals(p6Test, pointsPoly[5]);

	}

	@Test
	void testCopy() {
		Point2D[] sourcePoints = new Point2D[] {p1, p2, p3, p4, p5, p6};
		// create a copy
		Polygon2D copy =(Polygon2D) src.copy();
		copy.move(new Point2D(10,10));
		// check the the source was not changed
		Point2D[] pointsPoly = src.getPoints();
		for (int i=0; i<6; i++) {
			assertEquals(sourcePoints[i], pointsPoly[i]);
		}
		
	}

	@Test
	void testScale() {
		src.scale(new Point2D(0,0), 2);
		// check some points were scaled
		Point2D p3Test = new Point2D(8,8);
		Point2D p6Test = new Point2D(0,12);
		
		Point2D[] pointsPoly = src.getPoints();
		assertEquals(p3Test, pointsPoly[2]);
		assertEquals(p6Test, pointsPoly[5]);

	}

	@Test
	void testRotate() {
		src.rotate(new Point2D(0,6), 90);
		
		Point2D p1Test = new Point2D(6,6);
		Point2D p5Test = new Point2D(0,8);
		
		Point2D[] pointsPoly = src.getPoints();
		assertTrue(p1Test.close2equals(pointsPoly[0], Ex4_Const.EPS1));
		assertTrue(p5Test.close2equals(pointsPoly[4], Ex4_Const.EPS1));
		

	}

}
