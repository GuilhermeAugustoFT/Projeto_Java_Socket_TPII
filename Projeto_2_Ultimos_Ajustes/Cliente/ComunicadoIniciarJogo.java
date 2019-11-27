/**
Esta classe � respons�vel comunicar ao servidor que a partida pode iniciar.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tom�s Junior & Mateus Stolze V�zquez
@since 2019.
*/

public class ComunicadoIniciarJogo extends Comunicado
{
    private boolean podeIr;

	/**
	* Construtor da classe ComunicadoIniciarJogo.
	* Constroi uma nova inst�ncia da classe ComunicadoIniciarJogo, usando como parametro um boolean para sinalizar o come�o da partida.
	* @param pode	� uma vari�vel boolean que indica se a partida pode ou n�o come�ar.
	*/

    public ComunicadoIniciarJogo(boolean permitido)

    {
        this.podeIr = permitido;
    }


	/**
	* Este m�todo retorna o valor presente no atributo podeIr.
	* @return	Retorna o valor presente nesta vari�vel boolean.
	*/
    public boolean getPermitidoIniciar()
    {
        return this.podeIr;
    }
}