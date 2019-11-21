//package servidor;

//import servidor.AceitadoraDeConexao;


//import servidor.ComunicadoDeDesligamento;
//import servidor.Jogador;
//import servidor.Teclado;

import java.util.*;
import java.net.*;
public class Server {
    public static void main(String[] args) {
        System.out.println("Indique a porta desejada:");
        String porta = null;
        try {
            porta = Teclado.getUmString();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Valor invalido");
            return;
        }
        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

        AceitadoraDeConexao aceitadoraDeConexao = null;
        try {
            aceitadoraDeConexao = new AceitadoraDeConexao(porta, jogadores);
            try {
                System.out.println("\nIp para se conectar ao jogo: " + InetAddress.getLocalHost().getHostAddress() + "\n");
            } catch (Exception e) {
            }
            aceitadoraDeConexao.start();
        } catch (Exception ex) {
            System.err.println("Escolha uma porta para rodar o servidor!\n");
            return;
        }

        for (; ; ) {
            System.out.println("O servidor esta ativo! Para desativa-lo,");
            System.out.println("use o comando \"desligar\"\n");
            System.out.print("> ");

            String comando = null;
            try {
                comando = Teclado.getUmString();
            } catch (Exception ex) {
            }

            if (comando.toLowerCase().equals("desligar")) {
                synchronized (jogadores) {
                    for (Jogador jogador : jogadores) {
                        ComunicadoDeDesligamento com = new ComunicadoDeDesligamento();
                        try {
                            jogador.receba(com);
                            jogador.byebye();
                        } catch (Exception ex) {
                        }
                    }
                }
                System.out.println("O servidor foi desativado!\n");
                System.exit(0);
            } else
                System.out.println("Comando invalido!\n");
        }
    }
}