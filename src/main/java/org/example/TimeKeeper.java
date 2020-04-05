package org.example;

import static java.lang.System.currentTimeMillis;

public class TimeKeeper implements Runnable {
    Board board;
    TimeKeeper(Board b)
    {
        board=b;
    }
    @Override
    public void run() {
        while(currentTimeMillis()<=3000)
        {}
        try {
            board.giveTokRand(null,4,null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
