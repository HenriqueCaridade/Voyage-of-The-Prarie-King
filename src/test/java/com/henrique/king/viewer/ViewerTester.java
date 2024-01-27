package com.henrique.king.viewer;

import com.henrique.image.MyImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.gui.GUI;
import com.henrique.king.gui.LanternaGUI;
import com.henrique.king.viewer.menu.Menu;
import com.henrique.king.viewer.menu.MenuViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;

public class ViewerTester {
    @Test
    void drawTest() throws IOException {
        // For example MenuViewer
        Menu menu = Mockito.mock(Menu.class);
        MenuViewer mv = new MenuViewer(menu);
        GUI gui = Mockito.mock(LanternaGUI.class);
        mv.draw(gui);
        Mockito.verify(gui, Mockito.times(1)).refresh();
        Mockito.verify(gui, Mockito.times(1)).clear();
        Mockito.verify(gui, Mockito.times(4)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }
}
