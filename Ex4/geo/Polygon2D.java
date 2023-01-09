package Exe.Ex4.geo;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{
	@Override
	public String toString() {
		String str = "Polygon2D";
		for (Point2D point : _points) {
			str += "," + point;
		}
		return str;
	}
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
		if (_points.size()==2) {
			return (new Segment2D(_points.get(0), _points.get(1)).contains(ot));
		}
		double[] x = this.getX().clone();
		Arrays.sort(x);
		int hit =0;
		Line2D.Double ray = new Line2D.Double(ot.x(), ot.y(),x[x.length-1]+1 ,ot.y());
		for (int i=0; i<_points.size()-1;i++) {
			Point2D first = _points.get(i);
			Point2D second = _points.get(i+1);
			Line2D.Double side = new Line2D.Double(first.x(), first.y(), second.x(), second.y());
			if (ray.intersectsLine(side)) {
				hit++;
			}
		}
		Point2D p1 = _points.get(0);
		Point2D pLast = _points.get(_points.size()-1);
		Line2D.Double lastSide = new Line2D.Double(p1.x(), p1.y(), pLast.x(), pLast.y());
		if (ray.intersectsLine(lastSide)) hit++;
		return (hit%2!=0);
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
		for (Point2D p : _points) {
			p.move(vec);
		}
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		// Deep copy to ArrayLisy that contains Objects
		ArrayList<Point2D> copyPoints = new ArrayList();
		for (Point2D p : _points) {
			copyPoints.add(new Point2D(p));
		}
		return new Polygon2D(copyPoints);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		// TODO Auto-generated method stub
		for (Point2D p : _points) {
			p.scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		// TODO Auto-generated method stub
		for (Point2D p : _points) {
			p.rotate(center, angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		return (Point2D[]) _points.toArray();
	}
	////////////Private Functions////////////////
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
	///// Will be used for getting bounding box of shape collection

	public double getMinX() {
		double ans=0;
		double[] allX = this.getX();
		ans= allX[0];
		for (int i=0; i<allX.length; i++) {
			if (allX[i]<ans) ans=allX[i];
		}
		return ans;
	}
	public double getMaxX() {
		double ans=0;
		double[] allX = this.getX();
		ans= allX[0];
		for (int i=0; i<allX.length; i++) {
			if (allX[i]>ans) ans=allX[i];
		}
		return ans;
	}
	public double getMinY() {
		double ans=0;
		double[] allY = this.getY();
		ans= allY[0];
		for (int i=0; i<allY.length; i++) {
			if (allY[i]<ans) ans=allY[i];
		}
		return ans;
	}
	public double getMaxY() {
		double ans=0;
		double[] allY = this.getY();
		ans= allY[0];
		for (int i=0; i<allY.length; i++) {
			if (allY[i]>ans) ans=allY[i];
		}
		return ans;
	}
}
