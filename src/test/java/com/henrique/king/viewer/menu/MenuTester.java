package com.henrique.king.viewer.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTester {
    @Test
    void getEntryTest(){
        Menu m = new Menu();
        assertEquals("PLAY", m.getEntry(0));
        assertEquals("EXIT", m.getEntry(1));
        assertEquals(2, m.getNumberEntries());
    }

    @Test
    void nextEntryTest(){
        Menu m = new Menu();
        assertTrue(m.isSelectedPlay());
        m.nextEntry();
        assertTrue(m.isSelectedExit());
        m.nextEntry();
        assertTrue(m.isSelectedPlay());
    }

    @Test
    void previousEntryTest(){
        Menu m = new Menu();
        assertTrue(m.isSelectedPlay());
        m.previousEntry();
        assertTrue(m.isSelectedExit());
        m.previousEntry();
        assertTrue(m.isSelectedPlay());
    }
}
