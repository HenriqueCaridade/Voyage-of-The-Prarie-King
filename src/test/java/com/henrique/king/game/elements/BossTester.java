package com.henrique.king.game.elements;

import com.henrique.king.game.Vector2D;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BossTester {
    @Test
    public void getNumberOfLifesTest(){
        Boss boss = new Boss(Mockito.mock(Vector2D.class));
        assertEquals(3, boss.getNumberOfLifes());
    }

    @Test
    public void lostLifeTest(){
        Boss boss = new Boss(Mockito.mock(Vector2D.class));
        boss.lostLife();
        assertEquals(2,boss.getNumberOfLifes());
    }

    @Test
    public void tickTest(){
        Boss boss = new Boss(Mockito.mock(Vector2D.class));
        assertFalse(boss.tick());
        for(int i = 0; i < 2; i++)
            boss.tick();
        assertTrue(boss.tick());
        assertFalse(boss.tick());
    }
}
