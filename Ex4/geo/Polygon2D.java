package Exe.Ex4.geo;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{
	//data
	private Point2D[] _points;
	private int _l;
	
	//constructors
	public Polygon2D(Point2D[] p) {
		this._points = p.clone();
		this._l = p.length;
	}
	
	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		double ans=0;
		for (int i=0; i<this._l-1; i++) {
			ans += this._points[i].distance(this._points[i+1]);
		}
		ans += this._points[0].distance(this._points[this._l-1]);
		return ans;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		for (int i=0; i<this._l;i++) {
			this._points[i].move(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		for (int i=0; i<this._l;i++) {
			this._points[i].scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		for (int i=0; i<this._l;i++) {
			this._points[i].scale(center, angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		return this._points.clone();
	}
	
}
