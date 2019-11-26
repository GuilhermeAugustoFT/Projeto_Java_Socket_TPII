/**
A classe PedidoDeNomeacao � respons�vel por dizer ao servidor que o usuario escolheu um nome de usuario.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tom�s Junior & Mateus Stolze V�zquez
@since 2019.
*/
public class PedidoDeNomeacao extends Comunicado
{
	/**
	* Variavel do tipo String que armazena o nome do Jogador.
	*/
    private String nickname;

    /**
    * Construtor da classe PedidoDeNomeacao, que coloca na vari�vel "nickname" o nome que o usu�rio escolheu.
    * @param mick armazena o nome que o usuario escolheu.
    * @throws Exception se o nome passado por par�metro for nulo ou sem nenhum caracter diferente de espa�o em branco.
    */
    public PedidoDeNomeacao(String nick) throws Exception
    {
		if(nick == null || nick.trim().equals(""))
			throw new Exception("Escolha um nome valido!!!");
        this.nickname = nick;
    }

	/**
	* M�todo que retorna a String presente na v�riavel "nickname".
	* @return retorna o nome presente na v�riavel "nickname".
	*/
    public String getNickname()
    {
        return this.nickname;
    }
}