package com.henrique.king.viewer.game;

import com.henrique.image.MyImage;
import com.henrique.image.MyTransparentImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Bullet;
import com.henrique.king.gui.GUI;

public class BulletViewer implements ElementViewer<Bullet> {
    private static final MyImage bulletImg = new MyTransparentImage("bullet.png");
    @Override
    public void draw(Bullet bullet, GUI gui, Vector2D offset) {
        gui.drawImage(bulletImg, bullet.getPos().mult(16).add(offset));
    }
}