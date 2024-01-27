package com.henrique.king.viewer.game;

import com.henrique.image.MyImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.King;
import com.henrique.king.gui.GUI;
import com.henrique.king.gui.LanternaGUI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class KingViewerTester {
    @Test
    void drawTest(){
        King king = Mockito.mock(King.class);
        Mockito.when(king.getPos()).thenReturn(new Vector2D(0,0));
        KingViewer kv = new KingViewer();
        GUI gui = Mockito.mock(LanternaGUI.class);
        kv.draw(king, gui, new Vector2D(0,0));
        Mockito.verify(gui, Mockito.times(1)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }
}
