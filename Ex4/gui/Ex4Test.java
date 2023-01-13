package Exe.Ex4.gui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Exe.Ex4.Ex4_Const;
import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Ex4Test {
	static Ex4 ex4;
	static ShapeCollectionable shapes;
	
	@BeforeEach
	void setUp() {
		ex4 = Ex4.getInstance();
		shapes = ex4.getShape_Collection();
	}
	
	@Test
	void testSelectShapes() {
		// add a rect2D
		Point2D p1 = new Point2D(3,3);
		Point2D p2 = new Point2D(9,3);
		Point2D p3 = new Point2D(9,10);
		Point2D p4 = new Point2D(3,10);
		Rect2D r1 = new Rect2D(p1, p2, p3, p4);
		GUIShape gs1 = new GUIShape(r1, false, Color.BLACK, ex4.getTagCounter());
		shapes.add(gs1);
		assertFalse(gs1.isSelected()); // make sure it wasn't selected before action
		ex4.actionPerformed("Point");
		ex4.mouseClicked(new Point2D(5,7)); // a click inside the rect should select it
		assertTrue(gs1.isSelected());
		
		// add a simple circle 
		Circle2D c1 = new Circle2D(new Point2D(15,15), 10);
		GUIShape gs2 = new GUIShape(c1, false, Color.BLACK, ex4.getTagCounter());
		shapes.add(gs2);
		ex4.mouseClicked(new Point2D(20,20)); //click inside the circle
		assertTrue(gs2.isSelected());
		
		// select none shapes
		ex4.actionPerformed("None");
		assertFalse(gs1.isSelected());
		assertFalse(gs2.isSelected());
		
		// select all shapes
		ex4.actionPerformed("All");
		assertTrue(gs1.isSelected());
		assertTrue(gs2.isSelected());
	}

}
