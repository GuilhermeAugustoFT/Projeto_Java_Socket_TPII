import java.io.*;
import java.net.*;
import java.util.*;

/*
A classe Jogador é responsável por estabelecer a comunicacao com o Cliente.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tomás Junior & Mateus Stolze Vázquez
@since 2019.
*/
public class Jogador
{
    private Socket conexao;
    private ObjectInputStream receptor;
    private ObjectOutputStream transmissor;
    private int tentativa;
    private String nickname;

	/**
	* Construtor da classe Jogador.
	* Cria uma nova instancia da classe utilizando os obejetos recebido por parametro.
	* @param  conexao	Estabelece conexão com o servidor.
	* @param  receptor	O servidor recebe informações vindas do cliente.
	* @param  transmissor	O usuário recebe informações vindas do servidor.
    * @throws Exception	Se a conexão for nula.
	* @throws Exception	Se o receptor for nulo.
	* @throws Exception	Se o transmissor for nulo.
    */
    public Jogador (Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor) throws Exception // se parametro nulos
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (receptor==null)
            throw new Exception ("Receptor ausente");

        if (transmissor==null)
            throw new Exception ("Transmissor ausente");

        this.conexao     = conexao;
        this.receptor    = receptor;
        this.transmissor = transmissor;
    }

    /**
	* O método receba() faz com que o cliente receba as informações mandadas pelo servidor.
	* @param x É o comunicado que o servidor quer mandar.
	* @throws Exception Se houver erro durante a transmissão de dados.
    */
    public void receba (Comunicado x) throws Exception
    {
        try
        {
            this.transmissor.writeObject (x);
            this.transmissor.flush       ();
        }
        catch (IOException erro)
        {
            throw new Exception ("Erro de transmissao\n" +
            					 "Possivelmente o servidor foi desligado!\n");
        }
    }


    public static int conferir(int tentativaJogador, int numeroSorteado)
    {
        if(tentativaJogador < numeroSorteado)
            return -1;
        else if(tentativaJogador > numeroSorteado)
            return 1;

        return 0;
    }
    /**
	* Este método é responsável por receber as informações que o usuario manda.
	* @throws Exception	Se houver erro durante a recepção de dados.
	* @return Um comunicado recebido do cliente.
    */
    public Comunicado envie () throws Exception
    {
        try
        {
            return (Comunicado)this.receptor.readObject();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao\n" +
            					  "Possivelmente o servidor foi desligado!\n");
        }
    }

	/**
	* Este metodo guarda o valor da tentativa do usuario.
	* @param int tentativa, valor que o usuario chutou.
	* @throws Exception	Se a tentativa fo menor que zero.
    */
    public void setTentativa(int tentativa) throws Exception
    {
        if(tentativa < 0)
            throw new Exception("Jogada invalida");
        this.tentativa = tentativa;
    }

	/**
	* Metodo que armazena o nome que o usuario deseja.
	* @param nick É uma string com o nome passado pelo usuario.
	* @throws Exception Se o nome for nulo ou nao possui caracteres alem de espaços em branco.
	*/
    public void setNickname(String nick) throws Exception
    {
        if(nick == null)
            throw new Exception("Nome invalido!");
        this.nickname = nick;
    }

	/**
	* Metodo que retorna o valor da tentativa do usuario.
	* @return	Um int com o valor da tentativa.
	*/
    public int getTentativa()
    {
        return this.tentativa;
    }

  	/**
	* Metodo que retorna o nome do usuario.
	* @return	Uma String com o nome do usuário.
	*/
    public String getNickname()
    {
        return this.nickname;
    }

    /**
	* Metodo que desconecta o usuario do servidor.
	* Desconecta o usuario fechando suas conexoes com o transmissor, receptor e conexao.
	* @throws Exception Se houver erro na desconexão.
	*/
    public void byebye () throws Exception
    {
        try
        {
            this.transmissor.close();
            this.receptor.close();
            this.conexao.close();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de desconexao");
        }
    }
}