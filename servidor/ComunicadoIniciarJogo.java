public class ComunicadoIniciarJogo extends Comunicado
{
    private boolean podeIr;

    public ComunicadoIniciarJogo(boolean permitido)

    {
        this.podeIr = permitido;
    }

    public boolean getPermitidoIniciar()
    {
        return this.podeIr;
    }
}