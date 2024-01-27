package com.henrique.king.controller.game;

import com.henrique.king.controller.Controller;
import com.henrique.king.game.Arena;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena) {
        super(arena);
    }
}
