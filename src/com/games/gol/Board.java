package com.games.gol;
import javax.swing.*;
import java.awt.*;

import java.util.Random;

/**
 * Created by ayoa on 19/05/2016.
 */
class Board extends JPanel {

    private int count;
    private int width;
    private int height;
    private int[][] board;

    private Random randomGenerator = new Random();

    public Board(int width, int height ){
        this.setBackground(Color.DARK_GRAY);
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
        generateRandomCells();

    }

    private void generateRandomCells() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (randomGenerator.nextInt(100) > 91) {  //generating integers in range 0..99
                    board[i][j] = 1;
                }
            }
        }
    }

    public void updateGeneration(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height ; j++){
                applyRules(i, j);
            }
        }
    }

    /**
     * Conway's Game of Life Rules:
     * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
     * Any live cell with two or three live neighbours lives on to the next generation.
     * Any live cell with more than three live neighbours dies, as if by over-population.
     * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
     */
    private void applyRules(int x, int y){

        int neighbours = countNumberOfLivingCells(x,y);

        if(board[x][y] == 1 && neighbours < 2){
            board[x][y] = 0;
        }

        if(board[x][y] == 1 && (neighbours == 2 || neighbours == 3)){
            board[x][y] = 1;
        }

        if(board[x][y] == 1 && neighbours > 3){
            board[x][y] = 0;
        }

        if(board[x][y] == 0 && neighbours == 3 ){
            board[x][y] = 1;
        }

    }

    private int countNumberOfLivingCells(int x, int y){
        int countCells = 0;


        if(x > 0 && y > 0 && board[x-1][y-1] == 1){
            countCells ++;
        }

        if(x > 0 && board[x-1][y] == 1){
            countCells ++;
        }

        if(x > 0 && y + 1 < height && board[x-1][y+1] == 1){
            countCells ++;
        }

        if(y > 0 && board[x][y-1] == 1){
            countCells ++;
        }

        if(y + 1 < height && board[x][y+1] == 1){
            countCells ++;
        }

        if(y > 0 && x + 1 < width && board[x+1][y-1] == 1){
            countCells ++;
        }

        if(x + 1 < width && board[x+1][y] == 1){
            countCells ++;
        }

        if(x + 1 < width && y + 1 < height  && board[x+1][y+1] == 1){
            countCells ++;
        }

        return countCells;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.drawString("Generation: " + count++, 0, height);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    g.setColor(Color.green);
//                    g.fillRect(i,j,1,1);
                    g.fillRect(j * 4, i * 4, 4, 4);
                }
            }
        }
    }
}