
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nnnnew
 */
public class BoardStage {
    private int[] board;
    
    public BoardStage(int[] board) {
        this.board = board;
    }
    
    public boolean isGoalStage() {
        int[] goalStage = {1, 2, 3, 8, 0, 4, 7, 6, 5};
        if(Arrays.equals(getBoard(), goalStage)) {
            return true;
        }
        return false;
    }
    
    public void printStage() {
        for(int i = 1; i <= 9; ++i) {
            System.out.print(getBoard()[i - 1] + " ");
            if(i % 3 == 0 ) {
                System.out.println();
            }
        }
        System.out.println();
    }
    
    public int findIndexSpace() {
        for(int i = 0; i < 9; ++i) {
            if(board[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public int[] getBoard() {
        return board;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }
    
}
