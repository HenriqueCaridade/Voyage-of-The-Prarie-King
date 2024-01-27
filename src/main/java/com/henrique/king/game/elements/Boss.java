package com.henrique.king.game.elements;

import com.henrique.image.MyImage;
import com.henrique.image.MyTransparentImage;
import com.henrique.king.game.Vector2D;

public class Boss extends Element {
    private static final MyImage img = new MyTransparentImage("boss.png");
    public static final int ticksToMove = 4;
    private int numberOfLifes = 3;
    private int ticks = 0;

    public Boss(Vector2D pos) {
        this.pos = pos;
    }

    public boolean tick(){
        ticks++;
        if(ticks == ticksToMove) {
            ticks = 0;
            return true;
        }
        return false;
    }

    public int getNumberOfLifes(){return numberOfLifes;}
    public void lostLife(){numberOfLifes--;}
}
