import java.net.*;
import java.util.*;

/**
Classe que realiza a conexao entre o servidor e o cliente.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tom�s Junior & Mateus Stolze V�zquez
@since 2019.
*/
public class AceitadoraDeConexao extends Thread
{
    private static final int PORTA_PADRAO = 3000;

    private ServerSocket pedido;
    private ArrayList<Jogador> jogadores;

    /**
    	Construtor da classe AceitadoraDeConexao
    	Constroi uma nova inst�ncia da classe AceitadoraDeConexao.
    	@param escolha String na qual est� armazenada a porta.
    	@param jogadores Lista de jogares da classe Parceiro.
    	@throws Exception 	Se a porta for inv�lida.
    	@throws Exception 	Se a lista de jogadores estiver vazia.
    */
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

    /**
    	Metodo responsavel por aceitar as conex�es entre o servidor e o cliente.
    	Este metodo aceita novas conexoes de novos usuarios.
    */
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