/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nnnnew
 */
public class Main {
    public static void main(String[] args) {
        int[] initialBoard1 = {2,3,1,7,0,8,6,5,4};
        DFSearch df = new DFSearch(initialBoard1);
        BFSearch bf = new BFSearch(initialBoard1);
        bf.search();
        //df.search();
    }
}
