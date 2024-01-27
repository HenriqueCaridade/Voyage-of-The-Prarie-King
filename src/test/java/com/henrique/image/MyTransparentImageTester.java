package com.henrique.image;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTransparentImageTester {
    @Test
    void defaultConstructorTest(){
        MyTransparentImage img = new MyTransparentImage();
        assertEquals(0, img.width);
        assertEquals(0, img.height);
        assertTrue(img.pixels.isEmpty());
    }

    @Test
    void constructorWhiteTest(){
        MyTransparentImage img = new MyTransparentImage("white.png"); // White 16x16 image
        assertEquals(16, img.width);
        assertEquals(16, img.height);
        assertFalse(img.pixels.isEmpty());
        TextColor textColor = TextColor.Factory.fromString("#FFFFFF");
        assertEquals(1, img.pixels.size());
        assertTrue(img.pixels.containsKey(textColor));
        assertEquals(16 * 16, img.pixels.get(textColor).size()); // Amount of white pixels is 16x16
    }

    @Test
    void constructorBWTest(){
        MyTransparentImage img = new MyTransparentImage("bw.png"); // Black and White Halves 16x16 image
        assertEquals(16, img.width);
        assertEquals(16, img.height);
        assertFalse(img.pixels.isEmpty());
        TextColor whiteColor = TextColor.Factory.fromString("#FFFFFF");
        TextColor blackColor = TextColor.Factory.fromString("#000000");
        assertEquals(2, img.pixels.size());
        assertTrue(img.pixels.containsKey(whiteColor));
        assertTrue(img.pixels.containsKey(blackColor));
        assertEquals(16 * 8, img.pixels.get(whiteColor).size());
        assertEquals(16 * 8, img.pixels.get(blackColor).size());
    }

    @Test
    void constructorTransparencyTest(){
        MyTransparentImage img = new MyTransparentImage("transp.png"); // White and Transparent Halves 16x16 image
        assertEquals(16, img.width);
        assertEquals(16, img.height);
        assertFalse(img.pixels.isEmpty());
        TextColor whiteColor = TextColor.Factory.fromString("#FFFFFF");
        assertEquals(1, img.pixels.size());
        assertTrue(img.pixels.containsKey(whiteColor));
        assertEquals(16 * 8, img.pixels.get(whiteColor).size());
    }

    @Test
    void constructorNonExistentFileTest() {
        MyTransparentImage img = new MyTransparentImage("nonexistent.png");
        assertTrue(img.pixels.isEmpty());
    }

    @Test
    void drawTest(){
        MyTransparentImage img = new MyTransparentImage("transp.png"); // White and Transparent Halves 16x16 image
        TextColor whiteColor = TextColor.Factory.fromString("#FFFFFF");
        TextGraphics tg = Mockito.mock(TextGraphics.class);
        img.draw(tg, 0, 0);
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(whiteColor);
        Mockito.verify(tg, Mockito.times(8 * 16)).setCharacter(Mockito.any(int.class), Mockito.any(int.class), Mockito.same(' '));
    }
}
