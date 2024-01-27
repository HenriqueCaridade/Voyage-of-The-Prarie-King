package com.henrique.king.game.elements;

import com.henrique.king.game.Vector2D;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KingTester {
    @Test
    public void getNumberOfLivesTest(){
        King king = new King(Mockito.mock(Vector2D.class));
        assertEquals(3,king.getNumberOfLives());
    }

    @Test
    public void lostLivesTest(){
        King king = new King(Mockito.mock(Vector2D.class));
        king.lostLives(2);
        assertEquals(1,king.getNumberOfLives());

        king = new King(Mockito.mock(Vector2D.class));
        king.lostLives(1);
        assertEquals(2,king.getNumberOfLives());

        king = new King(Mockito.mock(Vector2D.class));
        king.lostLives(5);
        assertEquals(0,king.getNumberOfLives());
    }

    @Test
    public void increaseLifeTest(){
        King king = new King(Mockito.mock(Vector2D.class));
        king.increaseLife();
        assertEquals(4,king.getNumberOfLives());
        king.increaseLife();
        assertEquals(5,king.getNumberOfLives());
        king.increaseLife();
        assertEquals(6,king.getNumberOfLives());
    }
}

