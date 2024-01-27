package com.henrique.king.viewer.menu;

import com.henrique.image.MyImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.gui.GUI;
import com.henrique.king.gui.LanternaGUI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuViewerTester {
    @Test
    void drawElementsPlayTest(){
        Menu menu = Mockito.mock(Menu.class);
        Mockito.when(menu.isSelectedPlay()).thenReturn(true);
        MenuViewer mv = new MenuViewer(menu);
        GUI gui = Mockito.mock(LanternaGUI.class);
        mv.drawElements(gui);
        Mockito.verify(gui, Mockito.times(4)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }

    @Test
    void drawElementsExitTest(){
        Menu menu = Mockito.mock(Menu.class);
        Mockito.when(menu.isSelectedPlay()).thenReturn(false);
        MenuViewer mv = new MenuViewer(menu);
        GUI gui = Mockito.mock(LanternaGUI.class);
        mv.drawElements(gui);
        Mockito.verify(gui, Mockito.times(4)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }
}
