package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Exe.Ex4.*;
class Rect2DTest {
	static Point2D p1, p2, p3, p4;
	static Rect2D src;

	@BeforeEach
	void setUpRect(){
		p1 = new Point2D(3.25, 5.53125);
		p2 = new Point2D(5.828125, 5.53125);
		p3 = new Point2D(5.828125, 8.09375);
		p4 = new Point2D(3.25, 8.09375);
		src = new Rect2D(p1, p2, p3, p4);
	}

	@Test
	void testToString() {
		String srcst = src.toString();
		assertEquals("Rect2D,3.25,5.53125,5.828125,5.53125,5.828125,8.09375,3.25,8.09375", srcst);
	}

	@Test
	void testContains() {
		boolean ansP1 = src.contains(new Point2D(2.84, 7));
		boolean ansP2 = src.contains(new Point2D(3.9062, 7.062));
		assertFalse(ansP1);
		assertTrue(ansP2);
	}

	@Test
	void testArea() {
		double area = src.area();
		assertEquals(6.6064, area, Ex4_Const.EPS1);
	}

	@Test
	void testPerimeter() {
		double perimeter = src.perimeter();
		assertEquals(10.281, perimeter, Ex4_Const.EPS1);
	}

	@Test
	void testMove() {
		// Click on to points to create a vector 
		Point2D p0 = new Point2D(4.4375, 6.93751);
		Point2D p1 = new Point2D(7.0, 7.593751);
		src.move(p0.vector(p1));
		// create the 4 points with the expected values
		Point2D p1Test = new Point2D(5.8125,6.1875);
		Point2D p2Test = new Point2D(8.390625,6.1875);
		Point2D p3Test = new Point2D(8.390625,8.75);
		Point2D p4Test = new Point2D(5.8125,8.75);
		// check that the 4 points of the rect got the new points values
		assertTrue(p1Test.close2equals(src.get_p1(), Ex4_Const.EPS1));
		assertTrue(p2Test.close2equals(src.get_p2(), Ex4_Const.EPS1));
		assertTrue(p3Test.close2equals(src.get_p3(), Ex4_Const.EPS1));
		assertTrue(p4Test.close2equals(src.get_p4(), Ex4_Const.EPS1));


	}

	@Test
	void testCopy() {
		Rect2D rectCopy = (Rect2D) src.copy();
		// move the copy and check only the copy's points changed
		rectCopy.move(new Point2D(4,5));
		assertFalse(src.get_p1().close2equals(rectCopy.get_p1(), Ex4_Const.EPS1));
		assertFalse(src.get_p2().close2equals(rectCopy.get_p2(), Ex4_Const.EPS1));
		assertFalse(src.get_p3().close2equals(rectCopy.get_p3(), Ex4_Const.EPS1));
		assertFalse(src.get_p4().close2equals(rectCopy.get_p4(), Ex4_Const.EPS1));
	}

	@Test
	void testScale1() {
		src.scale(new Point2D(4.25,8.8751), 1.1);
		// create the 4 points with the expected values
		Point2D p1Test = new Point2D(3.15,5.196875);
		Point2D p2Test = new Point2D(5.9859375,5.196875);
		Point2D p3Test = new Point2D(5.9859375,8.015625);
		Point2D p4Test = new Point2D(3.15,8.015625);
		// check that the 4 points of the rect got the new points values
		assertTrue(p1Test.close2equals(src.get_p1(), Ex4_Const.EPS1));
		assertTrue(p2Test.close2equals(src.get_p2(), Ex4_Const.EPS1));
		assertTrue(p3Test.close2equals(src.get_p3(), Ex4_Const.EPS1));
		assertTrue(p4Test.close2equals(src.get_p4(), Ex4_Const.EPS1));
	}

	@Test
	void testRotate() {
		Point2D center = new Point2D(4.5625,6.9843751);
		Point2D ptag = new Point2D(5.40625,7.5156251);
		double angleDegrees = Math.toDegrees(center.getAngleFromPoints(ptag));
		src.rotate(center, angleDegrees);
		// create the 4 points with the expected values
		Point2D p1Test = new Point2D(4.226063679894874,5.055375487781523);
		Point2D p2Test = new Point2D(6.4077577207512615,6.429034698691101);
		Point2D p3Test = new Point2D(5.0424237171805295,8.597506351421087);
		Point2D p4Test = new Point2D(2.8607296763241417,7.223847140511509);
		// check that the 4 points of the rect got the new points values
		assertTrue(p1Test.close2equals(src.get_p1(), Ex4_Const.EPS1));
		assertTrue(p2Test.close2equals(src.get_p2(), Ex4_Const.EPS1));
		assertTrue(p3Test.close2equals(src.get_p3(), Ex4_Const.EPS1));
		assertTrue(p4Test.close2equals(src.get_p4(), Ex4_Const.EPS1));
	}

	@Test
	void testGetPoints() {
		// rotate the rect and get the min and max points
		Point2D center = new Point2D(4.40625,6.843751);
		Point2D ptag = new Point2D(5.1875,7.251);
		double angleDegrees = Math.toDegrees(center.getAngleFromPoints(ptag));
		src.rotate(center, angleDegrees);
		Point2D[] rotatedRectBox = src.getPoints();
		// create a test array with bounding box points
		Point2D[] pointsTest = new Point2D[2];
		pointsTest[0] = new Point2D(2.80371,5.1458388);
		pointsTest[1] = new Point2D(6.273286,8.608756);
		assertTrue(pointsTest[0].close2equals(rotatedRectBox[0], Ex4_Const.EPS1));
		assertTrue(pointsTest[1].close2equals(rotatedRectBox[1], Ex4_Const.EPS1));
	}

	@Test
	void testGetMinX() {
		assertEquals(3.25, src.getMinX());
	}

	@Test
	void testGetMaxX() {
		assertEquals(5.828125, src.getMaxX());
	}

	@Test
	void testGetMinY() {
		assertEquals(5.53125, src.getMinY());
	}

	@Test
	void testGetMaxY() {
		assertEquals(8.09375, src.getMaxY());
	}

}
