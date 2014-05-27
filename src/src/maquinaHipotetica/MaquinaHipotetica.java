package maquinaHipotetica;

import java.util.Scanner;

public class MaquinaHipotetica 
{

	// Contador de programa, registrador que retem
	// o endereço da próxima instrução.
	static String[] pc = new String[2];
	
	
	//Registrador de instrução, retem a instrução
	// atual, em execução.
	String[] ri = new String[4];
	
	// Banco de registradores.
	String[][] reg = new String[16][2];
	
	// Banco de memória principal.
	static String[][] mp = new String[256][2];
	 // posição da memoria principal
	static int mp_pos = 0;
	
	/*
	 * Método para conversão de endereço de memória 
	 * em hexadecimal para posição adequada no vetor
	 * de memória.
	 * 
	 * @author		Marco Sousa.
	 * @param end	Endereço de memória em hexadecimal.
	 * @return 		Retorna a posição do vetor referente ao endereço hexadecimal.
	 *  
	 */
	public static int getEndMemoria(String end)
	{
		int pos = Integer.parseInt(end, 16);
		return pos;
	}
	
	
	/*
	 * Método para implementar o Prompt Interativo. 
	 * Gerencia a entrda de dados para a memória da 
	 * máquina hipotética.
	 * 
	 * @author	Jorge Lucas
	 * @author	Gil Everton
	 */
	public static void promptInterativo()
	{
		int pi_estado = 0;
		
		Scanner entrada = new Scanner(System.in);
		// String para exibir as intruções digitas
		String exibe = "";
		// string para testar se a intrução é a instrução de parada
		String testa = "";
		System.out.print(">>> ");
		while(true)
		{
			if(pi_estado == 3)
			{
				break;
			}
		testa= entrada.next() ;
		exibe += "\n>>> " + testa;
		// testa se a instrução é @
		if(testa.toUpperCase().substring(0, 1).equals("@")){
			pi_estado = 3;
			pc[0] = String.valueOf( getEndMemoria(testa.substring(1,2)));
			pc[1] = String.valueOf(getEndMemoria(testa.substring(2,3)));
			System.out.print('@');
			System.out.print(pc[0]);
			System.out.println(pc[1]);
			break;
		}
		// testa se a instrução é #
		else if (testa.toUpperCase().substring(0,1).equals("#"))
		{
			pi_estado = 1;
			
			
			mp_pos = getEndMemoria(testa.substring(1,3));
			pi_estado = 0;
			
		}
		else
		{
			mp[mp_pos][0] = testa.substring(0,1);
			mp[mp_pos][1] = testa.substring(1,2);
			mp_pos++;
			mp[mp_pos][0] = testa.substring(2,3);
			mp[mp_pos][1] = testa.substring(3,4);
			mp_pos++;
		}
		
		// exibe as instruções que ja foram digitadas
		System.out.println(exibe.toUpperCase());
		System.out.print(">>> ");
			
		}
		
	}
	
	/*
	 * Método para exibir o conteúdo presente na memória da máquina 
	 * hipotética.
	 * 
	 * @author	Jorge Lucas
	 * @author	Gil Everton
	 */
	public static void mostraMemoria()
	{	
		int i = 0;
		String end = "";
		for(i = 0; i < 256; i++)
		{
			if(mp[i][0] != null)
			{
				end = Integer.toHexString(i);
				System.out.print(end.toUpperCase());
				System.out.print(" | ");
				System.out.print(mp[i][0]);
				System.out.println(mp[i][1]);
			}
			
		}
	}
	
	/*
	 * Método principal do pragrama.
	 */
	public static void main(String[] args)
	{
		promptInterativo();
		mostraMemoria();
	}
}
