package com.henrique.king.viewer.game;

import com.henrique.image.MyImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Monster;
import com.henrique.king.gui.GUI;
import com.henrique.king.gui.LanternaGUI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MonsterViewerTester {
    @Test
    void drawTest(){
        Monster monster = Mockito.mock(Monster.class);
        Mockito.when(monster.getPos()).thenReturn(new Vector2D(0,0));
        MonsterViewer mv = new MonsterViewer();
        GUI gui = Mockito.mock(LanternaGUI.class);
        mv.draw(monster, gui, new Vector2D(0,0));
        Mockito.verify(gui, Mockito.times(1)).drawImage(Mockito.any(MyImage.class), Mockito.any(Vector2D.class));
    }
}
