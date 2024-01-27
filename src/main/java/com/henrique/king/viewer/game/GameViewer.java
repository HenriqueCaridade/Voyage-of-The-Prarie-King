package com.henrique.king.viewer.game;

import com.googlecode.lanterna.TextColor;
import com.henrique.image.MyBasicImage;
import com.henrique.image.MyImage;
import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.Element;
import com.henrique.king.game.elements.items.Heart;
import com.henrique.king.gui.GUI;
import com.henrique.king.viewer.Viewer;

import java.util.List;

public class GameViewer extends Viewer<Arena> {
    private static final TextColor backgroundColor = TextColor.Factory.fromString("#efbb51");
    public static final MyImage heartImg = new MyBasicImage("heart16.png");
    public final MyImage mapImg;
    public GameViewer(Arena arena) {
        super(arena);
        String[] imageNames = {"back1.png", "bush.png", "spawn.png", "border.png", "back2.png"};
        MyBasicImage[] images = new MyBasicImage[imageNames.length];
        for(int i = 0; i < images.length; i++)
            images[i] = new MyBasicImage(imageNames[i], backgroundColor);
        mapImg = getMapImage(images);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawImage(mapImg, getModel().getOrigin());
        MyBasicImage scoreImg = MyBasicImage.renderFont(Integer.toString(getModel().getScore()));
        gui.drawImage(scoreImg, new Vector2D((288 - scoreImg.getWidth()) / 2, 4));
        drawElements(gui, getModel().getDroppedItems(), new ItemViewer());
        drawElements(gui, getModel().getMonsters(), new MonsterViewer());
        drawElement(gui, getModel().getBoss(), new BossViewer());
        drawElements(gui, getModel().getBullets(), new BulletViewer());
        drawElement(gui, getModel().getKing(), new KingViewer());
        drawHearts(gui);
    }

    void drawHearts(GUI gui){
        Vector2D origin = getModel().getOrigin();
        for(int i = 0; i < getModel().getKing().getNumberOfLives(); i++)
            gui.drawImage(heartImg, origin.add(new Vector2D(16 * i, 256)));
    }

    <T extends Element> void drawElements(GUI gui, List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(gui, element, viewer);
    }

    <T extends Element> void drawElement(GUI gui, T element, ElementViewer<T> viewer) {
        if(element instanceof Heart)
            viewer.draw(element, gui, getModel().getOrigin().add(new Vector2D(4, 4)));
        else
            viewer.draw(element, gui, getModel().getOrigin());
    }

    private MyBasicImage getMapImage(MyBasicImage[] images) {
        MyBasicImage img = new MyBasicImage();
        byte[][] auxGrid = getModel().getLevelGrid(1);
        for (int i = 0; i < auxGrid.length; i++) {
            for (int j = 0; j < auxGrid[i].length; j++) {
                byte blockType = auxGrid[i][j];
                img.addImage(images[blockType], 16 * j, 16 * i);
            }
        }
        return img;
    }
}
