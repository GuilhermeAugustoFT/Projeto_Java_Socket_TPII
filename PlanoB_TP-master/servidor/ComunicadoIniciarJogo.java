
/**
Esta classe é responsável comunicar ao servidor que a partida pode iniciar.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tomás Junior & Mateus Stolze Vázquez
@since 2019.
*/

public class ComunicadoIniciarJogo extends Comunicado
{
    private boolean podeIr;

	/**
	Construtor da classe ComunicadoIniciarJogo.
	Constroi uma nova instância da classe ComunicadoIniciarJogo, usando como parametro um boolean para sinalizar o começo da partida.
	@param pode	É uma variável boolean que indica se a partida pode ou não começar.
	*/

    public ComunicadoIniciarJogo(boolean permitido)
    {
        this.podeIr = permitido;
    }

	/**
		Este método retorna o valor presente no atributo podeIr.
		@return	Retorna o valor presente nesta variável boolean.
	*/

    public boolean getPermitidoIniciar()
    {
        return this.podeIr;
    }
}