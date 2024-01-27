package com.henrique.king.states;


import com.henrique.king.controller.Controller;
import com.henrique.king.controller.game.ArenaController;
import com.henrique.king.game.Arena;
import com.henrique.king.viewer.Viewer;
import com.henrique.king.viewer.game.GameViewer;

public class GameState extends State<Arena> {
    public GameState(Arena arena) {
        super(arena);
    }

    public int getFrameTime(){
        return 1000 / 10; // 10 FPS
    }

    @Override
    protected Viewer<Arena> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }
}
