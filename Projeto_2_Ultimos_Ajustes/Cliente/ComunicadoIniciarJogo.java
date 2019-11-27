/**
Esta classe é responsável comunicar ao servidor que a partida pode iniciar.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tomás Junior & Mateus Stolze Vázquez
@since 2019.
*/

public class ComunicadoIniciarJogo extends Comunicado
{
    private boolean podeIr;

	/**
	* Construtor da classe ComunicadoIniciarJogo.
	* Constroi uma nova instância da classe ComunicadoIniciarJogo, usando como parametro um boolean para sinalizar o começo da partida.
	* @param pode	É uma variável boolean que indica se a partida pode ou não começar.
	*/

    public ComunicadoIniciarJogo(boolean permitido)

    {
        this.podeIr = permitido;
    }


	/**
	* Este método retorna o valor presente no atributo podeIr.
	* @return	Retorna o valor presente nesta variável boolean.
	*/
    public boolean getPermitidoIniciar()
    {
        return this.podeIr;
    }

	/**
	* Calcula o código de espalhamento (ou código de hash) de um ComunicadoIniciarJogo
	* Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
	* hashcode) do resultado representado pela instância à qual o método for aplicado.
	* @return o código de espalhamento do ComunicadoIniciarJogo chamante do método.
	*/

	   public int hashCode()
	   {
		int ret = 2112;
		ret = ret * 17 + new Boolean(this.podeIr).hashCode();
		if(ret < 0)
			ret = - ret;
		return ret;
	}

	/**
	* Verifica a igualdade entre dois ComunicadoIniciarJogo.
	* Verifica se o Object fornecido como parâmetro representa um
	* ComunicadoIniciarJogo igual àquele representado pela instância à qual este
	* método for aplicado, resultando true em caso afirmativo,
	* ou false, caso contrário.
	* @param  obj o objeto a ser comparado com a instância à qual esse método
	* for aplicado.
	* @return true, caso o Object fornecido ao método e a instância chamante do
	* método representarem ComunicadoIniciarJogo iguais, ou false, caso contrário.
	*/

	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;

		ComunicadoIniciarJogo com = (ComunicadoIniciarJogo)obj;
		if(this.podeIr != com.podeIr)
			return false;

		return true;
	}

	/**
	* Gera uma representação textual de todo conteúdo do ComunicadoIniciarJogo.
	* Produz e resulta um String com todos os dados contidos
	* na ComunicadoIniciarJogo.
	* @return um String contendo todo o conteúdo do ComunicadoIniciarJogo.
	*/

	public String toString()
	{
		String ret = "";
		if(this.podeIr == false)
			ret += "false";
		else
			ret += "true";
		return ret;
	}
}