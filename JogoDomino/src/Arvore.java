
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
        ArrayList<Peca> Ap;
        ArrayList<Nodo> Ln;
    }
    private ArrayList<Nodo> arvore;

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
    public ArrayList<Nodo> constroiArvore(int p1, int p2, ArrayList<Peca> mesa, ArrayList<Peca> j2, int n, int nF) {
        while (nF > 0) {
            Nodo nod = new Nodo();
            if (n == 0) {
                nod.nivel = n;
                n++;
                nod.Ap = mesa;
                nod.ponta1 = p1;
                nod.ponta2 = p2;
                arvore.add(nod);
                nF--;
            } else if (n % 2 != 0) {
                nod = arvore.get(n - 1);
                if (!j2.isEmpty()) {
                    for (int i = 0; i < j2.size(); i++) {
                        arvore = new ArrayList<>();
                        if (p1 == j2.get(i).getPonta1()) {
                            Nodo nod1 = new Nodo();
                            nod1.nivel = n;
                            mesa.add(j2.get(i));
                            nod1.Ap = mesa;
                            nod1.ponta1 = j2.get(i).getPonta2();
                            nod1.ponta2 = p2;
                            nod1.Ln = this.constroiArvore(j2.get(i).getPonta2(), p2, mesa, j2, n + 1, nF - 1);
                            nod.Ln.add(nod1);
//                            arvore = new ArrayList<>();
//                            arvore.add(nod1);
//                            nod.Ln = arvore;
                        }
                        arvore = new ArrayList<>();
                        if (p1 == j2.get(i).getPonta2()) {
                            Nodo nod1 = new Nodo();
                            nod1.nivel = n;
                            mesa.add(j2.get(i));
                            nod1.Ap = mesa;
                            nod1.ponta1 = j2.get(i).getPonta1();
                            nod1.ponta2 = p2;
                            nod1.Ln = this.constroiArvore(j2.get(i).getPonta1(), p2, mesa, j2, n + 1, nF - 1);
                            nod.Ln.add(nod1);
//                            arvore = new ArrayList<>();
//                            arvore.add(nod1);
//                            nod.Ln = arvore;
                        }
                        arvore = new ArrayList<>();
                        if (p2 == j2.get(i).getPonta1()) {
                            Nodo nod1 = new Nodo();
                            nod1.nivel = n;
                            mesa.add(j2.get(i));
                            nod1.Ap = mesa;
                            nod1.ponta1 = p1;
                            nod1.ponta2 = j2.get(i).getPonta2();
                            nod1.Ln = this.constroiArvore(p1, j2.get(i).getPonta2(), mesa, j2, n + 1, nF - 1);
                            nod.Ln.add(nod1);
//                            arvore = new ArrayList<>();
//                            arvore.add(nod1);
//                            nod.Ln = arvore;
                        }
                        arvore = new ArrayList<>();
                        if (p2 == j2.get(i).getPonta2()) {
                            Nodo nod1 = new Nodo();
                            nod1.nivel = n;
                            mesa.add(j2.get(i));
                            nod1.Ap = mesa;
                            nod1.ponta1 = p1;
                            nod1.ponta2 = j2.get(i).getPonta1();
                            nod1.Ln = this.constroiArvore(p1, j2.get(i).getPonta1(), mesa, j2, n + 1, nF - 1);
                            nod.Ln.add(nod1);
//                            arvore = new ArrayList<>();
//                            arvore.add(nod1);
//                            nod.Ln = arvore;
                        }
                    }
                    nF--;
                    //VER NECECIDADE DE ADICIONAR NODO NA ÁRVORE
                } else {
                    return arvore;
                }
            } else {
                //Parte que constroi os nodos de escolha aleatória do jogador MIN
                MontedePecas m = new MontedePecas();
                ArrayList<Peca> j = m.getMonte();
                //Remove o que tem na mão do computador
                for (int i = 0; i < j2.size(); i++) {
                    j.remove(j2.get(i));
                }
                //Remove o que tem na mesa
                for (int i = 0; i < mesa.size(); i++) {
                    j.remove(mesa.get(i));
                }
                if (!j.isEmpty()) {
                    for (int i = 0; i < j.size(); i++) {
                        if (p1 == j.get(i).getPonta1()) {
                            
                        }
                        if (p1 == j.get(i).getPonta2()) {

                        }
                        if (p2 == j.get(i).getPonta1()) {

                        }
                        if (p2 == j.get(i).getPonta2()) {

                        }
                    }
                }else{
                    return arvore;
                }
            }
        }
        return arvore;
    }

    public void retornaMelhorPeca() {

    }

}
