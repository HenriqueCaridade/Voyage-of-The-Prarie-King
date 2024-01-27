package com.henrique.king.viewer.menu;

import com.henrique.image.MyBasicImage;
import com.henrique.image.MyImage;
import com.henrique.king.game.Vector2D;
import com.henrique.king.gui.GUI;
import com.henrique.king.viewer.Viewer;

public class MenuViewer extends Viewer<Menu> {
    private static final MyImage logo = new MyBasicImage("VOPK_logo.png");
    private static final MyImage playText = MyBasicImage.renderFont("PLAY");
    private static final MyImage exitText = MyBasicImage.renderFont("EXIT");
    private static final MyImage selector = new MyBasicImage("selector.png");
    private final int playX, exitX;

    public MenuViewer(Menu menu) {
        super(menu);
        playX = 144 - playText.getWidth() / 2;
        exitX = 144 - exitText.getWidth() / 2;
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawImage(logo, new Vector2D(0, 4));
        gui.drawImage(playText, new Vector2D(playX, 240));
        gui.drawImage(exitText, new Vector2D(exitX, 260));
        if(getModel().isSelectedPlay())
            gui.drawImage(selector, new Vector2D(playX - 8, 240));
        else
            gui.drawImage(selector, new Vector2D(exitX - 8, 260));
    }
}
