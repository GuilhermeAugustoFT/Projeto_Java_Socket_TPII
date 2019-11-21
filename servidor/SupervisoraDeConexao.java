import java.io.*;
import java.net.*;
import java.util.*;


public class SupervisoraDeConexao extends Thread
{
	private static int qtsChutaram;
    private static int qtdJogadores;
    private Jogador usuario;
    private Socket conexao;
    private ArrayList<Jogador> jogadores;
    private static int numeroSorteado;
    private int quemJoga = 0;


    public SupervisoraDeConexao(Socket conexao, ArrayList<Jogador> jogadores) throws Exception
    {
        if (conexao == null)
            throw new Exception("Conexao ausente");

        if (jogadores == null)
            throw new Exception("Jogadores ausentes");

        this.conexao = conexao;
        this.jogadores = jogadores;
    }

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
                        	String resultado2 = gerarResultado(2);
                        	this.jogadores.get(1).receba(new Resultado(resultado1 + "\n" + resultado2));
                        	this.jogadores.get(0).receba(new Resultado(resultado2 + "\n" + resultado1));
             			   	qtsChutaram = 0;
						}
						this.jogadores.get(quemJoga).receba(new PedidoDeVez());
                    }

                    else if (comunicado instanceof PedidoParaSair)
                    {

                            synchronized (this.jogadores)
                            {
                                this.jogadores.remove(this.usuario);
                                this.usuario.receba(new Resultado("Voce foi desconectado do servidor!!\n\n"));
                                this.qtdJogadores--;
                            }
                            this.usuario.byebye();
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
			result = "O numero sorteado era " + numeroSorteado + ", entao, como dois jogadores acertaram, houve empate nessa rodada!\n\n";
			sortearNumero();
			return result;
		}

		if(comQual == 1)
		{
        	switch (Jogador.conferir(jogadaJogador1, numeroSorteado))
        	{
           	 case 0:
           	 		result = "O numero sorteado era " + numeroSorteado+ ", " +this.jogadores.get(0).getNickname() + " venceu a partida!\n\n";
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
                result = "O numero sorteado era " + numeroSorteado + " " + this.jogadores.get(1).getNickname() + " venceu a partida!\n\n";
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