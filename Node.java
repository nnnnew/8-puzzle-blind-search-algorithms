
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nnnnew
 */
public class Node {
    Node prevNode;
    BoardStage stageBoard;
    LinkedList<Node> child;
    
    public Node(BoardStage stageBoard, Node prevNode) {
        this.stageBoard = stageBoard;
        this.prevNode = prevNode;
        child = new LinkedList<>();
    }
    
    public Node(BoardStage stageBoard) {
        this(stageBoard, null);
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }

    public BoardStage getStageBoard() {
        return stageBoard;
    }

    public void setStageBoard(BoardStage stageBoard) {
        this.stageBoard = stageBoard;
    }

    public LinkedList<Node> getChild() {
        return child;
    }

    public void setChild(LinkedList<Node> child) {
        this.child = child;
    }
}
