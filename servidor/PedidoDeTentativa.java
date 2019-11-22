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
}