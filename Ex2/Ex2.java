package Ex2;
/** 
 * This class represents a set of functions on a polynom - represented as array of doubles.
 * In general, such an array {-1,2,3.1} represents the following polynom 3.1x^2+2x-1=0,
 * The index of the entry represents the power of x.
 * 
 * Your goal is to complete the functions below, see the marking: // *** add your code here ***
 *
 * @author boaz.benmoshe
 *
 */
public class Ex2 {
	/** Epsilon value for numerical computation, it serves as a "close enough" threshold. */
	public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
	/** The zero polynom is represented as an array with a single (0) entry. */
	public static final double[] ZERO = {0};
	
	/** Two polynoms are equal if and only if the have the same coefficients - up to an epsilon (aka EPS) value.
	 * @param p1 first polynom
	 * @param p2 second polynom
	 * @return true iff p1 represents the same polynom as p2.
	 */
	public static boolean equals(double[] p1, double[] p2) {
		boolean ans = true;
		if (p1.length==p2.length) {
			for (int i=0; i<p1.length; i++) {
				if (Math.abs(p1[i]-p2[i]) >= EPS) {
					ans = false;
					break;
				}
			} 
		} else {
			ans = false;
		}
		return ans;
	}
	/**
	 * Computes the f(x) value of the polynom at x.
	 * @param poly
	 * @param x
	 * @return f(x) - the polynom value at x.
	 */
	public static double f(double[] poly, double x) {
		double ans = 0;
		for (int i=0; i<poly.length; i++) {
			ans = ans + poly[i]*Math.pow(x, i);
		}
		return ans;
	}
	/** 
	 * Computes a String representing the polynom.
	 * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
	 * @param poly the polynom represented as an array of doubles
	 * @return String representing the polynom: 
	 */
	public static String poly(double[] poly) {
		String ans = "";
		for (int i=poly.length-1; i>=0; i--) {
			if (i==poly.length-1) {
				ans = poly[i] + "x^" + i;
			} else if (i<poly.length && i>0) {
				if (poly[i]>0) {
					ans = ans + " +" + poly[i] + "x^" + i;
				}
				if (poly[i]<0) {
					ans = ans + " " + poly[i] + "x^" + i;
				}
			} else {
				if (poly[i]>0) {
					ans = ans + " +" + poly[i];
				}
				if (poly[i]<0) {
					ans = ans + " " + poly[i];
				}
			}
		}
		return ans;
	}

	/**
	 * Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x2) <= 0. 
	 * This function should be implemented iteratively (none recursive).
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		if (Math.abs(f(p, x12))<eps) {
			return x12;
		}
		/**
		 * I checked whether the f values of polynom in the given range
		 * goes from - to + or the opposite, because it affects the decision
		 * whether to move towards x1 or towards x2.
		 */
		if (f(p, x1)<0) {
			while (Math.abs(f(p, x12))>=eps) {
				if (f(p, x12)<0) {
					x1 = x12;
					x12 = (x1+x2)/2;
				} else {
					x2 = x12;
					x12 = (x1+x2)/2;
				}
			}
		} else if (f(p, x1)>0) {
			while (Math.abs(f(p, x12))>=eps) {
				if (f(p, x12)<0) {
					x2 = x12;
					x12 = (x1+x2)/2;
				} else {
					x1 = x12;
					x12 = (x1+x2)/2;
				}
			}
		}
		// *** add your code here ***
		
		// **************************
		return x12;
	}
	/** Given a polynom (p), a range [x1,x2] and an epsilon eps. 
	 * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps, 
	 * assuming p(x1)*p(x2) <= 0. 
	 * This function should be implemented recursivly.
	 * @param p - the polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
	 */
	public static double root_rec(double[] p, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		if (Math.abs(f(p, x12))<eps) {
			return x12;
		} else {
			if (f(p, x1)<0) {
				if (f(p, x12)<0) {
					return root_rec(p, x12, x2, eps);
				} else {
					return root_rec(p, x1, x12, eps);
				}
			} else {
				if (f(p, x12)<0) {
					return root_rec(p, x1, x12, eps);
				} else {
					return root_rec(p, x12, x2, eps);
				}
			}
			
		}		
		// **************************
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
	 * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
	 * @return an x value (x1<=x<=x2) for which |p1(x) -p2(x)| < eps.
	 */
	public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
		double x12 = (x1+x2)/2;
		if (Math.abs(f(p1, x12)-f(p2, x12))<eps) {
			return x12;
		}
		if (f(p1, x1)>f(p2, x1)) {
			while (Math.abs(f(p1, x12)-f(p2, x12))>=eps) {
				if (f(p2, x12)>f(p1, x12)) {
					x2 = x12;
					x12 = (x1+x2)/2;
				} else {
					x1 = x12;
					x12 = (x1+x2)/2;
				}
			}
		} else {
			while (Math.abs(f(p1, x12)-f(p2, x12))>=eps) {
				if (f(p2, x12)>f(p1, x12)) {
					x1 = x12;
					x12 = (x1+x2)/2;
				} else {
					x2 = x12;
					x12 = (x1+x2)/2;
				}
			}
		}
		return x12;
	}
	/**
	 * Given two polynoms (p1,p2), a range [x1,x2] and an integer representing the number of "boxes". 
	 * This function computes an approximation of the area between the polynoms within the x-range.
	 * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
	 * @param p1 - first polynom
	 * @param p2 - second polynom
	 * @param x1 - minimal value of the range
	 * @param x2 - maximal value of the range
	 * @param numberOfBoxes - a natural number representing the number of boxes between x1 and x2.
	 * @return the approximated area between the two polynoms within the [x1,x2] range.
	 */
	public static double area(double[] p1,double[]p2, double x1, double x2, int numberOfBoxes) {
		double ans = 0;
		double width = (x2-x1)/numberOfBoxes;
		for (double i=x1; i<x2; i+= width) {
			ans+= width * Math.abs(f(p1, i)-f(p2, i));
		}
		return ans;
	}
	/**
	 * This function computes the array representation of a polynom from a String
	 * representation. Note:given a polynom represented as a double array,  
	 * getPolynomFromString(poly(p)) should return an array equals to p.
	 * 
	 * @param p - a String representing polynom.
	 * @return
	 * 
	 * I made 2 assist functions. See documentation for them at the bottom.
	 */
	public static double[] getPolynomFromString(String p) {
		String[] polyTemp = p.split(" ");
		double[] polyArr = new double[getPower(polyTemp[0])+1];
		for (int i=0; i<polyTemp.length; i++) {
			polyArr[getPower(polyTemp[i])] = getCoefficient(polyTemp[i]);
		}
		return polyArr;
	}
	/**
	 * This function computes the polynom which is the sum of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] add(double[] p1, double[] p2) {
		// *** add your code here ***
		double[] poSum = null;
		//In case the polynom p1 is longer than p1 or equal
		if (p1.length>=p2.length) {
			poSum = p1.clone();
			for (int i=0; i<p2.length; i++) {
				poSum[i]+=p2[i];
			}
		//In case p2 is longer
		} else {
			poSum = p2.clone();
			for (int i=0; i<p1.length; i++) {
				poSum[i]+=p1[i];
			}
		}
		return poSum;
		// **************************
	}
	/**
	 * This function computes the polynom which is the multiplication of two polynoms (p1,p2)
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] mul(double[] p1, double[] p2) {
		/* Because the last variable in p1 and the last variable in p2
		 * represents the greatest "power" in each polynom we know the result's
		 * length should be the sum of their length minus 1 (because we start with 0).
		 * 
		 *  Then we iterate through one polynom and multiply the second with
		 *  each variable of the first while each time saving the result at
		 *  the right location (i+j). And adding the result to the last result
		 *  of the multiplication.
		 *  
		 */
		double[] ans = new double[p1.length+p2.length-1];
		for (int i=0; i<p1.length; i++) {
			for (int j=0; j<p2.length; j++) {
				ans[i+j] = ans[i+j]+p1[i]*p2[j];
			}
		}
		return ans;
	}
	/**
	 * This function computes the derivative polynom of po.
	 * @param po
	 * @return
	 */
	public static double[] derivative (double[] po) {
		// *** add your code here ***
		double[] poDerivative = new double[po.length-1];
		for (int i=1; i<po.length; i++) {
			poDerivative[i-1] = i * po[i];
		}
		return poDerivative;
		// **************************
	}
	/**
	 * This function computes a polynomial representation from a set of 2D points on the polynom.
	 * Note: this function only works for a set of points containing three points, else returns null.
	 * @param xx
	 * @param yy
	 * @return an array of doubles representing the coefficients of the polynom.
	 * Note: you can assume xx[0]!=xx[1]!=xx[2]
	 */
	public static double[] PolynomFromPoints(double[] xx, double[] yy) {
		/*
		 * Since we have 3 points we can set 3 equations:
		 * 
		 * a*x1^2+b*x1 = c
		 * a*x2^2+b*x2 = c
		 * a*x3^2+b*x3 = c
		 * 
		 * The solution for the equation system is a, b, c, which are
		 * the coefficients of the polynom. The equations for a, b, c were
		 * taken from "https://math.stackexchange.com/questions/680646/get-polynomial-function-from-3-points"
		 */
		double [] ans = null;
		if(xx!=null && yy!=null && xx.length==3 && yy.length==3) {
			// *** add your code here ***
			ans = new double[3];
			double x1 = xx[0], y1 = yy[0];
			double x2 = xx[1], y2 = yy[1];
			double x3 = xx[2], y3 = yy[2];
			double a = (x1*(y3-y2)+x2*(y1-y3)+x3*(y2-y1))/((x1-x2)*(x1-x3)*(x2-x3));
			double b = (y2-y1)/(x2-x1)- a*(x1+x2);
			double c = y1-a*Math.pow(x1, 2)-b*x1;
			ans[0] = c;
			ans[1] = b;
			ans[2] = a;
			// **************************
		}
		return ans;
	}
	///////////////////// Private /////////////////////
	// you can add any additional functions (private) below
	/**
	 * These 2 functions assists the function "getPolynomFromString".
	 * The functions accepts a string which is a part of the whole
	 * polynom. Example: "-4x^3".
	 * The "getCoefficient" returns -4.
	 * The "getPower" returns 3.
	 */
	public static double getCoefficient(String s) {
		double coEfficient;
		if (s.indexOf("x")==-1) {
			coEfficient = Double.parseDouble(s);
		} else {
			coEfficient = Double.parseDouble(s.substring(0, s.indexOf("x")));
		}
		return coEfficient;
	}
	public static int getPower(String s) {
		int power;
		if (s.indexOf("x")==-1) {
			power = 0;
		} else {
			power = Integer.parseInt(s.substring(s.indexOf("x")+2, s.length()));
		}
		return power;
	}
}
