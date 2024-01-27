package com.henrique.king.states;


import com.henrique.king.controller.Controller;
import com.henrique.king.controller.menu.MenuController;
import com.henrique.king.viewer.Viewer;
import com.henrique.king.viewer.menu.Menu;
import com.henrique.king.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    public int getFrameTime() {
        return 1000 / 10; // 10 FPS
    }

    @Override
    protected Viewer<Menu> getViewer() { return new MenuViewer(getModel()); }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}
