package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

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

	@Test
	void testLoad() {
		/*
		 * The shapes that should be loaded
		 * GUIShape,-16776961,true,0,Circle2D,4.0,2.0,2.0
		 * GUIShape,-65536,true,1,Rect2D,1.5,8.0,4.0,8.0,4.0,12.0,1.5,12.0
		 * GUIShape,-16777216,true,2,Triangle2D,4.65,7.5,5.3,8.0,8.0,9.0
		 */
		ShapeCollection shapes = new ShapeCollection();
		shapes.load("drawTest");
		GUI_Shapeable circle = shapes.get(0);
		// check the circle was loaded with right propertys
		assertEquals(-16776961, circle.getColor().getRGB());
		assertTrue(circle.isFilled());
		assertEquals(0, circle.getTag());
		Circle2D geoCircle = (Circle2D) circle.getShape();
		assertEquals(new Point2D(4,2),geoCircle.get_center());

		GUI_Shapeable rect = shapes.get(1);
		// check the rect was loaded with right propertys
		assertEquals(-65536, rect.getColor().getRGB());
		assertTrue(rect.isFilled());
		assertEquals(1, rect.getTag());
		Rect2D geoRect = (Rect2D) rect.getShape();
		assertEquals(new Point2D(1.5,8),geoRect.get_p1());
		assertEquals(new Point2D(4,8),geoRect.get_p2());
		assertEquals(new Point2D(4,12),geoRect.get_p3());
		assertEquals(new Point2D(1.5,12),geoRect.get_p4());
		
		// check the triangle was loaded with right propertys
		GUI_Shapeable triangle = shapes.get(2);
		assertEquals(-16777216, triangle.getColor().getRGB());
		assertTrue(triangle.isFilled());
		assertEquals(2, triangle.getTag());
		Triangle2D geoTri = (Triangle2D) triangle.getShape();
		Point2D[] trianglePoints = geoTri.getPoints();
		assertEquals(new Point2D(4.65,7.5),trianglePoints[0]);
		assertEquals(new Point2D(5.3,8.0),trianglePoints[1]);
		assertEquals(new Point2D(8.0,9.0),trianglePoints[2]);

	}
}