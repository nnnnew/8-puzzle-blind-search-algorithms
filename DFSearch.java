
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import sun.security.ec.CurveDB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nnnnew
 */
public class DFSearch {

    private Stack<Node> stackNode;
    private LinkedList<int[]> wasMoveStages;
    private Node root;

    public DFSearch(int[] initialBoard) {
        root = new Node(new BoardStage(initialBoard));
        stackNode = new Stack<>();
        stackNode.add(root);
        wasMoveStages = new LinkedList<>();
        wasMoveStages.add(initialBoard);
    }

    public void search() {
            search(stackNode.pop());
       }

    private void search(Node curNode) {
        if(curNode.getStageBoard().isGoalStage()) {
            printPath(curNode);
            System.exit(0);
        }
        //curNode.getStageBoard().printStage();
        moveStage(curNode);
        search(stackNode.pop());
    }
    
    private void addToStack(Stack<BoardStage> stackStage, Node curNode) {
        System.out.println("--------------");
        while(!stackStage.empty()) {
            BoardStage tempStage = stackStage.pop();
            tempStage.printStage();
            if(!wasMoveStage(tempStage.getBoard())) {
                Node newNode = new Node(tempStage, curNode);
                curNode.child.add(newNode);
                if(tempStage.isGoalStage()) {
                    printPath(newNode);
                    System.exit(0);
                }
                stackNode.add(newNode);
                wasMoveStages.add(tempStage.getBoard());
            }
        }
    }

    private void moveStage(Node curNode) {
        int indexSpace = curNode.getStageBoard().findIndexSpace();
        Stack<BoardStage> tempBoardMove = new Stack<>();
        
        if(indexSpace == 0) {
            tempBoardMove.add(new BoardStage(swap(0, 3, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(0, 1, curNode.getStageBoard().getBoard().clone())));
            
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
            tempBoardMove.add(new BoardStage(swap(3, 4, curNode.getStageBoard().getBoard().clone())));
            tempBoardMove.add(new BoardStage(swap(3, 0, curNode.getStageBoard().getBoard().clone())));
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
        addToStack(tempBoardMove, curNode);
    }
    
    private boolean wasMoveStage(int[] board) {
        for(int i = 0; i < wasMoveStages.size(); ++i) {
            if(Arrays.equals(board, wasMoveStages.get(i))) {
                return true;
            }
        }
        return false;
    }

    private int[] swap(int index1, int index2, int[] board) {
        int[] tempBoard = board;
        int temp = tempBoard[index1];
        tempBoard[index1] = tempBoard[index2];
        tempBoard[index2] = temp;
        return tempBoard;

    }

    private void printPath(Node curNode) {
        Stack<BoardStage> path = getPath(curNode);
        while (!path.isEmpty()) {
            path.pop().printStage();
        }
    }

    private Stack<BoardStage> getPath(Node goalNode) {
        Node trav = goalNode;
        Stack<BoardStage> stackPath = new Stack<>();
        int stage = 0;
        while (trav != null) {
            ++stage;
            stackPath.add(trav.getStageBoard());
            trav = trav.prevNode;
        }
        System.out.println("Stage level solving is " + stage);
        return stackPath;
    }
}
