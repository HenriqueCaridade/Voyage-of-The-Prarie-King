package com.henrique.king.controller.game;

import com.henrique.king.Game;
import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Bullet;
import com.henrique.king.game.elements.items.Wheel;
import com.henrique.king.gui.GUI;

import java.io.IOException;
import java.util.ArrayList;

public class BulletController extends GameController {
    private static final long moveTime = 100;
    private static final long shootTime = 300;
    private long lastMoveTime;
    private long lastShootTime;

    public BulletController(Arena arena) {
        super(arena);
        this.lastMoveTime = 0;
        this.lastShootTime = 0;
    }

    @Override
    public void tick(Game game, GUI.ACTION[] actions, long time) throws IOException {
        if(time - lastMoveTime > moveTime){
            moveBullets();
            lastMoveTime = time;
        }
        for(GUI.ACTION action : actions)
            if(checkShot(action, time)) {
                if (getModel().hasItemOf(Wheel.class)){
                    Vector2D kingPos = getModel().getKing().getPos();
                    addBullet(kingPos, Vector2D.V_UP);
                    addBullet(kingPos, Vector2D.V_DOWN);
                    addBullet(kingPos, Vector2D.V_LEFT);
                    addBullet(kingPos, Vector2D.V_RIGHT);
                    addBullet(kingPos, Vector2D.V_UP_LEFT);
                    addBullet(kingPos, Vector2D.V_UP_RIGHT);
                    addBullet(kingPos, Vector2D.V_DOWN_LEFT);
                    addBullet(kingPos, Vector2D.V_DOWN_RIGHT);
                } else
                    addBullets(action);
                break;
            }
    }

     void moveBullets(){
        ArrayList<Bullet> bullets = getModel().getBullets();
        for (int i = 0; i < bullets.size();){
            bullets.get(i).move();
            if(!getModel().inBounds(bullets.get(i).getPos())){
                bullets.remove(bullets.get(i));
            } else i++;
        }
        getModel().setBullets(bullets);
    }

    void addBullets(GUI.ACTION action){
        Vector2D pos = getModel().getKing().getPos();
        switch (action){
            case SHOOT_UP:
                addBullet(pos, Vector2D.V_UP);
                break;
            case SHOOT_DOWN:
                addBullet(pos, Vector2D.V_DOWN);
                break;
            case SHOOT_LEFT:
                addBullet(pos, Vector2D.V_LEFT);
                break;
            case SHOOT_RIGHT:
                addBullet(pos, Vector2D.V_RIGHT);
                break;
            case SHOOT_UP_LEFT:
                addBullet(pos, Vector2D.V_UP_LEFT);
                break;
            case SHOOT_UP_RIGHT:
                addBullet(pos, Vector2D.V_UP_RIGHT);
                break;
            case SHOOT_DOWN_LEFT:
                addBullet(pos, Vector2D.V_DOWN_LEFT);
                break;
            case SHOOT_DOWN_RIGHT:
                addBullet(pos, Vector2D.V_DOWN_RIGHT);
                break;
        }
    }

    boolean checkShot(GUI.ACTION action, long time){
        switch (action){
            case SHOOT_UP, SHOOT_DOWN, SHOOT_LEFT, SHOOT_RIGHT, SHOOT_UP_LEFT, SHOOT_UP_RIGHT, SHOOT_DOWN_LEFT, SHOOT_DOWN_RIGHT:
                if(time - lastShootTime > shootTime){
                    lastShootTime = time;
                    return true;
                }
        }
        return false;
    }

    void addBullet(Vector2D pos, Vector2D dir){
        getModel().addBullet(pos, dir);
    }
}
