import java.io.*;
import java.net.*;
import java.util.*;

/**
A classe SupervisoraDeConexao é responsável por manter o servidor ativo.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tomás Junior & Mateus Stolze Vázquez
@since 2019.
*/

public class SupervisoraDeConexao extends Thread
{
	private static int qtsChutaram;
    private static int qtdJogadores;
    private Jogador usuario;
    private Socket conexao;
    private ArrayList<Jogador> jogadores;
    private static int numeroSorteado;
    private int quemJoga = 0;
    private static boolean empatou = false;

    /**
    	Construtor da classe SupervisoraDeConexao.
    	Constroi uma nova instância da classe SupervisoraDeConexao, utilizando os objetos passados por parametro.
    	@param  conexao É a conexao entre o servidor e a aplicação.
    	@param  jogadores É a lista de jogadores conectados no momento.
    	@throws Exception Caso a conexão seja nula.
    	@throws Exception Se a lista estiver vazia.
    */
    public SupervisoraDeConexao(Socket conexao, ArrayList<Jogador> jogadores) throws Exception
    {
        if (conexao == null)
            throw new Exception("Conexao ausente");

        if (jogadores == null)
            throw new Exception("Jogadores ausentes");

        this.conexao = conexao;
        this.jogadores = jogadores;
    }

	   /**
	    Este é o método responsável por manter a comunicação ativa entre servidor e aplicação.
    ´	*/
    public void run() {
        // pegamos o ‘transmissor’ da conexão
		ObjectOutputStream transmissor = null;
		try
		{
			transmissor = new ObjectOutputStream(this.conexao.getOutputStream()); // instância-se o transmissor
		}
		catch(Exception e)
		{
			return;
		}
        // pegamos o ‘receptor’ da conexão
		ObjectInputStream receptor = null;
		try
		{
			receptor = new ObjectInputStream(this.conexao.getInputStream()); // instância-se o receptor
		}
		catch(Exception e) // em caso de erro
		{
			try
			{
				transmissor.close();// fechamos o transmissor que foi aberto anteriorme
			}
			catch(Exception ex)
			{}
			return;
		}

        try
        {
            this.usuario = new Jogador(conexao, receptor, transmissor);
        }
        catch (Exception erro)
        {} // sei que passei os parametros corretos

        try
        {
            synchronized (this.jogadores)
            {
                this.jogadores.add(this.usuario);
                this.qtdJogadores++;
                if(this.qtdJogadores == 2)
                {
                    for(Jogador player: jogadores)
                        player.receba(new ComunicadoIniciarJogo(true));
                    sortearNumero();
                    this.jogadores.get(quemJoga).receba(new PedidoDeVez());
                }
            }

                for (;;) {
                    Comunicado comunicado = this.usuario.envie();

                    if (comunicado == null)
                        return;
                    else if(comunicado instanceof PedidoDeNomeacao)
                    {
                        this.usuario.setNickname(((PedidoDeNomeacao)comunicado).getNickname());
                    }

                    else if(comunicado instanceof PedidoDeTentativa)
                    {
						if(quemJoga == 0)
							quemJoga = 1;
						else
							quemJoga = 0;
						qtsChutaram++;
                        this.usuario.setTentativa(((PedidoDeTentativa) comunicado).getValorTentativa());
                        if(qtsChutaram == 2)
                        {
                        	String resultado1 = gerarResultado(1);
                        	String resultado2 =" ";
                        	if(!empatou)
                        		resultado2 = gerarResultado(2);
                        	if(empatou)
                        	 empatou = false;
                        	this.jogadores.get(1).receba(new Resultado(resultado1 + "" + resultado2));
                        	this.jogadores.get(0).receba(new Resultado(resultado2 + "" + resultado1));
             			   	qtsChutaram = 0;
						}
						this.jogadores.get(quemJoga).receba(new PedidoDeVez());
                    }

                    else if (comunicado instanceof PedidoParaSair)
                    {
						this.jogadores.get(0).receba(new Resultado("Um dos jogadores saiu da partida, voce sera desconectado em breve!"));
						this.jogadores.get(1).receba(new Resultado("Um dos jogadores saiu da partida, voce sera desconectado em breve!"));
						this.jogadores.remove(1);
						this.jogadores.remove(0);
						this.jogadores.get(0).byebye();
						this.jogadores.get(1).byebye();
                    }



                }

        }
        catch (Exception erro) {
            try {
                transmissor.close();
                receptor.close();
            } catch (Exception falha) {
            } // so tentando fechar antes de acabar a thread

            return;
        }
    }

    private int sortearNumero()
    {
       numeroSorteado = (int) (Math.random() * 100);
       return numeroSorteado;
    }

    private String gerarResultado(int comQual)throws Exception
    {
		String result = "";
        int jogadaJogador1 = this.jogadores.get(0).getTentativa();
        int jogadaJogador2 = this.jogadores.get(1).getTentativa();

        if(jogadaJogador1 == jogadaJogador2 && jogadaJogador1 == numeroSorteado)
       	{
			result = "O numero sorteado era " + numeroSorteado + ", entao, como dois jogadores acertaram, houve empate nessa rodada!\n" +
			"Um novo numero foi sorteado. Boa sorte para adivinha-lo!!\n\n";
			sortearNumero();
			empatou = true;
			return result;
		}

		if(comQual == 1)
		{
        	switch (Jogador.conferir(jogadaJogador1, numeroSorteado))
        	{
           	 case 0:
           	 		result = "O numero sorteado era " + numeroSorteado+ ", " +this.jogadores.get(0).getNickname() + " venceu a partida!\n" +
           	 		"Um novo numero foi sorteado. Boa sorte para adivinha-lo!!\n\n";
            	    sortearNumero();
            	    break;
          	case 1:
          		 	result = this.jogadores.get(0).getNickname() + " chutou o numero " + jogadaJogador1 + ", mas o numero sorteado e menor que isso.\n\n";
					break;
         	case -1:
					result = this.jogadores.get(0).getNickname() + " chutou o numero " + jogadaJogador1 + ", mas o numero sorteado e maior que isso.\n\n";
 					break;
        }
	}
	else
	{

        switch (Jogador.conferir(jogadaJogador2, numeroSorteado))
        {
            case 0:
                result = "O numero sorteado era, " + numeroSorteado + " " + this.jogadores.get(1).getNickname() + " venceu a partida!\n\n" +
                "Um novo numero foi sorteado. Boa sorte para adivinha-lo!!\n\n";
                sortearNumero();
                break;
            case 1:
				result = this.jogadores.get(1).getNickname() + " chutou o numero " + jogadaJogador2 + ", mas o numero sorteado e menor que isso.\n\n";
               	break;
            case -1:
				result = this.jogadores.get(1).getNickname() + " chutou o numero " + jogadaJogador2 + ", mas o numero sorteado e maior que isso.\n\n";
				break;
        }
	}

	return result;
    }

}