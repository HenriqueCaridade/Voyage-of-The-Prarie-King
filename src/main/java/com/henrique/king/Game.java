package com.henrique.king;

import com.henrique.king.gui.LanternaGUI;
import com.henrique.king.states.MenuState;
import com.henrique.king.states.State;
import com.henrique.king.viewer.menu.Menu;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private State state;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(288, 288, "Voyage of The Prairie King");
        this.state = new MenuState(new Menu());
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        new Game().run();
    }

    public void setState(State state) { this.state = state; }

    public void run() throws IOException {

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.tick(this, gui, startTime);

            if(state == null) break;
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = state.getFrameTime() - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {}
        }

        gui.close();
    }
}
