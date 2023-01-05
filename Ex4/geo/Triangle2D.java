package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.gui.Ex4;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{
	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	
	public Triangle2D(Point2D p1, Point2D p2, Point2D p3) {
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
		this._p3 = new Point2D(p3);
	}
	
	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		Triangle2D t1 = new Triangle2D(ot, this._p1, this._p2);
		Triangle2D t2 = new Triangle2D(ot, this._p2, this._p3);
		Triangle2D t3 = new Triangle2D(ot, this._p1, this._p3);
		double sumArea = t1.area()+t2.area()+t3.area();
		return (Math.abs(sumArea-this.area())<=Ex4_Const.EPS1);
//		return false;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		/*
		 * Heron's formula
		 * https://en.wikipedia.org/wiki/Heron%27s_formula
		 */
		double a = this._p1.distance(this._p2);
		double b = this._p2.distance(this._p3);
		double c = this._p3.distance(this._p1);
		double p = (a+b+c)/2;
		return (Math.sqrt(p*(p-a)*(p-b)*(p-c)));
	}

	@Override
	public double perimeter() {
		return (this._p1.distance(this._p2)+this._p2.distance(this._p3)+this._p3.distance(this._p1));
	}

	@Override
	public void move(Point2D vec) {
		this._p1.move(vec);
		this._p2.move(vec);
		this._p3.move(vec);
		
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Triangle2D(_p1, _p2, _p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		_p3.rotate(center, angleDegrees);
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		Point2D[] ans = new Point2D[] {this._p1, this._p2, this._p3};
		return ans;
	}
	
}
