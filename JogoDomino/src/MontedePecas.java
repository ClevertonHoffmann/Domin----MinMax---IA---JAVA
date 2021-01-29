
import java.util.ArrayList;
/**
 * Classe responsável pelo monte de peças seja ele o cemitério ou antes da distribuição;
 *
 * @author Cleverton
 */
public class MontedePecas {

    private ArrayList<Peca> monte;
    private Peca p;

    public MontedePecas() {
        this.inicializaMonte();
    }

    /**
     * Método responsável por iniciar monte de peças do dominó
     */
    public void inicializaMonte() {
        monte = new ArrayList();
        for (int i = 0; i <= 6; i++) {
            for (int j = 0 + i; j <= 6; j++) {
                p = new Peca(i, j);
                monte.add(p);
            }
        }
    }

    /**
     * Retorna peça aleatória do monte removendo ela
     * @return Peca aleatória ou null quando o monte estiver vazio
     */
    public Peca compraPeca() {
        Peca p = new Peca(0,0);
        if(this.getMonte().size()!=0){
        int j = (int) ((int) (monte.size()-1)*Math.random());
            if (this.getMonte().get(j) != null) {
                p = (Peca) this.getMonte().get(j);
                this.monte.remove(j);
            }
            return p;
        }else{
            return null;
        }
    }

    public ArrayList<Peca> distribuiPecas() {
        Peca p;
        int j;
        ArrayList<Peca> ap = new ArrayList();
        for (int i = 0; ap.size() < 7; i++) {
            j = (int) ((int) (monte.size())*Math.random());
            if (this.getMonte().get(j) != null) {
                p = (Peca) this.getMonte().get(j);
                ap.add(p);
                this.monte.remove(j);
            }
        }
        return ap;
    }

    public ArrayList getMonte() {
        return monte;
    }

    public void setMonte(ArrayList monte) {
        this.monte = monte;
    }

    @Override
    public String toString() {
        return "MontedePecas{" + "monte=" + monte + '}';
    }

}
