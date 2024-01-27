package com.henrique.king.controller.game;

import com.henrique.king.game.Arena;
import com.henrique.king.Game;
import com.henrique.king.game.Vector2D;
import com.henrique.king.gui.GUI;

public class KingController extends GameController {
    private static final long moveTime = 100;
    private long lastMoveTime;

    public KingController(Arena arena) {
        super(arena);
        this.lastMoveTime = 0;
    }

    @Override
    public void tick(Game game, GUI.ACTION[] actions, long time) {
        for(GUI.ACTION action : actions)
            if(time - lastMoveTime > moveTime) {
                moveKing(action, time);
                break;
            }
    }

    void moveKing(GUI.ACTION action, long time) {
        Vector2D pos = getModel().getKing().getPos();
        switch (action){
            case UP:
                moveKingTo(pos.add(Vector2D.V_UP));
                break;
            case DOWN:
                moveKingTo(pos.add(Vector2D.V_DOWN));
                break;
            case LEFT:
                moveKingTo(pos.add(Vector2D.V_LEFT));
                break;
            case RIGHT:
                moveKingTo(pos.add(Vector2D.V_RIGHT));
                break;
            case UP_LEFT:
                moveKingTo(pos.add(Vector2D.V_UP_LEFT));
                break;
            case UP_RIGHT:
                moveKingTo(pos.add(Vector2D.V_UP_RIGHT));
                break;
            case DOWN_LEFT:
                moveKingTo(pos.add(Vector2D.V_DOWN_LEFT));
                break;
            case DOWN_RIGHT:
                moveKingTo(pos.add(Vector2D.V_DOWN_RIGHT));
                break;
            default:
                return;
        }
        lastMoveTime = time;
    }

    void moveKingTo(Vector2D pos){
        if(getModel().canMoveTo(pos))
            getModel().getKing().setPos(pos);
    }
}
