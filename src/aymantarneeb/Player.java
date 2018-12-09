/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aymantarneeb;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jimmy
 */
public class Player {
    private String Name;
    private int score;
    public ArrayList<PlayingCard> Hand;
    
    public Player(int pos){
        score=0;
        Name="Player "+pos;
        Hand=new ArrayList<PlayingCard>();
    }
    public void setHand(){
        Random set=new Random();
        //if(AymanTarneeb.KotchenaGame.size()>)
        for(int i=0;i<4;i++){
        int Randnum=set.nextInt(AymanTarneeb.KotchenaGame.size());
        Hand.add(AymanTarneeb.KotchenaGame.get(Randnum));
        AymanTarneeb.KotchenaGame.remove(Randnum);
        }
    }
    
    public void setScore(int score){
        this.score+=score;
    }
    
    public int getScore(){
        return score;
    }
    
    public String getName(){
        return Name;
    }
}
