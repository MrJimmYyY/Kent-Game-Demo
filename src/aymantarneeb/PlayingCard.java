/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aymantarneeb;

import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author ayman
 */
public class PlayingCard implements Serializable {
    public static enum ShapeTypes{spades,diamonds,clubs,hearts};
    public int Value;
    public ShapeTypes Shape;
    public String CardName;
    public String ImageName;
    public JLabel Holder;
    
}
