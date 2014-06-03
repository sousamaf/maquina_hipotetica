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
	static String[][] reg = new String[16][2];
	
	// Banco de memória principal.
	static String[][] mp = new String[256][2];

	// posição da memoria principal
	static int mp_pos = 0;

	private static boolean parar = false;
	
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
			testa = entrada.next() ;
			exibe += "\n>>> " + testa;
			// testa se a instrução é @
			if(testa.toUpperCase().substring(0, 1).equals("@"))
			{
				pi_estado = 3;
				pc[0] = String.valueOf(testa.substring(1,2));
				pc[1] = String.valueOf(testa.substring(2,3));
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
		System.out.println("Banco de memória principal.");
		for(i = 0; i < 256; i++)
		{
			if(mp[i][0] != null)
			{
				end = Integer.toHexString(i);
				if(i < 16)
					System.out.print("0");
				System.out.print(end.toUpperCase());
				System.out.print(" | ");
				System.out.print(mp[i][0].toUpperCase());
				System.out.println(mp[i][1].toUpperCase());
			}
			
		}
	}
	
	/*
	 * Método para exibir o conteúdo presente no registrador da máquina 
	 * hipotética.
	 * 
	 * @author	Rafael Carlos
	 */
	public static void mostraRegistrador()
	{	
		int i = 0;
		String end = "";
		
		System.out.println("Banco de registradores.");
		
		for(i = 0; i < 16; i++)
		{
			if(reg[i][0] != null)
			{
				end = Integer.toHexString(i);
				System.out.print(end.toUpperCase());
				System.out.print(" | ");
				System.out.print(reg[i][0].toUpperCase());
				System.out.println(reg[i][1].toUpperCase());
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
		
		// Atualiza o contador de programa.
		// @TODO: verifiar se é o local correto para esta atualização.
		iPos += 2;
		sPos = Integer.toHexString(iPos);
		
		if(sPos.length() > 1)
		{
			pc[0] = sPos.substring(0, 1);
			pc[1] = sPos.substring(1, 2);
		}
		else
		{
			pc[0] = "0";
			pc[1] = sPos.substring(0, 1);
		}
	}
	
	/*
	 * Método de decodificação e execução das instruções.
	 * 
	 * @author Ricardo Santiago.
	 */
	public static void decodificaInstrucao()
	{
		String endereco;
		
		switch(ri[0])
		{
			case "1":
				endereco = ri[2] + ri[3];
				instrucao_1(ri[1], endereco);
			break;
			
			case "2":
				endereco = ri[2] + ri[3];
				instrucao_2(ri[1], endereco);
			break;
			
			case "3":
				endereco = ri[2] + ri[3];
				instrucao_3(ri[1], endereco);				
			break;
			
			case "4": instrucao_4(ri[1], ri[2]);
			break;
			
			case "5":
				instrucao_5(ri[1], ri[2], ri[3]);
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
			
			case "B":
				endereco = ri[2] + ri[3];
				instrucao_B(ri[1], endereco);
			break;
			
			case "C": 
				instrucao_C(ri[1] + ri[2] + ri[3]);
			break;	
			
			case "D": 
				instrucao_D(ri[3]);
			break;
			
			case "E": 
				instrucao_E(ri[3]);
			break;
			
		}
	
	}
	
	/*
	 * Método da instrução LOAD
	 * busca em um endereço da memoria e atribui o conteudo deste endereço
	 * em um registrador
	 * 
	 * @autor Jorge Lucas
	 */
	public static void instrucao_1(String registrador, String endereco)
	{
		reg[getEndMemoria(registrador)][0] = mp[getEndMemoria(endereco)][0];
		reg[getEndMemoria(registrador)][1] = mp[getEndMemoria(endereco)][1];
		
		
	}

	/* 
	 * Metodo da instrução 2 "LOAD"
	 * Atribui o valor XY ao registrador 
	 * @author Alexandra Carvalho
	 */
	public static void instrucao_2(String registrador, String valor)
	{
		reg[getEndMemoria(registrador)][0] = valor.substring(0, 1);
		reg[getEndMemoria(registrador)][1] = valor.substring(1, 2);
	}

	/*
	 * Implementação da instrução 3, STORE.
	 * Armazena o conteúdo de um registrador informado em um endereço
	 * de memória principal.
	 * 
	 * @author Alexandra Carvalho
	 * @author Ricardo Santiago
	 */
	public static void instrucao_3(String registrador, String endereco)
	{
//		System.out.println(getEndMemoria(registrador));
		mp[getEndMemoria(endereco)][0] = reg[getEndMemoria(registrador)][0];
		mp[getEndMemoria(endereco)][1] = reg[getEndMemoria(registrador)][1];		
	}

	/*
	 * Método da instrução "MOVE" (copia)
	 * Copia o padrão de bits do registradorOrigem
	 * para o registradorDestino
	 * @author	Matheus Fernandes e Sostenes Oliveira
	 */
	public static void instrucao_4(String registradorOrigem, String registradorDestino)
	{
		reg[getEndMemoria(registradorDestino)][0] = reg[getEndMemoria(registradorOrigem)][0];
		reg[getEndMemoria(registradorDestino)][1] = reg[getEndMemoria(registradorOrigem)][1];
	}

	/* Metodo que realiza a soma do valor de dois registradores e salva a soma em um terceiro registrador.
	 * @author Bruno Martinovski
	 * 
	 */
	public static void instrucao_5(String registrador1, String registrador2, String registrador3)
	{ 
		int valor1 = Integer.parseInt(reg[getEndMemoria(registrador2)][0] + reg[getEndMemoria(registrador2)][1], 16);
		int valor2 = Integer.parseInt(reg[getEndMemoria(registrador3)][0] + reg[getEndMemoria(registrador3)][1], 16);
		int total = valor1 + valor2;

		String sTotal = Integer.toHexString(total);

		if(sTotal.length() > 1)
		{
			reg[getEndMemoria(registrador1)][0] = sTotal.substring(0, 1);
			reg[getEndMemoria(registrador1)][1] = sTotal.substring(1, 2);
		}
		else
		{
			reg[getEndMemoria(registrador1)][0] = "0";
			reg[getEndMemoria(registrador1)][1] = sTotal.substring(0, 1);
		}
	}

	/* Método da intrução "ADD" (soma).
	 * Recebe como parametro os dois registradores que serão somados,
	 * logo em seguida armazena o resultado da operação em um terceiro registrador, chamado
	 * registradorFinal.
	 * @author Matheus Fernandes 
	 * @author Sostenes Oliveira
	 */
	public void instrucao_6(String registrador1, String registrador2, String registradorFinal)
	{
		double reg1 = Integer.valueOf(reg[getEndMemoria(registrador1)][0]);
		reg1 += Integer.valueOf(reg[getEndMemoria(registrador1)][1]);	
	
		double reg2 = Integer.valueOf(reg[getEndMemoria(registrador2)][0]);
		reg2 += Integer.valueOf(reg[getEndMemoria(registrador2)][1]);
		
		int regFinal1 = (int) (reg1 + reg2);
		
		String regFinal2 = Integer.toHexString(regFinal1);
		
		reg[getEndMemoria(registradorFinal)][0] = String.valueOf(getEndMemoria(regFinal2.substring(0,1))); 
		reg[getEndMemoria(registradorFinal)][1] = String.valueOf(getEndMemoria(regFinal2.substring(1,2))); 	
	}

	/*
	 * 
	 */
	public void instrucao_7()
	{
		
	}

	/*
	 * 
	 */
	public void instrucao_8()
	{
		
	}

	/*
	 * 
	 */
	public void instrucao_9()
	{
		
	}

	/*
	 * 
	 */
	public void instrucao_A()
	{
		
	}

	/* Método da instrução JUMP
	 * É feita a comparação entre o registrador passado como parâmetro, e
	 * o registrador 0. Se a condição for satisfeita, o contador de programa
	 * recebe o novo endereço da próxima instrução.
	 * @author: Matheus Fernandes.
	 */
	public static void instrucao_B(String registrador, String endereco)
	{		
		if(reg[0][0].equals(reg[getEndMemoria(registrador)][0]) && reg[0][1].equals(reg[getEndMemoria(registrador)][1]))
		{
			pc[0] = String.valueOf(endereco.substring(0,1));
			pc[1] = String.valueOf(endereco.substring(1,2));
		}
	}
	
	/*
	 * 
	 */
	public static void instrucao_C(String complemento)
	{ 
		for (char value : complemento.toCharArray())
		{
			if(value != '0')
			{
				parar = false;
				break;
			} else 
			{
				parar = true;
			}
		}
	}

	/*
	 * Método que implementa a instrução de entrada de dados via teclado.
	 * Seu formato: D00X, onde:
	 * D: seu OP.
	 * 0: sem importância, símbolo usado para garantir o tamnho da instrução.
	 * X: registrador que receberá o valor informado pelo usuário.
	 * 
	 * @author	Marco Sousa.
	 */
	public static void instrucao_D(String registrador)
	{
		Scanner entrada = new Scanner(System.in);
		String input = new String();
		System.out.print("<<< ");
		input = entrada.next();
		
		reg[getEndMemoria(registrador)][0] = input.substring(0, 1);
		reg[getEndMemoria(registrador)][1] = input.substring(1, 2);
		
	}

	/*
	 * Método que implementa a instrução de saída de dados via tela.
	 * Seu formato: E00X, onde:
	 * E: seu OP.
	 * 0: sem importância, símbolo usado para garantir o tamanho da instrução.
	 * X: registrador que terá seu valor exibido em tela.
	 * 
	 * @author	Marco Sousa.
	 */
	public static void instrucao_E(String registrador)
	{
		System.out.print("R" + registrador + ": ");
		System.out.print(reg[getEndMemoria(registrador)][0]);
		System.out.println(reg[getEndMemoria(registrador)][1]);
	}

	
	/*
	 * Método principal do pragrama.
	 */
	public static void main(String[] args)
	{
		promptInterativo();
		mostraMemoria();
		
		while(true)
		{
			buscaInstrucao();
			System.out.println("Executando: " + ri[0] + ri[1] + ri[2] + ri[3]);
			decodificaInstrucao();
			if(parar  == true)
				break;
		}
		mostraRegistrador();
		mostraMemoria();
	}
}
