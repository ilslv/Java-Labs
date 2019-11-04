package com.company;

import java.util.*;

public class Matrix {
    private int randRangeMin = -100;
    private int randRangeMax = 100;
    private ArrayList<ArrayList<Integer>> matrix;

    public Matrix(int dimX, int dimY){
        initMatrix(dimX, dimY);
    }

    public Matrix(int dimX, int dimY, int randRangeMin, int randRangeMax){
        this.randRangeMin = randRangeMin;
        this.randRangeMax = randRangeMax;
        initMatrix(dimX, dimY);
    }

    public int get(int x, int y){
        return this.matrix.get(x).get(y);
    }

    public List<Integer> get(int row){
        return this.matrix.get(row);
    }

    public void set(int x, int y, int value){
        this.matrix.get(x).set(y, value);
    }

    private class rowSumComparator implements Comparator<ArrayList<Integer>>{

        @Override
        public int compare(ArrayList<Integer> row1, ArrayList<Integer> row2) {
            int rowSum1 = 0;
            int rowSum2 = 0;
            for(int element : row1){
                rowSum1 += element;
            }
            for(int element : row2){
                rowSum2 += element;
            }
            return rowSum1 - rowSum2;
        }
    }

    public void sortByRowSum(){
        Collections.sort(this.matrix, new rowSumComparator());
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        for(ArrayList<Integer> row : matrix){
            for(int element : row){
                res.append(String.valueOf(element)).append("\t");
            }
            res.append("\n");
        }
        return res.toString();
    }

    private void initMatrix(int dimX, int dimY){
        Random rand = new Random();
        this.matrix = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i < dimX; i++){
            ArrayList<Integer> randomRow = new ArrayList<Integer>();
            for(int j=0; j < dimY; j++){
                randomRow.add(
                        (int)(randRangeMin + (randRangeMax - randRangeMin) * Math.random())
                );
            }
            this.matrix.add(randomRow);
        }
    }
}
