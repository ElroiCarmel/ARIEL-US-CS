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
//    public double get_a() {
//        return this._a;
//    }
//    public double get_b() {
//        return this._b;
//    }
//    public double get_c() {
//        return this._c;
//    }
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

}
