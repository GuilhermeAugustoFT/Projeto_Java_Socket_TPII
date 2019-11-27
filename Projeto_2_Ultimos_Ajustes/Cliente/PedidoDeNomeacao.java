/**
A classe PedidoDeNomeacao � respons�vel por dizer ao servidor que o usuario escolheu um nome de usuario.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tom�s Junior & Mateus Stolze V�zquez
@since 2019.
*/
public class PedidoDeNomeacao extends Comunicado
{
	/**
	* Variavel do tipo String que armazena o nome do Jogador.
	*/
    private String nickname;

	/**
	* Construtor da classe PedidoDeNomeacao, que coloca na vari�vel "nickname" o nome que o usu�rio escolheu.
	* @param mick armazena o nome que o usuario escolheu.
	* @throws Exception se o nome passado por par�metro for nulo ou sem nenhum caracter diferente de espa�o em branco.
    */
    public PedidoDeNomeacao(String nick)
    {
        this.nickname = nick;
    }

	/**
	* M�todo que retorna a String presente na v�riavel "nickname".
	* @return retorna o nome presente na v�riavel "nickname".
	*/
    public String getNickname()
    {
        return this.nickname;
    }

	/**
	* Calcula o c�digo de espalhamento (ou c�digo de hash) de um PedidoDeNomeacao
	* Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
	* hashcode) do resultado representado pela inst�ncia � qual o m�todo for aplicado.
	* @return o c�digo de espalhamento do PedidoDeNomeacao chamante do m�todo.
	*/

	public int hashCode()
	{
		int ret = 2112;
		ret = ret * 17 + new String(this.nickname).hashCode();
		if(ret < 0)
			ret = - ret;
		return ret;
	}

	/**
	* Verifica a igualdade entre dois PedidoDeNomeacao.
	* Verifica se o Object fornecido como par�metro representa um
	* PedidoDeNomeacao igual �quele representado pela inst�ncia � qual este
	* m�todo for aplicado, resultando true em caso afirmativo,
	* ou false, caso contr�rio.
	* @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
	* for aplicado.
	* @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
	* m�todo representarem PedidoDeNomeacao iguais, ou false, caso contr�rio.
	*/

	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;

		PedidoDeNomeacao pd = (PedidoDeNomeacao)obj;
		if(this.nickname != pd.nickname)
			return false;

		return true;
	}

	/**
	* Gera uma representa��o textual de todo conte�do do PedidoDeNomeacao.
	* Produz e resulta um String com todos os dados contidos
	* na PedidoDeNomeacao.
	* @return um String contendo todo o conte�do do PedidoDeNomeacao.
	*/

	public String toString()
	{
		String ret = "";
		ret += this.nickname;
		return ret;
	}
}