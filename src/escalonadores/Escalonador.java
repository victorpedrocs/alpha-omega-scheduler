package escalonadores;

import java.io.IOException;
import java.util.ArrayList;

import processos.Processo;
import processos.Saida;
import simulador.Core;

public abstract class Escalonador { 
	
	protected ArrayList<Processo> fila_espera; //processos esperando para ir pro simulador

	public Escalonador(){ 

		fila_espera = new ArrayList<Processo>();
	}

	public void controla(ArrayList<Core> processador, ArrayList<Saida> lista_saida) throws IOException{ //controla processos da lista de espera

		for(int i=0; i<processador.size(); i++) //verifica todos os cores do processador
			escalona(processador.get(i), lista_saida); // os que estiverem disponiveis escalonam, os que nao estao continuam a execucao

		if(!esperaVazia()) //se a fila de espera nao estiver vazia
			
			for (int i=0; i < fila_espera.size(); i++) 
				fila_espera.get(i).incrementaTempo_espera(); // incrementa o tempo de espera de quem ficou na fila de espera
	}

	public abstract void escalona(Core core, ArrayList<Saida> lista_saida) throws IOException; //de acordo com a disponibilidade dos cores novos processos sao executados
	
	public boolean esperaVazia(){ // verifica se a fila de espera esta vazia

		if(fila_espera.size() == 0)		

			return true;			
		else			

			return false;
	}

	public void esperar(Processo processo) { // coloca o processo na fila de espera

		fila_espera.add(processo);
	}

	public ArrayList<Processo> getFila_espera(){ // pega a fila de espera

		return fila_espera;
	}
}