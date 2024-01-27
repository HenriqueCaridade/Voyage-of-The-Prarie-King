package com.henrique.king.controller.menu;


import com.henrique.king.controller.Controller;
import com.henrique.king.Game;
import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;
import com.henrique.king.gui.GUI;
import com.henrique.king.states.GameState;
import com.henrique.king.viewer.menu.Menu;

import java.io.IOException;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void tick(Game game, GUI.ACTION[] actions, long time) throws IOException {
        for (GUI.ACTION action : actions){
            switch (action) {
                case UP, UP_LEFT, UP_RIGHT:
                    getModel().previousEntry();
                    break;
                case DOWN, DOWN_LEFT, DOWN_RIGHT:
                    getModel().nextEntry();
                    break;
                case SELECT:
                    if (getModel().isSelectedExit()) game.setState(null);
                    if (getModel().isSelectedPlay())
                        game.setState(new GameState(new Arena(16, 16, new Vector2D(16, 16), 1)));
                default:
                    continue;
            }
            break;
        }
    }
}
