package Exe.Ex4.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

//import javax.swing.text.Segment;

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

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the StdDraw
 * with the Map class. Written for 101 java course it uses simple static
 * functions to allow a "Singleton-like" implementation.
 * 
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI {
	private ShapeCollectionable _shapes = new ShapeCollection();
	private GUI_Shapeable _gs;
	private Color _color = Color.blue;
	private boolean _fill = false;
	private String _mode = "";
	private Point2D _p1;
	private Point2D _p2; // For drawing Triangle2D
	private ArrayList<Point2D> _polyPoints = new ArrayList<>(); // For drawing Polygon2D
	
	private int shapeCount=0;
	private static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}

	public void init(ShapeCollectionable s) {
		if (s == null) {
			_shapes = new ShapeCollection();
		} else {
			_shapes = s.copy();
		}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}

	public void show(double d) {
		StdDraw_Ex4.setScale(0, d);
		StdDraw_Ex4.show();
		drawShapes();
	}

	public static Ex4 getInstance() {
		if (_winEx4 == null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable sh = _shapes.get(i);

			drawShape(sh);
		}
		if (_gs != null) {
			drawShape(_gs);
		}
		StdDraw_Ex4.show();
	}

	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if (g.isSelected()) {
			StdDraw_Ex4.setPenColor(Color.gray);
		}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		if (gs instanceof Circle2D) {
			Circle2D c = (Circle2D) gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if (isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			} else {
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		if (gs instanceof Rect2D) {
			Rect2D r = (Rect2D) gs;
			double[] x = new double[] { r.get_p1().x(), r.get_p2().x(), r.get_p3().x(), r.get_p4().x() };
			double[] y = new double[] { r.get_p1().y(), r.get_p2().y(), r.get_p3().y(), r.get_p4().y() };
			if (isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			} else {
				StdDraw_Ex4.polygon(x, y);
			}
		} 

		if (gs instanceof Polygon2D) {
			Polygon2D poly = (Polygon2D) gs;
			if (isFill) {
				StdDraw_Ex4.filledPolygon(poly.getX(), poly.getY());
			} else {
				StdDraw_Ex4.polygon(poly.getX(), poly.getY());
			}
		} 

		if (gs instanceof Segment2D) {
			Segment2D s = (Segment2D) gs;
			Point2D p1 = s.getPoints()[0];
			Point2D p2 = s.getPoints()[1];
			StdDraw_Ex4.line(p1.x(), p1.y(), p2.x(), p2.y());
		}

		if (gs instanceof Triangle2D) {
			Triangle2D t = (Triangle2D) gs;
			double[] x = new double[] { t.getPoints()[0].x(), t.getPoints()[1].x(), t.getPoints()[2].x() };
			double[] y = new double[] { t.getPoints()[0].y(), t.getPoints()[1].y(), t.getPoints()[2].y() };
			if (isFill) {
				StdDraw_Ex4.filledPolygon(x, y);
			} else {
				StdDraw_Ex4.polygon(x, y);
			}
		}

	}

	private void setColor(Color c) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			if (s.isSelected()) {
				s.setColor(c);
			}
		}
	}

	private void setFill() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			if (s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if (p.equals("Blue")) {
			_color = Color.BLUE;
			setColor(_color);
		}
		if (p.equals("Red")) {
			_color = Color.RED;
			setColor(_color);
		}
		if (p.equals("Green")) {
			_color = Color.GREEN;
			setColor(_color);
		}
		if (p.equals("White")) {
			_color = Color.WHITE;
			setColor(_color);
		}
		if (p.equals("Black")) {
			_color = Color.BLACK;
			setColor(_color);
		}
		if (p.equals("Yellow")) {
			_color = Color.YELLOW;
			setColor(_color);
		}
		if (p.equals("Fill")) {
			_fill = true;
			setFill();
		}
		if (p.equals("Empty")) {
			_fill = false;
			setFill();
		}
		if (p.equals("Clear")) {
			_shapes.removeAll();
			shapeCount=0;
		}
		if (p.equals("Remove")) {
			remove();
		}
		if (p.equals("All")) {
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shapeable s = _shapes.get(i);
				s.setSelected(true);
			}
		}
		if (p.equals("None")) {
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shapeable s = _shapes.get(i);
				s.setSelected(false);
			}
		}
		if (p.equals("Anti")) {
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shapeable s = _shapes.get(i);
				if (s.isSelected()) {
					s.setSelected(false);
				} else {
					s.setSelected(true);
				}
			}
		}
		if (p.equals("ByToString")) {
			_shapes.sort(ShapeComp.CompByToString);
		}
		if (p.equals("ByArea")) {
			_shapes.sort(ShapeComp.CompByArea);
		}
		if (p.equals("ByAntiArea")) {
			_shapes.sort(ShapeComp.CompByAntiArea);
		}
		if (p.equals("ByPerimeter")) {
			_shapes.sort(ShapeComp.CompByPerimeter);
		}
		if (p.equals("ByAntiPerimeter")) {
			_shapes.sort(ShapeComp.CompByAntiPerimeter);
		}
		if (p.equals("ByTag")) {
			_shapes.sort(ShapeComp.CompByTag);
		}
		if (p.equals("ByAntiTag")) {
			_shapes.sort(ShapeComp.CompByAntiTag);
		}
		
		if(p.equals("Info")) {
			String str = getInfo();
			System.out.println(str);
		}

		if(p.equals("Save")) {
			//https://www.codejava.net/java-se/swing/show-simple-open-file-dialog-using-jfilechooser
			JFileChooser filechooser = new JFileChooser();
			filechooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int res = filechooser.showSaveDialog(StdDraw_Ex4.getFrame());
			
			if(res == JFileChooser.APPROVE_OPTION) {
				try {
					filechooser.getSelectedFile().getAbsoluteFile().createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				_shapes.save(filechooser.getSelectedFile().getPath());
			}
		}
		
		// Work similar to the same
		if(p.equals("Load")) {
			JFileChooser filechooser = new JFileChooser();
			filechooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int res = filechooser.showOpenDialog(StdDraw_Ex4.getFrame());
			
			if(res == JFileChooser.APPROVE_OPTION) {
				try {
					filechooser.getSelectedFile().getAbsoluteFile().createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				_shapes.load(filechooser.getSelectedFile().getPath());
			}
		}
		
		
		drawShapes();

	}

	public void mouseClicked(Point2D p) {
		System.out.println("Mode: " + _mode + "  " + p);
		if (_mode.equals("Circle")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				shapeCount++;
				_gs = null;
				_p1 = null;
			}
		}
		if (_mode.equals("Polygon")) {
			if (_gs == null) {
				_polyPoints.add(p);
				_p1 = new Point2D(p);
			} else {
				_polyPoints.add(p);
			}
		}

		if (_mode.equals("Triangle")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else if (_p2 == null) {
				_p2 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				shapeCount++;
				_gs = null;
				_p1 = null;
				_p2 = null;
			}
		}
		if (_mode.equals("Segment")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				shapeCount++;
				_gs = null;
				_p1 = null;
			}
		}
		if (_mode.equals("Rect")) {
			if (_gs == null) {
				_p1 = new Point2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				shapeCount++;
				_gs = null;
				_p1 = null;
			}
		}


		if (_mode.equals("Move")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			} else {
				_p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
				move();
				_p1 = null;
			}
		}

		if (_mode.equals("Copy")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			} else {
				_p1 = new Point2D(p.x() - _p1.x(), p.y() - _p1.y());
				copy();
				_p1 = null;
			}
		}

		if(_mode.equals("Scale_90%")) {
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if (s.isSelected() && g != null) {
					g.scale(p, 0.9);
				}
			}
		}

		if(_mode.equals("Scale_110%")) {
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shapeable s = _shapes.get(i);
				GeoShapeable g = s.getShape();
				if (s.isSelected() && g != null) {
					g.scale(p, 1.1);
				}
			}
		}

		if (_mode.equals("Rotate")) {
			if (_p1 == null) {
				_p1 = new Point2D(p);
			} else {
				_p2 = new Point2D(p);
				rotate();
				_p1 = null;
				_p2 = null;
			}
		}

		if (_mode.equals("Point")) {
			select(p);
		}

		drawShapes();
	}

	private void select(Point2D p) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (g != null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}

	private void move() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (s.isSelected() && g != null) {
				g.move(_p1);
			}
		}
	}

	private void copy() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (s.isSelected() && g != null) {				
				GUI_Shapeable temp = s.copy();
				temp.setTag(shapeCount++); 
				temp.getShape().move(_p1);
				_shapes.add(temp);

			}
		}
	}

	private void remove() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (s.isSelected() && g != null) {
				_shapes.removeElementAt(i);
			}
		}
	}

	private void rotate() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if (s.isSelected() && g != null) {
				g.rotate(_p1, Math.toDegrees(_p1.getAngleFromPoints(_p2)));
			}
		}
	}

	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");
		if (_mode.equals("Polygon") && _gs!=null) {
			Polygon2D poly = new Polygon2D(_polyPoints);
			_gs = new GUIShape(poly, _fill, _color, shapeCount);
			_shapes.add(_gs);
			shapeCount++;
			_gs = null;
			_p1 = null;
			_polyPoints.clear();
			drawShapes();
		} else if (_gs!=null) {
			_gs=null;
			_p1=null;
			_p2=null;
			drawShapes();

		}

	}

	public void mouseMoved(MouseEvent e) {
		if (_p1 != null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			//			System.out.println("M: "+x1+","+y1);
			Point2D p = new Point2D(x1, y1);
			if (_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1, r);
			}
			if (_mode.equals("Polygon")) {
				Polygon2D poly = new Polygon2D(_polyPoints);
				poly.addPoint(p);
				gs = poly;
			}
			if (_mode.equals("Triangle")) {
				if (_p2 != null) {
					gs = new Triangle2D(_p1, _p2, p);
				} else {
					gs = new Segment2D(_p1, p);
				}
			}
			if (_mode.equals("Segment")) {
				gs = new Segment2D(_p1, p);
			}
			if (_mode.equals("Rect")) {
				Point2D p2 = new Point2D(p.x(), _p1.y());
				Point2D p4 = new Point2D(_p1.x(), p.y());
				gs = new Rect2D(_p1, p2, p, p4);
			}

			_gs = new GUIShape(gs, false, Color.pink, shapeCount);
			drawShapes();
		}
	}

	@Override
	public ShapeCollectionable getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}

	@Override
	public void show() {
		show(Ex4_Const.DIM_SIZE);
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans += s.toString() + "\n";
		}
		return ans;
	}
}
