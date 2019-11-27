/**
A classe PedidoDeTentativa � respons�vel por dizer ao servidor que o usuario chutou algum n�mero.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tom�s Junior & Mateus Stolze V�zquez
@since 2019.
*/
public class PedidoDeTentativa extends Comunicado
{
	/**
	* Variavel inteira que armazena o n�mero que o usu�rio chutou
	*/
    private int tentativa;

	/**
	* Construtor da classe PedidoDeTentativa, que coloca na vari�vel "tentativa" o valor que o usu�rio chutou.
	* @param tentativa armazena o valor que o usu�rio chutou.
	* @throws Exception se o valor passado por par�metro for impossivel de ser alocado � vari�vel "tentativa" desta classe.
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
	* M�todo que retorna o valor presente na v�riavel "tentativa".
	* @return retorna o valor presente na v�riavel "tentativa".
	*/
    public int getValorTentativa()
    {
        return this.tentativa;
    }

	/**
	* Calcula o c�digo de espalhamento (ou c�digo de hash) de um PedidoDeTentativa
	* Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
	* hashcode) do resultado representado pela inst�ncia � qual o m�todo for aplicado.
	* @return o c�digo de espalhamento do PedidoDeTentativa chamante do m�todo.
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
	* Verifica se o Object fornecido como par�metro representa um
	* PedidoDeTentativa igual �quele representado pela inst�ncia � qual este
	* m�todo for aplicado, resultando true em caso afirmativo,
	* ou false, caso contr�rio.
	* @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
	* for aplicado.
	* @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
	* m�todo representarem PedidoDeTentativa iguais, ou false, caso contr�rio.
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
	* Gera uma representa��o textual de todo conte�do do PedidoDeTentativa.
	* Produz e resulta um String com todos os dados contidos
	* na PedidoDeTentativa.
	* @return um String contendo todo o conte�do do PedidoDeTentativa.
	*/

	public String toString()
	{
		String ret = "";
		ret += this.tentativa + "";
		return ret;
	}
}