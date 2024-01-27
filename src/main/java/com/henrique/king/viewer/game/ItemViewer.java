package com.henrique.king.viewer.game;

import com.henrique.image.MyImage;
import com.henrique.image.MyTransparentImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.items.Confuse;
import com.henrique.king.game.elements.items.Heart;
import com.henrique.king.game.elements.items.Item;
import com.henrique.king.game.elements.items.Wheel;
import com.henrique.king.gui.GUI;

public class ItemViewer implements ElementViewer<Item> {
    private static final MyImage wheelImg = new MyTransparentImage("Wheel.png");
    private static final MyImage confuseImg = new MyTransparentImage("confuse.png");
    private static final MyImage heartImg = new MyTransparentImage("heart8.png");
    @Override
    public void draw(Item item, GUI gui, Vector2D offset) {
        Vector2D pos = item.getPos().mult(16).add(offset);
        if(item instanceof Wheel)
            gui.drawImage(wheelImg, pos);
        else if(item instanceof Confuse)
            gui.drawImage(confuseImg, pos);
        else if(item instanceof Heart)
            gui.drawImage(heartImg, pos);
    }
}
