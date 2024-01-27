package com.henrique.king.states;

import com.henrique.king.controller.Controller;
import com.henrique.king.Game;
import com.henrique.king.gui.GUI;
import com.henrique.king.viewer.Viewer;

import java.io.IOException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController();

    abstract public int getFrameTime();

    public T getModel() {
        return model;
    }

    public void tick(Game game, GUI gui, long time) throws IOException {
        GUI.ACTION[] actions = gui.getNextActions();
        controller.tick(game, actions, time);
        viewer.draw(gui);
    }
}
