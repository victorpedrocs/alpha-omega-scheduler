package simulador;

import java.io.IOException;

import escalonadores.EscalonadorAlfaOmega;
import escalonadores.EscalonadorFCFS;
import processos.EscritorArquivo;
import processos.LeitorArquivo;

public class Principal {
	
	public static void main(String[] args) throws IOException {
		
		LeitorArquivo entrada;
		EscritorArquivo saidaAlfaOmega= new EscritorArquivo("alfaomega.txt");
		EscritorArquivo fcfs= new EscritorArquivo("fcfs.txt");
		
		//-------------------------------

		System.out.println("\n\nEscalonador Alfa-Omega\n");
		entrada= new LeitorArquivo("processos.txt");
		Simulador simulador1= new Simulador(entrada, saidaAlfaOmega, new EscalonadorAlfaOmega(), 2);
		simulador1.execucao();	
		
		//-------------------------------

		System.out.println("\n\nEscalonador FCFS\n");
		entrada= new LeitorArquivo("processos.txt");
		Simulador simulador2= new Simulador(entrada, fcfs, new EscalonadorFCFS(), 2);
		simulador2.execucao();	
		
	}

}
