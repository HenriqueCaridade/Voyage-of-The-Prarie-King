package com.henrique.image;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyBasicImageTester {
    @Test
    void defaultConstructorTest(){
        MyBasicImage img = new MyBasicImage();
        assertEquals(0, img.width);
        assertEquals(0, img.height);
    }

    @Test
    void constructorWhiteTest(){
        MyBasicImage img = new MyBasicImage("white.png"); // White 16x16 image
        assertEquals(16, img.width);
        assertEquals(16, img.height);
        TextCharacter auxChar = TextCharacter.fromCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#FFFFFF"))[0];
        for(int i = 0; i < img.width; i++)
            for(int j = 0; j < img.height; j++)
                assertEquals(auxChar, img.textImage.getCharacterAt(i, j));
    }

    @Test
    void constructorBWTest(){
        MyBasicImage img = new MyBasicImage("bw.png"); // Black and White Halves 16x16 image
        assertEquals(16, img.width);
        assertEquals(16, img.height);
        TextCharacter whiteChar = TextCharacter.fromCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#FFFFFF"))[0];
        TextCharacter blackChar = TextCharacter.fromCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#000000"))[0];
        for(int i = 0; i < img.width; i++)
            for(int j = 0; j < img.height; j++)
                assertEquals((i < 8) ? blackChar : whiteChar, img.textImage.getCharacterAt(i, j));
    }

    @Test
    void constructorTransparencyTest(){
        MyBasicImage img = new MyBasicImage("transp.png"); // White and Transparent Halves 16x16 image
        assertEquals(16, img.width);
        assertEquals(16, img.height);
        TextCharacter whiteChar = TextCharacter.fromCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#FFFFFF"))[0];
        TextCharacter defaultChar = TextCharacter.DEFAULT_CHARACTER;
        for(int i = 0; i < img.width; i++)
            for(int j = 0; j < img.height; j++)
                assertEquals((i < 8) ? whiteChar : defaultChar, img.textImage.getCharacterAt(i, j));
    }

    @Test
    void constructorFillerTest(){
        TextColor fillColor = TextColor.Factory.fromString("#123456");
        MyBasicImage img = new MyBasicImage("transp.png", fillColor); // White and Transparent Halves 16x16 image
        assertEquals(16, img.width);
        assertEquals(16, img.height);
        TextCharacter whiteChar = TextCharacter.fromCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#FFFFFF"))[0];
        TextCharacter fillChar = TextCharacter.fromCharacter(' ', TextColor.Factory.fromString("#000000"), fillColor)[0];
        for(int i = 0; i < img.width; i++)
            for(int j = 0; j < img.height; j++)
                assertEquals((i < 8) ? whiteChar : fillChar, img.textImage.getCharacterAt(i, j));
    }

    @Test
    void constructorNonExistentFileTest(){
        MyBasicImage img = new MyBasicImage("nonexistent.png");
        assertNull(img.textImage);
    }

    @Test
    void constructorFillerNonExistentFileTest(){
        MyBasicImage img = new MyBasicImage("nonexistent.png", null);
        assertNull(img.textImage);
    }

    @Test
    void addImageTest(){
        MyBasicImage img = new MyBasicImage("bw.png"); // Black and White Halves 16x16 image
        MyBasicImage imgToAdd = new MyBasicImage("bw.png");
        img.addImage(imgToAdd, 16, 0); // Black and White Quarters 32x16 image
        assertEquals(32, img.width);
        assertEquals(16, img.height);
        TextCharacter whiteChar = TextCharacter.fromCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#FFFFFF"))[0];
        TextCharacter blackChar = TextCharacter.fromCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#000000"))[0];
        for(int i = 0; i < img.width; i++)
            for(int j = 0; j < img.height; j++)
                assertEquals(((i % 16) < 8) ? blackChar : whiteChar, img.textImage.getCharacterAt(i, j));
    }

    @Test
    void renderFontTest(){
        MyBasicImage img = MyBasicImage.renderFont("IL"); // Rendered IL String 12x8 image
        // I - 5x8 image,  L - 6x8 image
        assertEquals(5 + 6 + 1, img.width);
        assertEquals(8, img.height);
        TextCharacter whiteChar = TextCharacter.fromCharacter(' ', TextColor.Factory.fromString("#000000"), TextColor.Factory.fromString("#FFFFFF"))[0];
        TextCharacter defaultChar = TextCharacter.DEFAULT_CHARACTER;
        int[][] whiteIPixels = {{0,0},{1,0},{2,0},{3,0},{4,0},{0,7},{1,7},{2,7},{3,7},{4,7},{2,1},{2,2},{2,3},{2,4},{2,5},{2,6}}; // I Pixels
        int[][] whiteLPixels = {{6,0},{6,1},{6,2},{6,3},{6,4},{6,5},{6,6},{6,7},{7,7},{8,7},{9,7},{10,7},{11,7}}; // L Pixels
        for(int i = 0; i < 5; i++) // I Verification
            for(int j = 0; j < img.height; j++) {
                boolean validPixel = false;
                for(int[] pixel : whiteIPixels)
                    if(pixel[0] == i && pixel[1] == j){
                        validPixel = true;
                        break;
                    }
                assertEquals(validPixel ? whiteChar : defaultChar, img.textImage.getCharacterAt(i, j));
            }
        for(int j = 0; j < img.height; j++) // Separation Verification
            assertEquals(defaultChar, img.textImage.getCharacterAt(5, j));
        for(int i = 6; i < img.width; i++) // L Verification
            for(int j = 0; j < img.height; j++) {
                boolean validPixel = false;
                for(int[] pixel : whiteLPixels)
                    if(pixel[0] == i && pixel[1] == j){
                        validPixel = true;
                        break;
                    }
                assertEquals(validPixel ? whiteChar : defaultChar, img.textImage.getCharacterAt(i, j));
            }
    }

    @Test
    void drawTest(){
        MyBasicImage img = new MyBasicImage();
        TextGraphics tg = Mockito.mock(TextGraphics.class);
        img.draw(tg, 0, 0);
        Mockito.verify(tg, Mockito.times(1)).drawImage(Mockito.any(TerminalPosition.class), Mockito.any(BasicTextImage.class));
    }
}
