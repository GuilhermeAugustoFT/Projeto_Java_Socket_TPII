/**
A classe PedidoDeTentativa é responsável por dizer ao servidor que o usuario chutou algum número.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tomás Junior & Mateus Stolze Vázquez
@since 2019.
*/
public class PedidoDeTentativa extends Comunicado
{
	/**
	* Variavel inteira que armazena o número que o usuário chutou
	*/
    private int tentativa;

	/**
	* Construtor da classe PedidoDeTentativa, que coloca na variável "tentativa" o valor que o usuário chutou.
	* @param tentativa armazena o valor que o usuário chutou.
	* @throws Exception se o valor passado por parâmetro for impossivel de ser alocado à variável "tentativa" desta classe.
    */
    public PedidoDeTentativa(int tentativa) throws Exception
    {
        try
        {
            this.tentativa = tentativa;
        }
        catch(Exception ex)
        {
            throw new Exception(ex.getMessage());
        }
    }

	/**
	* Método que retorna o valor presente na váriavel "tentativa".
	* @return retorna o valor presente na váriavel "tentativa".
	*/
    public int getValorTentativa()
    {
        return this.tentativa;
    }

	/**
	* Calcula o código de espalhamento (ou código de hash) de um PedidoDeTentativa
	* Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
	* hashcode) do resultado representado pela instância à qual o método for aplicado.
	* @return o código de espalhamento do PedidoDeTentativa chamante do método.
	*/

	public int hashCode()
	{
		int ret = 2112;
		ret = ret * 17 + new Integer(this.tentativa).hashCode();
		if(ret < 0)
			ret = - ret;
		return ret;
	}

	/**
	* Verifica a igualdade entre dois PedidoDeTentativa.
	* Verifica se o Object fornecido como parâmetro representa um
	* PedidoDeTentativa igual àquele representado pela instância à qual este
	* método for aplicado, resultando true em caso afirmativo,
	* ou false, caso contrário.
	* @param  obj o objeto a ser comparado com a instância à qual esse método
	* for aplicado.
	* @return true, caso o Object fornecido ao método e a instância chamante do
	* método representarem PedidoDeTentativa iguais, ou false, caso contrário.
	*/

	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;

		PedidoDeTentativa pd = (PedidoDeTentativa)obj;
		if(this.tentativa != pd.tentativa)
			return false;

		return true;
	}

	/**
	* Gera uma representação textual de todo conteúdo do PedidoDeTentativa.
	* Produz e resulta um String com todos os dados contidos
	* na PedidoDeTentativa.
	* @return um String contendo todo o conteúdo do PedidoDeTentativa.
	*/

	public String toString()
	{
		String ret = "";
		ret += this.tentativa + "";
		return ret;
	}
}