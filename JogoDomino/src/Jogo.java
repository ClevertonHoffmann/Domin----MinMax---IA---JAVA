
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe responsável por gerenciar o jogo de dominó
 *
 * @author Cleverton
 */
public class Jogo {

    private ArrayList<Peca> mesa = new ArrayList();
    private ArrayList<Peca> j1;
    private ArrayList<Peca> j2;
    private MontedePecas m;
    private int vez; //1 jogador1 - 2 jogador2
    private int fim = 0; //se for 2 jogo foi fechado sem mais jogadas
    private int lado; //Lado do dominó para encaixar peça

    /**
     * Método principal que inicializa o jogo e realiza a chamada das funções do jogo
     * @return boolean
     */
    public boolean playGame() {
        //Instancia e cria o monte de peças do dominó
        m = new MontedePecas();

        //Distribui as peças para os dois jogadores
        j1 = m.distribuiPecas(); //MIN
        j2 = m.distribuiPecas(); //MAX

        //Realiza a jogada da primeira peça automáticamente jogador que tiver um dobre
        mesa.add(this.retornaPecaMaiorDobre());
        int p1 = mesa.get(0).getPonta1();
        int p2 = mesa.get(0).getPonta2();
        System.out.print("MESA: ");
        System.out.println(" [" + p1 + "," + p2 + "]\n");
        if (j1.size() < j2.size()) {
            this.vez = 2;
        } else {
            this.vez = 1;
        }

//        if(this.vez == 1){
//            System.out.print("SUA MÃO: ");
//            for (int i = 0; i < j1.size(); i++) {
//                System.out.print(" [" + j1.get(i).getPonta1() + "," + j1.get(i).getPonta2() + "]");
//            }
//            System.out.println("");
//        }
        //Enquanto os jogadores tiverem peças que encaixam e nenhuma mão estiver vazia retorna uma peça para encaixe
        while (m.getMonte().size() > 0 || (j1.size() > 0 && j2.size() > 0)) {
            Peca p;
            if (!j1.isEmpty() && !j2.isEmpty()) {
                p = this.retornaPecaEncaixe(p1, p2);
            } else {
                p = null;
                break;
            }
            this.fim = 0;
            if (p != null) {
                if (this.vez == 2) {
                    //Encaixa no começo
                    if (this.lado == 1) {
                        if (p1 == p.getPonta2()) {
                            mesa.add(0, p);//Encaixa no começo
                        } else {
                            if (p1 == p.getPonta1()) {
                                mesa.add(0, this.giraPeca(p));//Encaixa no começo
                            } else {
                                System.out.print("Peça escolhida inválida! Escolha outra peça!\n");
                                this.vez = 1;
                                if (p.getPonta1() != 8) {
                                    j1.add(p);//Retorna a peça a mão pois não encaixa no jogo!
                                }
                            }
                        }
                    } else {
                        if (p2 == p.getPonta1()) {
                            mesa.add(p);//Encaixa no fim
                        } else {
                            if (p2 == p.getPonta2()) {
                                mesa.add(this.giraPeca(p));//Encaixa no fim
                            } else {
                                System.out.print("Peça escolhida inválida! Escolha outra peça!\n");
                                this.vez = 1;
                                if (p.getPonta1() != 8) {
                                    j1.add(p);//Retorna a peça a mão pois não encaixa no jogo!
                                }
                            }
                        }
                    }
                    p1 = mesa.get(0).getPonta1();
                    p2 = mesa.get(mesa.size() - 1).getPonta2();
                } else {
                    //Encaixa no começo
                    if(this.lado != 0){
                    if (this.lado == 1) {
                        if (p1 == p.getPonta2()) {
                            mesa.add(0, p);//Encaixa no começo
                        } else {
                            if (p1 == p.getPonta1()) {
                                mesa.add(0, this.giraPeca(p));//Encaixa no começo
                            }
                        }
                    }else{
                        if (p2 == p.getPonta1()) {
                            mesa.add(p);//Encaixa no fim
                        } else {
                            if (p2 == p.getPonta2()) {
                                mesa.add(this.giraPeca(p));//Encaixa no fim
                            } 
                        }
                    }
                    }else{ //original
                        if (p1 == p.getPonta2()) {
                            mesa.add(0, p);//Encaixa no começo
                        } else {
                            if (p2 == p.getPonta1()) {
                                mesa.add(p);//Encaixa no final
                            } else {
                                //Encaixes com giro na peça para encaixar
                                if (p1 == p.getPonta1()) {
                                    mesa.add(0, this.giraPeca(p));//Encaixa no começo
                                } else {
                                    if (p2 == p.getPonta2()) {
                                        mesa.add(this.giraPeca(p));//Encaixa no final
                                    }
                                }
                            }
                        }
                    }
                    p1 = mesa.get(0).getPonta1();
                    p2 = mesa.get(mesa.size() - 1).getPonta2();
                }
            } else {
                break;
            }
//JOGO DO COMPUTADOR VS ELE MESMO
//            this.fim = 0;
//            if (p != null) {
//                //Encaixes normais sem girar a peça
//                if (p1 == p.getPonta2()) {
//                    mesa.add(0, p);//Encaixa no começo
//                } else {
//                    if (p2 == p.getPonta1()) {
//                        mesa.add(p);//Encaixa no final
//                    } else {
//                        //Encaixes com giro na peça para encaixar
//                        if (p1 == p.getPonta1()) {
//                            mesa.add(0, this.giraPeca(p));//Encaixa no começo
//                        } else {
//                            if (p2 == p.getPonta2()) {
//                                mesa.add(this.giraPeca(p));//Encaixa no final
//                            }
//                        }
//                    }
//                }
//                p1 = mesa.get(0).getPonta1();
//                p2 = mesa.get(mesa.size() - 1).getPonta2();
//            } else {
//                break;
//            }
            //Apenas apresentação dos dominós ja jogados na mesa
            System.out.print("MESA: ");
            for (int i = 0; i < mesa.size(); i++) {
                System.out.print(" [" + mesa.get(i).getPonta1() + "," + mesa.get(i).getPonta2() + "]");
            }
            System.out.println("\n");
        }
        //Parte que calcula os pontos do vencedor e define quem ganhou e quem perdeu ou empate
        int soma1 = 0;
        int soma2 = 0;
        for (int i = 0; i < j1.size(); i++) {
            soma1 = soma1 + j1.get(i).getPonta1() + j1.get(i).getPonta2();
        }
        for (int i = 0; i < j2.size(); i++) {
            soma2 = soma2 + j2.get(i).getPonta1() + j2.get(i).getPonta2();
        }
        if (!j1.isEmpty() && !j2.isEmpty()) {
            if (soma1 == soma2) {
                System.out.println("Jogo fechado!");
                System.out.println("Empate!");
            } else {
                if (soma1 < soma2) {
                    System.out.println("Jogo fechado!");
                    System.out.println("Você ganhou! Fez " + soma2 + " pontos!");
                } else {
                    System.out.println("Jogo fechado!");
                    System.out.println("Jogador 2 ganhou! Fez " + soma1 + " pontos!");
                }
            }
        } else {
            if (j1.isEmpty()) {
                System.out.println("Você ganhou! Fez " + soma2 + " pontos!");
            } else {
                System.out.println("Jogador 2 ganhou! Fez " + soma1 + " pontos!");
            }
        }
        System.out.println(j1);
        System.out.println(j2);
        System.out.println("Fim de Jogo!");
        return true;
    }

    /**
     * Método responsável por girar peça para encaixe no jogo
     *
     * @param p
     * @return Peça girada
     */
    public Peca giraPeca(Peca p) {
        Peca p1 = new Peca(p.getPonta2(), p.getPonta1());
        return p1;
    }

    /**
     * Retorna a peça com maior dobre das duas mãos para iniciar o jogos
     *
     * @return
     */
    public Peca retornaPecaMaiorDobre() {
        Peca p;
        int k = 6;
        while (k >= 0) {
            for (int i = 0; i < 7; i++) {
                if (j1.get(i).getPonta1() == k && j1.get(i).getPonta2() == k) {
                    p = j1.get(i);
                    j1.remove(i);
                    return p;
                }
                if (j2.get(i).getPonta1() == k && j2.get(i).getPonta2() == k) {
                    p = j2.get(i);
                    j2.remove(i);
                    return p;
                }
            }
            k--;
        }
        //Caso não encontre nenhum jogador com um dobre inicializa o monte de peças e redistribui as peças
        m.inicializaMonte();
        j1 = m.distribuiPecas();
        j2 = m.distribuiPecas();
        return this.retornaPecaMaiorDobre();
    }

    /**
     * Método de jogo do computador contra ele mesmo
     *
     * @param p1
     * @param p2
     * @return Peca aleatória
     */
    public Peca retornaPecaAleatoriaEncaixe(int p1, int p2) {
        Peca p;
        if (vez == 1) {
            int k = j1.size();
            for (int i = 0; i < k; i++) {
                if (j1.get(i).getPonta1() == p1 || j1.get(i).getPonta2() == p1 || j1.get(i).getPonta1() == p2 || j1.get(i).getPonta2() == p2) {
                    p = j1.get(i);
                    j1.remove(i);
                    this.vez = 2;
                    return p;
                }
            }
            while (m.getMonte().size() > 0) {
                j1.add(m.compraPeca());
                int j = j1.size() - 1;
                if (j1.get(j).getPonta1() == p1 || j1.get(j).getPonta2() == p1 || j1.get(j).getPonta1() == p2 || j1.get(j).getPonta2() == p2) {
                    p = j1.get(j);
                    j1.remove(j);
                    this.vez = 2;
                    return p;
                }
            }
            this.vez = 2;
            this.fim++;
        }
        if (vez == 2) {
            int k = j2.size();
            for (int i = 0; i < k; i++) {
                if (j2.get(i).getPonta1() == p1 || j2.get(i).getPonta2() == p1 || j2.get(i).getPonta1() == p2 || j2.get(i).getPonta2() == p2) {
                    p = j2.get(i);
                    j2.remove(i);
                    this.vez = 1;
                    return p;
                }
            }
            while (m.getMonte().size() > 0) {
                j2.add(m.compraPeca());
                int j = j2.size() - 1;
                if (j2.get(j).getPonta1() == p1 || j2.get(j).getPonta2() == p1 || j2.get(j).getPonta1() == p2 || j2.get(j).getPonta2() == p2) {
                    p = j2.get(j);
                    j2.remove(j);
                    this.vez = 1;
                    return p;
                }
            }
            this.vez = 1;
            this.fim++;
        }
        if (fim < 2 && (!j1.isEmpty() && !j2.isEmpty())) {
            return this.retornaPecaAleatoriaEncaixe(p1, p2);
        } else {
            return null;
        }
    }

    /**
     * Retorna peça de encaixe conforme escolha do computador ou do jogador
     * Chama também o método de IA para escolha de uma peça
     * @param p1
     * @param p2
     * @return Peça de encaixe no jogo
     */
    public Peca retornaPecaEncaixe(int p1, int p2) {
        Peca p;
        if (vez == 1) {
            System.out.print("SUA MÃO: ");
            for (int i = 0; i < j1.size(); i++) {
                System.out.print(" [" + j1.get(i).getPonta1() + "," + j1.get(i).getPonta2() + "]");
            }
            System.out.println("");
            //Realiza a leitura do código digitado
            Scanner read = new Scanner(System.in);
            System.out.println("Digite o número da peça escolhida entre 1 e " + j1.size() + ", ou 0 caso tem que comprar na pilha!");
            String escT = read.nextLine();
            if (escT.matches("[0-" + j1.size() + "]") == true) {
                int esc = Integer.parseInt(escT);
                if (esc != 0) {
                    System.out.println("Digite o lado na mesa que vai jogar a peça!");
                    this.lado = Integer.parseInt(read.nextLine());
                    p = j1.get(esc - 1);
                    j1.remove(esc - 1);
                    this.vez = 2;
                    return p;
                } else {
                    while (m.getMonte().size() > 0) {
                        j1.add(m.compraPeca());
                        int j = j1.size() - 1;
                        if (j1.get(j).getPonta1() == p1 || j1.get(j).getPonta2() == p1 || j1.get(j).getPonta1() == p2 || j1.get(j).getPonta2() == p2) {
                            p = j1.get(j);
                            j1.remove(j);
                            this.vez = 2;
                            return p;
                        }
                    }
                }
            } else {
                //Retorna peça fora do intervalo pois o valor escolhido não pertence ao dominó
                this.vez = 2;
                return p = new Peca(8, 8);
            }
            this.vez = 2;
            this.fim++;
        }
        if (vez == 2) {
            BuscaIA b = new BuscaIA(mesa, j2, p1, p2);
            p = b.IA();
            if (p != null) {
                this.lado = b.ladoEncaixe;
                this.vez = 1;
                for (int i = 0; i < j2.size(); i++) {
                    if(j2.get(i).getPonta1() == p.getPonta1()&& j2.get(i).getPonta2() == p.getPonta2()|| j2.get(i).getPonta1() == p.getPonta2()&& j2.get(i).getPonta2() == p.getPonta1()){
                        j2.remove(i);
                    }
                }
                return p;
            }else{
                this.lado = 0;//Caso compra do monte encaixa aleatóriamente no dominó
            }
//            int k = j2.size();
//            for (int i = 0; i < k; i++) {
//                if (j2.get(i).getPonta1() == p1 || j2.get(i).getPonta2() == p1 || j2.get(i).getPonta1() == p2 || j2.get(i).getPonta2() == p2) {
//                    p = j2.get(i);
//                    j2.remove(i);
//                    this.vez = 1;
//                    return p;
//                }
//            }
            while (m.getMonte().size() > 0) {
                j2.add(m.compraPeca());
                int j = j2.size() - 1;
                if (j2.get(j).getPonta1() == p1 || j2.get(j).getPonta2() == p1 || j2.get(j).getPonta1() == p2 || j2.get(j).getPonta2() == p2) {
                    p = j2.get(j);
                    j2.remove(j);
                    this.vez = 1;
                    return p;
                }
            }
            this.vez = 1;
            this.fim++;
        }
        if (fim < 2 && (!j1.isEmpty() && !j2.isEmpty())) {
            return this.retornaPecaEncaixe(p1, p2);
        } else {
            return null;
        }
    }

}
