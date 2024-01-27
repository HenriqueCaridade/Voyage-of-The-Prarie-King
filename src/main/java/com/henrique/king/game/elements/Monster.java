package com.henrique.king.game.elements;

import com.henrique.image.MyImage;
import com.henrique.image.MyTransparentImage;
import com.henrique.king.game.Vector2D;

public class Monster extends Element {
    private static final MyImage img = new MyTransparentImage("Monster.png");
    public static final int ticksToMove = 6;
    private int ticks = 0;

    public Monster(Vector2D pos) {
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
}
