import java.util.Scanner;


public class MenuInterativo {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		// String para exibir as intruções digitas
		String exibe = "";
		// string para testar se a intrução é a instrução de parada
		String testa = "";
		
		
		
		
		
		System.out.println("digite a intrução");
		
		// loop de repetição que só paraqundo o usuário digitar C000
		while(true)
		{
			
			testa= entrada.next() ;
			
			exibe += testa + "\n";
			
			// exibe as instruções que ja foram digitadas
			System.out.println(exibe.toUpperCase());
			
			
			// testa se a intrução é C000
		if(testa.toUpperCase().equals("C000")){
				System.out.println("condição de parada digitada");
				break;
				
			}
		}
		
		

	}

}

