/**
A classe Resultado � respons�vel por gerar uma string contendo uma mensagem para ser passada ao cliente.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tom�s Junior & Mateus Stolze V�zquez
@since 2019.
*/

public class Resultado extends Comunicado
{
	/**
	* V�riavel do tipo String que armazenha a mensagem do resultado.
	*/
    private String resultado;

	/**
	* Construtor da classe Resultado, que coloca na vari�vel "resultado" a mensagem que o servidor quer mandar.
	* @param result mensagem que o servidor quer passar para o cliente.
	*/
    public Resultado(String result)
    {
        this.resultado = result;
    }

	/**
	* M�todo que retorna qual a mensagem presente na vari�vel resultado.
	* @return Retorna uma String contendo a mensagem presente na vari�vel resultado.
 	*/
    public String getResultado()
    {
        return this.resultado;
    }

    /**
	* Calcula o c�digo de espalhamento (ou c�digo de hash) de um resultado.
	* Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
	* hashcode) do resultado representado pela inst�ncia � qual o m�todo for aplicado.
	* @return o c�digo de espalhamento do resultado chamante do m�todo.
    */

    public int hashCode()
	{
		int ret = 2112;
		ret = ret * 17 + new String(this.resultado).hashCode();
		if(ret < 0)
			ret = - ret;
		return ret;
	}

	/**
	* Verifica a igualdade entre dois resultados.
	* Verifica se o Object fornecido como par�metro representa um
	* resultado igual �quele representado pela inst�ncia � qual este
	* m�todo for aplicado, resultando true em caso afirmativo,
	* ou false, caso contr�rio.
	* @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
	* for aplicado.
	* @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
	* m�todo representarem resultados iguais, ou false, caso contr�rio.
    */

	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;

		Resultado res = (Resultado)obj;
		if(this.resultado != res.resultado)
			return false;

		return true;
	}

	/**
	* Gera uma representa��o textual de todo conte�do do resultado.
	* Produz e resulta um String com todos os resultados contidos
	* na Resultado.
	* @return um String contendo todo o conte�do do Resutado.
    */

	public String toString()
	{
		String ret = "";
		ret += this.resultado;
		return ret;
	}
}