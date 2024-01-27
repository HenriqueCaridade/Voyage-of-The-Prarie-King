package com.henrique.king.viewer.game;

import com.henrique.image.MyImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.items.Confuse;
import com.henrique.king.game.elements.items.Heart;
import com.henrique.king.game.elements.items.Wheel;
import com.henrique.king.gui.GUI;
import com.henrique.king.gui.LanternaGUI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ItemViewerTester {
    @Test
    void drawWheelTest(){
        Wheel wheel = Mockito.mock(Wheel.class);
        Mockito.when(wheel.getPos()).thenReturn(new Vector2D(0,0));
        ItemViewer iv = new ItemViewer();
        GUI gui = Mockito.mock(LanternaGUI.class);
        iv.draw(wheel, gui, new Vector2D(0,0));
        Mockito.verify(gui, Mockito.times(1)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }

    @Test
    void drawConfuseTest(){
        Confuse confuse = Mockito.mock(Confuse.class);
        Mockito.when(confuse.getPos()).thenReturn(new Vector2D(0,0));
        ItemViewer iv = new ItemViewer();
        GUI gui = Mockito.mock(LanternaGUI.class);
        iv.draw(confuse, gui, new Vector2D(0,0));
        Mockito.verify(gui, Mockito.times(1)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }

    @Test
    void drawHeartTest(){
        Heart heart = Mockito.mock(Heart.class);
        Mockito.when(heart.getPos()).thenReturn(new Vector2D(0,0));
        ItemViewer iv = new ItemViewer();
        GUI gui = Mockito.mock(LanternaGUI.class);
        iv.draw(heart, gui, new Vector2D(0,0));
        Mockito.verify(gui, Mockito.times(1)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }
}
