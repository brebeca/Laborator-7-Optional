package org.example;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Board board;
    List<Player> players;

    /**
     * @param n numarul de tokenuri
     * @param nrPlayers numarul de jucatori
     * @param names numele jucatorilor
     * @param k dimensiunea progresiei ce trebuie facuta
     */
    Game(int n, int nrPlayers , List<String> names,int k){
        players= new ArrayList<>();
        board=new Board(n);
        int i=0;
        Runnable newP=null;
        for(String name: names){

            if(i%3==0)
                newP=new ManualPlayer(name,board,k);
            else if(i%3==1)
                newP=new RandomPlayer(name,board,k);
            else
                newP=new SmartPlayer(name,board,k);

            players.add((Player) newP);
           i++;
        }

    }
    Game(int n , String name1,String name2,int k){
        players= new ArrayList<>();
        board=new Board(n);
        Runnable  newP=new SmartPlayer(name1,board,k);
        players.add((Player) newP);
        Runnable  newP2=new SmartPlayer(name2,board,k);
        players.add((Player) newP2);
    }

    /**
     * se porneste executia firelor de executie pentru fiecare jucator
     * se pornese si un thread pentru TimeKeeper
     */
    public void startGame(){
        for(Player player: players)
        new Thread(player).start();
        new Thread(new TimeKeeper(board)).start();
    }
}
