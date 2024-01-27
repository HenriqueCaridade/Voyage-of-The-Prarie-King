package com.henrique.king.game.elements;

import com.henrique.image.MyImage;
import com.henrique.image.MyTransparentImage;
import com.henrique.king.game.Vector2D;

public class King extends Element {
    private static final MyImage img = new MyTransparentImage("King.png");
    private static final long shottingSpeed = 300;
    private long lastShot;
    private int numberOfLives = 3;

    public King(Vector2D pos) {
        this.pos = pos;
        this.lastShot = System.currentTimeMillis();
    }

    public int getNumberOfLives(){
        return numberOfLives;
    }
    public void lostLives(int number){
        numberOfLives -= number;
        if(numberOfLives < 0)
            numberOfLives = 0;
    }
    public void increaseLife(){
        numberOfLives++;
    }
}
