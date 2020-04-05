package org.example;

public class RandomPlayer extends Player {
    RandomPlayer(String name, Board b, int k) {
        super(name, b, k);
    }

    /**
     * cere un token cu paramentrii corespunzatori
     * verifica daca este null inseamana ca jocul s a terminat si trebuie sa semnaleze(prin a returna tot null) ca trebuie sa termine executia
     * daca nu a primit null vadauga tokenul la lista sa si verifica iar daca a format o progresie
     * daca a format o progresie isi scrie numele in winP si reurneaza null pt ca jocul s a terminat
     * daca nu a formati o progresie retuenaza tok si jocul continua
     * @return tokenul primit daca jocul coniuna, null daca s a terminat
     * @throws InterruptedException
     */
    @Override
      Token getTok() throws InterruptedException {
        Token tok=board.giveTokRand(name,1,null);
        if(tok==null)
            return null;
        System.out.println(name+" am primit : "+tok.number);
        myTokens.add(tok);
        if(arithmP() ){
            board.setWinP(name);
            return null;
        }
        return tok;
    }

}
