package Exe.Ex4.geo;

import java.util.Arrays;

import Exe.Ex4.Ex4Main;
import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {
	//data
	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	private Point2D _p4;
	//constructor
	public Rect2D(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2); 
		this._p3 = new Point2D(p3);
		this._p4 = new Point2D(p4);
	}
	@Override
	public boolean contains(Point2D ot) {
		Triangle2D t1 = new Triangle2D(ot, _p1, _p2);
		Triangle2D t2 = new Triangle2D(ot, _p2, _p3);
		Triangle2D t3 = new Triangle2D(ot, _p3, _p4);
		Triangle2D t4 = new Triangle2D(ot, _p4, _p1);
		double sumAreas = t1.area()+t2.area()+t3.area()+t4.area();
		return (Math.abs(this.area()-sumAreas)<= Ex4_Const.EPS);
	}
	public Point2D get_p1() {
		return _p1;
	}
	public Point2D get_p2() {
		return _p2;
	}
	public Point2D get_p3() {
		return _p3;
	}
	public Point2D get_p4() {
		return _p4;
	}

	@Override
	public double area() {
		double width = _p1.distance(_p2);
		double height = _p2.distance(_p3);
		return width*height;

	}

	@Override
	public double perimeter() {
		double width = _p1.distance(_p2);
		double height = _p2.distance(_p3);
		return 2*width+2*height;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
		_p4.move(vec);
		
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Rect2D(_p1, _p2, _p3, _p4);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
		_p4.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		_p3.rotate(center, angleDegrees);
		_p4.rotate(center, angleDegrees);
	}

	@Override
	public String toString() {
		return "Rect2D," + _p1 + "," + _p2 + "," + _p3 + "," + _p4;
	}
	@Override
	public Point2D[] getPoints() {
		double[] pX = new double[] {_p1.x(), _p2.x(), _p3.x(), _p4.x()};
		double[] pY = new double[] {_p1.y(), _p2.y(), _p3.y(), _p4.y()};
		Arrays.sort(pX);
		Arrays.sort(pY);
		Point2D[] ans = new Point2D[2];
		ans[0] = new Point2D(pX[0], pY[0]);
		ans[1] = new Point2D(pX[3], pY[3]);
		return ans;
		}
	////////////Private Functions////////////////
	///// Will be used for getting bounding box of shape collection

	public double getMinX() {
		return this.getPoints()[0].x();
	}
	public double getMaxX() {
		return this.getPoints()[1].x();
	}
	public double getMinY() {
		return this.getPoints()[0].y();
	}
	public double getMaxY() {
		return this.getPoints()[1].y();
	}
}


