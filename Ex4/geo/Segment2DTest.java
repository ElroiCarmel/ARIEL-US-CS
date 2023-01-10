package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Exe.Ex4.*;
class Segment2DTest {
	static Point2D p1, p2;
	static Segment2D src;
	@BeforeEach
	void setUpSegment() {
		p1 = new Point2D(3.515625,6.3593751);
		p2 = new Point2D(6.03125,8.31251);
		src = new Segment2D(p1, p2);
	}

	@Test
	void testContains() {
		assertTrue(src.contains(new Point2D(5.046875,7.5468751)));
		assertFalse(src.contains(new Point2D(4.09375,7.81251)));
	}

	@Test
	void testArea() {
		assertEquals(0, src.area());
	}

	@Test
	void testPerimeter() {
		double perimeterTest = 2*p1.distance(p2);
		assertEquals(perimeterTest, src.perimeter());
	}

	@Test
	void testMove() {
		// Click on two points to create a vector 
		Point2D p0 = new Point2D(5.234375,7.1093751);
		Point2D p1 = new Point2D(6.15625,6.468751);
		src.move(p0.vector(p1));
		// create the 2 points with the expected values
		Point2D p1Test = new Point2D(4.4375,5.71875);
		Point2D p2Test = new Point2D(6.953125,7.671875);
		// check that the 2 points of the segment got the new points values
		assertTrue(p1Test.close2equals(src.get_p1(), Ex4_Const.EPS1));
		assertTrue(p2Test.close2equals(src.get_p2(), Ex4_Const.EPS1));
	}

	@Test
	void testCopy() {
		// create copy
		Segment2D segCopy = (Segment2D) src.copy();
		// scale the copy and check only the copy's points changed
		segCopy.scale(new Point2D(4,5), 1.1);
		assertFalse(src.get_p1().close2equals(segCopy.get_p1(), Ex4_Const.EPS1));
		assertFalse(src.get_p2().close2equals(segCopy.get_p2(), Ex4_Const.EPS1));

	}

	@Test
	void testScale() {
		src.scale(new Point2D(4.515625,8.593751), 0.9);
		// create the 2 points with the expected values
		Point2D p1Test = new Point2D(3.615625,6.5828124);
		Point2D p2Test = new Point2D(5.8796875,8.340625);
		// check that the 2 points of the segment got the new points values
		assertTrue(p1Test.close2equals(src.get_p1(), Ex4_Const.EPS1));
		assertTrue(p2Test.close2equals(src.get_p2(), Ex4_Const.EPS1));
	}

	@Test
	void testRotate() {
		Point2D center = new Point2D(5.5625,6.9843751);
		Point2D ptag = new Point2D(6.8125,7.781251);
		double angleDegrees = Math.toDegrees(center.getAngleFromPoints(ptag));
		src.rotate(center, angleDegrees);
		// create the 2 points with the expected values
		Point2D p1Test = new Point2D(4.1724926,5.3570453);
		Point2D p2Test = new Point2D(5.24381940,8.356266);
		// check that the 2 points of the segment got the new points values
		assertTrue(p1Test.close2equals(src.get_p1(), Ex4_Const.EPS1));
		assertTrue(p2Test.close2equals(src.get_p2(), Ex4_Const.EPS1));
	}

	@Test
	void testGetPoints() {
		Point2D[] srcPoints = src.getPoints();
		Point2D[] testPoints = new Point2D[2];
		testPoints[0] = new Point2D(p1);
		testPoints[1] = new Point2D(p2);

		assertTrue(srcPoints[0].close2equals(testPoints[0]));
		assertTrue(srcPoints[1].close2equals(testPoints[1]));

	}

}
