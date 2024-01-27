package com.henrique.king.game;

import com.henrique.csvReader.CSVReader;
import com.henrique.king.game.elements.*;
import com.henrique.king.game.elements.items.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Arena {
    boolean[][] levelGrid;
    private ArrayList<Vector2D> spawns;
    private ArrayList<Monster> monsters;
    private ArrayList<Bullet> bullets;
    private Vector2D origin;
    ArrayList<Item> droppedItems;
    ArrayList<Item> currItems;
    private final int width, height;
    private Boss boss;
    King king;
    private final long startTime;
    private int score = 0;
    public Arena(int width, int height, Vector2D origin, int levelNumber) throws IOException {
        this.width = width;
        this.height = height;
        this.origin = origin;
        this.startTime = System.currentTimeMillis();
        this.king = new King(new Vector2D(8, 8));
        this.spawns = new ArrayList<>();
        this.droppedItems = new ArrayList<>();
        this.currItems = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.levelGrid = new boolean[16][16];
        setLevel(levelNumber);
    }

    public King getKing() { return king; }

    public ArrayList<Monster> getMonsters() { return monsters; }

    public void setMonsters(ArrayList<Monster> monsters) { this.monsters = monsters; }

    public ArrayList<Item> getDroppedItems() { return droppedItems; }

    public ArrayList<Bullet> getBullets() { return bullets; }

    public void setBullets(ArrayList<Bullet> bullets) { this.bullets = bullets; }

    public Boss getBoss() { return boss; }

    public void setBoss(Boss boss) { this.boss = boss; }

    public void addBullet(Vector2D pos, Vector2D dir){
        bullets.add(new Bullet(pos, dir));
    }

    public Vector2D getOrigin() { return origin; }

    public void setScore(){
        score = (int) (System.currentTimeMillis() - startTime) / 1000;
    }

    public int getScore(){ return score; }

    public void bossSpawn(Vector2D bosspos){
        if(boss != null) return;
        if(Math.random() < 0.05)
            boss = new Boss(bosspos);
    }

    public void dropItems(Vector2D pos){
        if(Math.random() < 0.08){
            switch (new Random().nextInt(3)) {
                case 0:
                    droppedItems.add(new Confuse(pos));
                    break;
                case 1:
                    droppedItems.add(new Wheel(pos));
                    break;
                case 2:
                    droppedItems.add(new Heart(pos));
                    break;
            }
        }
    }

    public void checkItems(){
        for(int i = 0; i < droppedItems.size();){
            if (droppedItems.get(i).despawn())
                droppedItems.remove(i);
            else i++;
        }
        for(int i = 0; i < currItems.size();){
            if (currItems.get(i).ended())
                currItems.remove(i);
            else i++;
        }
    }

    public void useItem(){
        for(int i = 0; i < droppedItems.size();){
            if (king.getPos().equals(droppedItems.get(i).getPos())) {
                Item currItem = droppedItems.get(i);
                currItem.use(this);
                currItems.add(currItem);
                droppedItems.remove(i);
            } else i++;
        }
    }

    public boolean hasItemOf(Class<?> c){
        for(Item i : currItems)
            if(c.isInstance(i))
                return true;
        return false;
    }

    public void spawnMonster(){
        int choice = (int) Math.floor(Math.random() * spawns.size());
        monsters.add(new Monster(spawns.get(choice)));
    }

    public boolean inBounds(Vector2D pos){
        if(pos.getX() < 0 || pos.getX() >= width) return false;
        if(pos.getY() < 0 || pos.getY() >= height) return false;
        return true;
    }

    public boolean canMoveTo(Vector2D pos){
        if(!inBounds(pos)) return false;
        return levelGrid[pos.getY()][pos.getX()];
    }

    void setLevel(int levelNumber) {
        byte[][] auxGrid = getLevelGrid(levelNumber);
        for (int i = 0; i < auxGrid.length; i++) {
            for (int j = 0; j < auxGrid[i].length; j++) {
                byte blockType = auxGrid[i][j];
                levelGrid[i][j] = (blockType != 1 && blockType != 2);
                if(blockType == 2){ // Spawn Block
                    spawns.add(new Vector2D(j, i));
                }
            }
        }
    }

    public byte[][] getLevelGrid(int levelNumber){
        byte[][] auxGrid = new byte[16][16];
        String[][] out = CSVReader.readResource("/levels/" + levelNumber + ".csv");
        if(out == null) { return null; }
        for(int i = 0; i < out.length; i++){
            for(int j = 0; j < out[i].length; j++){
                auxGrid[i][j] = (byte) Integer.parseInt(out[i][j]);
            }
        }
        return auxGrid;
    }
}
