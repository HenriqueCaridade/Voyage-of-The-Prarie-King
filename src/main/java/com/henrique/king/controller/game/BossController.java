package com.henrique.king.controller.game;

import com.henrique.king.Game;
import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Boss;
import com.henrique.king.game.elements.Element;
import com.henrique.king.game.elements.Monster;
import com.henrique.king.game.elements.items.Confuse;
import com.henrique.king.gui.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BossController extends GameController {
    private static final long moveSpeed = 300;
    private long lastMoveTime;
    public BossController(Arena arena) {
        super(arena);
        this.lastMoveTime = 0;
    }

    @Override
    public void tick(Game game, GUI.ACTION[] actions, long time) throws IOException {
        if(time - lastMoveTime > moveSpeed) {
            moveBoss();
            lastMoveTime = time;
        }
    }

    void moveBoss(){
        Boss boss = getModel().getBoss();
        if(boss == null) return;
        if(!boss.tick()) return;
        Vector2D pos = chooseDirection(boss);
        if(!getModel().canMoveTo(pos)) return;
        ArrayList<Monster> monsters = getModel().getMonsters();
        boss.setPos(pos);
        for (int i = 0; i < monsters.size();) {
            if (monsters.get(i).getPos().equals(pos)) {
                monsters.remove(i);
            } else i++;
        }
        getModel().setMonsters(monsters);
        getModel().setBoss(boss);
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
}

