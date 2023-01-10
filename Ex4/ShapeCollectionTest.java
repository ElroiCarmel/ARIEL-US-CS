package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import Exe.Ex4.geo.*;

class ShapeCollectionTest {
	@Test
	void testAll() {
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
		
		//SOME SORTING TESTS
		// sort by area
		ShapeComp byArea = new ShapeComp(Ex4_Const.Sort_By_Area);
		shapes.sort(byArea);
		// we use the shapes' tag for checking
		assertEquals(segGui.getTag(), shapes.get(0).getTag()); // Segment should be first
		assertEquals(triGui.getTag(), shapes.get(1).getTag()); // Triangle should be second
		assertEquals(crGui.getTag(), shapes.get(2).getTag()); // Circle should be last
		
		// sort by anti perimeter
		ShapeComp byAntiPerimeter = new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter);
		shapes.sort(byAntiPerimeter);
		// should be circle-triangle-segment
		assertEquals(crGui.getTag(), shapes.get(0).getTag());
		assertEquals(triGui.getTag(), shapes.get(1).getTag());
		assertEquals(segGui.getTag(), shapes.get(2).getTag());
		
		// BOUNDING BOX TEST
		/*
		 * These should be the points of the rect
		 * containing all the shapes in the collection
		 * (got by simple check of min and max points values)
		 */
		Point2D p1BBox = new Point2D(-2,-2);
		Point2D p2BBox = new Point2D(8,-2);
		Point2D p3BBox = new Point2D(8,9);
		Point2D p4BBox = new Point2D(-2,9);
		Rect2D boundingRect = shapes.getBoundingBox();
		assertEquals(p1BBox, boundingRect.get_p1());
		assertEquals(p2BBox, boundingRect.get_p2());
		assertEquals(p3BBox, boundingRect.get_p3());
		assertEquals(p4BBox, boundingRect.get_p4());
		
		// REMOVE ALL TEST
		shapes.removeAll();
		assertEquals(0, shapes.size());


		
	}
}
