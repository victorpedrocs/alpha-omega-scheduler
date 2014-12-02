package escalonadores;

import java.io.IOException;
import java.util.ArrayList;

import processos.Processo;
import processos.Saida;
import simulador.Core;
import simulador.Processador;

public abstract class Escalonador { 
	
	// Atributes
	protected ArrayList<Processo> fila_espera; //processos esperando para ir pro simulador

	// Constructor
	public Escalonador(){ 

		fila_espera = new ArrayList<Processo>();
	}

	
	// Abstract Methods
	
	public abstract void escalona(Core core, ArrayList<Saida> lista_saida) throws IOException; //de acordo com a disponibilidade dos cores novos processos sao executados
	
	// Methods
	public void controla( 
			Processador processador, // Array of Cores
			ArrayList<Saida> lista_saida ) throws IOException 
	{ //controla processos da lista de espera

		for(Core core : processador) //verifica todos os cores do processador
			escalona(core, lista_saida); // os que estiverem disponiveis escalonam, os que nao estao continuam a execucao

		if(!whaitEmpty()) //se a fila de espera nao estiver vazia
			
			for (int i=0; i < fila_espera.size(); i++) 
				fila_espera.get(i).incrementaTempo_espera(); // incrementa o tempo de espera de quem ficou na fila de espera
	}

	
	
	public boolean whaitEmpty(){ // verifica se a fila de espera esta vazia

		return (fila_espera.size() == 0);
	}

	public void whait(Processo processo) { // coloca o processo na fila de espera

		fila_espera.add(processo);
	}

	public ArrayList<Processo> getWhaitQueue(){ // pega a fila de espera

		return fila_espera;
	}
}