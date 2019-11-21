public class PedidoDeTentativa extends Comunicado
{
    private int tentativa;
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

    public int getValorTentativa()
    {
        return this.tentativa;
    }
}