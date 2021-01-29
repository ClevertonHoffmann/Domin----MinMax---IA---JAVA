
import java.util.ArrayList;

/**
 * Classe que constroi a árvore de estados com seus valores heurísticos
 *
 * @author Cleverton
 */
public class Arvore {

    protected class Nodo {

        int nivel;
        int heuristicaMinMax;
        int ponta1;
        int ponta2;
        int lado; //Lado que a peça se encaixa no tabuleiro
        ArrayList<Peca> Ap;
        ArrayList<Nodo> Ln;

    }
    public ArrayList<Nodo> arvore;

    public Arvore() {
        arvore = new ArrayList<>();
    }

    /**
     * Constroi a árvore de possibilidades de jogadas
     *
     * @param p1
     * @param p2
     * @param mesa
     * @param j2
     * @param n
     * @param nF
     * @return
     */
    protected ArrayList<Nodo> constroiArvore(int p1, int p2, ArrayList<Peca> mesa, ArrayList<Peca> j2, int n, int nF) {
        while (nF > 0) {
            Nodo nod = new Nodo();
            nod.Ap = new ArrayList<>();
            if (n == 0) {
                nod.nivel = n;
                n++;
                for (int i = 0; i < mesa.size(); i++) {
                    nod.Ap.add(mesa.get(i));
                }
                nod.ponta1 = p1;
                nod.ponta2 = p2;
                arvore.add(nod);
                nod = arvore.get(0);
            } else if (n % 2 != 0) {
                nod = arvore.get(0);
                nod.Ln = new ArrayList<>();
                if (!j2.isEmpty()) {
                    for (int i = 0; i < j2.size(); i++) {
                        if (p1 == j2.get(i).getPonta1()) {
                            Nodo nod1 = new Nodo();
                            nod1.nivel = n;
                            nod.Ap.add(j2.get(i));
                            nod1.Ap = nod.Ap;
                            nod1.ponta1 = j2.get(i).getPonta2();
                            nod1.ponta2 = p2;
                            nod1.lado = 2;
                            nod1.heuristicaMinMax = this.retornaHeuristica(j2.get(i).getPonta2(), p2, nod1.Ap, j2);
                            //   nod1.Ln = this.constroiArvore(j2.get(i).getPonta2(), p2, nod1.Ap, j2, n + 1, nF - 1);
                            nod.Ln.add(nod1);
                        }
                        if (p1 == j2.get(i).getPonta2()) {
                            Nodo nod1 = new Nodo();
                            nod1.nivel = n;
                            nod.Ap.add(j2.get(i));
                            nod1.Ap = nod.Ap;
                            nod1.ponta1 = j2.get(i).getPonta1();
                            nod1.ponta2 = p2;
                            nod1.lado = 2;
                            nod1.heuristicaMinMax = this.retornaHeuristica(j2.get(i).getPonta1(), p2, nod1.Ap, j2);
                            //  nod1.Ln = this.constroiArvore(j2.get(i).getPonta1(), p2, nod1.Ap, j2, n + 1, nF - 1);
                            nod.Ln.add(nod1);
                        }
                        if (p2 == j2.get(i).getPonta1()) {
                            Nodo nod1 = new Nodo();
                            nod1.nivel = n;
                            nod.Ap.add(j2.get(i));
                            nod1.Ap = nod.Ap;
                            nod1.ponta1 = p1;
                            nod1.lado = 1;
                            nod1.ponta2 = j2.get(i).getPonta2();
                            nod1.heuristicaMinMax = this.retornaHeuristica(p1, j2.get(i).getPonta2(), nod1.Ap, j2);
                            //   nod1.Ln = this.constroiArvore(p1, j2.get(i).getPonta2(), nod1.Ap, j2, n + 1, nF - 1);
                            nod.Ln.add(nod1);
                        }
                        if (p2 == j2.get(i).getPonta2()) {
                            Nodo nod1 = new Nodo();
                            nod1.nivel = n;
                            nod.Ap.add(j2.get(i));
                            nod1.Ap = nod.Ap;
                            nod1.ponta1 = p1;
                            nod1.lado = 1;
                            nod1.ponta2 = j2.get(i).getPonta1();
                            nod1.heuristicaMinMax = this.retornaHeuristica(p1, j2.get(i).getPonta1(), nod1.Ap, j2);
                            //  nod1.Ln = this.constroiArvore(p1, j2.get(i).getPonta1(), nod1.Ap, j2, n + 1, nF - 1);
                            nod.Ln.add(nod1);
                        }
                    }
                    return arvore;
                } else {
                    return arvore;
                }
            } //else {
//                //Parte que constroi os nodos de escolha aleatória do jogador MIN
//                MontedePecas m = new MontedePecas();
//                ArrayList<Peca> j = m.getMonte();
//                //Remove o que tem na mão do computador
//                for (int i = 0; i < j2.size(); i++) {
//                    j.remove(j2.get(i));
//                }
//                //Remove o que tem na mesa
//                for (int i = 0; i < mesa.size(); i++) {
//                    j.remove(mesa.get(i));
//                }
//                nod = arvore.get(arvore.get(0).nivel);///VERIFICAR PARA MAIS NÍVEIS DA ÁRVORE
//                nod.Ln = new ArrayList<Nodo>();
//                if (!j.isEmpty()) {
//                    for (int i = 0; i < j.size(); i++) {
//                        if (p1 == j.get(i).getPonta1()) {
//                            Nodo nod1 = new Nodo();
//                            nod1.nivel = n;
//                            nod.Ap.add(j.get(i));
//                            nod1.Ap = nod.Ap;
//                            nod1.ponta1 = j.get(i).getPonta2();
//                            nod1.ponta2 = p2;
//                            nod1.Ln = this.constroiArvore(j.get(i).getPonta2(), p2, nod1.Ap, j2, n + 1, nF - 1);
//                            nod.Ln.add(nod1);
//                        }
//                        if (p1 == j.get(i).getPonta2()) {
//                            Nodo nod1 = new Nodo();
//                            nod1.nivel = n;
//                            nod.Ap.add(j.get(i));
//                            nod1.Ap = nod.Ap;
//                            nod1.ponta1 = j.get(i).getPonta1();
//                            nod1.ponta2 = p2;
//                            nod1.Ln = this.constroiArvore(j.get(i).getPonta1(), p2, nod1.Ap, j2, n + 1, nF - 1);
//                            nod.Ln.add(nod1);
//                        }
//                        if (p2 == j.get(i).getPonta1()) {
//                            Nodo nod1 = new Nodo();
//                            nod1.nivel = n;
//                            nod.Ap.add(j.get(i));
//                            nod1.Ap = nod.Ap;
//                            nod1.ponta1 = p1;
//                            nod1.ponta2 = j.get(i).getPonta2();
//                            nod1.Ln = this.constroiArvore(p1, j.get(i).getPonta2(), nod1.Ap, j2, n + 1, nF - 1);
//                            nod.Ln.add(nod1);
//                        }
//                        if (p2 == j.get(i).getPonta2()) {
//                            Nodo nod1 = new Nodo();
//                            nod1.nivel = n;
//                            nod.Ap.add(j.get(i));
//                            nod1.Ap = nod.Ap;
//                            nod1.ponta1 = p1;
//                            nod1.ponta2 = j.get(i).getPonta1();
//                            nod1.Ln = this.constroiArvore(p1, j.get(i).getPonta1(), nod1.Ap, j2, n + 1, nF - 1);
//                            nod.Ln.add(nod1);
//                        }
//                    }
//                }else{
//                    return arvore;
//                }
//                
//            }
            nF--;
        }
        return arvore;
    }

//    /**
//     * 
//     */
//    public Peca retornaPecaMenorHeuristica(Arvore a1) {
//        a1.arvore.get(0);
//        return null;
//    }

    /**
     * Retorna heurística conforme a quantidade de possibilidades de peças que o
     * jogador adversário pode jogar
     *
     * @param p1
     * @param p2
     * @param mesa
     * @param j2
     * @return int
     */
    public int retornaHeuristica(int p1, int p2, ArrayList<Peca> mesa, ArrayList<Peca> j2) {
        int qnt = 0;
        MontedePecas m = new MontedePecas();
        ArrayList<Peca> j = m.getMonte();
        //Remove o que tem na mão do computador
        for (int i = 0; i < j2.size(); i++) {
            for (int k = 0; k < j.size(); k++) {
                if ((j2.get(i).getPonta1() == j.get(k).getPonta1() && j2.get(i).getPonta2() == j.get(k).getPonta2())|| (j2.get(i).getPonta1() == j.get(k).getPonta2() && j2.get(i).getPonta2() == j.get(k).getPonta1())) {
                    j.remove(k);
                }
            }
        }
        //Remove o que tem na mesa
        for (int i = 0; i < mesa.size(); i++) {
            for (int k = 0; k < j.size(); k++) {
                if ((mesa.get(i).getPonta1() == j.get(k).getPonta1() && mesa.get(i).getPonta2() == j.get(k).getPonta2())|| (mesa.get(i).getPonta1() == j.get(k).getPonta2() && mesa.get(i).getPonta2() == j.get(k).getPonta1())) {
                    j.remove(k);
                }
            }
        }
        if (!j.isEmpty()) {
            for (int i = 0; i < j.size(); i++) {
                if (p1 == j.get(i).getPonta1()) {
                    qnt++;
                }
                if (p1 == j.get(i).getPonta2()) {
                    qnt++;
                }
                if (p2 == j.get(i).getPonta1()) {
                    qnt++;
                }
                if (p2 == j.get(i).getPonta2()) {
                    qnt++;
                }
            }
        }
        return qnt;
    }
}
