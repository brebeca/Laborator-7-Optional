package org.example;

import java.util.*;

public abstract class Player implements Runnable {
    String name;
    Board board;
    int k;
    List<Token> myTokens;
    Player(String name,Board b, int k)
    {
        board=b;
        this.name=name;
        myTokens=new ArrayList<Token>();
        this.k=k;
    }

    /**
     * ruleaza pana i se intoarce null semn ca jocul s a terminat sau ca a treut perioada de timp setata de clasa TimeKeper
     * pentru a marca cine a iesit se printeza numele si mesajul "voi iesi"
     */
  public void run() {

        while(true){

            try {
                if (getTok()==null) break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      System.out.println(name+"voi iesi");
    }

    /**
     * functia care cere un token suprascrisa in functie de clasa care extinde
     * @return noul token primit
     * @throws InterruptedException
     */
    abstract Token getTok() throws InterruptedException ;

    /**
     * ce ordoneaza elementele din lista
     *  apoi verifica daca diferenta dintre oricare doua elemente consecutive e accesi peste tot(verifica dc exista ratie)
     * @return true daca exista progresie aritmetica, false altfel
     */
    boolean arithmP()
    {
        if(myTokens.size()<k)
            return false;
        Collections.sort(myTokens,new NumberComp());
        int d =  myTokens.get(1).number-myTokens.get(0).number;
        for (int i = 2; i <myTokens.size()-1 ; i++)
            if (myTokens.get(i).number-myTokens.get(i-1).number != d)
                return false;

        return true;
    }

}


class NumberComp implements Comparator<Token>
{
    public int compare(Token m1, Token m2)
    {
        if (m1.number < m2.number) return -1;
        if (m1.number > m2.number) return 1;
        else return 0;
    }
}