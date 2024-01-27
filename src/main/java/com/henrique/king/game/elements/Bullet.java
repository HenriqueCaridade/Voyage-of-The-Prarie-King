package com.henrique.king.game.elements;

import com.henrique.king.game.Vector2D;

public class Bullet extends Element {
    private Vector2D dir;

    public Bullet(Vector2D pos, Vector2D direction) {
        this.pos = pos;
        this.dir = direction;
    }
    public void move(){
        pos = new Vector2D(dir.getX() + pos.getX(),dir.getY() + pos.getY());
    }
}
