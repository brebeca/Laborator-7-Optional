package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Board {
    List<Token> tokens;
    int m;
    String winP=null;
    String last=null;
    private boolean timeToDie=false;

    Board(int n)
    {
        tokens= new ArrayList<Token>();
        int i=1;
        while(i<=n)
        {
            tokens.add(new Token(i));
            i++;
        }
    }

    /**
     * prmia data verifica daca jucatorul care vrea sa intre in sectiunea critia este ultimul care  a accesat sectiunea critica, daca da il pune sa astepte
     * verifica daca jocul s a terminat in unul din urmatoareel cazuri:
     * 1)winP nu mai este null-> cineva a castigat
     * 2)timeToDie a fost setata ca true-> timpul de joc s a terminat
     * 3)lista este goala -> nu mai sunt tokenuri de dat
     * in functie de tipul cereii din variabila tip :
     * 1)Random -> genereaza un index random intre 0 si tokens.size() si reurneaza tokenul de pe acea pozitie
     * 2)Manual -> printeaza toate tokenurile si asteapta ca userul sa introduca indexul valorii dorite si returneza tokenul de pe acea positie
     * 3)Smart -> apeleaza functia player.getIndexOfBestChoice(tokens) si retuenaza tokenul de la intexul respectiv
     * 4)Time expired -> seteaza timeToDie true semnaland ca nu mai poate da tokenuri ca timpul s a termiant
     * inainte de ficare return functia updateaza variabila last cu numele playerului care sets acum in functie si notifica eventualele threaduri care sunt in wait
     * @param name numele juctaorului care cere token
     * @param tip tipul de jucator care cere : 1-> random ; 2-> manual ; 3->smart ; 4-> timekeeperul (semnaleaza ca timpul setat s a terminat)
     * @param player instanta juncatorului smart pentru a putea apela functie de calculare a tokenului cel mai optim
     * @return null daca jocul s a terminat sau  tokenul cerut altfel
     * @throws InterruptedException
     */
    public synchronized Token giveTokRand(String name , int tip,SmartPlayer player) throws InterruptedException {
        if(last==name) {System.out.println(name+"voi astepta");wait();System.out.println(name+"asteptare terminata");}

        if(winP!=null) {
            last=name;
            System.out.println(name+" am citit ca winP :"+winP);
            notify();
            return null;
        }

        if(timeToDie==true){
            last=name;
            System.out.println(name+" time to die");
            notify();
            return null;
        }

        if(tokens.isEmpty()||tokens.size()==0){
            last=name;
            notify();
            System.out.println("Nu castinga nimeni. S au terminat tokenurile");
            return null;
        }
        if(tip==1) {

            Random rand = new Random();
            int index = rand.nextInt(tokens.size());
            if (index < 0) index = 0;
            Token toGive = tokens.get(index);
            tokens.remove(index);
            last = name;
            notify();
            return toGive;
        }
        if(tip==2) {
            Scanner input= new Scanner(System.in);
            System.out.println("Alegeti indexul unuia dintre numere");
            for(Token t:tokens)
            {
                System.out.println(t.number+" -> index: "+tokens.indexOf(t));

            }
            int index=input.nextInt();
            Token toGive = tokens.get(index);
            tokens.remove(index);
            last = name;
            notify();
            return toGive;

        }
        if(tip==3){
            int index= player.getIndexOfBestChoice(tokens);
            Token toGive = tokens.get(index);
            tokens.remove(index);
            last = name;
            notify();
            return toGive;
        }
        if(tip==4) {
            notify();
            timeToDie = true;
        }
        return null;
    }

    public synchronized void setWinP(String castigator) {
        if(this.winP==null){
        this.winP = castigator;
        System.out.println("A castigat "+winP);}
    }

    public  List<Token> getTokens() {
        return tokens;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLast() {
        return last;
    }
}
