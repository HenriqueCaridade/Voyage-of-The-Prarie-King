package com.henrique.king.game.elements;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.henrique.king.game.Vector2D;

import static org.junit.jupiter.api.Assertions.*;

public class BulletTester {
    @Test
    public void moveTesterXAxisTest(){
        Vector2D position = Mockito.mock(Vector2D.class);
        Mockito.when(position.getX()).thenReturn(1);
        Mockito.when(position.getY()).thenReturn(1);
        Vector2D direction = Mockito.mock(Vector2D.class);
        Mockito.when(direction.getX()).thenReturn(1);
        Mockito.when(direction.getY()).thenReturn(0);
        Bullet b = new Bullet(position,direction);
        Vector2D expected = new Vector2D(2,1);
        b.move();
        assertEquals(b.getPos(),expected);
    }

    @Test
    public void moveTesterYAxisTest(){
        Vector2D position = Mockito.mock(Vector2D.class);
        Mockito.when(position.getX()).thenReturn(1);
        Mockito.when(position.getY()).thenReturn(1);
        Vector2D direction = Mockito.mock(Vector2D.class);
        Mockito.when(direction.getX()).thenReturn(0);
        Mockito.when(direction.getY()).thenReturn(1);
        Bullet b = new Bullet(position,direction);
        Vector2D expected = new Vector2D(1,2);
        b.move();
        assertEquals(b.getPos(),expected);
    }

    @Test
    public void moveTesterDiagonallyTest(){
        Vector2D position = Mockito.mock(Vector2D.class);
        Mockito.when(position.getX()).thenReturn(1);
        Mockito.when(position.getY()).thenReturn(1);
        Vector2D direction = Mockito.mock(Vector2D.class);
        Mockito.when(direction.getX()).thenReturn(1);
        Mockito.when(direction.getY()).thenReturn(1);
        Bullet b = new Bullet(position,direction);
        Vector2D expected = new Vector2D(2,2);
        b.move();
        assertEquals(b.getPos(),expected);
    }
}