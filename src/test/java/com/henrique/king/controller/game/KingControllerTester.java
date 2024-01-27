package com.henrique.king.controller.game;

import com.henrique.king.Game;
import com.henrique.king.game.Arena;
import com.henrique.king.game.Vector2D;
import com.henrique.king.game.elements.King;
import com.henrique.king.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.henrique.king.gui.GUI.ACTION.*;

public class KingControllerTester {
    King king;
    KingController kingController;
    Arena arena;
    Game game;

    @BeforeEach
    void setup(){
        king = Mockito.mock(King.class);
        arena = Mockito.mock(Arena.class);
        game = Mockito.mock(Game.class);
        Mockito.when(arena.getKing()).thenReturn(king);
        kingController = Mockito.spy(new KingController(arena));
    }

    @Test
    void tickTest(){
        Mockito.when(king.getPos()).thenReturn(new Vector2D(1, 1));
        GUI.ACTION[] actions = new GUI.ACTION[]{UP};
        kingController.tick(game,actions,150); // Calls method
        Mockito.verify(kingController,Mockito.times(1)).moveKing(Mockito.eq(UP),Mockito.any(long.class));
        kingController.tick(game,actions,200); // Doesn't Call method
        Mockito.verify(kingController,Mockito.times(1)).moveKing(Mockito.eq(UP),Mockito.any(long.class));
    }

    @Test
    void moveKingUPTest(){
        Mockito.when(king.getPos()).thenReturn(new Vector2D(1, 1));
        kingController.moveKing(UP,100);
        Mockito.verify(kingController,Mockito.times(1)).moveKingTo(new Vector2D(1,0));
    }

    @Test
    void moveKingDOWNTest(){
        Mockito.when(king.getPos()).thenReturn(new Vector2D(1, 1));
        kingController.moveKing(DOWN,100);
        Mockito.verify(kingController,Mockito.times(1)).moveKingTo(new Vector2D(1,2));
    }

    @Test
    void moveKingLEFTTest(){
        Mockito.when(king.getPos()).thenReturn(new Vector2D(1, 1));
        kingController.moveKing(LEFT,100);
        Mockito.verify(kingController,Mockito.times(1)).moveKingTo(new Vector2D(0,1));
    }

    @Test
    void moveKingRIGHTTest(){
        Mockito.when(king.getPos()).thenReturn(new Vector2D(1, 1));
        kingController.moveKing(RIGHT,100);
        Mockito.verify(kingController,Mockito.times(1)).moveKingTo(new Vector2D(2,1));
    }

    @Test
    void moveKingUP_LEFTTest(){
        Mockito.when(king.getPos()).thenReturn(new Vector2D(1, 1));
        kingController.moveKing(UP_LEFT,100);
        Mockito.verify(kingController,Mockito.times(1)).moveKingTo(new Vector2D(0,0));
    }

    @Test
    void moveKingUP_RIGHTTest(){
        Mockito.when(king.getPos()).thenReturn(new Vector2D(1, 1));
        kingController.moveKing(UP_RIGHT,100);
        Mockito.verify(kingController,Mockito.times(1)).moveKingTo(new Vector2D(2,0));
    }

    @Test
    void moveKingDOWN_LEFTTest(){
        Mockito.when(king.getPos()).thenReturn(new Vector2D(1, 1));
        kingController.moveKing(DOWN_LEFT,100);
        Mockito.verify(kingController,Mockito.times(1)).moveKingTo(new Vector2D(0,2));
    }

    @Test
    void moveKingDOWN_RIGHTTest(){
        Mockito.when(king.getPos()).thenReturn(new Vector2D(1, 1));
        kingController.moveKing(DOWN_RIGHT,100);
        Mockito.verify(kingController,Mockito.times(1)).moveKingTo(new Vector2D(2,2));
    }

    @Test
    void moveKingToCanMoveTest(){
        Vector2D pos = Mockito.mock(Vector2D.class);
        Mockito.when(arena.canMoveTo(pos)).thenReturn(true);
        kingController.moveKingTo(pos);
        Mockito.verify(king,Mockito.times(1)).setPos(pos);
    }

    @Test
    void moveKingToCannotMoveTest(){
        Vector2D pos = Mockito.mock(Vector2D.class);
        Mockito.when(arena.canMoveTo(pos)).thenReturn(false);
        kingController.moveKingTo(pos);
        Mockito.verify(king,Mockito.times(0)).setPos(pos);
    }

}
