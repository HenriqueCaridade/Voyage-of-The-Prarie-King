package com.henrique.king.viewer.game;

import com.henrique.image.MyImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Boss;
import com.henrique.king.gui.GUI;
import com.henrique.king.gui.LanternaGUI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BossViewerTester {
    @Test
    void drawTest(){
        Boss boss = Mockito.mock(Boss.class);
        Mockito.when(boss.getPos()).thenReturn(new Vector2D(0,0));
        BossViewer bv = new BossViewer();
        GUI gui = Mockito.mock(LanternaGUI.class);
        bv.draw(boss, gui, new Vector2D(0,0));
        Mockito.verify(gui, Mockito.times(1)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }

    @Test
    void drawNullBossTest(){
        Boss boss = null;
        BossViewer bv = new BossViewer();
        GUI gui = Mockito.mock(LanternaGUI.class);
        bv.draw(boss, gui, new Vector2D(0,0));
        Mockito.verify(gui, Mockito.times(0)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }
}
