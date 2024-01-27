package com.henrique.king.viewer.game;


import com.henrique.image.MyImage;
import com.henrique.image.MyTransparentImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Monster;
import com.henrique.king.gui.GUI;

public class MonsterViewer implements ElementViewer<Monster> {
    private static final MyImage monsterImg = new MyTransparentImage("Monster.png");
    @Override
    public void draw(Monster monster, GUI gui, Vector2D offset) {
        gui.drawImage(monsterImg, monster.getPos().mult(16).add(offset));
    }
}
