package com.henrique.king.controller.game;

import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Bullet;
import com.henrique.king.game.elements.King;
import com.henrique.king.game.elements.items.Wheel;
import com.henrique.king.gui.GUI;
import static com.henrique.king.gui.GUI.ACTION.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BulletControllerTester {
    Arena arena;
    BulletController bulletController;

    @BeforeEach
    void setup(){
        arena = Mockito.mock(Arena.class);
        bulletController = new BulletController(arena);
    }

    @Test
    void addBulletTest(){
        arena.addBullet(Vector2D.V_NULL, Vector2D.V_NULL);
        Mockito.verify(arena, Mockito.times(1)).addBullet(Vector2D.V_NULL, Vector2D.V_NULL);
    }

    @Test void tickWithoutWheelTest() throws IOException {
        King king = Mockito.mock(King.class);
        Mockito.when(king.getPos()).thenReturn(Vector2D.V_NULL);
        Mockito.when(arena.getKing()).thenReturn(king);
        Mockito.when(arena.getBullets()).thenReturn(new ArrayList<>());
        Mockito.when(arena.hasItemOf(Wheel.class)).thenReturn(false);
        bulletController.tick(null, new GUI.ACTION[]{GUI.ACTION.SHOOT_UP},1000);
        Mockito.verify(arena, Mockito.times(1)).addBullet(Mockito.eq(Vector2D.V_NULL), Mockito.any(Vector2D.class));
    }

    @Test void tickWithWheelTest() throws IOException {
        King king = Mockito.mock(King.class);
        Mockito.when(king.getPos()).thenReturn(Vector2D.V_NULL);
        Mockito.when(arena.getKing()).thenReturn(king);
        Mockito.when(arena.getBullets()).thenReturn(new ArrayList<>());
        Mockito.when(arena.hasItemOf(Wheel.class)).thenReturn(true);
        bulletController.tick(null, new GUI.ACTION[]{SHOOT_UP},1000);
        Mockito.verify(arena, Mockito.times(8)).addBullet(Mockito.eq(Vector2D.V_NULL), Mockito.any(Vector2D.class));
    }

    @Test
    void checkShotTest(){
        King king = Mockito.mock(King.class);
        Mockito.when(king.getPos()).thenReturn(Vector2D.V_NULL);
        int time = 400;
        for(GUI.ACTION i : new GUI.ACTION[]{SHOOT_UP, SHOOT_DOWN, SHOOT_LEFT, SHOOT_RIGHT, SHOOT_UP_LEFT, SHOOT_UP_RIGHT, SHOOT_DOWN_LEFT, SHOOT_DOWN_RIGHT}){
            assertTrue(bulletController.checkShot(i, time));
            time += 400;
        }
    }

    @Test
    void checkShotFalseTest(){
        King king = Mockito.mock(King.class);
        Mockito.when(king.getPos()).thenReturn(Vector2D.V_NULL);
        int time = 20;
        for(GUI.ACTION i : new GUI.ACTION[]{SHOOT_UP, SHOOT_DOWN, SHOOT_LEFT, SHOOT_RIGHT, SHOOT_UP_LEFT, SHOOT_UP_RIGHT, SHOOT_DOWN_LEFT, SHOOT_DOWN_RIGHT}){
            assertFalse(bulletController.checkShot(i, time));
            time += 20;
        }
    }

    @Test
    void checkShotDoubleTest(){
        King king = Mockito.mock(King.class);
        Mockito.when(king.getPos()).thenReturn(Vector2D.V_NULL);
        assertTrue(bulletController.checkShot(SHOOT_UP,400));
        assertFalse(bulletController.checkShot(SHOOT_UP,420));
    }

    @Test
    void moveBulletsOutOfBoundsTest(){
        ArrayList<Bullet> bullets = Mockito.spy(new ArrayList<>());
        Bullet bullet = Mockito.mock(Bullet.class);
        Mockito.when(bullet.getPos()).thenReturn(Vector2D.V_NULL);
        bullets.add(bullet);
        Mockito.when(arena.getBullets()).thenReturn(bullets);
        Mockito.when(arena.inBounds(Mockito.any(Vector2D.class))).thenReturn(false);
        bulletController.moveBullets();
        Mockito.verify(bullet,Mockito.times(1)).move();
        Mockito.verify(arena,Mockito.times(1)).setBullets(Mockito.any(ArrayList.class));
        Mockito.verify(bullets,Mockito.times(1)).remove(Mockito.any(Bullet.class));
    }

    @Test
    void moveBulletsInBoundsTest(){
        ArrayList<Bullet> bullets = Mockito.spy(new ArrayList<>());
        Bullet bullet = Mockito.mock(Bullet.class);
        Mockito.when(bullet.getPos()).thenReturn(Vector2D.V_NULL);
        bullets.add(bullet);
        Mockito.when(arena.getBullets()).thenReturn(bullets);
        Mockito.when(arena.inBounds(Mockito.any(Vector2D.class))).thenReturn(true);
        bulletController.moveBullets();
        Mockito.verify(bullet,Mockito.times(1)).move();
        Mockito.verify(arena,Mockito.times(1)).setBullets(Mockito.any(ArrayList.class));
        Mockito.verify(bullets,Mockito.times(0)).remove(Mockito.any(Bullet.class));
    }

    @Test
    void addBulletsTest(){
        King king = Mockito.mock(King.class);
        Mockito.when(king.getPos()).thenReturn(Vector2D.V_NULL);
        Mockito.when(arena.getKing()).thenReturn(king);
        for(GUI.ACTION i : new GUI.ACTION[]{SHOOT_UP, SHOOT_DOWN, SHOOT_LEFT, SHOOT_RIGHT, SHOOT_UP_LEFT, SHOOT_UP_RIGHT, SHOOT_DOWN_LEFT, SHOOT_DOWN_RIGHT}){
            bulletController.addBullets(i);
        }
        Mockito.verify(arena, Mockito.times(8)).addBullet(Mockito.eq(Vector2D.V_NULL),Mockito.any(Vector2D.class));
    }
}
