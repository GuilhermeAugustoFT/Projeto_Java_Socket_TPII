//package servidor;

public class Jogada  extends Comunicado {

    private int numero;
    private int numeroASerAdvinhado;

    public Jogada(int n) throws Exception
    {
        if(n < 0)
            throw new Exception("Número inválido");
        this.numeroASerAdvinhado = n;
    }

    public void setTentativa(int tentativa) throws Exception
    {
        if(tentativa < 0)
            throw new Exception("Número inválido");
        this.numero = tentativa;
    }

    public static int conferir(Jogada j, int numeroSorteado)
    {
        if(j.numero < numeroSorteado)
            return -1;
        else if(j.numero > numeroSorteado)
            return 1;

        return 0;
    }

    public int getNumeroASerAdvinhado()
    {
        return this.numeroASerAdvinhado;
    }

    public int getTentativa()
    {
        return numero;
    }


}
