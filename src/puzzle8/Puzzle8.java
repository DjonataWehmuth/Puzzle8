/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle8;

/** 
 *
 * @author Djonata Wehmuth
 */
public class Puzzle8 {

    static Integer[] problema = {
            0, 7, 1,
            5, 4, 2,
            3, 8, 6
    };
    static Integer[] resultado = {
            1, 2, 3,
            4, 5, 6,
            7, 8, 0
    };

    public static void main( String[] args ) {
        Tabuleiro tabuleiro = new Tabuleiro(resultado, problema);
        tabuleiro.PercorreTree();
    }

}
