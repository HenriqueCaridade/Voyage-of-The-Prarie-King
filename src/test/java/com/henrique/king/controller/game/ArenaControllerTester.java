package com.henrique.king.controller.game;

import com.henrique.king.Game;
import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Boss;
import com.henrique.king.game.elements.Bullet;
import com.henrique.king.game.elements.King;
import com.henrique.king.game.elements.Monster;
import com.henrique.king.gui.GUI;
import com.henrique.king.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaControllerTester {
    Game game;
    Boss boss;
    King king;
    Arena arena;
    ArenaController arenaController;

    @BeforeEach
    void setup(){
        game = Mockito.mock(Game.class);
        arena = Mockito.mock(Arena.class);
        boss = Mockito.mock(Boss.class);
        king = Mockito.mock(King.class);
        Mockito.when(arena.getBoss()).thenReturn(boss);
        Mockito.when(arena.getKing()).thenReturn(king);
        arenaController = Mockito.spy(new ArenaController(arena));
    }

    @Test
    void tickTest() throws IOException {
        KingController kingController = Mockito.mock(KingController.class);
        BossController bossController = Mockito.mock(BossController.class);
        MonsterController monsterController = Mockito.mock(MonsterController.class);
        BulletController bulletController = Mockito.mock(BulletController.class);
        arenaController.setKingController(kingController);
        arenaController.setBossController(bossController);
        arenaController.setMonsterController(monsterController);
        arenaController.setBulletController(bulletController);
        Mockito.when(boss.getPos()).thenReturn(Vector2D.V_NULL);

        Mockito.when(king.getNumberOfLives()).thenReturn(3);

        arenaController.tick(game, new GUI.ACTION[]{GUI.ACTION.UP}, 400);

        Mockito.verify(kingController, Mockito.times(1)).tick(Mockito.same(game), Mockito.any(GUI.ACTION[].class), Mockito.anyLong());
        Mockito.verify(monsterController, Mockito.times(1)).tick(Mockito.same(game), Mockito.any(GUI.ACTION[].class), Mockito.anyLong());
        Mockito.verify(bossController, Mockito.times(1)).tick(Mockito.same(game), Mockito.any(GUI.ACTION[].class), Mockito.anyLong());
        Mockito.verify(bulletController, Mockito.times(1)).tick(Mockito.same(game), Mockito.any(GUI.ACTION[].class), Mockito.anyLong());
        Mockito.verify(arenaController, Mockito.times(1)).monsterHitKing();
        Mockito.verify(arenaController, Mockito.times(2)).killMonster();
        Mockito.verify(arena, Mockito.times(0)).spawnMonster();

        arenaController.tick(game, new GUI.ACTION[]{GUI.ACTION.DOWN}, 800);

        Mockito.verify(kingController, Mockito.times(2)).tick(Mockito.same(game), Mockito.any(GUI.ACTION[].class), Mockito.anyLong());
        Mockito.verify(monsterController, Mockito.times(2)).tick(Mockito.same(game), Mockito.any(GUI.ACTION[].class), Mockito.anyLong());
        Mockito.verify(bossController, Mockito.times(2)).tick(Mockito.same(game), Mockito.any(GUI.ACTION[].class), Mockito.anyLong());
        Mockito.verify(bulletController, Mockito.times(2)).tick(Mockito.same(game), Mockito.any(GUI.ACTION[].class), Mockito.anyLong());
        Mockito.verify(arenaController, Mockito.times(2)).monsterHitKing();
        Mockito.verify(arenaController, Mockito.times(4)).killMonster();
        Mockito.verify(arena, Mockito.times(1)).spawnMonster();
    }

    @Test
    void tickQUITTest() throws IOException {
        arenaController.tick(game, new GUI.ACTION[]{GUI.ACTION.QUIT}, 1000);
        Mockito.verify(game, Mockito.times(1)).setState(Mockito.any(State.class));
    }

    @Test
    void monsterHitKingTest(){
        Mockito.when(boss.getPos()).thenReturn(Vector2D.V_NULL);
        Mockito.when(king.getPos()).thenReturn(new Vector2D(2,2));
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(Mockito.mock(Monster.class));
        monsters.add(Mockito.mock(Monster.class));
        monsters = Mockito.spy(monsters);
        Mockito.when(monsters.get(0).getPos()).thenReturn(new Vector2D(2,2));
        Mockito.when(monsters.get(1).getPos()).thenReturn(new Vector2D(3,3));
        Mockito.when(arena.getMonsters()).thenReturn(monsters);

        arenaController.monsterHitKing();

        Mockito.verify(king, Mockito.times(1)).lostLives(1);
        Mockito.verify(monsters, Mockito.times(1)).remove(Mockito.anyInt());
        Mockito.verify(arena, Mockito.times(1)).setMonsters(Mockito.any(ArrayList.class));
    }

    @Test
    void bossHitKingTest(){
        Mockito.when(boss.getPos()).thenReturn(new Vector2D(2,2));
        Mockito.when(king.getPos()).thenReturn(new Vector2D(2,2));

        arenaController.monsterHitKing();

        Mockito.verify(king, Mockito.times(1)).lostLives(2);
    }

    @Test
    void killMonsterTest(){
        Mockito.when(boss.getPos()).thenReturn(new Vector2D(1, 1));
        Mockito.when(boss.getNumberOfLifes()).thenReturn(1);
        Mockito.when(king.getPos()).thenReturn(new Vector2D(2,2));
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(Mockito.mock(Monster.class));
        monsters.add(Mockito.mock(Monster.class));
        monsters.add(Mockito.mock(Monster.class));
        monsters = Mockito.spy(monsters);
        Mockito.when(monsters.get(0).getPos()).thenReturn(new Vector2D(3,3));
        Mockito.when(monsters.get(1).getPos()).thenReturn(new Vector2D(4,4));
        Mockito.when(monsters.get(2).getPos()).thenReturn(new Vector2D(6,6));
        ArrayList<Bullet> bullets = new ArrayList<>();
        bullets.add(Mockito.mock(Bullet.class));
        bullets.add(Mockito.mock(Bullet.class));
        bullets.add(Mockito.mock(Bullet.class));
        bullets.add(Mockito.mock(Bullet.class));
        bullets = Mockito.spy(bullets);
        Mockito.when(bullets.get(0).getPos()).thenReturn(new Vector2D(1,1));
        Mockito.when(bullets.get(1).getPos()).thenReturn(new Vector2D(3,3));
        Mockito.when(bullets.get(2).getPos()).thenReturn(new Vector2D(4,4));
        Mockito.when(bullets.get(3).getPos()).thenReturn(new Vector2D(5,5));
        Mockito.when(arena.getMonsters()).thenReturn(monsters);
        Mockito.when(arena.getBullets()).thenReturn(bullets);

        arenaController.killMonster();

        assertEquals(1, monsters.size());
        assertEquals(1, bullets.size());
        Mockito.verify(boss, Mockito.times(1)).lostLife();
        Mockito.verify(boss, Mockito.times(1)).getNumberOfLifes();
        Mockito.verify(arena, Mockito.times(1)).setBoss(Mockito.nullable(Boss.class));
        Mockito.verify(monsters, Mockito.times(2)).remove(Mockito.anyInt());
        Mockito.verify(bullets, Mockito.times(3)).remove(Mockito.anyInt());
        Mockito.verify(arena, Mockito.times(2)).dropItems(Mockito.any(Vector2D.class));
        Mockito.verify(arena, Mockito.times(2)).bossSpawn(Mockito.any(Vector2D.class));
        Mockito.verify(arena, Mockito.times(1)).setMonsters(Mockito.any(ArrayList.class));
        Mockito.verify(arena, Mockito.times(1)).setBullets(Mockito.any(ArrayList.class));
    }
}