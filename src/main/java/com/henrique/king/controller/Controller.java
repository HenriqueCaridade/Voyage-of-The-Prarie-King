package com.henrique.king.controller;

import com.henrique.king.Game;
import com.henrique.king.gui.GUI;

import java.io.IOException;

abstract public class Controller<T> {
    private final T model;
    public Controller(T model) {
        this.model = model;
    }
    public T getModel() {
        return model;
    }
    public abstract void tick(Game game, GUI.ACTION[] actions, long time) throws IOException;
}
