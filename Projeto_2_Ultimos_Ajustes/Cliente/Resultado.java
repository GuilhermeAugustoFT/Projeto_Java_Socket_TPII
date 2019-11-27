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
}