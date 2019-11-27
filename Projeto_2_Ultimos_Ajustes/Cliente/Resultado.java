/**
A classe Resultado é responsável por gerar uma string contendo uma mensagem para ser passada ao cliente.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tomás Junior & Mateus Stolze Vázquez
@since 2019.
*/
public class Resultado extends Comunicado
{
	/**
		* Váriavel do tipo String que armazenha a mensagem do resultado.
	*/
    private String resultado;

	/**
	* Construtor da classe Resultado, que coloca na variável "resultado" a mensagem que o servidor quer mandar.
	* @param result mensagem que o servidor quer passar para o cliente.
	*/
    public Resultado(String result)
    {
        this.resultado = result;
    }

	/**
	* Método que retorna qual a mensagem presente na variável resultado.
	* @return Retorna uma String contendo a mensagem presente na variável resultado.
 	*/
    public String getResultado()
    {
        return this.resultado;
    }

	/**
	* Calcula o código de espalhamento (ou código de hash) de um resultado.
	* Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
	* hashcode) do resultado representado pela instância à qual o método for aplicado.
	* @return o código de espalhamento do resultado chamante do método.
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
	* Verifica se o Object fornecido como parâmetro representa um
	* resultado igual àquele representado pela instância à qual este
	* método for aplicado, resultando true em caso afirmativo,
	* ou false, caso contrário.
	* @param  obj o objeto a ser comparado com a instância à qual esse método
	* for aplicado.
	* @return true, caso o Object fornecido ao método e a instância chamante do
	* método representarem resultados iguais, ou false, caso contrário.
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
	* Gera uma representação textual de todo conteúdo do resultado.
	* Produz e resulta um String com todos os resultados contidos
	* na Resultado.
	* @return um String contendo todo o conteúdo do Resutado.
	*/

	public String toString()
	{
		String ret = "";
		ret += this.resultado;
		return ret;
	}
}