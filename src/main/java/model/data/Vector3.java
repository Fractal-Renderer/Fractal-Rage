package model.data;

public class Vector3 {

    public float x;
    public float y;
    public float z;

    public int side;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3 multiply(Vector3 b) { //crossproduct
        Vector3 c = new Vector3(0,0,0);
        c.setX(this.getY()*b.getY() - this.getZ()*b.getY());
        c.setY(this.getZ()*b.getX() - this.getX()*b.getZ());
        c.setZ(this.getX()*b.getY() - this.getY()*b.getX());
        return c;
    }

    public Vector3 multiply(float b) { //crossproduct
        long stthisrt = System.currentTimeMillis();
        Vector3 c = new Vector3(0,0,0);
        c.setX(b * this.getX());
        c.setY(b * this.getY());
        c.setZ(b* this.getZ());
        return c;
    }
    public Vector3 add(Vector3 b) {
        Vector3 c = new Vector3(0,0,0);
        c.setX(this.getX() + b.getX());
        c.setY(this.getY() + b.getY());
        c.setZ(this.getZ() + b.getZ());
        return c;
    }

    public Vector3 add(float b) {
        Vector3 c = new Vector3(0,0,0);
        c.setX(this.getX() + b);
        c.setY(this.getY() + b);
        c.setZ(this.getZ() + b);
        return c;
    }

    public Vector3 sub(Vector3 b) {
        Vector3 c = new Vector3(0,0,0);
        c.setX(this.getX() - b.getX());
        c.setY(this.getY() - b.getY());
        c.setZ(this.getZ() - b.getZ());
        return c;
    }
    public Vector3 subInvert(float b) {
        Vector3 c = new Vector3(0,0,0);
        c.setX(b - this.getX());
        c.setY(b - this.getY());
        c.setZ(b - this.getZ());
        return c;
    }

    public Vector3 sub(float b) {
        Vector3 c = new Vector3(0,0,0);
        c.setX(this.getX() - b);
        c.setY(this.getY() - b);
        c.setZ(this.getZ() - b);
        return c;
    }

    public float mod() {
        return (float) Math.sqrt((Math.pow(this.getX(), 2.0f) + Math.pow(this.getY(), 2.0f) + Math.pow(this.getZ(), 2.0f)));
    }

    public Vector3 abs() {
        return new Vector3(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
    }

    public Vector3 max(float b) {
        return new Vector3(Math.max(this.x, b), Math.max(this.y, b), Math.max(this.z, b));
    }

    public Vector3 div(float b) {
        return new Vector3(this.x / b, this.y / b, this.z / b);
    }

    public Vector3 min(float b) {
        return new Vector3(Math.min(this.x, b), Math.min(this.y, b), Math.min(this.z, b));
    }

    public Vector3 pow(float p) {
        return new Vector3((float) Math.pow(this.x, p), (float) Math.pow(this.y, p), (float) Math.pow(this.z, p));
    }
    public Vector3 exp() {
        float mod = this.mod();
        Vector3 exp = (this.div(mod).multiply((float) Math.sin(mod))).add((float) Math.cos(mod));
        return exp;
    }

    public Vector3 normalize() {
        Vector3 c = new Vector3(0,0,0);
        float l = this.mod();
        c.setX(this.getX() / l);
        c.setY(this.getY() / l);
        c.setZ(this.getZ() / l);
        return c;
    }

    public float dot(Vector3 b) {
        return (this.getX() * b.getX()) + (this.getY() * b.getY()) + (this.getZ() * b.getZ());
    }

    public Vector3 multiply(Vector3 a, float b) { //crossproduct
        long start = System.currentTimeMillis();
        Vector3 c = new Vector3(0,0,0);
        c.setX(b * a.getX());
        c.setY(b * a.getY());
        c.setZ(b* a.getZ());
        return c;
    }
    public Vector3 add(Vector3 a, Vector3 b) {
        Vector3 c = new Vector3(0,0,0);
        c.setX(a.getX() + b.getX());
        c.setY(a.getY() + b.getY());
        c.setZ(a.getZ() + b.getZ());
        return c;
    }
    public Vector3 sub(Vector3 a, Vector3 b) {
        Vector3 c = new Vector3(0,0,0);
        c.setX(a.getX() - b.getX());
        c.setY(a.getY() - b.getY());
        c.setZ(a.getZ() - b.getZ());
        return c;
    }
    public Vector3 sub(Vector3 a, float b) {
        Vector3 c = new Vector3(0,0,0);
        c.setX(b - a.getX());
        c.setY(b - a.getY());
        c.setZ(b - a.getZ());
        return c;
    }
    public float mod(Vector3 a) {
        return (float) Math.sqrt((Math.pow(a.getX(), 2.0f) + Math.pow(a.getY(), 2.0f) + Math.pow(a.getZ(), 2.0f)));
    }
    public Vector3 normalize(Vector3 v) {
        float l = v.mod(v);
        v.setX(v.getX() / l);
        v.setY(v.getY() / l);
        v.setZ(v.getZ() / l);
        return v;
    }

    public float dot(Vector3 a, Vector3 b) {
        return (a.getX() * b.getX()) + (a.getY() * b.getY()) + (a.getZ() * b.getZ());
    }

    public Vector3 clamp(float min, float max) {
        Vector3 c = new Vector3();
        c.x = Math.max(min, Math.min(max, x));
        c.y = Math.max(min, Math.min(max, y));
        c.z = Math.max(min, Math.min(max, z));
        return c;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}