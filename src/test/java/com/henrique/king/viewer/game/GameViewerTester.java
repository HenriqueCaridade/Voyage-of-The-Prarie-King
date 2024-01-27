package com.henrique.king.viewer.game;

import com.henrique.image.MyImage;
import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Boss;
import com.henrique.king.game.elements.Bullet;
import com.henrique.king.game.elements.King;
import com.henrique.king.game.elements.Monster;
import com.henrique.king.game.elements.items.Item;
import com.henrique.king.game.elements.items.Wheel;
import com.henrique.king.gui.LanternaGUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class GameViewerTester {
    King king;
    Arena arena;
    GameViewer gameViewer;
    LanternaGUI gui;

    @BeforeEach
    void setup(){
        king = Mockito.mock(King.class);
        arena = Mockito.mock(Arena.class);
        Mockito.when(king.getNumberOfLives()).thenReturn(3);
        Mockito.when(arena.getKing()).thenReturn(king);
        Mockito.when(arena.getOrigin()).thenReturn(Vector2D.V_NULL);
        Mockito.when(arena.getLevelGrid(1)).thenReturn(new byte[][]{});
        Mockito.when(arena.getDroppedItems()).thenReturn(new ArrayList<>(List.of(new Item[]{new Wheel(Vector2D.V_NULL)})));
        Mockito.when(arena.getMonsters()).thenReturn(new ArrayList<>(List.of(new Monster[]{new Monster(Vector2D.V_NULL)})));
        Mockito.when(arena.getBoss()).thenReturn(new Boss(Vector2D.V_NULL));
        Mockito.when(arena.getBullets()).thenReturn(new ArrayList<>(List.of(new Bullet[]{new Bullet(Vector2D.V_NULL, Vector2D.V_UP)})));
        Mockito.when(arena.getKing()).thenReturn(new King(Vector2D.V_NULL));
        gameViewer = new GameViewer(arena);
        gui = Mockito.mock(LanternaGUI.class);
    }

    @Test
    void drawElementsTest() {
        gameViewer.drawElements(gui);
        Mockito.verify(gui, Mockito.times(10)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }

    @Test
    void drawHeartsTest() {
        gameViewer.drawHearts(gui);
        Mockito.verify(gui, Mockito.times(3)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }

    @Test
    void drawElementsListTest() {
        MonsterViewer mv = Mockito.mock(MonsterViewer.class);
        gameViewer.drawElements(gui, new ArrayList<>(List.of(new Monster[]{new Monster(Vector2D.V_NULL), new Monster(Vector2D.V_NULL)})), mv);
        Mockito.verify(mv, Mockito.times(2)).draw(Mockito.any(Monster.class), Mockito.same(gui), Mockito.any(Vector2D.class));
    }

    @Test
    void drawElementTest() {
        KingViewer kv = Mockito.mock(KingViewer.class);
        gameViewer.drawElement(gui, new King(Vector2D.V_NULL), kv);
        Mockito.verify(kv, Mockito.times(1)).draw(Mockito.any(King.class), Mockito.same(gui), Mockito.any(Vector2D.class));
    }
}
