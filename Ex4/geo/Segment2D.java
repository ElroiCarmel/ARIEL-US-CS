package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment2D implements GeoShapeable{
	
	@Override
	public String toString() {
		return "Segment2D," + _p1 + "," + _p2;
	}

	private Point2D _p1;
	private Point2D _p2;
	
	public Segment2D(Point2D p1, Point2D p2) {
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
	}
	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		double sumOt = _p1.distance(ot)+ot.distance(_p2);
		return Math.abs(_p1.distance(_p2)-sumOt)<=Ex4_Const.EPS1;
	
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		return 2*_p1.distance(_p2);
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		_p1.move(vec);
		_p2.move(vec);
		
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Segment2D(_p1, _p2) ;
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		Point2D[] ans = new Point2D[2];
		ans[0] = this._p1;
		ans[1] = this._p2;
		return ans;
	}
	////////////Private Functions////////////////
	///// Will be used for getting bounding box of shape collection

	public double getMinX() {
		return Math.min(_p1.x(), _p2.x());
	}
	public double getMaxX() {
		return Math.max(_p1.x(), _p2.x());
	}
	public double getMinY() {
		return Math.min(_p1.y(), _p2.y());
	}
	public double getMaxY() {
		return Math.max(_p1.y(), _p2.y());
	}
}
