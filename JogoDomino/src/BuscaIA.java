
import java.util.ArrayList;
/**
 *
 * @author Cleverton
 */
public class BuscaIA {
    
    private ArrayList<Peca> m;
    private ArrayList<Peca> j;
    int p1;
    int p2;
    int ladoEncaixe;

    /**
     * Implementa método contrutor da classe que possui os métodos de busca e escolha da peça a ser jogada
     * @param mesa
     * @param j2
     * @param pont1
     * @param pont2 
     */
    public BuscaIA(ArrayList<Peca> mesa, ArrayList<Peca> j2, int pont1, int pont2){
        this.m = mesa;
        this.j = j2;
        this.p1 = pont1;
        this.p2 = pont2;
    }
    
    public Peca IA(){
        Arvore a = new Arvore();
        a.constroiArvore(p1, p2, m, j, 0, 2);
        ArrayList<Arvore.Nodo> Ln = a.arvore.get(0).Ln;
        int menHeur = 100;
        Peca p = new Peca(8,8);
        if(Ln.size()>0){
            for (int i = 0; i < Ln.size(); i++) {
                if(Ln.get(i).heuristicaMinMax<menHeur){
                    p = new Peca(Ln.get(i).ponta1, Ln.get(i).ponta2);
                    this.ladoEncaixe = Ln.get(i).lado;
                    menHeur = Ln.get(i).heuristicaMinMax;
                }
            }
            return p;
        }
        return null;
    }
    
    
}
