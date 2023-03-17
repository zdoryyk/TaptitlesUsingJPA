package com.example.Taptitles.spring.models.logicModel;

import com.example.Taptitles.spring.controllers.CommentController;
import com.example.Taptitles.spring.controllers.PlayerController;
import com.example.Taptitles.spring.controllers.RatingController;
import com.example.Taptitles.spring.controllers.ScoreController;
import org.springframework.context.ApplicationContext;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field {

    private final int rows;
    private final int cols;

    private static String[][] array;
    private int size;

    private  CommentController commentController;

    private PlayerController playerController;

    private RatingController ratingController;

    private ScoreController scoreController;

    public Field(int x, int y, ApplicationContext applicationContext) {
        this.rows = x;
        this.cols = y;
        size = x;
        array = generateField();
        ratingController = applicationContext.getBean(RatingController.class);
        playerController = applicationContext.getBean(PlayerController.class);
        scoreController = applicationContext.getBean(ScoreController.class);
        commentController = applicationContext.getBean(CommentController.class);
    }


    private String[][] generateField(){
        int size = rows % 2 == 0 ? rows : cols;

        String[][] field = new String[rows][cols];
        String[] symbols = {"1","2","3","4","5","6","7","8","9","K","J","F","O","M"};

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                field[i][j] = "0";
            }
        }
        int row, col;

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                do {
                    row = new Random().nextInt(100) % size;
                    col = new Random().nextInt(100) % size;
                } while (!field[row][col].equals("0"));
                field[row][col] = symbols[i];
            }
        }
        return field;
    }


    public void show(){
        System.out.print(" ");
        for(int i = 0; i < cols; i++){
            System.out.print("   " + (char)(i + 'A'));
        }
        System.out.println();
        for(int i = 0; i < rows; i++)
        {
            System.out.print((char)(i + 'A') + "  ¦");
            for(int j = 0; j < cols; j++)
            {
                System.out.print(array[i][j] + " ¦ ");
            }
            System.out.println();
        }
        System.out.println("ENTER <X> to EXIT \n");
    }

    public boolean handleInput() {

            show();
            System.out.print("Input what u wanna replace (example AA->AB): ");

            Scanner in = new Scanner(System.in);
            String input = in.next();
            if(input.equals("X")){
                for(int i = 0; i < size; i++){
                    for(int j = 0; j < size; j++)
                    {
                        array[i][j] = " ";
                    }
                }
                return false;
            }
            Pattern p;
            switch (size) {
                case 4 ->  {p = Pattern.compile("^[A-D]{2}(->)[A-D]{2}$");}
                case 5 -> {
                    p = Pattern.compile("^[A-E]{2}(->)[A-E]{2}$");
                }
                case 6 -> {
                    p = Pattern.compile("^[A-F]{2}(->)[A-F]{2}$");
                }
                case 7 -> {
                    p = Pattern.compile("^[A-G]{2}(->)[A-G]{2}$");
                }
                case 8 -> {
                    p = Pattern.compile("^[A-H]{2}(->)[A-H]{2}$");
                }
                case 9 -> {
                    p = Pattern.compile("^[A-I]{2}(->)[A-I]{2}$");
                }
                default -> {
                    p = Pattern.compile("^[A-C]{2}(->)[A-C]{2}$");
                }
            }

            Matcher m = p.matcher(input);
            if(input.charAt(0) == input.charAt(4) && input.charAt(1) == input.charAt(5))handleInput();
            if (!m.matches()) handleInput();

            int startRow = input.charAt(0) - 'A';
            int startCol = input.charAt(1) - 'A';
            int endRow   = input.charAt(4) - 'A';
            int endCol   = input.charAt(5) - 'A';

            String temp1 = array[startRow][startCol], temp2 = array[endRow][endCol];


            if(array[startRow][startCol].equals(array[endRow][endCol])) {
                array[startRow][startCol] = " ";
                array[endRow][endCol] = " ";
                if (isReachable(startRow, startCol, endRow, endCol)) {
                    array[startRow][startCol] = " ";
                    array[endRow][endCol] = " ";
                    return true;
                } else {
                    array[startRow][startCol] = temp1;
                    array[endRow][endCol] = temp2;
                }
                 if(startCol == endCol && (startCol == 0 || startCol == size -1)){
                    array[startRow][startCol] = " ";
                    array[endRow][endCol] = " ";
                    return true;
                }else if(startRow == endRow && (startRow == 0 || startRow == size -1)){
                    array[startRow][startCol] = " ";
                    array[endRow][endCol] = " ";
                    return true;
                }
            }
            return false;
    }



    public State getState(){
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
                if(!Objects.equals(array[i][j], " ")) return State.PLAYING;

        }
        array = generateField();
        return State.SOLVED;
    }



    private boolean isReachable(int startRow, int startCol, int endRow, int endCol) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{startRow, startCol});

        boolean[][] visited = new boolean[array.length][array[0].length];
        visited[startRow][startCol] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currRow = curr[0];
            int currCol = curr[1];

            if (currRow == endRow && currCol == endCol) {
                return true;
            }

            for (int[] dir : directions) {
                int nextRow = currRow + dir[0];
                int nextCol = currCol + dir[1];

                if (nextRow >= 0 && nextRow < array.length && nextCol >= 0 && nextCol < array[0].length
                        && !visited[nextRow][nextCol] && array[nextRow][nextCol].equals(" ")) {

                    visited[nextRow][nextCol] = true;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }
        return false;
    }


}
