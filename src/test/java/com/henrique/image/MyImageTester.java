package com.henrique.image;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyImageTester {
    byte[] byteArraySetup(int width, int height, boolean isAlpha){
        byte[] byteArray = new byte[width * height * (isAlpha ? 4 : 3)];
        Arrays.fill(byteArray, (byte) 0x7F); // ALL GRAY IMAGE
        return byteArray;
    }

    BufferedImage imageSetup(int width, int height, boolean isAlpha){
        BufferedImage image = Mockito.mock(BufferedImage.class);
        WritableRaster aux1 = Mockito.mock(WritableRaster.class);
        DataBufferByte aux2 = Mockito.mock(DataBufferByte.class);
        Mockito.when(aux2.getData()).thenReturn(byteArraySetup(width, height, isAlpha));
        Mockito.when(aux1.getDataBuffer()).thenReturn((DataBufferByte) aux2);
        Mockito.when(image.getRaster()).thenReturn(aux1);
        // Mockito.when(((DataBufferByte) image.getRaster().getDataBuffer()).getData()).thenReturn(...);
        Mockito.when(image.getWidth()).thenReturn(width);
        Mockito.when(image.getHeight()).thenReturn(height);
        Mockito.when(image.getAlphaRaster()).thenReturn(isAlpha ? Mockito.mock(WritableRaster.class) : null);
        return image;
    }

    @Test
    void convert2DAlphaTest(){
        BufferedImage image = imageSetup(10, 10, true); // Gray Semi-Transparent Image Mock
        int value = 0x7F7F7F7F;

        int[][] result = MyImage.convert2D(image);

        assertEquals(result.length, 10);
        assertEquals(result[0].length, 10);

        for (int[] ints : result)
            for (int i : ints)
                assertEquals(i, value);
    }

    @Test
    void convert2DNoAlphaTest(){
        BufferedImage image = imageSetup(10, 10, false); // Gray Image Mock
        int value = 0xFF7F7F7F; // 255 Alpha

        int[][] result = MyImage.convert2D(image);

        assertEquals(result.length, 10);
        assertEquals(result[0].length, 10);

        for (int[] ints : result)
            for (int i : ints)
                assertEquals(i, value);
    }

    @Test
    void getAlphaTest(){
        assertEquals(0, MyImage.getAlpha(0x00FFFFFF));
        assertEquals(255, MyImage.getAlpha(0xFFFFFFFF));
        assertEquals(127, MyImage.getAlpha(0x7FFFFFFF));
        assertEquals(128, MyImage.getAlpha(0x80FFFFFF));
    }

    @Test
    void pixelColor(){
        TextColor color = MyImage.pixelColor(0x00FFFFFF);
        assertEquals(255, color.getRed());
        assertEquals(255, color.getGreen());
        assertEquals(255, color.getBlue());
    }
}
