package com.henrique.king.viewer.game;

import com.henrique.image.MyImage;
import com.henrique.image.MyTransparentImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Boss;
import com.henrique.king.gui.GUI;

public class BossViewer implements ElementViewer<Boss> {
    private static final MyImage bossImg = new MyTransparentImage("boss.png");
    @Override
    public void draw(Boss boss, GUI gui, Vector2D offset) {
        if(boss == null) return;
        gui.drawImage(bossImg, boss.getPos().mult(16).add(offset));
    }
}
