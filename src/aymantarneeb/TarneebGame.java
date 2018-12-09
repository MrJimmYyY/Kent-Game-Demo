/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aymantarneeb;

import java.awt.AWTEventMulticaster;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author ayman
 */
public class TarneebGame extends JFrame implements Serializable
{
   
    ArrayList <PlayingCard> Cards=new ArrayList<PlayingCard>();
    private String DirectoryPath="Cards";
    //ArrayList<JLabel> ArrayOfLabel=new ArrayList<JLabel>();
    String score="Player 1 Score : ";
    String score2="Player 2 Score : ";
    JLabel XCor=new JLabel(score);
    JLabel YCor=new JLabel(score2);
    private int prevX;
    private int prevY;
    private JButton newGame;
    private JTextField NoPlayers;
    private JButton submit;
    private Random getRandom;
    private ArrayList<PlayingCard> GroundHand;
    ArrayList<Player> players;
    GraphicsEnvironment env;
    private JButton next;
    private CardsActions a=new CardsActions();
    private CardsMotion m=new CardsMotion();
    private int playernum;
    
    public TarneebGame() throws IOException, ClassNotFoundException
    {
        setTitle("Kent Beta Ver 1.0");
        env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        setSize(env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height);
       getRandom=new Random();
       next=new JButton("Next Player");
       next.setVisible(false);
       playernum=0;
       add(next);
       players=new ArrayList<Player>();
        GroundHand=new ArrayList<PlayingCard>();
        setLayout(null);
        next.setBounds(0, (int)env.getMaximumWindowBounds().getHeight()/2, 150, 50);
        newGame=new JButton("New Game");
        newGame.setBounds((env.getMaximumWindowBounds().width/2)-30,env.getMaximumWindowBounds().height/2-30,100,30);
        add(newGame);
        newGame.addActionListener(new NewGameNsubmit());
        NoPlayers=new JTextField("Number of Players");
        NoPlayers.setBounds((env.getMaximumWindowBounds().width/2)-30,env.getMaximumWindowBounds().height/2-30,100,20);
        add(NoPlayers);
        NoPlayers.setVisible(false);
        submit=new JButton("Submit");
        submit.addActionListener(new NewGameNsubmit());
        submit.setBounds((env.getMaximumWindowBounds().width/2)-30,env.getMaximumWindowBounds().height/2+70,100,30);
        add(submit);
        submit.setVisible(false);
        XCor.setBounds(10,840,160,30);
        YCor.setBounds(170,840,160,30);
        add(XCor);
        add(YCor);
    
        //setLayout(new FlowLayout());
        int x=0;
        int y=10;
        int elementsinrow=0;
        ObjectInputStream CardsReader=new ObjectInputStream(new FileInputStream(DirectoryPath+"\\AllCards.bin"));
        Cards=(ArrayList<PlayingCard>)CardsReader.readObject();
        CardsReader.close();
        for(int i=0;i<52;i++){
        PlayingCard p=new PlayingCard();
        JLabel j=new JLabel();
        j.setIcon(new ImageIcon(new ImageIcon(Cards.get(i).ImageName).getImage().getScaledInstance(240, 320, Image.SCALE_DEFAULT)));
        Cards.get(i).Holder=j;
        x+=0;
             
        add(Cards.get(i).Holder);
        //Cards.get(i).Holder.setBounds(x,y, 240, 320);
        elementsinrow++;
        if (elementsinrow>3)
        {
        x=0;
        y+=0;
        elementsinrow=0;
        }
        } 
        next.addActionListener(new nextWatcher());
    }
    
    private class NewGameNsubmit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(newGame)){
                submit.setVisible(true);
                NoPlayers.setVisible(true);
                newGame.setVisible(false);
                setVisible(false);
                setVisible(true);
            }
            else{
                submit.setVisible(false);
                NoPlayers.setVisible(false);
                AymanTarneeb.KotchenaGame=Cards;
                for(int i=0;i<2;i++){
                    players.add(new Player(i+1));
                    players.get(i).setHand();
                    int x1=(int)env.getMaximumWindowBounds().getWidth()/4;
                    int y1=(int)env.getMaximumWindowBounds().getHeight()-300;
                    for(int j=0;j<players.get(i).Hand.size();j++){
                    players.get(i).Hand.get(j).Holder.addMouseListener(a);
                    players.get(i).Hand.get(j).Holder.addMouseMotionListener(m);
                    players.get(i).Hand.get(j).Holder.setBounds(x1, y1, 240, 320);
                    if(i==1){
                    players.get(i).Hand.get(j).Holder.setVisible(false);
                    }
                    x1+=240;
                    }
                }
                for(int i=0;i<4;i++){
                players.get(0).Hand.get(i).Holder.setVisible(true);
                }

                int x2=(int)env.getMaximumWindowBounds().getWidth()/4;
                int y2=(int)env.getMaximumWindowBounds().getHeight()/2-200;
                for(int i=0;i<4;i++){
                    int randnum=getRandom.nextInt(AymanTarneeb.KotchenaGame.size()+1);
                    GroundHand.add(AymanTarneeb.KotchenaGame.get(randnum));
                    AymanTarneeb.KotchenaGame.remove(randnum);
                    GroundHand.get(i).Holder.setBounds(x2, y2, 240, 320);
                    getContentPane().add(GroundHand.get(i).Holder);
                    x2+=240;
                }
                for(int i=0;i<4;i++){
                GroundHand.get(i).Holder.setVisible(true);
                }
            }
        }
    }
    

private class CardsMotion implements MouseMotionListener{
        @Override
    public void mouseDragged(MouseEvent e) {
        for (PlayingCard p:players.get(0).Hand)
        {
            
        if (p.Holder.equals(e.getSource()))
        {
            
            //JOptionPane.showMessageDialog(null, "Label 0");
           // int i=Cards.indexOf(e.getSource());
            p.Holder.setBounds(e.getXOnScreen()-10,e.getYOnScreen()-20, 240, 320);
           // XCor.setText(""+p.Value);
        } 
        
        }
                for (PlayingCard p:players.get(1).Hand)
        {
            
        if (p.Holder.equals(e.getSource()))
        {
            
            //JOptionPane.showMessageDialog(null, "Label 0");
           // int i=Cards.indexOf(e.getSource());
            p.Holder.setBounds(e.getXOnScreen()-10,e.getYOnScreen()-20, 240, 320);
           // XCor.setText(""+p.Value);
        } 
        }
      
        //System.out.println("E"+e.getSource().toString());
    }
    
        @Override
    public void mouseMoved(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
}

private class CardsActions implements MouseListener{
        @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        boolean found=false;
    for(int all=0;all<2;all++){  
        for (int j=0;j<players.get(all).Hand.size();j++/*PlayingCard p:players.get(0).Hand*/){
            if(players.get(all).Hand.get(j).Holder.equals(e.getSource())){
          if(e.getYOnScreen()<=700){
              next.setVisible(true);
              if(players.get(all).Hand.get(j).Value==12 || (players.get(all).Hand.get(j).Value==7 && players.get(all).Hand.get(j).Shape==PlayingCard.ShapeTypes.diamonds)){
                 if(!GroundHand.isEmpty()){ 
                  int size=GroundHand.size();
                  for(int i=0;i<size;i++){
                      GroundHand.get(0).Holder.setVisible(false);
                      GroundHand.remove(0);
                      players.get(all).setScore(1);
                  }
                  players.get(all).Hand.get(j).Holder.setVisible(false);
                  players.get(all).Hand.remove(j);
                  players.get(all).setScore(10);
                  XCor.setText(score+players.get(0).getScore());
                  YCor.setText(score2+players.get(1).getScore());
              }
              }
              else{
            for(int k=0;k<GroundHand.size();k++/*PlayingCard v:GroundHand*/){
                if(GroundHand.get(k).Value==players.get(all).Hand.get(j).Value){
                    players.get(all).Hand.get(j).Holder.setVisible(false);
                    GroundHand.get(k).Holder.setVisible(false);
                    found=true;
                    GroundHand.remove(k);
                    players.get(all).Hand.remove(j);
                    players.get(all).setScore(2);
                    XCor.setText(score+players.get(0).getScore());
                    YCor.setText(score2+players.get(1).getScore());
                }
                if(found){
                    break;
                }
            }
            if(!found){
                GroundHand.add(players.get(all).Hand.get(j));
                players.get(all).Hand.remove(j);
            }
        }
                        for(PlayingCard s:players.get(all).Hand){
                      s.Holder.removeMouseMotionListener(m);
                      s.Holder.removeMouseListener(a);
                  }
          }
        else
            players.get(all).Hand.get(j).Holder.setBounds(prevX-1,prevY+20, 240, 320);

        }                     
        }
    }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
               for(PlayingCard p:players.get(0).Hand){
            if(p.Holder.equals(e.getSource())){
                prevX=e.getX();
                prevY=e.getY();
            }
        }
               for(PlayingCard p:players.get(1).Hand){
                   if(p.Holder.equals(e.getSource())){
                prevX=e.getX();
                prevY=e.getY();
            }
               }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

private class nextWatcher implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            next.setVisible(false);
            if(players.get(playernum).Hand.isEmpty()){
                if(AymanTarneeb.KotchenaGame.isEmpty()){
                    for(PlayingCard p:GroundHand){
                        p.Holder.setVisible(false);
                    }
                    XCor.setBounds(env.getMaximumWindowBounds().width/2,env.getMaximumWindowBounds().height/2,150,50);
                    YCor.setBounds(env.getMaximumWindowBounds().width/2,env.getMaximumWindowBounds().height/2+60,150,50);
                }
                else{    
                players.get(playernum).setHand();
                }
            }
                for(int i=0;i<players.get(playernum).Hand.size();i++){
                    players.get(playernum).Hand.get(i).Holder.setVisible(false);
                }
             if(playernum==0){
                 playernum++;
             }
             else
                 playernum--;
                    int x1=(int)env.getMaximumWindowBounds().getWidth()/4;
                    int y1=(int)env.getMaximumWindowBounds().getHeight()-300;
             for(int i=0;i<players.get(playernum).Hand.size();i++){
                    players.get(playernum).Hand.get(i).Holder.setVisible(true);
                    players.get(playernum).Hand.get(i).Holder.addMouseListener(a);
                    players.get(playernum).Hand.get(i).Holder.addMouseMotionListener(m);
                    players.get(playernum).Hand.get(i).Holder.setBounds(x1, y1, 240, 320);
                    x1+=240;
                }
        }
    
}

}
