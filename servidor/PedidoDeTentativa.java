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
}