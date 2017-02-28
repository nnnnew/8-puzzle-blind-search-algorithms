
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nnnnew
 */
public class BFSearch {
    private Queue<Node> queueNode;
    private LinkedList<int[]> wasMoveStage;
    private Node root;
    
    public BFSearch(int[] initialBoard) {
        BoardStage newBoardStage = new BoardStage(initialBoard);
        root = new Node(newBoardStage);
        queueNode = new LinkedList<>();
        wasMoveStage = new LinkedList<>();
        wasMoveStage.add(initialBoard);
        queueNode.add(root);
    }
    
    public void search() {
        if(root.getStageBoard().isGoalStage()) {
            System.out.println("Stage level solving is " + 1);
            root.getStageBoard().printStage();
        }
        else {
            searching(queueNode.poll());
        }
    }
    
    private void printPath(Node curNode) {
        Stack<BoardStage> path = getPath(curNode);
        while(!path.isEmpty()) {
            path.pop().printStage();
        }
    }
    
    private Stack<BoardStage> getPath(Node goalNode) {
        Node trav = goalNode;
        Stack<BoardStage> stackPath = new Stack<>();
        int stage = 0;
        while(trav != null) {
            ++stage;
            stackPath.add(trav.getStageBoard());
            trav = trav.prevNode;
        }
        System.out.println("Stage level solving is " + stage);
        return stackPath;
    }
    
    private void searching(Node curNode) {
        while(true) {
            moveStage(curNode);
            curNode = queueNode.poll();
            curNode.getStageBoard().printStage();
        }
    }
    
    private void addToQueue(ArrayList<BoardStage> arrBoardMove, Node curNode) {
        for(int i = 0; i < arrBoardMove.size(); ++i) {
            BoardStage boardStage = arrBoardMove.get(i);
            //boardStage.printStage();
            if(!wasMoveStage(boardStage.getBoard())) {
                Node newNode = new Node(boardStage, curNode);
                curNode.child.add(newNode);
                queueNode.add(newNode);
                wasMoveStage.add(boardStage.getBoard());
                if(boardStage.isGoalStage()) {
                    printPath(newNode);
                    System.exit(0);
                }
            }
        }
    }
    
    private boolean wasMoveStage(int[] board) {
        for(int i = 0; i < wasMoveStage.size(); ++i) {
            if(Arrays.equals(board, wasMoveStage.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    private void moveStage(Node curNode) {
        int indexSpace = curNode.getStageBoard().findIndexSpace();
        ArrayList<BoardStage> tempBoardMove = new ArrayList<>();
        
        if(indexSpace == 0) {
            tempBoardMove.add(new BoardStage(swap(0, 1, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(0, 3, curNode.getStageBoard().getBoard().clone())));
        }
        else if(indexSpace == 1) {
            tempBoardMove.add(new BoardStage(swap(1, 0, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(1, 2, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(1, 4, curNode.getStageBoard().getBoard().clone())));
        }
        else if(indexSpace == 2) {
            tempBoardMove.add(new BoardStage(swap(2, 1, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(2, 5, curNode.getStageBoard().getBoard().clone())));
        }
        else if(indexSpace == 3) {
            tempBoardMove.add(new BoardStage(swap(3, 0, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(3, 4, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(3, 6, curNode.getStageBoard().getBoard().clone())));
        }
        else if(indexSpace == 4) {
            tempBoardMove.add(new BoardStage(swap(4, 1, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(4, 3, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(4, 5, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(4, 7, curNode.getStageBoard().getBoard().clone())));
        }
        else if(indexSpace == 5) {
            tempBoardMove.add(new BoardStage(swap(5, 2, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(5, 4, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(5, 8, curNode.getStageBoard().getBoard().clone())));
        }
        else if(indexSpace == 6) {
            tempBoardMove.add(new BoardStage(swap(6, 3, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(6, 7, curNode.getStageBoard().getBoard().clone())));
        }
        else if(indexSpace == 7) {
            tempBoardMove.add(new BoardStage(swap(7, 4, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(7, 6, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(7, 8, curNode.getStageBoard().getBoard().clone())));
        }
        else {
            tempBoardMove.add(new BoardStage(swap(8, 5, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(8, 7, curNode.getStageBoard().getBoard().clone())));
        }
        addToQueue(tempBoardMove, curNode);
    }
    
    private int[] swap(int index1, int index2, int[] board) {
        int[] tempBoard = board;
        int temp = tempBoard[index1];
        tempBoard[index1] = tempBoard[index2];
        tempBoard[index2] = temp;
        return tempBoard;
        
    }
    
}
