package com.henrique.king.gui;

import com.henrique.image.MyImage;
import com.henrique.king.game.Vector2D;

import java.io.IOException;

public interface GUI {

    ACTION[] getNextActions() throws IOException;

    void drawImage(MyImage img, Vector2D pos);
    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, SHOOT_UP, SHOOT_RIGHT, SHOOT_DOWN, SHOOT_LEFT, SHOOT_UP_LEFT, SHOOT_UP_RIGHT, SHOOT_DOWN_LEFT, SHOOT_DOWN_RIGHT, NONE, QUIT, SELECT}
}
