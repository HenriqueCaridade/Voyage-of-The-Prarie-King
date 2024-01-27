package com.henrique.king.viewer.game;

import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Element;
import com.henrique.king.gui.GUI;

public interface ElementViewer<T extends Element> {
    void draw(T element, GUI gui, Vector2D offset);
}
