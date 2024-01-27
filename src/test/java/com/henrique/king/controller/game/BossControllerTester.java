package com.henrique.king.controller.game;

import com.henrique.king.Game;
import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Boss;
import com.henrique.king.game.elements.King;
import com.henrique.king.game.elements.Monster;
import com.henrique.king.game.elements.items.Confuse;
import com.henrique.king.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static com.henrique.king.game.Vector2D.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BossControllerTester {
    Arena arena;
    BossController bossController;

    @BeforeEach
    void setup(){
        arena = Mockito.mock(Arena.class);
        bossController = Mockito.spy(new BossController(arena));
    }

    @Test
    void moveBossTest(){
        Boss boss = Mockito.mock(Boss.class);
        King king = Mockito.mock(King.class);
        Mockito.when(boss.tick()).thenReturn(true);
        Mockito.when(arena.getBoss()).thenReturn(boss);
        Mockito.when(boss.getPos()).thenReturn(V_NULL);
        Mockito.when(arena.getKing()).thenReturn(king);
        Mockito.when(king.getPos()).thenReturn(new Vector2D(2, 2));
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(Mockito.mock(Monster.class));
        monsters.add(Mockito.mock(Monster.class));
        monsters = Mockito.spy(monsters);
        Mockito.when(monsters.get(0).getPos()).thenReturn(new Vector2D(1, 0));
        Mockito.when(monsters.get(1).getPos()).thenReturn(new Vector2D(0, 1));
        Mockito.doReturn(monsters).when(arena).getMonsters();
        Mockito.doReturn(true).when(arena).canMoveTo(Mockito.any(Vector2D.class));
        bossController.moveBoss();
        Mockito.verify(bossController, Mockito.times(1)).chooseDirection(Mockito.any(Boss.class));
        Mockito.verify(arena, Mockito.times(1)).getMonsters();
        Mockito.verify(monsters, Mockito.times(1)).remove(Mockito.anyInt());
        Mockito.verify(arena, Mockito.times(1)).setMonsters(Mockito.any(ArrayList.class));
        Mockito.verify(arena, Mockito.times(1)).setBoss(Mockito.any(Boss.class));
    }

    @Test
    void moveBossCannotMoveTest(){
        Boss boss = Mockito.mock(Boss.class);
        King king = Mockito.mock(King.class);
        Mockito.when(boss.tick()).thenReturn(true);
        Mockito.when(arena.getBoss()).thenReturn(boss);
        Mockito.when(boss.getPos()).thenReturn(V_NULL);
        Mockito.when(arena.getKing()).thenReturn(king);
        Mockito.when(king.getPos()).thenReturn(new Vector2D(2, 2));
        Mockito.doReturn(false).when(arena).canMoveTo(Mockito.any(Vector2D.class));
        bossController.moveBoss();
        Mockito.verify(bossController, Mockito.times(1)).chooseDirection(Mockito.any(Boss.class));
        Mockito.verify(arena, Mockito.times(0)).getMonsters();
    }

    @Test
    void moveBossNoTickTest(){
        Boss boss = Mockito.mock(Boss.class);
        Mockito.when(boss.tick()).thenReturn(false);
        Mockito.when(arena.getBoss()).thenReturn(boss);
        bossController.moveBoss();
        Mockito.verify(bossController, Mockito.times(0)).chooseDirection(Mockito.any(Boss.class));
    }

    @Test
    void moveBossNullTest(){
        Mockito.when(arena.getBoss()).thenReturn(null);
        bossController.moveBoss();
        Mockito.verify(bossController, Mockito.times(0)).chooseDirection(Mockito.any(Boss.class));
    }

    @Test
    void tickTest() throws IOException {
        Game game = Mockito.mock(Game.class);
        GUI.ACTION[] actions = new GUI.ACTION[]{};
        bossController.tick(game,actions,350); // Calls method
        Mockito.verify(bossController,Mockito.times(1)).moveBoss();
        bossController.tick(game,actions,600); // Doesn't Call method
        Mockito.verify(bossController,Mockito.times(1)).moveBoss();
    }

    @Test
    void chooseDirectionConfusedTest() {
        Monster monster = Mockito.mock(Monster.class);
        Mockito.doReturn(true).when(arena).hasItemOf(Confuse.class);
        Mockito.doReturn(V_NULL).when(monster).getPos();
        Vector2D pos = bossController.chooseDirection(monster);
        boolean isValid = false;
        for(Vector2D expectedPos : new Vector2D[]{V_UP, V_DOWN, V_LEFT, V_RIGHT})
            if(expectedPos.equals(pos)) {
                isValid = true;
                break;
            }
        assertTrue(isValid);
    }

    @Test
    void chooseDirectionNotConfusedTest() {
        King king = Mockito.mock(King.class);
        Monster monster = Mockito.mock(Monster.class);
        Mockito.doReturn(false).when(arena).hasItemOf(Confuse.class);
        Mockito.doReturn(V_NULL).when(monster).getPos();
        Mockito.doReturn(king).when(arena).getKing();
        Mockito.doReturn(new Vector2D(2, 2)).when(king).getPos();
        Vector2D pos = bossController.chooseDirection(monster);
        boolean isValid = false;
        for(Vector2D expectedPos : new Vector2D[]{V_DOWN, V_RIGHT})
            if(expectedPos.equals(pos)) {
                isValid = true;
                break;
            }
        assertTrue(isValid);
    }
}

