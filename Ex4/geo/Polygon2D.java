package Exe.Ex4.geo;

import java.util.ArrayList;

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
	private ArrayList<Point2D> _points;
	
	//constructors
	public Polygon2D() {
		_points = new ArrayList<>();
	}
	
	public Polygon2D(ArrayList<Point2D> p) {
		_points = (ArrayList<Point2D>) p.clone();
	}
	
	public void addPoint(Point2D p) {
		_points.add(new Point2D(p));
	}
	
	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		// Shoelace formula
		double[] x = this.getX();
		double[] y = this.getY();
		double firstSigma = 0;
		double secondSigma = 0;
		for(int i=0; i<x.length-1;i++) {
			firstSigma += x[i]*y[i+1];
		}
		for(int i=0; i<y.length-1;i++) {
			secondSigma += y[i]*x[i+1];
		}
		double X_nY_1 = x[x.length-1]*y[0];
		double X_1Y_n = x[0]*y[y.length-1];
		double ans = 0.5*Math.abs(firstSigma+X_nY_1-secondSigma-X_1Y_n);
		return ans;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		double ans=0;
		for (int i=0; i<_points.size()-1; i++) {
			ans += _points.get(i).distance(_points.get(i+1));
		}
		ans += _points.get(0).distance(_points.get(_points.size()-1));
		return ans;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		for (int i=0; i<_points.size();i++) {
			_points.get(i).move(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Polygon2D(_points);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		for (int i=0; i<_points.size();i++) {
			_points.get(i).scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		for (int i=0; i<_points.size();i++) {
			_points.get(i).rotate(center, angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		return (Point2D[]) _points.toArray();
	}
	
	public double[] getX() {
		double[] ans = new double[_points.size()];
		for (int i=0; i<_points.size();i++) {
			ans[i] = _points.get(i).x();
		}
		return ans;
	}
	public double[] getY() {
		double[] ans = new double[_points.size()];
		for (int i=0; i<_points.size();i++) {
			ans[i] = _points.get(i).y();
		}
		return ans;
	}
	
}
