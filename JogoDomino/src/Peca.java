
/**
 * Classe responsável por implementar a estrutura de uma peça de dominó
 *
 * @author Cleverton
 */
public class Peca {

    private int ponta1;
    private int ponta2;

    public Peca(int p1, int p2) {
        this.ponta1 = p1;
        this.ponta2 = p2;
    }

    public int getPonta1() {
        return ponta1;
    }

    public void setPonta1(int ponta1) {
        this.ponta1 = ponta1;
    }

    public int getPonta2() {
        return ponta2;
    }

    public void setPonta2(int ponta2) {
        this.ponta2 = ponta2;
    }

    @Override
    public String toString() {
        return "Peca{" + "ponta1=" + ponta1 + ", ponta2=" + ponta2 + '}';
    }

}
