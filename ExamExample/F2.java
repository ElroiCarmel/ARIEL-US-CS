import java.util.ArrayList;
import java.util.Collections;

public class F2 {
    //data
    private double _a;
    private double _b;
    private double _c;
    //constructor
    public F2(double a, double b, double c) {
        this._a=a;
        this._b=b;
        this._c=c;
    }
    //methods
    public double f(double x) {
        return _a*x*x+_b*x+_c;
    }
    public F2 add(F2 p) {
        return new F2(this._a+p._a, this._b+p._b, this._c+p._c);
    }
    public double[] get() {
        return new double[] {_a, _b, _c};
    }
    public double extreme(F2 p) {
       if (p._a==0) {
           throw new RuntimeException("The parabula has no extreme point!!");
       }
       return -p._b/(2*p._a);
    }
    public static int numberRoots(F2 p) {
        double delta = p._b*p._b-4*p._a*p._c;
        int ans = 0;
        if (delta>0) ans = 2;
        if (delta==0) ans = 1;
        return ans;
    }
    public static void sort(ArrayList<F2> l) {
        F2Comp nn = new F2Comp();
        Collections.sort(l, nn);
    }
    @Override
    public String toString() {
        String ans = "";
        ans = _a+"x^2+"+_b+"x+" +_c;
        return ans;
    }
}
