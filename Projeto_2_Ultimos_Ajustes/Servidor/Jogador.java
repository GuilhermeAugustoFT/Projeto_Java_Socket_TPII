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
	 	Construtor da classe Jogador.
	    Cria uma nova instancia da classe utilizando os obejetos recebido por parametro.
	    @param  conexao	Estabelece conexão com o servidor.
	    @param  receptor	O servidor recebe informações vindas do cliente.
	    @param  transmissor	O usuário recebe informações vindas do servidor.
	    @throws Exception	Se a conexão for nula.
	    @throws Exception	Se o receptor for nulo.
	    @throws Exception	Se o transmissor for nulo.
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
	    O método receba() faz com que o cliente receba as informações mandadas pelo servidor.
	    @param x É o comunicado que o servidor quer mandar.
	    @throws Exception Se houver erro durante a transmissão de dados.
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
            throw new Exception ("Erro de transmissao");
        }
    }

	/**
	    Este método é responsável por receber as informações que o usuario manda.
	    @throws Exception	Se houver erro durante a recepção de dados.
	    @return Um comunicado recebido do cliente.
    */
    public Comunicado envie () throws Exception
    {
        try
        {
            return (Comunicado)this.receptor.readObject();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao");
        }
    }

	/**
		Metodo que compara a jogada de um jogador com o numero sorteado.
		O metodo recebe por parametro o numero que o usuario chutou e o numero sorteado,
		retornando -1 se a jogada do jogador for menor que o numero sorteado, 1 se for maior, e 0 se os numeros forem iguais.
		@return um inteiro designando se o numero chutado pelo cliente é maior, menor ou igual ao numero sorteado.
	*/
	public static int conferir(int tentativaJogador, int numeroSorteado)
	{
	        if(tentativaJogador < numeroSorteado)
	            return -1;
	        else if(tentativaJogador > numeroSorteado)
	            return 1;

	        return 0;
    }


	/**
	    Este metodo guarda o valor da tentativa do usuario.
	    @param int tentativa, valor que o usuario chutou.
	    @throws Exception	Se a tentativa fo menor que zero.
    */
    public void setTentativa(int tentativa) throws Exception
    {
		if(tentativa < 0)
			throw new Exception("Jogada invalida!!!");
        this.tentativa = tentativa;
    }

	/**
		Metodo que armazena o nome que o usuario deseja.
		@param nick É uma string com o nome passado pelo usuario.
		@throws Exception Se o nome for nulo ou nao possui caracteres alem de espaços em branco.
	*/
    public void setNickname(String nick) throws Exception
    {
        if(nick == null || nick.trim().equals(""))
            throw new Exception("Nome invalido!");
        this.nickname = nick;
    }

	/**
		Metodo que retorna o valor da tentativa do usuario.
		@return	Um int com o valor da tentativa.
	*/
    public int getTentativa()
    {
        return this.tentativa;
    }

   	/**
		Metodo que retorna o nome do usuario.
		@return	Uma String com o nome do usuário.
	*/
    public String getNickname()
    {
        return this.nickname;
    }

	/**
		Metodo que desconecta o usuario do servidor.
		Desconecta o usuario fechando suas conexoes com o transmissor, receptor e conexao.
		@throws Exception Se houver erro na desconexão.
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

    /**
	* Calcula o código de espalhamento (ou código de hash) de um Jogador
	* Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
	* hashcode) do resultado representado pela instância à qual o método for aplicado.
	* @return o código de espalhamento do Jogador chamante do método.
    */

    public int hashCode()
	{
		int ret = 2112;
		ret = ret * 17 + new Integer(this.tentativa).hashCode();
		ret = ret * 17 + new String(this.nickname).hashCode();
		if(ret < 0)
			ret = - ret;
		return ret;
	}

	/**
	* Verifica a igualdade entre dois Jogador.
	* Verifica se o Object fornecido como parâmetro representa um
	* Jogador igual àquele representado pela instância à qual este
	* método for aplicado, resultando true em caso afirmativo,
	* ou false, caso contrário.
	* @param  obj o objeto a ser comparado com a instância à qual esse método
	* for aplicado.
	* @return true, caso o Object fornecido ao método e a instância chamante do
	* método representarem Jogador iguais, ou false, caso contrário.
    */

	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;

		Jogador j = (Jogador)obj;
		if(this.tentativa != j.tentativa)
			return false;
		if(!this.nickname.equals(j.nickname))
			return false;

		return true;
	}

	/**
	* Gera uma representação textual de todo conteúdo do Jogador.
	* Produz e resulta um String com todos os dados contidos
	* na Jogador.
	* @return um String contendo todo o conteúdo do Jogador.
    */

	public String toString()
	{
		String ret = "";
		ret += this.tentativa + "";
		ret += this.nickname;
		return ret;
	}

	/**
	* Clona um jogador.
	* Produz e resulta uma cópia do jogador representado pela instância
	* à qual o método for aplicado.
	* @return a cópia do jogador representado pela instância à qual
	* o método for aplicado.
    */

	public Object clone()
	{
		Jogador ret = null;
		try
		{
			ret = new Jogador(this);
		}
		catch(Exception e)
		{}
		return ret;
	}

	/**
	* Constroi uma cópia da instância da classe Jogador dada.
	* Para tanto, deve ser fornecida uma instancia da classe Jogador para ser
	* utilizada como modelo para a construção da nova instância criada.
	* @param modelo a instância da classe Jogador a ser usada como modelo.
	* @throws Exception se o modelo for null.
    */

	public Jogador(Jogador modelo) throws Exception
	{
		if(modelo == null)
			throw new Exception ("Parâmetro nulo");

		this.conexao = modelo.conexao;
		this.receptor = modelo.receptor;
		this.transmissor = modelo.transmissor;
		this.tentativa = modelo.tentativa;
		this.nickname = modelo.nickname;
	}
}