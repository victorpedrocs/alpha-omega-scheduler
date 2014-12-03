package simulador;

import java.io.IOException;

import escalonadores.AlfaOmegaMultiFila;
import escalonadores.EscalonadorAlfaOmega;
import escalonadores.EscalonadorFIFO;
import processos.EscritorArquivo;
import processos.LeitorArquivo;

public class Principal {
	
	public static void main(String[] args) throws IOException {
		
		Integer nCores = 4;
		String arquivoProcessos = "processos5.txt";
		LeitorArquivo entrada;
		EscritorArquivo saidaAlfaOmega= new EscritorArquivo("alfaomega-"+nCores+"cores-"+arquivoProcessos);
		EscritorArquivo fcfs= new EscritorArquivo("fcfs-"+nCores+"cores-"+arquivoProcessos);
		EscritorArquivo alfaOmegaMultiFila = new EscritorArquivo("alfaomega-multifila-"+nCores+"cores-"+arquivoProcessos);
		
		//-------------------------------

		System.out.println("\n\nEscalonador Alfa-Omega\n");
		entrada= new LeitorArquivo(arquivoProcessos);
		Simulador simulador1= new Simulador(entrada, saidaAlfaOmega, new EscalonadorAlfaOmega(), nCores);
		simulador1.execucao();	
		
		//-------------------------------

		System.out.println("\n\nEscalonador FCFS\n");
		entrada= new LeitorArquivo(arquivoProcessos);
		Simulador simulador2= new Simulador(entrada, fcfs, new EscalonadorFIFO(), nCores);
		simulador2.execucao();
		
		//-------------------------------
		
		System.out.println("\n\nEscalonador AO-MultiFila\n");
		entrada = new LeitorArquivo(arquivoProcessos);
		Simulador simulador3 = new Simulador(entrada, alfaOmegaMultiFila, new AlfaOmegaMultiFila(nCores), nCores);
		simulador3.execucao();
		
	}

}
