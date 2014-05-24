package maquinaHipotetica;

import java.util.Scanner;



public class MaquinaHipotetica 
{

	// Contador de programa, registrador que retem
	// o endereÃ§o da prÃ³xima instruÃ§Ã£o.
	String[] pc = new String[2];
	
	//Registrador de instruÃ§Ã£o, retem a instruÃ§Ã£o
	// atual, em execuÃ§Ã£o.
	String[] ri = new String[4];
	
	// Banco de registradores.
	String[][] reg = new String[16][2];
	
	// Banco de memÃ³ria principal.
	String[][] mp = new String[256][2];
	
	public static int getEndMemoria(String end)
	{
		int pos = Integer.parseInt(end, 16);
		return pos;
	}
	
	public void promptInterativo()
	{
		int pi_estado = 0;
		
		Scanner entrada = new Scanner(System.in);
		// String para exibir as intruções digitas
		String exibe = "";
		// string para testar se a intrução é a instrução de parada
		String testa = "";
		
		
		System.out.println("digite a instrução");
		while(true)
		{
			testa= entrada.next() ;
			
			exibe += testa + "\n";
			
			// exibe as instruções que ja foram digitadas
			System.out.println(exibe.toUpperCase());
			
			
			// testa se a instrução é @
		if(testa.toUpperCase().substring(0, 1).equals("@")){
			pi_estado = 3;
				
			}
		// testa se a instrução é #
		else if (testa.toUpperCase().substring(0,1).equals("#"))
		{
			pi_estado = 1;
			
		}
		else if (testa.toUpperCase().substring(0,1).equals(" "))
				{
			
				}
			
			
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println(getEndMemoria("56"));
	}
}
