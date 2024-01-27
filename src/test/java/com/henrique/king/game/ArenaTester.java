package com.henrique.king.game;

import com.henrique.king.game.elements.Boss;
import com.henrique.king.game.elements.King;
import com.henrique.king.game.elements.items.Confuse;
import com.henrique.king.game.elements.items.Heart;
import com.henrique.king.game.elements.items.Item;
import com.henrique.king.game.elements.items.Wheel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class ArenaTester {
    Arena arena;

    @BeforeEach
    void setup() throws IOException {
        arena = new Arena(16, 16, Vector2D.V_NULL, 1);
    }

    @Test
    void bossSpawnTest(){
        arena.setBoss(null);
        arena.bossSpawn(Vector2D.V_DOWN);
        Boss boss = arena.getBoss();
        if(boss == null) return; // Didn't Spawn
        assertEquals(Vector2D.V_DOWN, boss.getPos());
    }

    @Test
    void bossNullSpawnTest(){
        arena.setBoss(new Boss(Vector2D.V_RIGHT));
        arena.bossSpawn(Vector2D.V_DOWN);
        Boss boss = arena.getBoss();
        if(boss.getPos() == Vector2D.V_RIGHT) return; // Didn't Spawn
        assertEquals(Vector2D.V_DOWN, boss.getPos());
    }

    @Test
    void dropItemsTest(){
        // droppedItems is Empty by default
        assertTrue(arena.droppedItems.isEmpty());
        arena.dropItems(Vector2D.V_DOWN);
        ArrayList<Item> items = arena.getDroppedItems();
        if(items.size() == 0) return; // Didn't Spawn
        assertEquals(1, items.size());
        assertEquals(Vector2D.V_DOWN, items.get(0).getPos());
    }

    @Test
    void checkItemsTest(){
        arena.droppedItems.add(Mockito.mock(Wheel.class));
        arena.droppedItems.add(Mockito.mock(Heart.class));
        arena.currItems.add(Mockito.mock(Confuse.class));
        arena.droppedItems = Mockito.spy(arena.droppedItems);
        arena.currItems = Mockito.spy(arena.currItems);
        for(Item mock : arena.droppedItems)
            Mockito.doReturn(true).when(mock).despawn();
        for(Item mock : arena.currItems)
            Mockito.doReturn(false).when(mock).ended();
        arena.checkItems();
        for(Item mock : arena.droppedItems)
            Mockito.verify(mock, Mockito.times(1)).despawn();
        for(Item mock : arena.currItems)
            Mockito.verify(mock, Mockito.times(1)).ended();
        Mockito.verify(arena.droppedItems, Mockito.times(2)).remove(Mockito.anyInt());
        Mockito.verify(arena.currItems, Mockito.times(0)).remove(Mockito.anyInt());
    }

    @Test
    void useItemTest(){
        arena.king = Mockito.mock(King.class);
        arena.droppedItems.add(Mockito.mock(Wheel.class));
        arena.droppedItems.add(Mockito.mock(Heart.class));
        arena.currItems.add(Mockito.mock(Confuse.class));
        arena.droppedItems = Mockito.spy(arena.droppedItems);
        arena.currItems = Mockito.spy(arena.currItems);
        Mockito.when(arena.king.getPos()).thenReturn(new Vector2D(2, 2));
        Mockito.when(arena.droppedItems.get(0).getPos()).thenReturn(new Vector2D(2, 2));
        Mockito.when(arena.droppedItems.get(1).getPos()).thenReturn(new Vector2D(3, 4));
        arena.useItem();
        assertEquals(1, arena.droppedItems.size());
        assertEquals(2, arena.currItems.size());
        Mockito.verify(arena.droppedItems, Mockito.times(1)).remove(Mockito.anyInt());
        Mockito.verify(arena.currItems.get(0), Mockito.times(0)).use(Mockito.any(Arena.class));
        Mockito.verify(arena.currItems.get(1), Mockito.times(1)).use(Mockito.any(Arena.class));
        Mockito.verify(arena.currItems, Mockito.times(1)).add(Mockito.any(Item.class));

    }

    @Test
    void hasItemOfTest(){
        arena.currItems.add(Mockito.mock(Wheel.class));
        arena.currItems.add(Mockito.mock(Heart.class));
        assertTrue(arena.hasItemOf(Wheel.class));
        assertTrue(arena.hasItemOf(Heart.class));
        assertFalse(arena.hasItemOf(Confuse.class));
    }

    @Test
    void spawnMonsterTest(){
        assertTrue(arena.getMonsters().isEmpty());
        arena.spawnMonster();
        assertFalse(arena.getMonsters().isEmpty());
    }

    @Test
    void inBoundsTest(){
        assertTrue(arena.inBounds(new Vector2D(0,0)));
        assertTrue(arena.inBounds(new Vector2D(7,3)));
        assertTrue(arena.inBounds(new Vector2D(15,15)));
        assertFalse(arena.inBounds(new Vector2D(30,40)));
        assertFalse(arena.inBounds(new Vector2D(-10,-8)));
    }

    @Test
    void canMoveToTest() {
        arena.levelGrid = new boolean[][]{
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false}
        };
        assertTrue(arena.canMoveTo(new Vector2D(5, 5)));
        assertFalse(arena.canMoveTo(new Vector2D(10, 10)));
        assertFalse(arena.canMoveTo(new Vector2D(-1, -1)));
        assertFalse(arena.canMoveTo(new Vector2D(16, 16)));
    }

    @Test
    void setLevelTest() {
        byte[][] auxGrid = {
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1}
        };
        arena = Mockito.spy(arena);
        Mockito.doReturn(auxGrid).when(arena).getLevelGrid(Mockito.anyInt());
        arena.setLevel(1);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if(j < 8)
                    assertTrue(arena.levelGrid[i][j]);
                else
                    assertFalse(arena.levelGrid[i][j]);
            }
        }
    }

    @Test
    void getLevelGridTest(){
        byte[][] out = arena.getLevelGrid(1234);
        assertNotNull(out);
        byte[][] outExpected = {
                {1,2,3,4},
                {1,2,3,4},
                {1,2,3,4},
                {1,2,3,4}
        };
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                assertEquals(outExpected[i][j],out[i][j]);
    }
}