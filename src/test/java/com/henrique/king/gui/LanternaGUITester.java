package com.henrique.king.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.henrique.image.MyImage;
import com.henrique.king.game.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LanternaGUITester {
    Screen screen;
    TextGraphics tg;
    LanternaGUI gui;

    @BeforeEach
    void setup() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);

        gui = new LanternaGUI(screen);
    }

    @Test
    void drawImageTest() {
        // For example MyBasicImage
        MyImage img = Mockito.mock(MyImage.class);
        gui.drawImage(img, new Vector2D(0,0));
        Mockito.verify(img, Mockito.times(1)).draw(tg, 0, 0);
    }

    @Test
    void clearTest(){
        gui.clear();
        Mockito.verify(screen, Mockito.times(1)).clear();
    }

    @Test
    void refreshTest() throws IOException {
        gui.refresh();
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    void closeTest() throws IOException {
        gui.close();
        Mockito.verify(screen, Mockito.times(1)).close();
    }

    @Test
    void getActionsUpTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(ks.getKeyType()).thenReturn(KeyType.ArrowUp);
        Mockito.when(screen.pollInput()).thenReturn(ks, (KeyStroke) null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.UP, actions[0]);
    }

    @Test
    void getActionsDownTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(ks.getKeyType()).thenReturn(KeyType.ArrowDown);
        Mockito.when(screen.pollInput()).thenReturn(ks, (KeyStroke) null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.DOWN, actions[0]);
    }

    @Test
    void getActionsLeftTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(ks.getKeyType()).thenReturn(KeyType.ArrowLeft);
        Mockito.when(screen.pollInput()).thenReturn(ks, (KeyStroke) null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.LEFT, actions[0]);
    }

    @Test
    void getActionsRightTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(ks.getKeyType()).thenReturn(KeyType.ArrowRight);
        Mockito.when(screen.pollInput()).thenReturn(ks, (KeyStroke) null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.RIGHT, actions[0]);
    }

    @Test
    void getActionsUpLeftTest() throws IOException {
        KeyStroke ks1 = Mockito.mock(KeyStroke.class);
        KeyStroke ks2 = Mockito.mock(KeyStroke.class);
        Mockito.when(ks1.getKeyType()).thenReturn(KeyType.ArrowUp);
        Mockito.when(ks2.getKeyType()).thenReturn(KeyType.ArrowLeft);
        Mockito.when(screen.pollInput()).thenReturn(ks1, ks2, null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.UP_LEFT, actions[0]);
    }

    @Test
    void getActionsUpRightTest() throws IOException {
        KeyStroke ks1 = Mockito.mock(KeyStroke.class);
        KeyStroke ks2 = Mockito.mock(KeyStroke.class);
        Mockito.when(ks1.getKeyType()).thenReturn(KeyType.ArrowUp);
        Mockito.when(ks2.getKeyType()).thenReturn(KeyType.ArrowRight);
        Mockito.when(screen.pollInput()).thenReturn(ks1, ks2, null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.UP_RIGHT, actions[0]);
    }

    @Test
    void getActionsDownLeftTest() throws IOException {
        KeyStroke ks1 = Mockito.mock(KeyStroke.class);
        KeyStroke ks2 = Mockito.mock(KeyStroke.class);
        Mockito.when(ks1.getKeyType()).thenReturn(KeyType.ArrowDown);
        Mockito.when(ks2.getKeyType()).thenReturn(KeyType.ArrowLeft);
        Mockito.when(screen.pollInput()).thenReturn(ks1, ks2, null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.DOWN_LEFT, actions[0]);
    }

    @Test
    void getActionsDownRightTest() throws IOException {
        KeyStroke ks1 = Mockito.mock(KeyStroke.class);
        KeyStroke ks2 = Mockito.mock(KeyStroke.class);
        Mockito.when(ks1.getKeyType()).thenReturn(KeyType.ArrowDown);
        Mockito.when(ks2.getKeyType()).thenReturn(KeyType.ArrowRight);
        Mockito.when(screen.pollInput()).thenReturn(ks1, ks2, null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.DOWN_RIGHT, actions[0]);
    }

    @Test
    void getActionsShootUpTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(ks.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks.getCharacter()).thenReturn('w');
        Mockito.when(screen.pollInput()).thenReturn(ks, (KeyStroke) null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.SHOOT_UP, actions[0]);
    }

    @Test
    void getActionsShootDownTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(ks.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks.getCharacter()).thenReturn('s');
        Mockito.when(screen.pollInput()).thenReturn(ks, (KeyStroke) null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.SHOOT_DOWN, actions[0]);
    }

    @Test
    void getActionsShootLeftTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(ks.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks.getCharacter()).thenReturn('a');
        Mockito.when(screen.pollInput()).thenReturn(ks, (KeyStroke) null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.SHOOT_LEFT, actions[0]);
    }

    @Test
    void getActionsShootRightTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(ks.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks.getCharacter()).thenReturn('d');
        Mockito.when(screen.pollInput()).thenReturn(ks, (KeyStroke) null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.SHOOT_RIGHT, actions[0]);
    }

    @Test
    void getActionsShootUpLeftTest() throws IOException {
        KeyStroke ks1 = Mockito.mock(KeyStroke.class);
        KeyStroke ks2 = Mockito.mock(KeyStroke.class);
        Mockito.when(ks1.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks1.getCharacter()).thenReturn('w');
        Mockito.when(ks2.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks2.getCharacter()).thenReturn('a');
        Mockito.when(screen.pollInput()).thenReturn(ks1, ks2, null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.SHOOT_UP_LEFT, actions[0]);
    }

    @Test
    void getActionsShootUpRightTest() throws IOException {
        KeyStroke ks1 = Mockito.mock(KeyStroke.class);
        KeyStroke ks2 = Mockito.mock(KeyStroke.class);
        Mockito.when(ks1.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks1.getCharacter()).thenReturn('w');
        Mockito.when(ks2.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks2.getCharacter()).thenReturn('d');
        Mockito.when(screen.pollInput()).thenReturn(ks1, ks2, null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.SHOOT_UP_RIGHT, actions[0]);
    }

    @Test
    void getActionsShootDownLeftTest() throws IOException {
        KeyStroke ks1 = Mockito.mock(KeyStroke.class);
        KeyStroke ks2 = Mockito.mock(KeyStroke.class);
        Mockito.when(ks1.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks1.getCharacter()).thenReturn('s');
        Mockito.when(ks2.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks2.getCharacter()).thenReturn('a');
        Mockito.when(screen.pollInput()).thenReturn(ks1, ks2, null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.SHOOT_DOWN_LEFT, actions[0]);
    }

    @Test
    void getActionsShootDownRightTest() throws IOException {
        KeyStroke ks1 = Mockito.mock(KeyStroke.class);
        KeyStroke ks2 = Mockito.mock(KeyStroke.class);
        Mockito.when(ks1.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks1.getCharacter()).thenReturn('s');
        Mockito.when(ks2.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks2.getCharacter()).thenReturn('d');
        Mockito.when(screen.pollInput()).thenReturn(ks1, ks2, null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.SHOOT_DOWN_RIGHT, actions[0]);
    }

    @Test
    void getActionsSimultaneousActionsTest() throws IOException {
        KeyStroke ks1 = Mockito.mock(KeyStroke.class);
        KeyStroke ks2 = Mockito.mock(KeyStroke.class);
        Mockito.when(ks1.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks1.getCharacter()).thenReturn('w');
        Mockito.when(ks2.getKeyType()).thenReturn(KeyType.ArrowRight);
        Mockito.when(screen.pollInput()).thenReturn(ks1, ks2, null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(2, actions.length);
        boolean walkRight = false, shootUp = false;
        for(int i = 0; i < 2; i++)
            walkRight = (GUI.ACTION.RIGHT == actions[i]) || walkRight;
        for(int i = 0; i < 2; i++)
            shootUp = (GUI.ACTION.SHOOT_UP == actions[i]) || shootUp;
        assertTrue(walkRight);
        assertTrue(shootUp);
    }

    @Test
    void getActionsSelectTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(ks.getKeyType()).thenReturn(KeyType.Enter);
        Mockito.when(screen.pollInput()).thenReturn(ks, (KeyStroke) null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.SELECT, actions[0]);
    }

    @Test
    void getActionsCharacterQuitTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(ks.getKeyType()).thenReturn(KeyType.Character);
        Mockito.when(ks.getCharacter()).thenReturn('q');
        Mockito.when(screen.pollInput()).thenReturn(ks);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.QUIT, actions[0]);
    }

    @Test
    void getActionsEOFQuitTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(ks.getKeyType()).thenReturn(KeyType.EOF);
        Mockito.when(screen.pollInput()).thenReturn(ks);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.QUIT, actions[0]);
    }

    @Test
    void getActionsNullTest() throws IOException {
        KeyStroke ks = Mockito.mock(KeyStroke.class);
        Mockito.when(screen.pollInput()).thenReturn(null);
        GUI.ACTION[] actions = gui.getNextActions();
        assertEquals(1, actions.length);
        assertEquals(GUI.ACTION.NONE, actions[0]);
    }
}
