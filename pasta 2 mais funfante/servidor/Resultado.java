//package servidor;

public class Resultado extends Comunicado
{
    private String resultado;

    public Resultado(String result)
    {
        this.resultado = result;
    }

    public String getResultado()
    {
        return this.resultado;
    }
}