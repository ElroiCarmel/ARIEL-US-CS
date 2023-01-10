package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

/* 
 * hanan helped me
 */
class GUIShapeTest {
	// will be used to create the shapes in the functions
	private ArrayList<Point2D> _points = new ArrayList<Point2D>();
	GeoShapeable shape = null;
	GUIShape gs = null;
	
	
	@Test
	public void testInit() {
		
		// Test Circle
		String shapeStr = "GUIShape,-16777216,true,1,Circle2D,5.0,4.0,6.9";
		String [] ww = shapeStr.split(",");
		
		assertEquals(ww[2], "true");
		// create the shape from the string
		gs = new GUIShape(shapeStr);
		// create the shape manually
		Circle2D c = new Circle2D(new Point2D(5,4),6.8);
		// check both shapes has the same value
		assertEquals(gs.getShape().getPoints()[0], c.getPoints()[0]);
		assertEquals(gs.getColor().getRGB(),Color.BLACK.getRGB());	
		
		// Test Triangle
		String str1 = "GUIShape,-16777216,true,1,Triangle2D,3.0,3.0,7.0,7.0,5.0,5.0"; // A segment data 
		String [] ww1 = shapeStr.split(",");
		assertEquals(ww1[2], "true");
		gs = new GUIShape(str1);
		
		Triangle2D t = new Triangle2D(new Point2D(3,3),new Point2D(7,7), new Point2D(5,5));
		
		assertEquals(gs.getShape().getPoints()[2], t.getPoints()[2]);
		assertEquals(gs.getColor().getRGB(),Color.BLACK.getRGB());	
		
		// Polygon test
		String str2 = "GUIShape,-16777216,true,2,Polygon2D,1,2,2.0,7.0,7.0,7.0,4.0,4.0,8.0,4.0"; // A polygon data 
		String [] ww2 = shapeStr.split(",");
		assertEquals(ww2[2], "true");
		
		gs = new GUIShape(str2);
		
		_points.add(new Point2D(1,2));
		_points.add(new Point2D(2,7));
		_points.add(new Point2D(7,7));
		_points.add(new Point2D(4,4));		
		_points.add(new Point2D(8,4));
		
		Polygon2D p = new Polygon2D(_points);
		for (int i = 0; i<_points.size(); i++) {
			assertEquals(gs.getShape().getPoints()[i],p.getPoints()[i]);
		}
		assertEquals(gs.getColor().getRGB(),Color.BLACK.getRGB());	
				
	}
}