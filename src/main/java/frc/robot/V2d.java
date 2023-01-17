package frc.robot;

public class V2d {
    public double x;
    public double y;


    public V2d() { }
    public V2d(double _x, double _y) { this.x = _x; this.y = _y; }

    public V2d add(V2d b) {
        return new V2d(this.x + b.x, this.y + b.y); }

    public V2d sub(V2d b) {
        return new V2d(this.x - b.x, this.y - b.y); }

    public V2d multiply(double s) {
        return new V2d(this.x * s, this.y * s); }

    public V2d divide(double s) {
        return new V2d(this.x / s, this.y / s); }

    public double length() {
        return Math.sqrt(this.x*this.x + this.y*this.y); }

    public V2d normalized() {
        double l = this.length();
        return l == 0? new V2d() : this.divide(this.length()); }

    public V2d rotateDegrees(double d) {
        double cos = Math.cos(d);
        double sin = Math.sin(d);

        return new V2d(
            this.x * cos - this.y * sin,
            this.y * cos + this.x * sin);
    }

    public static double cross(V2d a, V2d b){
        return a.x*b.y - a.y*b.x; }

    public static V2d cross(V2d a, double s) {
        return new V2d( s * a.y, -s * a.x ); }

    public static V2d cross(double s, V2d a ) {
        return new V2d( -s * a.y, s * a.x ); }

    public double dot(V2d b) {
        return this.x*b.x + this.y*b.y;
    }

    @Override
    public String toString() {
        return String.valueOf(this.x) + ", " + String.valueOf(this.y);
    }
}