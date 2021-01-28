
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
        System.out.println(a);
        return null;
    }
    
    
}
