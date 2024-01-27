package com.henrique.king.game.elements.items;

import com.henrique.image.MyImage;
import com.henrique.image.MyTransparentImage;
import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;

public class Heart extends Item {
    private static final long duration = 0;
    private static final MyImage img = new MyTransparentImage("heart8.png");
    public Heart(Vector2D pos){
        super(duration);
        this.pos = pos;
    }
    @Override
    public void use(Arena arena){
        super.use(arena);
        if(arena.getKing().getNumberOfLives() < 5){
            arena.getKing().increaseLife();
        }
    }
}
