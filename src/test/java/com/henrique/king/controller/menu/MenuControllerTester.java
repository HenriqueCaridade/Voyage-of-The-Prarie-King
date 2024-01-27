package com.henrique.king.controller.menu;

import com.henrique.king.Game;
import com.henrique.king.gui.GUI;
import com.henrique.king.states.GameState;
import com.henrique.king.viewer.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static com.henrique.king.gui.GUI.ACTION.*;

public class MenuControllerTester {
    Menu menu;
    MenuController menuController;
    Game game;

    @BeforeEach
    void setup(){
        menu = Mockito.mock(Menu.class);
        menuController = new MenuController(menu);
        game = Mockito.mock(Game.class);
    }

    @Test
    void UPTest() throws IOException {
        GUI.ACTION[] actions = new GUI.ACTION[]{UP};
        menuController.tick(game,actions,100);
        Mockito.verify(menu,Mockito.times(1)).previousEntry();
    }

    @Test
    void UP_LEFTTest() throws IOException {
        GUI.ACTION[] actions = new GUI.ACTION[]{UP_LEFT};
        menuController.tick(game,actions,100);
        Mockito.verify(menu,Mockito.times(1)).previousEntry();
    }

    @Test
    void UP_RIGHTTest() throws IOException {
        GUI.ACTION[] actions = new GUI.ACTION[]{UP_RIGHT};
        menuController.tick(game,actions,100);
        Mockito.verify(menu,Mockito.times(1)).previousEntry();
    }

    @Test
    void DOWNTest() throws IOException {
        GUI.ACTION[] actions = new GUI.ACTION[]{DOWN};
        menuController.tick(game,actions,100);
        Mockito.verify(menu,Mockito.times(1)).nextEntry();
    }

    @Test
    void DOWN_LEFTTest() throws IOException {
        GUI.ACTION[] actions = new GUI.ACTION[]{DOWN_LEFT};
        menuController.tick(game,actions,100);
        Mockito.verify(menu,Mockito.times(1)).nextEntry();
    }

    @Test
    void DOWN_RIGHTTest() throws IOException {
        GUI.ACTION[] actions = new GUI.ACTION[]{DOWN_RIGHT};
        menuController.tick(game,actions,100);
        Mockito.verify(menu,Mockito.times(1)).nextEntry();
    }

    @Test
    void SELECTExitTest() throws IOException {
        GUI.ACTION[] actions = new GUI.ACTION[]{SELECT};
        Mockito.when(menu.isSelectedExit()).thenReturn(true);
        Mockito.when(menu.isSelectedPlay()).thenReturn(false);
        menuController.tick(game,actions,100);
        Mockito.verify(game,Mockito.times(1)).setState(null);
    }

    @Test
    void SELECTPlayTest() throws IOException {
        GUI.ACTION[] actions = new GUI.ACTION[]{SELECT};
        Mockito.when(menu.isSelectedExit()).thenReturn(false);
        Mockito.when(menu.isSelectedPlay()).thenReturn(true);
        menuController.tick(game,actions,100);
        Mockito.verify(game,Mockito.times(1)).setState(Mockito.any(GameState.class));
    }

    @Test
    void actionFilterTest() throws IOException {
        GUI.ACTION[] actions = new GUI.ACTION[]{SHOOT_UP,SELECT};
        Mockito.when(menu.isSelectedExit()).thenReturn(false);
        Mockito.when(menu.isSelectedPlay()).thenReturn(true);
        menuController.tick(game,actions,100);
        Mockito.verify(game,Mockito.times(1)).setState(Mockito.any(GameState.class));
    }
}
