package maquina_hipotetica;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

public class MaquinaHipotetica 
{

	// Contador de programa, registrador que retem
	// o endereço da próxima instrução.
	String[] pc = new String[2];
	
	//Registrador de instrução, retem a instrução
	// atual, em execução.
	String[] ri = new String[4];
	
	// Banco de registradores.
	String[][] reg = new String[16][2];
	
	// Banco de memória principal.
	String[][] mp = new String[256][2];
	
	public static int getEndMemoria(String end)
	{
		int pos = Integer.parseInt(end, 16);
		return pos;
	}
	
	public void promptInterativo()
	{
		int pi_estado = 0;
		while(true)
		{
			
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println(getEndMemoria("56"));
	}
}
