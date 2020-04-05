package org.example;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SmartPlayer extends Player {
    SmartPlayer(String name, Board b, int k) {
        super(name, b, k);
    }

    /**
     * cere un token cu paramentrii corespunzatori
     * pentrun primele doua tokenuri le cere random pt ca nu paote inca calcula o ratie majoritara dupa care sa aleaga
     * urmatoareel tokenuri le cere cu optinua de smart_player, adica 3 la variabila tip
     * verifica daca ce a primit este null inseamana ca jocul s a terminat si trebuie sa semnaleze(prin a returna tot null) ca trebuie sa termine executia
     * daca nu a primit null vadauga tokenul la lista sa si verifica iar daca a format o progresie
     * daca a format o progresie isi scrie numele in winP si reurneaza null pt ca jocul s a terminat
     * daca nu a formati o progresie retuenaza tok si jocul continua
     * @return tokenul primit daca jocul coniuna, null daca s a terminat
     * @throws InterruptedException
     */
    @Override
     Token getTok() throws InterruptedException {
        Token tok = null;
        if(myTokens.size()<2)
        {
            tok=board.giveTokRand(name,1,null);

        }
        else{
            tok=board.giveTokRand(name,3,this);
        }
        if(arithmP()){
            board.setWinP(name);
            return null;
        }
        board.setLast(name);
        return tok;
    }

    /**
     * calculeaz ratia cu frecventa ce mai mare in lista de tokenuri a jucatorului smart
     * pentru fiecrae token din lista tablei de joc cauta in lista jucatorului un token(care nu mai este in lista jucatorului ) care ar putea fi adaugat la progrsia cu ratia ratio
     * verifica daca poate fi adaugata la progresia cu ratia ratio daca exista un token al tablei care sa fie egal cu tokenul jucatorului + ratio sau
     *                                                                                                      sau care sa fie egal cu tokenul jucatorului -retio
     *daca gaseste verifica sa nu fie deja in lista jucatorului
     * daca nu este in lista jucatorului returneaza indexul tokenului respectiv
     * @param toke lista de tokenuri disponibile din care sa aleaga
     * @return indexul tokenului care sa competeze progresia cu ratia ratio sau 0 daca nu se gasete
     */

    public int getIndexOfBestChoice(List<Token> toke){

        int ratio=getRatio();
        for(Token t1:toke)
        {
            for(Token t2:myTokens) {
                if (t1.number == t2.number + ratio) {
                    boolean exists=false;
                    for (Token t3:myTokens){
                        if(t3.number==t1.number)
                            exists=true;
                    }
                    if(!exists) {
                        return toke.indexOf(t1);
                    }
                }
                if (t2.number == t1.number + ratio) {
                    boolean exists=false;
                    for (Token t3:myTokens){
                        if(t3.number==t1.number)
                            exists=true;
                    }
                    if(!exists) {
                        return toke.indexOf(t1);
                    }
                }
            }
        }
        return 0;
    }

    /**
     * calculeaza retia cu cea mai mare frecventa de aparitie
     * ordoneaza intiai lista de tokenuri
     * petru fiecrae posibila ratie itereaza prin lista si nuamra de cate ori apare
     * daca s a gasit o ratie cu un numar de aparitii mai mare decat cel inregistrat precedent este menorata in ratio
     * se retueneza reatia cu cele mai multe aparitii
     * @return ratia cea mai des intalnita
     */
    private int getRatio() {
        Collections.sort(myTokens,new NumberComp());
        int maxRatio=0, ratio=myTokens.get(1).number-myTokens.get(0).number, ratioNr, auxRat;

        for(int i =0 ;i<myTokens.size()-1;i++)
        {
            auxRat=myTokens.get(i+1).number-myTokens.get(i).number;
            ratioNr=1;
            for(int j=i+1;j<myTokens.size()-1;j++)
            {
                if(myTokens.get(j+1).number-myTokens.get(j).number==auxRat)
                {
                    ratioNr++;
                }
            }
            if(ratioNr>maxRatio){
                maxRatio=ratioNr;
                ratio=auxRat;
            }
        }

        return ratio;
    }
}
