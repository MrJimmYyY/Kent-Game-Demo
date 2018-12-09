/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aymantarneeb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author ayman
 */
public class AymanTarneeb implements Serializable {

    public static ArrayList<PlayingCard> KotchenaGame;
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here 
        TarneebGame p=new TarneebGame();
        p.setVisible(true);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
