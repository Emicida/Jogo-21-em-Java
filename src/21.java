import java.util.Random;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        System.out.println("\n\n-------Bem Vindo------- \n\nRegras do jogo: \n 01-Se Você alcançar 21 pontos, voce vence ganhando o dobro  de sua aposta. \n 02-Caso você e nem o computador façam 21 pontos ou mais,quem tiver mais pontos vence. \n 03-se passar de 21 pontos você perde. \n\n QUE COMECE OS JOGOS!!\n\n");
        int comando;
        do{
            System.out.println(" 1 - Jogar");
            System.out.println(" 2 - Sair");
            Scanner game = new Scanner(System.in);
            comando = game.nextInt();
            System.out.print("\r\n");
            Jogo jogo = new Jogo();
        }while(comando!=2);
    }
}

class Jogo{
    Baralho baralho;
    Jogador jogador;
    public Jogo(){
        this.baralho = new Baralho();
        this.baralho.Embaralhar();
        this.jogador = new Jogador();
        this.jogador.jogar(baralho);
        this.jogador.jogarpc(baralho);
        this.jogador.ganhador();
    }
}

class Baralho {
    private List<Carta> listaCartas;
    public Baralho() {
        listaCartas = new ArrayList<>();
        for(int i=0; i<14; i++){
            for(int j=0; j<4; j++){
                if(i<13){
                    if(j==0){
                        this.listaCartas.add(new Carta(i+1,"COPAS",false));
                    }

                    if(j==1){
                        this.listaCartas.add(new Carta(i+1,"PAUS",false));
                    }

                    if(j==2){
                        this.listaCartas.add(new Carta(i+1,"ESPADA",false));
                    }

                    else{
                        this.listaCartas.add(new Carta(i+1,"OURO",false));
                    }
                }
                else{
                    if(j==0){
                        this.listaCartas.add(new Carta(1,"JOKER",true));
                    }

                    if(j==1){
                        this.listaCartas.add(new Carta(2,"JOKER",true));
                    }

                    if(j==2){
                        this.listaCartas.add(new Carta(3,"JOKER",true));
                    }

                    else{
                        this.listaCartas.add(new Carta(4,"JOKER",true));
                    }
                }
            }
        }
    }
    public Carta removerCartaDoTopo(){
        return this.listaCartas.remove(0);
    }
    public void Embaralhar(){
        Collections.shuffle(this.listaCartas);
    }
}

class Carta{
    int numero;
    String naipe;
    boolean joker;
    public Carta(int numero,String naipe,boolean joker){
        this.numero=numero;
        this.naipe=naipe;
        this.joker=joker;
    }
    public void mostrar(){
        System.out.println("Numero da carta: " +this.numero);
        System.out.println("Naipe: " +this.naipe);
        if (this.joker){
            System.out.println("-----------JOKER----------.");
        }
    }
}

class Jogador {
    int valor;
    public

    Random jogador = new Random();
    int soma;
    int escolha;

    public void jogar(Baralho baralho) {
        Scanner valor = new Scanner(System.in);
        System.out.println("Escolha o valor de sua aposta: ");
        System.out.println("");
        this.valor = valor.nextInt();
        // return valor.nextInt();
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEscolha o que deseja fazer: ");
            System.out.println("1 - Pegar carta");
            System.out.println("2 - Parar");
            System.out.println("");
            this.escolha = scanner.nextInt();
            if (this.escolha == 2) {
                break;
            }
            Carta c = baralho.removerCartaDoTopo();
            c.mostrar();
            soma = soma + c.numero;
            System.out.println("Soma: " + soma);
            System.out.println("");
            if (soma > 21) {
                System.out.println("Voce passou de 21 pontos!");
                System.out.println("Voce perdeu: R$" + this.valor + ",00");
                System.exit(0);
            }

            if (soma == 21) {
                System.out.println("Voce ganhou!!!");
                System.out.println("Voce ganhou: R$" + this.valor * 2 + ",00");
                System.exit(0);
            }

        } while (this.escolha == 1);
    }

    int somapc = 0;
    Random computador = new Random();

    public void jogarpc(Baralho baralho) {
        do {
            Carta c = baralho.removerCartaDoTopo();
            c.mostrar();
            somapc = somapc + c.numero;
            System.out.println("Soma: " + somapc);
            System.out.println("");
            if (somapc > 21) {
                System.out.println("O computador passou de 21!");
                System.out.println("Voce ganhou: " + this.valor * 2);
                System.exit(0);
            }
            if (somapc == 21) {
                System.out.println("O computador conseguiu 21!");
                System.out.println("Voce perdeu: " + this.valor);
                System.exit(0);
            }
        } while (somapc < this.soma);
        System.out.println("\n\n");
    }

    public void ganhador() {
        if (this.soma < this.somapc) {
            System.out.println("Computador ganhou o jogo!");
            System.out.println("Voce perdeu: " + this.valor);
        } else {
            if (this.soma > this.somapc) {
                System.out.println("Jogador ganhou o jogo!");
                System.out.println("Voce ganhou: " + this.valor * 2);
            } else {
                System.out.println("empate!");
                System.out.println("Voce manteve a sua aposta, ninguem ganhou...");
            }
        }
    }
}
