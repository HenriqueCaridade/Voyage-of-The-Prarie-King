package com.henrique.king.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2DTester {
    @Test
    public void copyConstructorTest(){
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(v1);
        assertEquals(1, v2.getX());
        assertEquals(2, v2.getY());
    }

    @Test
    public void toStringTest(){
        Vector2D v1 = new Vector2D(1,2);
        assertEquals("(1,2)", v1.toString());
    }

    @Test
    public void settersTest(){
        Vector2D v1 = new Vector2D(0,0);
        v1.setX(1);
        v1.setY(2);
        assertEquals(1, v1.getX());
        assertEquals(2, v1.getY());
    }

    @Test
    public void addTest() {
        Vector2D vector1 = new Vector2D(2,2);
        Vector2D vector2 = new Vector2D(1,3);
        Vector2D vector3 = vector1.add(vector2);
        assertEquals(3,vector3.getX());
        assertEquals(5,vector3.getY());
    }

    @Test
    public void subTest(){
        Vector2D vector1 = new Vector2D(2,2);
        Vector2D vector2 = new Vector2D(1,3);
        Vector2D vector3 = vector1.sub(vector2);
        assertEquals(1,vector3.getX());
        assertEquals(-1,vector3.getY());
    }

    @Test
    public void multTest(){
        Vector2D vector1 = new Vector2D(2,4);
        vector1 = vector1.mult(3);
        assertEquals(6,vector1.getX());
        assertEquals(12,vector1.getY());
    }

    @Test
    public void euclideanNormaliseTest(){
        Vector2D vector1 = new Vector2D(3,5);
        vector1 = vector1.euclideanNormalise();
        assertEquals(1,vector1.getX());
        assertEquals(1,vector1.getY());

        Vector2D vector2 = new Vector2D(-4,-3);
        vector2 = vector2.euclideanNormalise();
        assertEquals(-1,vector2.getX());
        assertEquals(-1,vector2.getY());
    }
}
