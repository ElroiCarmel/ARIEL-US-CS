
package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D point in the plane.
 * Do NOT change this class! It would be used as is for testing.
 * Ex4: you should edit and update this class!
 * @author boaz.benmoshe
 */


public class Point2D{
    //public static final double EPS1 = 0.001, EPS2 = Math.pow(EPS1,2), EPS=EPS2;
    public static final Point2D ORIGIN = new Point2D(0,0);
    private double _x,_y;
    public Point2D(double x,double y) {
    	_x=x; _y=y;
    }
    public Point2D(Point2D p) {
       this(p.x(), p.y());
    }
    public Point2D(String s) {
        try {
            String[] a = s.split(",");
            _x = Double.parseDouble(a[0]);
            _y = Double.parseDouble(a[1]);
        }
        catch(IllegalArgumentException e) {
            System.err.println("ERR: got wrong format string for Point2D init, got:"+s+"  should be of format: x,y");
            throw(e);
        }
    }
    public double x() {return _x;}
    public double y() {return _y;}
 
    public int ix() {return (int)_x;}
    public int iy() {return (int)_y;}
  
    public Point2D add(Point2D p) {
    	Point2D a = new Point2D(p.x()+x(),p.y()+y());
    	return a;
    }
    public String toString()
    {
        return _x+","+_y;
    }

    public double distance()
    {
        return this.distance(ORIGIN);
    }
    public double distance(Point2D p2)
    {
        double dx = this.x() - p2.x();
        double dy = this.y() - p2.y();
        double t = (dx*dx+dy*dy);
        return Math.sqrt(t);
    }
    @Override
    public boolean equals(Object p)
    {
        if(p==null || !(p instanceof Point2D)) {return false;}
        Point2D p2 = (Point2D)p;
        return ( (_x==p2._x) && (_y==p2._y));
    }
    public boolean close2equals(Point2D p2, double eps)
    {
        return ( this.distance(p2) < eps );
    }
    public boolean close2equals(Point2D p2)
    {
        return close2equals(p2, Ex4_Const.EPS);
    }
    /**
     * This method returns the vector between this point and the target point. The vector is represented as a Point2D.
     * @param target
     * @return
     */
    public Point2D vector(Point2D target) {
    	double dx = target.x() - this.x();
    	double dy = target.y() - this.y();
    	return new Point2D(dx,dy);
    }
	
	public void move(Point2D vec) {
		this._x += vec.x();
		this._y += vec.y();
	}
	
	/////////////////////// You should implement the methods below ///////////////////////////
	public void scale(Point2D cen, double ratio) {
		//////////add your code below ///////////
		Point2D secondVector = new Point2D(cen.vector(this));
		secondVector.multiply(ratio);
		this._x = cen.x()+secondVector.x();
		this._y = cen.y()+secondVector.y();
		/////////////////////////////////////////
	}
	public void multiply(double skalar) {
		this._x *= skalar;
		this._y *= skalar;
	}
	public double getAngleFromPoints(Point2D p) {
		double dx = p.x()-this.x();
		double dy = p.y()-this.y();
		return Math.atan2(dy, dx);
		
	}
	
	public void rotate(Point2D cen, double angleDegrees) {
		//////////add your code below ///////////
		// !! Remember java works in radians!
		angleDegrees=Math.toRadians(angleDegrees);
		/* https://matthew-brett.github.io/teaching/rotation_2d.html
		 * Formula:
		 * x2 = cos(alpha)*x1 - sin(alpah)*y1
		 * y2 = sin(alpah)*x1 + cos(alpha)*y1
		 */
		// Compute vector from center to src
		Point2D vector = cen.vector(this);
		// Rotate vector using formula
		double x2 = Math.cos(angleDegrees)*vector.x() - Math.sin(angleDegrees)*vector.y();
		double y2 = Math.sin(angleDegrees)*vector.x() + Math.cos(angleDegrees)*vector.y();
		Point2D rotatedVector = new Point2D(x2,y2);
		// Get final vector [center+rotatedVector]
		Point2D ans = new Point2D(cen.add(rotatedVector));
		this._x=ans._x;
		this._y=ans._y;
		/////////////////////////////////////////
	}
   
}
