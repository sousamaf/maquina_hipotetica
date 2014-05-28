package maquinaHipotetica;

import java.util.Scanner;

public class MaquinaHipotetica 
{

	// Contador de programa, registrador que retem
	// o endereço da próxima instrução.
	static String[] pc = new String[2];
	
	//Registrador de instrução, retem a instrução
	// atual, em execução.
	static String[] ri = new String[4];
	
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
			pc[0] = String.valueOf(getEndMemoria(testa.substring(1,2)));
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
	 * Realiza a busca da próxima instrução na memória pricipal 
	 * e a registra na variável 'ri', registrador de instrução.
	 * 
	 * @author	Marco Sousa.
	 */
	public static void buscaInstrucao()
	{
		String sPos = "";
		int iPos = 0;
		
		sPos = pc[0] + pc[1];
		iPos = getEndMemoria(sPos);
		
		ri[0] = mp[iPos][0];
		ri[1] = mp[iPos][1];
		ri[2] = mp[iPos+1][0];
		ri[3] = mp[iPos+1][1];		
	}
	
	/*
	 * 
	 */
	public void decodificaInstrucao()
	{
		String endereco;
		switch(ri[0])
		{
		
		
			case "1":endereco = ri[2] + ri[3];
					intrucao_1(ri[1], endereco);
			break;
			
			case "2": endereco = ri[2] + ri[3];
					  intrucao_2(ri[2], endereco);
			break;
			
			case "3": // Metodo que não foi criado
			break;
			
			case "4": // Metodo que não foi criado
			break;
			
			case "5": // Metodo que não foi criado
			break;
			
			case "6": // Metodo que não foi criado
			break;
			
			case "7": // Metodo que não foi criado
			break;
			
			case "8": // Metodo que não foi criado
			break;	
			
			case "9": // Metodo que não foi criado
			break;
					
			case "A": // Metodo que não foi criado
			break;
			
			case "B": // Metodo que não foi criado				
			break;
			
			case "C": // Metodo que não foi criado
			break;	
			
			case "D": // Metodo que não foi criado
			break;
			
			case "E": // Metodo que não foi criado
			break;
			
		}
	
	}
	
	/*
	 * instrução LOAD
	 * busca em um endereço da memoria e atribui o conteudo deste endereço
	 * em um registrador
	 * 
	 * @autor Jorge Lucas
	 */
	public void intrucao_1(String registrador, String endereco)
	{
		reg[getEndMemoria(registrador)][0] = mp[getEndMemoria(endereco)][0];
		reg[getEndMemoria(registrador)][1] = mp[getEndMemoria(endereco)][1];
		
		
	}

	/* Metodo da instrução 2 "LOAD"
	 * Atribui o valor XY no registrador 
	 * @author: Alexandra Carvalho
	 */
	public void intrucao_2(String registrador, String valor)
	{
		reg[getEndMemoria(registrador)][0] = mp[getEndMemoria(valor)][0];
		reg[getEndMemoria(registrador)][1] = mp[getEndMemoria(valor)][1];
	}

	/*
	 * 
	 */
	public void intrucao_3()
	{
		
	}

	/*
	 * 
	 */
	public void intrucao_4()
	{
		
	}

	/*
	 * 
	 */
	public void intrucao_5()
	{
		
	}

	/*
	 * 
	 */
	public void intrucao_6()
	{
		
	}

	/*
	 * 
	 */
	public void intrucao_7()
	{
		
	}

	/*
	 * 
	 */
	public void intrucao_8()
	{
		
	}

	/*
	 * 
	 */
	public void intrucao_9()
	{
		
	}

	/*
	 * 
	 */
	public void intrucao_A()
	{
		
	}

	/*
	 * 
	 */
	public void intrucao_B()
	{
		
	}

	/*
	 * 
	 */
	public void intrucao_C()
	{
		
	}

	/*
	 * 
	 */
	public void intrucao_D()
	{
		
	}

	/*
	 * 
	 */
	public void intrucao_E()
	{
		
	}

	
	/*
	 * Método principal do pragrama.
	 */
	public static void main(String[] args)
	{
		promptInterativo();
		mostraMemoria();
		buscaInstrucao();
	}
}
