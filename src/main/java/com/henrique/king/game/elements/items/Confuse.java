package com.henrique.king.game.elements.items;

import com.henrique.image.MyImage;
import com.henrique.image.MyTransparentImage;
import com.henrique.king.game.Vector2D;

public class Confuse extends Item {
    private static final long duration = 12000;
    private static final MyImage img = new MyTransparentImage("confuse.png");

    public Confuse(Vector2D pos) {
        super(duration);
        this.pos = pos;
    }
}
