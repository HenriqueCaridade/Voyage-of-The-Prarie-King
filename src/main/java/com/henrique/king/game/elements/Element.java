package com.henrique.king.game.elements;

import com.henrique.king.game.Vector2D;

abstract public class Element {
    protected Vector2D pos;
    public void setPos(Vector2D p){
        pos = p;
    }
    public Vector2D getPos(){
        return pos;
    }
}
