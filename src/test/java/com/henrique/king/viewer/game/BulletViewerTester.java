package com.henrique.king.viewer.game;

import com.henrique.image.MyImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Bullet;
import com.henrique.king.gui.GUI;
import com.henrique.king.gui.LanternaGUI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BulletViewerTester {
    @Test
    void drawTest(){
        Bullet bullet = Mockito.mock(Bullet.class);
        Mockito.when(bullet.getPos()).thenReturn(new Vector2D(0,0));
        BulletViewer bv = new BulletViewer();
        GUI gui = Mockito.mock(LanternaGUI.class);
        bv.draw(bullet, gui, new Vector2D(0,0));
        Mockito.verify(gui, Mockito.times(1)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }
}