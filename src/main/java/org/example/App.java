package org.example;

import java.util.ArrayList;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        List<String> nume= new ArrayList<>();
        nume.add("Ply1");
        nume.add("Ply2");
        nume.add("Ply3");
        nume.add("Ply4");
        Game game= new Game(20,"Smart1","Smart2",5);
        Game game2= new Game(20,4,nume,5);

        game2.startGame();
    }
}
