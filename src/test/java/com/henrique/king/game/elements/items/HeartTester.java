package com.henrique.king.game.elements.items;

import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.King;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;

public class HeartTester {
    @Test
    public void UseIncreaseLifeTest(){
        Arena ar = Mockito.mock(Arena.class);
        King k = Mockito.mock(King.class);
        Heart heart = new Heart(Mockito.mock(Vector2D.class));
        Mockito.when(ar.getKing()).thenReturn(k);
        Mockito.when(k.getNumberOfLives()).thenReturn(3);
        heart.use(ar);
        Mockito.verify(k,Mockito.times(1)).increaseLife();
    }

    @Test
    public void UseNoIncreaseLifeTest(){
        Arena ar = Mockito.mock(Arena.class);
        King k = Mockito.mock(King.class);
        Heart heart = new Heart(Mockito.mock(Vector2D.class));
        Mockito.when(ar.getKing()).thenReturn(k);
        Mockito.when(k.getNumberOfLives()).thenReturn(5);
        heart.use(ar);
        Mockito.verify(k,Mockito.times(0)).increaseLife();
    }
}
