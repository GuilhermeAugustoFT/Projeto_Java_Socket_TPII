//package servidor;

import java.net.*;
import java.util.*;

public class AceitadoraDeConexao extends Thread
{
    private static final int PORTA_PADRAO = 3000;

    private ServerSocket pedido;
    private ArrayList<Jogador> jogadores;

    public AceitadoraDeConexao(String escolha, ArrayList<Jogador> jogadores) throws Exception
    {
        int porta  = AceitadoraDeConexao.PORTA_PADRAO;

        if(escolha != null)
        {
            porta = Integer.parseInt(escolha);
        }

        try
        {
            this.pedido = new ServerSocket(porta);
        }
        catch(Exception err)
        {
            throw new Exception ("Porta invalida");
        }

        if(jogadores == null)
            throw new Exception("Jogadores ausentes");

        this.jogadores = jogadores;
    }

    public void run()
    {
        for(;;)
        {
            Socket conexao = null;

            try
            {
                conexao = this.pedido.accept();
                System.out.println ("Novo Jogador conectado\n >");
            }
            catch(Exception err)
            {
                continue; //reinicia o loop
            }

            SupervisoraDeConexao supervisoraDeConexao=null;

            try
            {
                supervisoraDeConexao = new SupervisoraDeConexao (conexao, jogadores);
            }
            catch (Exception erro)
            {} // sei que passei parametros corretos para o construtor
            supervisoraDeConexao.start();
        }
    }
}