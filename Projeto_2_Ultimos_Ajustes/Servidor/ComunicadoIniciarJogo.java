
/**
Esta classe � respons�vel comunicar ao servidor que a partida pode iniciar.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tom�s Junior & Mateus Stolze V�zquez
@since 2019.
*/

public class ComunicadoIniciarJogo extends Comunicado
{
    private boolean podeIr;

	/**
	Construtor da classe ComunicadoIniciarJogo.
	Constroi uma nova inst�ncia da classe ComunicadoIniciarJogo, usando como parametro um boolean para sinalizar o come�o da partida.
	@param pode	� uma vari�vel boolean que indica se a partida pode ou n�o come�ar.
	*/

    public ComunicadoIniciarJogo(boolean permitido)
    {
        this.podeIr = permitido;
    }

	/**
		Este m�todo retorna o valor presente no atributo podeIr.
		@return	Retorna o valor presente nesta vari�vel boolean.
	*/

    public boolean getPermitidoIniciar()
    {
        return this.podeIr;
    }

    /**
	* Calcula o c�digo de espalhamento (ou c�digo de hash) de um ComunicadoIniciarJogo
	* Calcula e resulta o c�digo de espalhamento (ou c�digo de hash, ou ainda o
	* hashcode) do resultado representado pela inst�ncia � qual o m�todo for aplicado.
	* @return o c�digo de espalhamento do ComunicadoIniciarJogo chamante do m�todo.
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
	* Verifica se o Object fornecido como par�metro representa um
	* ComunicadoIniciarJogo igual �quele representado pela inst�ncia � qual este
	* m�todo for aplicado, resultando true em caso afirmativo,
	* ou false, caso contr�rio.
	* @param  obj o objeto a ser comparado com a inst�ncia � qual esse m�todo
	* for aplicado.
	* @return true, caso o Object fornecido ao m�todo e a inst�ncia chamante do
	* m�todo representarem ComunicadoIniciarJogo iguais, ou false, caso contr�rio.
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
	* Gera uma representa��o textual de todo conte�do do ComunicadoIniciarJogo.
	* Produz e resulta um String com todos os dados contidos
	* na ComunicadoIniciarJogo.
	* @return um String contendo todo o conte�do do ComunicadoIniciarJogo.
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