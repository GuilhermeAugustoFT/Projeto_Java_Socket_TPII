//package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

    private static final String HOST_PADRAO  = "177.220.18.12";
    private static final int    PORTA_PADRAO = 3000;
    private static Jogador servidor=null;

    public static void main(String[] args) {
        int porta = 0;
        String host = "";
        try
        {
            System.out.println("Para se conectar ao jogo, digite a porta no qual o servidor está rodando:\n");
            try
            {
                porta = Teclado.getUmInt();
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            System.out.println("Digite também o IP do host, que foi passado pelo servidor: (Ex: 177.220.18.12)\n");
            host = Teclado.getUmString();

            Socket conexao=null;
            try
            {

                System.out.println (host+" "+porta);
                conexao = new Socket (host, porta);
            }
            catch (Exception erro)
            {			erro.printStackTrace();

                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            ObjectOutputStream transmissor=null;
            try
            {
                transmissor =
                        new ObjectOutputStream(
                                conexao.getOutputStream());
            }
            catch (Exception erro)
            {			erro.printStackTrace();

                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            ObjectInputStream receptor=null;
            try
            {
                receptor =
                        new ObjectInputStream(
                                conexao.getInputStream());
            }
            catch (Exception erro)
            {			erro.printStackTrace();

                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }


            try
            {
                servidor =
                        new Jogador (conexao, receptor, transmissor);
            }
            catch (Exception erro)
            {			erro.printStackTrace();

                System.err.println ("Indique o servidor e a porta corretos!\n");
                return;
            }

            try
            {
                System.out.println("Conectado! \n");
                System.out.println("Aguardando um oponente...");
                ComunicadoIniciarJogo podeIr = (ComunicadoIniciarJogo) servidor.envie();
            }
            catch(Exception e)
            {}

            String nome = null;
            try
            {
				System.out.print("---------------------------------------------------\n" +
									"Regras do jogo: \n" +
									 "- Apenas chute um numero de 1 a 100. \n" +
									 "- O primeiro jogador a acertar o numero sorteado vence a partida. \n" +
									 "- O jogo te dara dicas se sua tentativa foi alta ou baixa em relacao ao numero sorteado. \n" +
									 "- Preste muita atencao nas dicas.\n" +
									 "- BOA SORTE.\n" +
									 "---------------------------------------------------\n");
                System.out.println("Digite seu nome:\n");
                nome = Teclado.getUmString();
                servidor.receba(new PedidoDeNomeacao(nome));
            }
            catch(Exception ex)
            {
                System.err.println ("Nome invalido!\n");
                return;
            }

            for(;;)
           {
				PedidoDeVez meuTurno = new PedidoDeVez();
				if(servidor.envie().getClass() == meuTurno.getClass())
			{
				int tentativa =-1;

                while(tentativa <0)
            {
				System.out.println("Digite a sua tentativa:\n");
                try
                {
                 tentativa = Teclado.getUmInt();
			    }
			    catch(Exception err)
			    {
					System.err.println("Opcao invalida");
					continue;
				}
			}

               servidor.receba(new PedidoDeTentativa(tentativa));
               Resultado result = (Resultado) servidor.envie();
               System.out.println(result.getResultado());
		   }
          }

        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
