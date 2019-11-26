/**
A classe PedidoDeNomeacao é responsável por dizer ao servidor que o usuario escolheu um nome de usuario.
@author Guilherme Augusto F. Teixeira & Vitor Mugnol Estavam de Araujo & Vicente Pinto Tomás Junior & Mateus Stolze Vázquez
@since 2019.
*/
public class PedidoDeNomeacao extends Comunicado
{
	/**
	* Variavel do tipo String que armazena o nome do Jogador.
	*/
    private String nickname;

    /**
    * Construtor da classe PedidoDeNomeacao, que coloca na variável "nickname" o nome que o usuário escolheu.
    * @param mick armazena o nome que o usuario escolheu.
    * @throws Exception se o nome passado por parâmetro for nulo ou sem nenhum caracter diferente de espaço em branco.
    */
    public PedidoDeNomeacao(String nick) throws Exception
    {
		if(nick == null || nick.trim().equals(""))
			throw new Exception("Escolha um nome valido!!!");
        this.nickname = nick;
    }

	/**
	* Método que retorna a String presente na váriavel "nickname".
	* @return retorna o nome presente na váriavel "nickname".
	*/
    public String getNickname()
    {
        return this.nickname;
    }
}