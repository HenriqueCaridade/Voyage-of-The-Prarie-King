package com.henrique.king.controller.game;

import com.henrique.king.Game;
import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Element;
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
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MonsterControllerTester {
    Arena arena;
    MonsterController monsterController;

    @BeforeEach
    void setup(){
        arena = Mockito.mock(Arena.class);
        monsterController = Mockito.spy(new MonsterController(arena));
    }

    @Test
    void tickTest() throws IOException {
        Game game = Mockito.mock(Game.class);
        GUI.ACTION[] actions = new GUI.ACTION[]{};
        monsterController.tick(game,actions,450); // Calls method
        Mockito.verify(monsterController,Mockito.times(1)).moveMonsters();
        monsterController.tick(game,actions,800); // Doesn't Call method
        Mockito.verify(monsterController,Mockito.times(1)).moveMonsters();
    }

    @Test
    void moveMonstersNoCollisionsTest(){
        ArrayList<Monster> monsters = Mockito.spy(new ArrayList<>());
        Monster monster = Mockito.mock(Monster.class);
        Mockito.doReturn(monsters).when(arena).getMonsters();
        monsters.add(monster);
        Mockito.doReturn(new Vector2D(0, 0)).when(monsterController).chooseDirection(Mockito.any(Element.class));
        Mockito.when(monster.tick()).thenReturn(true);
        Mockito.doReturn(true).when(arena).canMoveTo(Mockito.any(Vector2D.class));
        Mockito.doReturn(false).when(monsterController).monsterCollision(Mockito.same(monsters), Mockito.any(Vector2D.class));
        monsterController.moveMonsters();
        Mockito.verify(monster, Mockito.times(1)).setPos(Mockito.any(Vector2D.class));
    }

    @Test
    void moveMonstersCollisionsTest() {
        ArrayList<Monster> monsters = Mockito.spy(new ArrayList<>());
        Monster monster = Mockito.mock(Monster.class);
        Mockito.doReturn(monsters).when(arena).getMonsters();
        monsters.add(monster);
        Mockito.doReturn(new Vector2D(0, 0)).when(monsterController).chooseDirection(Mockito.any(Element.class));
        Mockito.when(monster.tick()).thenReturn(true);
        Mockito.doReturn(true).when(arena).canMoveTo(Mockito.any(Vector2D.class));
        Mockito.doReturn(true).when(monsterController).monsterCollision(Mockito.same(monsters), Mockito.any(Vector2D.class));
        monsterController.moveMonsters();
        Mockito.verify(monster, Mockito.times(0)).setPos(Mockito.any(Vector2D.class));
    }

    @Test
    void chooseDirectionConfusedTest() {
        Monster monster = Mockito.mock(Monster.class);
        Mockito.doReturn(true).when(arena).hasItemOf(Confuse.class);
        Mockito.doReturn(V_NULL).when(monster).getPos();
        Vector2D pos = monsterController.chooseDirection(monster);
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
        Vector2D pos = monsterController.chooseDirection(monster);
        boolean isValid = false;
        for(Vector2D expectedPos : new Vector2D[]{V_DOWN, V_RIGHT})
            if(expectedPos.equals(pos)) {
                isValid = true;
                break;
            }
        assertTrue(isValid);
    }

    @Test
    void monsterCollisionTrueTest() {
        ArrayList<Monster> monsters = Mockito.spy(new ArrayList<>());
        Monster monster1 = Mockito.mock(Monster.class);
        Monster monster2 = Mockito.mock(Monster.class);
        Monster monster3 = Mockito.mock(Monster.class);
        monsters.add(monster1);
        monsters.add(monster2);
        monsters.add(monster3);
        Vector2D pos = V_NULL;
        Mockito.doReturn(pos).when(monster1).getPos();
        Mockito.doReturn(pos).when(monster2).getPos();
        Mockito.doReturn(pos).when(monster3).getPos();
        assertTrue(monsterController.monsterCollision(monsters, pos));
    }

    @Test
    void monsterCollisionFalseTest() {
        ArrayList<Monster> monsters = Mockito.spy(new ArrayList<>());
        Monster monster1 = Mockito.mock(Monster.class);
        Monster monster2 = Mockito.mock(Monster.class);
        Monster monster3 = Mockito.mock(Monster.class);
        monsters.add(monster1);
        monsters.add(monster2);
        monsters.add(monster3);
        Vector2D pos = V_NULL;
        Mockito.doReturn(new Vector2D(4, 4)).when(monster1).getPos();
        Mockito.doReturn(new Vector2D(2, 1)).when(monster2).getPos();
        Mockito.doReturn(new Vector2D(6, 7)).when(monster3).getPos();
        assertFalse(monsterController.monsterCollision(monsters, pos));
    }
}

