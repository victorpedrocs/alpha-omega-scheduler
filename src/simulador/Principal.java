package simulador;

import java.io.IOException;

import escalonadores.AlfaOmegaMultiFila;
import escalonadores.EscalonadorAlfaOmega;
import escalonadores.EscalonadorFIFO;
import processos.EscritorArquivo;
import processos.LeitorArquivo;

public class Principal {
	
	public static void main(String[] args) throws IOException {
		
		Integer nCores = 2;
		
		LeitorArquivo entrada;
		EscritorArquivo saidaAlfaOmega= new EscritorArquivo("alfaomega.txt");
		EscritorArquivo fcfs= new EscritorArquivo("fcfs.txt");
		EscritorArquivo alfaOmegaMultiFila = new EscritorArquivo("alfaomega-multicore.txt");
		
		//-------------------------------

		System.out.println("\n\nEscalonador Alfa-Omega\n");
		entrada= new LeitorArquivo("processos.txt");
		Simulador simulador1= new Simulador(entrada, saidaAlfaOmega, new EscalonadorAlfaOmega(), nCores);
		simulador1.execucao();	
		
		//-------------------------------

		System.out.println("\n\nEscalonador FCFS\n");
		entrada= new LeitorArquivo("processos.txt");
		Simulador simulador2= new Simulador(entrada, fcfs, new EscalonadorFIFO(), nCores);
		simulador2.execucao();
		
		//-------------------------------
		
		System.out.println("\n\nEscalonador AO-MultiFila\n");
		entrada = new LeitorArquivo("processos.txt");
		Simulador simulador3 = new Simulador(entrada, alfaOmegaMultiFila, new AlfaOmegaMultiFila(nCores), nCores);
		simulador3.execucao();
		
	}

}
