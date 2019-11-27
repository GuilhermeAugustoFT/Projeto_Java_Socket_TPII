/**
A classe PedidoDeNomeacao é responsável por dizer ao servidor que o usuario escolheu um nome de usuario.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tomás Junior & Mateus Stolze Vázquez
@since 2019.
*/
public class PedidoDeNomeacao extends Comunicado
{
	/**
	* Variavel do tipo String que armazena o nome do Jogador.
	*/
    private String nickname;

	/**
	* Construtor da classe PedidoDeNomeacao, que coloca na variável "nickname" o nome que o usuário escolheu.
	* @param mick armazena o nome que o usuario escolheu.
	* @throws Exception se o nome passado por parâmetro for nulo ou sem nenhum caracter diferente de espaço em branco.
    */
    public PedidoDeNomeacao(String nick)
    {
        this.nickname = nick;
    }

	/**
	* Método que retorna a String presente na váriavel "nickname".
	* @return retorna o nome presente na váriavel "nickname".
	*/
    public String getNickname()
    {
        return this.nickname;
    }

	/**
	* Calcula o código de espalhamento (ou código de hash) de um PedidoDeNomeacao
	* Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
	* hashcode) do resultado representado pela instância à qual o método for aplicado.
	* @return o código de espalhamento do PedidoDeNomeacao chamante do método.
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
	* Verifica se o Object fornecido como parâmetro representa um
	* PedidoDeNomeacao igual àquele representado pela instância à qual este
	* método for aplicado, resultando true em caso afirmativo,
	* ou false, caso contrário.
	* @param  obj o objeto a ser comparado com a instância à qual esse método
	* for aplicado.
	* @return true, caso o Object fornecido ao método e a instância chamante do
	* método representarem PedidoDeNomeacao iguais, ou false, caso contrário.
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
	* Gera uma representação textual de todo conteúdo do PedidoDeNomeacao.
	* Produz e resulta um String com todos os dados contidos
	* na PedidoDeNomeacao.
	* @return um String contendo todo o conteúdo do PedidoDeNomeacao.
	*/

	public String toString()
	{
		String ret = "";
		ret += this.nickname;
		return ret;
	}
}