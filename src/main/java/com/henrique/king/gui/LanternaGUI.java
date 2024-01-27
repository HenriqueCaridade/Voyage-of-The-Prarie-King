package com.henrique.king.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.henrique.image.MyImage;
import com.henrique.king.game.Vector2D;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class LanternaGUI implements GUI {
    private final Screen screen;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height, String windowName) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal, windowName);
    }

    private Screen createScreen(Terminal terminal, String windowName) throws IOException {
        final Screen screen;
        ((AWTTerminalFrame) terminal).setTitle(windowName);
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);

        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 2);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }

    public ACTION[] getNextActions() throws IOException {
        KeyStroke key = screen.pollInput();
        if (key == null) return new ACTION[]{ACTION.NONE};

        Vector2D kingDirection = Vector2D.V_NULL;
        Vector2D bulletDir = Vector2D.V_NULL;
        do {
            if (key.getKeyType() == KeyType.EOF) return new ACTION[]{ACTION.QUIT};
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') return new ACTION[]{ACTION.QUIT};
            if (key.getKeyType() == KeyType.Enter) return new ACTION[]{ACTION.SELECT};

            switch (key.getKeyType()) {
                case ArrowUp:
                    kingDirection = kingDirection.add(Vector2D.V_UP);
                    break;
                case ArrowDown:
                    kingDirection = kingDirection.add(Vector2D.V_DOWN);
                    break;
                case ArrowLeft:
                    kingDirection = kingDirection.add(Vector2D.V_LEFT);
                    break;
                case ArrowRight:
                    kingDirection = kingDirection.add(Vector2D.V_RIGHT);
                    break;
                case Character:
                    switch (key.getCharacter()) {
                        case 'w': // UP
                            bulletDir = bulletDir.add(Vector2D.V_UP);
                            break;
                        case 's': // DOWN
                            bulletDir = bulletDir.add(Vector2D.V_DOWN);
                            break;
                        case 'a': // LEFT
                            bulletDir = bulletDir.add(Vector2D.V_LEFT);
                            break;
                        case 'd': // RIGHT
                            bulletDir = bulletDir.add(Vector2D.V_RIGHT);
                            break;
                    }
                    break;
            }
        } while((key = screen.pollInput()) != null);
        kingDirection = kingDirection.euclideanNormalise();
        bulletDir = bulletDir.euclideanNormalise();
        ArrayList<ACTION> res = new ArrayList<>();
        if(kingDirection.equals(Vector2D.V_UP)){
            res.add(ACTION.UP);
        } else if(kingDirection.equals(Vector2D.V_DOWN)){
            res.add(ACTION.DOWN);
        } else if(kingDirection.equals(Vector2D.V_LEFT)){
            res.add(ACTION.LEFT);
        } else if(kingDirection.equals(Vector2D.V_RIGHT)){
            res.add(ACTION.RIGHT);
        } else if(kingDirection.equals(Vector2D.V_UP_LEFT)){
            res.add(ACTION.UP_LEFT);
        } else if(kingDirection.equals(Vector2D.V_UP_RIGHT)){
            res.add(ACTION.UP_RIGHT);
        } else if(kingDirection.equals(Vector2D.V_DOWN_LEFT)){
            res.add(ACTION.DOWN_LEFT);
        } else if(kingDirection.equals(Vector2D.V_DOWN_RIGHT)){
            res.add(ACTION.DOWN_RIGHT);
        }
        if(bulletDir.equals(Vector2D.V_UP)){
            res.add(ACTION.SHOOT_UP);
        } else if(bulletDir.equals(Vector2D.V_DOWN)){
            res.add(ACTION.SHOOT_DOWN);
        } else if(bulletDir.equals(Vector2D.V_LEFT)){
            res.add(ACTION.SHOOT_LEFT);
        } else if(bulletDir.equals(Vector2D.V_RIGHT)){
            res.add(ACTION.SHOOT_RIGHT);
        } else if(bulletDir.equals(Vector2D.V_UP_LEFT)){
            res.add(ACTION.SHOOT_UP_LEFT);
        } else if(bulletDir.equals(Vector2D.V_UP_RIGHT)){
            res.add(ACTION.SHOOT_UP_RIGHT);
        } else if(bulletDir.equals(Vector2D.V_DOWN_LEFT)){
            res.add(ACTION.SHOOT_DOWN_LEFT);
        } else if(bulletDir.equals(Vector2D.V_DOWN_RIGHT)){
            res.add(ACTION.SHOOT_DOWN_RIGHT);
        }
        ACTION[] aux = new ACTION[res.size()];
        for(int i = 0; i < res.size(); i++)
            aux[i] = res.get(i);
        return aux;
    }

    @Override
    public void drawImage(MyImage img, Vector2D pos) {
        img.draw(screen.newTextGraphics(), pos.getX(), pos.getY());
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
