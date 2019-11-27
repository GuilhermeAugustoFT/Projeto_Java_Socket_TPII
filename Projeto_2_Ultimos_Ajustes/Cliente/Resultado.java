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
}