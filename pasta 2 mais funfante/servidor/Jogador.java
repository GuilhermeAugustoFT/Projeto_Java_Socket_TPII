//package servidor;

//import servidor.Comunicado;

import java.io.*;
import java.net.*;
import java.util.*;

public class Jogador
{
    private Socket conexao;
    private ObjectInputStream receptor;
    private ObjectOutputStream transmissor;
    private int tentativa;
    private String nickname;

    public Jogador (Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor) throws Exception // se parametro nulos
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (receptor==null)
            throw new Exception ("Receptor ausente");

        if (transmissor==null)
            throw new Exception ("Transmissor ausente");

        this.conexao     = conexao;
        this.receptor    = receptor;
        this.transmissor = transmissor;
    }

    public void receba (Comunicado x) throws Exception
    {
        try
        {
            this.transmissor.writeObject (x);
            this.transmissor.flush       ();
        }
        catch (IOException erro)
        {
            throw new Exception ("Erro de transmissao");
        }
    }

    public Comunicado envie () throws Exception
    {
        try
        {
            return (Comunicado)this.receptor.readObject();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao");
        }
    }

	public static int conferir(int tentativaJogador, int numeroSorteado)
	{
	        if(tentativaJogador < numeroSorteado)
	            return -1;
	        else if(tentativaJogador > numeroSorteado)
	            return 1;

	        return 0;
    }

    public void setTentativa(int tentativa) throws Exception
    {
		if(tentativa < 0)
			throw new Exception("Jogada invalida!!!");
        this.tentativa = tentativa;
    }

    public void setNickname(String nick) throws Exception
    {
        if(nick == null)
            throw new Exception("Nome invalido!");
        this.nickname = nick;
    }

    public int getTentativa()
    {
        return this.tentativa;
    }

    public String getNickname()
    {
        return this.nickname;
    }

    public void byebye () throws Exception
    {
        try
        {
            this.transmissor.close();
            this.receptor.close();
            this.conexao.close();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de desconexao");
        }
    }
}