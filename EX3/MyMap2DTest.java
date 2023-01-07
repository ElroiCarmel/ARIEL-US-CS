/*
 * ID 1: 208762971
 * ID 2: 207859919
 */

package Exe.EX3;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyMap2DTest {
	// Our work interface will be a map of 20*20
	static MyMap2D mapTest = new MyMap2D(20);
	int WHITE = Color.WHITE.getRGB();
	int BLACK = Color.BLACK.getRGB();
	int BLUE = Color.BLUE.getRGB();
	int RED = Color.RED.getRGB();
	int GREEN = Color.GREEN.getRGB();
	int YELLOW = Color.YELLOW.getRGB();
	
	/*
	 * Because in some tests we changed the map dimensions
	 * the purpose of this block of code is to "reset" our 
	 * work interface before each test.
	 */
	@BeforeEach
	void clearMap() {
		mapTest.init(20, 20);
		mapTest.fill(WHITE);
	}
	
	@Test
	void testDrawSegment() {
		/*
		 * Tests the case we want to draw a line between
		 * two points with the same y value. 
		 */
		Point2D p1 = new Point2D(5,16);
		Point2D p2 = new Point2D(9,16);
		mapTest.drawSegment(p1, p2, BLACK);
		for (int i=5;i<=9;i++) {
			assertEquals(BLACK, mapTest.getPixel(i, 16));
		}
		/*
		 * Tests the case we want to draw a line between two
		 * points with the same x value.
		 */
		Point2D p3 = new Point2D(3,10);
		Point2D p4 = new Point2D(3,15);
		mapTest.drawSegment(p3, p4, RED);
		for (int i=10;i<=15;i++) {
			assertEquals(RED, mapTest.getPixel(3, i));
		}
		/**
		 * Test the case we want to draw a line between two points so that
		 * point2.x=point1.x+1
		 * We know that the function should draw a line that looks like if
		 * it was split in the middle, and then moved right by one.
		 */
		Point2D p5 = new Point2D(5,17);
		Point2D p6 = new Point2D(6,10);
		mapTest.drawSegment(p5, p6, BLACK);
		for (int i=17;i>=14;i--) {
			assertEquals(BLACK, mapTest.getPixel(5, i));
		}
		for (int i=13; i>=10;i--) {
			assertEquals(BLACK, mapTest.getPixel(6, i));
		}
	}
	/*
	 * Simple check whether the function did paint the
	 * specified area of the rectangle.
	 */
	@Test
	void testDrawRect() {
		Point2D p1 = new Point2D(5,9);
		Point2D p2 = new Point2D(8,12);
		mapTest.drawRect(p1, p2, BLACK);
		for (int i=5; i<=8;i++) {
			for (int j=9;j<=12;j++) {
				assertEquals(BLACK, mapTest.getPixel(i, j));
			}
		}
	}
	/*
	 * Simple test to check if the function painted
	 * the cells only within the radius from the center point of
	 * the circle.
	 */
	@Test
	void testDrawCircle() {
		mapTest.init(40, 40);
		mapTest.fill(WHITE);
		mapTest.drawCircle(new Point2D(20,20), 15, BLACK);
		for (int i=0;i<mapTest.getWidth();i++) {
			for (int j=0; j<mapTest.getHeight(); j++) {
				double dx = i-20;
				double dy = j-20;
				double dis = Math.sqrt(dx*dx+dy*dy);
				if (dis<=15) {
					assertEquals(BLACK, mapTest.getPixel(i, j));
				} else {
					assertNotEquals(BLACK, mapTest.getPixel(i, j));
				}
			}
		}
		
	}
	/*
	 * In this test case we draw a rectangle and the activated
	 * "fill" on a point outside the rectangle. The function
	 * should paint all of the points outside the rectangle. The
	 * points inside the rectangle should stay with the same color.
	 */
	@Test
	void testFillPoint2D() {
		mapTest.drawRect(new Point2D(6, 14), new Point2D(13, 10), BLACK);
		mapTest.fill(new Point2D(4,16), YELLOW);
		for (int i=0;i<mapTest.getWidth();i++) {
			for (int j=0; j<mapTest.getHeight(); j++) {
				if (i>=6&&i<=13&&j>=10&&j<=14) {
					assertEquals(BLACK, mapTest.getPixel(i, j));
				} else {
					assertEquals(YELLOW, mapTest.getPixel(i, j));
				}
			}
		}
	}

	@Test
	void testFillXY() {
		/*
		 * In this test case I created a rectangle of 7*5 with color black
		 * Then activated "fill" with color blue on a point inside the
		 * the rectangle. 
		 */
		mapTest.drawRect(new Point2D(7,10), new Point2D(13,14), BLACK);
		int numCellFill = mapTest.fill(9, 12, BLUE);
		// The dimensions of the area that should be filled is 7*5 so the number
		// of painted cells should be 7*5=35.
		assertEquals(35, numCellFill);
		// Check the function actually painted some points inside the area
		assertEquals(BLUE, mapTest.getPixel(8,11));
		assertEquals(BLUE, mapTest.getPixel(11,13));
		// Check the function did not paint points outside the rectangle
		assertNotEquals(BLUE, mapTest.getPixel(15,18));
		assertNotEquals(BLUE, mapTest.getPixel(5,12));

		/* Test the case we activated fill on a point that has no
		 * no neighbours with the same color (a "lonely" point).
		 * Only the selected point's color should be painted. 
		 */
		mapTest.fill(WHITE); //First clearing the map
		mapTest.setPixel(5, 5, BLACK);
		mapTest.fill(5, 5, BLUE);
		assertEquals(BLUE, mapTest.getPixel(5,5));
		for (int i=0;i<mapTest.getWidth();i++) {
			for (int j=0; j<mapTest.getHeight(); j++) {
				if (i==5&&j==5) {
					assertEquals(BLUE, mapTest.getPixel(i, j));
				} else {
					assertNotEquals(BLUE, mapTest.getPixel(i, j));
				}
			}
		}
	}
		

	@Test
	void testShortestPath() {
		/*
		 * Test the case the way between 2 points is blocked by an other color
		 * so there is no path and the function should return "null".
		 */
		mapTest.drawSegment(new Point2D(0,10), new Point2D(19,10), BLACK);
		Point2D[] path = mapTest.shortestPath(new Point2D(3,5), new Point2D(5,14));
		assertNull(path);
		/*
		 * Test the case the 2 points are not in the same color. The function
		 * should return "null".
		 */
		mapTest.fill(WHITE);
		mapTest.drawRect(new Point2D(5,5), new Point2D(10,10), BLACK);
		Point2D[] path2 = mapTest.shortestPath(new Point2D(6,6), new Point2D(10,14));
		assertNull(path2);
		/*
		 * Test the case we chose 2 points with the same X value.
		 * The shortest path should be a direct line between them.
		 * 
		 */
		mapTest.fill(WHITE);
		mapTest.shortestPath(new Point2D(6,1), new Point2D(6,16));
		Point2D[] path3 = mapTest.shortestPath(new Point2D(6,1), new Point2D(6,16));
		for (int i=0;i<path3.length;i++) {
			mapTest.setPixel(path3[i], BLACK);
		}
		for (int i=0;i<mapTest.getWidth();i++) {
			for (int j=0; j<mapTest.getHeight(); j++) {
				if (i==6&&j>=1&&j<=16) {
					assertEquals(BLACK, mapTest.getPixel(i, j));
				} else {
					assertNotEquals(BLACK, mapTest.getPixel(i, j));
				}
			}
		}
	}

	@Test
	void testShortestPathDist() {
		/*
		 * Test the case we chose the same point. The distance from a point to
		 * itself is zero.
		 */
		int dist = mapTest.shortestPathDist(new Point2D(10,10), new Point2D(10,10));
		assertEquals(0, dist);

	}
	
	/*
	 * The general idea of the tests below is to check
	 * that the map changes according to Game of Life's rules,
	 * given some basic situations.
	 */
	@Test
	void testNextGenGol() {
		/*
		 * In the first case we draw a horizontal line of 3 points
		 * and by GOL rules we know it should change to a vertical line
		 * of three points with the same center point as before.
		 */
		mapTest.drawSegment(new Point2D(5,5), new Point2D(7,5), BLACK);
		mapTest.nextGenGol();
		for (int i = 0; i < mapTest.getWidth(); i++) {
			for (int j = 0; j < mapTest.getHeight(); j++) {
				if (i==6 && j<=6 && j>=4) {
					assertEquals(BLACK, mapTest.getPixel(i, j));
				} else
					assertEquals(WHITE, mapTest.getPixel(i, j));
			}
		}
		/*
		 * According to GOL rules we know that the next generation
		 * should "go back" to the same situation as before, which is 3 point
		 * horizontal line.
		 */
		mapTest.nextGenGol();
		for (int i = 0; i < mapTest.getWidth(); i++) {
			for (int j = 0; j < mapTest.getHeight(); j++) {
				if (i>=5&&i<=7&&j==5) {
					assertEquals(BLACK, mapTest.getPixel(i, j));
				} else
					assertEquals(WHITE, mapTest.getPixel(i, j));
			}
		}
		/*
		 * A rectangle of 2*2 should same according to the rules
		 */
		mapTest.fill(WHITE);
		mapTest.drawRect(new Point2D(4,4), new Point2D(5,5), BLACK);
		mapTest.nextGenGol();
		for (int i = 0; i < mapTest.getWidth(); i++) {
			for (int j = 0; j < mapTest.getHeight(); j++) {
				if (i>=4&&i<=5&&j>=4&&j<=5) {
					assertEquals(BLACK, mapTest.getPixel(i, j));
				} else
					assertEquals(WHITE, mapTest.getPixel(i, j));
			}
		}
		
	}

}
