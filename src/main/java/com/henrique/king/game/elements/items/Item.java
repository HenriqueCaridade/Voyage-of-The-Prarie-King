package com.henrique.king.game.elements.items;

import com.henrique.king.game.Arena;
import com.henrique.king.game.elements.Element;

abstract public class Item extends Element {
    private static final long timeUntilDespawn = 15000;
    private boolean caught = false;
    private long endTime, duration;
    public Item(long duration){
        this.duration = duration;
        endTime = System.currentTimeMillis() + timeUntilDespawn;
    }
    public boolean despawn(){
        if(caught) return true;
        return endTime < System.currentTimeMillis();
    }
    public boolean ended(){
        if(!caught) return false;
        return endTime < System.currentTimeMillis();
    }
    public void use(Arena arena){
        caught = true;
        endTime = System.currentTimeMillis() + duration;
    }
}
