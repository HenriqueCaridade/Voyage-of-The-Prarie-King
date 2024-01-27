package com.henrique.king.controller.game;

import com.henrique.king.game.Arena;
import com.henrique.king.Game;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Boss;
import com.henrique.king.game.elements.Element;
import com.henrique.king.game.elements.Monster;
import com.henrique.king.game.elements.items.Confuse;
import com.henrique.king.gui.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MonsterController extends GameController {
    private static final long moveSpeed = 400;
    private long lastMoveTime;
    public MonsterController(Arena arena) {
        super(arena);
        this.lastMoveTime = 0;
    }

    @Override
    public void tick(Game game, GUI.ACTION[] actions, long time) throws IOException {
        if(time - lastMoveTime > moveSpeed) {
            moveMonsters();
            lastMoveTime = time;
        }
    }

     void moveMonsters(){
        ArrayList<Monster> monsters = getModel().getMonsters();
        for(Monster m : monsters){
            if(!m.tick()) continue;
            Vector2D pos = chooseDirection(m);
            if (getModel().canMoveTo(pos) && !monsterCollision(monsters, pos)){
                m.setPos(pos);
            }
        }
    }

    public Vector2D chooseDirection(Element m) {
        if(getModel().hasItemOf(Confuse.class)) {
            switch (new Random().nextInt(4)) {
                case 0:
                    return new Vector2D(m.getPos().getX() - 1, m.getPos().getY());
                case 1:
                    return new Vector2D(m.getPos().getX() + 1, m.getPos().getY());
                case 2:
                    return new Vector2D(m.getPos().getX(), m.getPos().getY() - 1);
                case 3:
                    return new Vector2D(m.getPos().getX(), m.getPos().getY() + 1);
            }
        }
        Vector2D pos = getModel().getKing().getPos().sub(m.getPos());
        if (Math.random() * Math.abs(pos.getX()) >= Math.abs(pos.getY()))
            return new Vector2D(m.getPos().getX() + (int) Math.signum(pos.getX()), m.getPos().getY());
        else
            return new Vector2D(m.getPos().getX(), m.getPos().getY() + (int) Math.signum(pos.getY()));
    }

    public boolean monsterCollision(ArrayList<Monster> monsters, Vector2D pos){
        for(Element m : monsters)
            if (m.getPos().equals(pos))
                return true;
        return false;
    }
}
