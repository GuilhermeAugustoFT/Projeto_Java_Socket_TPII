
public class PedidoDeNomeacao extends Comunicado
{
    private String nickname;

    public PedidoDeNomeacao(String nick)
    {
        this.nickname = nick;
    }

    public String getNickname()
    {
        return this.nickname;
    }
}