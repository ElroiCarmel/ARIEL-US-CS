package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Exe.Ex4.geo.*;

class ShapeCollectionTest {
	@Test
	void test() {
		
		// our collection of work
		ShapeCollection shapes = new ShapeCollection();
		// create gui shape
		Circle2D cr = new Circle2D(new Point2D(3,3), 5);
		GUI_Shapeable crGui = new GUIShape(cr, true, Color.BLACK, 0);
		// Add to collection
		shapes.add(crGui);
		// check the circle is in the collection
		assertTrue(shapes.get(0).getShape() instanceof Circle2D);
		
		// COPY TEST
		// add some more shapes to collection
		Segment2D seg = new Segment2D(new Point2D(2,7), new Point2D(7,9));
		GUI_Shapeable segGui = new GUIShape(seg, true, Color.BLACK, 1);
		Triangle2D tri = new Triangle2D(new Point2D(7,7), new Point2D(4,8),new Point2D(1,2));
		GUI_Shapeable triGui = new GUIShape(tri, true, Color.BLACK, 2);
		shapes.add(segGui);
		shapes.add(triGui);
		// create a copy
		ShapeCollection shapesCopy = (ShapeCollection) shapes.copy();
		// make changes in the copy and check the original is not changing
		// For example removing shapes from the copy will not affect the original
		shapesCopy.removeElementAt(0);
		shapesCopy.removeElementAt(1);
		// The size of original should stay 3 and the copy's should be 1
		assertEquals(3, shapes.size());
		assertEquals(1, shapesCopy.size());
		
		//SORTING TESTS
		// sort by area
	}
}
