package com.henrique.king.game;

public class Vector2D {
    public static final Vector2D V_NULL = new Vector2D(0, 0);
    public static final Vector2D V_UP = new Vector2D(0, -1);
    public static final Vector2D V_DOWN = new Vector2D(0, 1);
    public static final Vector2D V_LEFT = new Vector2D(-1, 0);
    public static final Vector2D V_RIGHT = new Vector2D(1, 0);
    public static final Vector2D V_UP_LEFT = new Vector2D(-1, -1);
    public static final Vector2D V_UP_RIGHT = new Vector2D(1, -1);
    public static final Vector2D V_DOWN_LEFT = new Vector2D(-1, 1);
    public static final Vector2D V_DOWN_RIGHT = new Vector2D(1, 1);
    private int x, y;
    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Vector2D(Vector2D a){
        x = a.getX();
        y = a.getY();
    }
    public String toString(){
        return "("+x+","+y+")";
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int a) {
        x = a;
    }
    public void setY(int b) {
        y = b;
    }
    public boolean equals(Object a) {
        if (this == a) return true;
        if (a == null) return false;
        if (getClass() != a.getClass()) return false;
        Vector2D p = (Vector2D) a;
        return x == p.getX() && y == p.getY();
    }
    public Vector2D add(Vector2D pos) {
        return new Vector2D(this.x + pos.x, this.y + pos.y);
    }
    public Vector2D sub(Vector2D pos) {
        return new Vector2D(this.x - pos.x, this.y - pos.y);
    }
    public Vector2D mult(int a){ return  new Vector2D(this.x * a, this.y * a); }
    public Vector2D euclideanNormalise(){
        return new Vector2D((int) Math.signum(x), (int) Math.signum(y));
    }
}
