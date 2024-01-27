package com.henrique.king.controller.game;


import com.henrique.king.game.Arena;
import com.henrique.king.Game;
import com.henrique.king.game.elements.Boss;
import com.henrique.king.game.elements.Bullet;
import com.henrique.king.game.elements.King;
import com.henrique.king.game.elements.Monster;
import com.henrique.king.gui.GUI;
import com.henrique.king.states.MenuState;
import com.henrique.king.viewer.menu.Menu;

import java.io.IOException;
import java.util.ArrayList;

public class ArenaController extends GameController {
    private static final long spawnTime = 600;
    KingController kingController;
    BossController bossController;
    MonsterController monsterController;
    BulletController bulletController;
    private long lastSpawnTime;

    public ArenaController(Arena arena) {
        super(arena);
        this.kingController = new KingController(arena);
        this.bossController = new BossController(arena);
        this.monsterController = new MonsterController(arena);
        this.bulletController = new BulletController(arena);
        this.lastSpawnTime = 0;
    }

    public void setKingController(KingController kingController) {
        this.kingController = kingController;
    }
    public void setBossController(BossController bossController) {
        this.bossController = bossController;
    }
    public void setBulletController(BulletController bulletController) {
        this.bulletController = bulletController;
    }
    public void setMonsterController(MonsterController monsterController) {
        this.monsterController = monsterController;
    }

    public void tick(Game game, GUI.ACTION[] actions, long time) throws IOException {
        boolean checkQuit = false;
        for(GUI.ACTION a : actions) if (a == GUI.ACTION.QUIT) { checkQuit = true; break; }
        if (checkQuit || getModel().getKing().getNumberOfLives() == 0)
            game.setState(new MenuState(new Menu()));
        else {
            kingController.tick(game, actions, time);
            monsterController.tick(game, actions, time);
            bossController.tick(game, actions, time);
            monsterHitKing();
            killMonster();
            bulletController.tick(game, actions, time);
            killMonster();
            getModel().checkItems();
            getModel().useItem();
            getModel().setScore();
            if(time - lastSpawnTime > spawnTime){
                getModel().spawnMonster();
                lastSpawnTime = time;
            }
        }
    }

    public void monsterHitKing() {
        Boss boss = getModel().getBoss();
        King king = getModel().getKing();
        if(boss != null)
            if(boss.getPos().equals(king.getPos()))
                king.lostLives(2);

        ArrayList<Monster> monsters = getModel().getMonsters();
        for(int i = 0; i < monsters.size();){
            if(monsters.get(i).getPos().equals(king.getPos())){
                king.lostLives(1);
                monsters.remove(i);
            } else i++;
        }
        getModel().setMonsters(monsters);
    }

    public void killMonster(){
        ArrayList<Bullet> bullets = getModel().getBullets();
        ArrayList<Monster> monsters = getModel().getMonsters();
        Boss boss = getModel().getBoss();
        for(int j = 0; j < bullets.size();) {
            if (boss == null) break;
            if (bullets.get(j).getPos().equals(boss.getPos())) {
                boss.lostLife();
                if (boss.getNumberOfLifes() == 0)
                    boss = null;
                bullets.remove(j);
            } else j++;
        }
        getModel().setBoss(boss);
        for(int i = 0; i < monsters.size();){
            boolean checkMonster = true;
            for(int j = 0; j < bullets.size(); j++){
                if(monsters.get(i).getPos().equals(bullets.get(j).getPos())){
                    bullets.remove(j);
                    getModel().dropItems(monsters.get(i).getPos());
                    getModel().bossSpawn(monsters.get(i).getPos());
                    monsters.remove(i);
                    checkMonster = false;
                    break;
                }
            }
            if(checkMonster) i++;
        }
        getModel().setBullets(bullets);
        getModel().setMonsters(monsters);
    }
}