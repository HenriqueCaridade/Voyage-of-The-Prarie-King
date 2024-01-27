package com.henrique.king.viewer.game;


import com.henrique.image.MyImage;
import com.henrique.image.MyTransparentImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.King;
import com.henrique.king.gui.GUI;

public class KingViewer implements ElementViewer<King> {
    private static final MyImage kingImg = new MyTransparentImage("King.png");
    @Override
    public void draw(King king, GUI gui, Vector2D offset) {
        gui.drawImage(kingImg, king.getPos().mult(16).add(offset));
    }
}
