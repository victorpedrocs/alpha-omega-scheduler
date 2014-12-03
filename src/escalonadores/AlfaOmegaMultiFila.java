package escalonadores;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import processos.Processo;
import processos.Saida;
import simulador.Core;
import simulador.Processador;

public class AlfaOmegaMultiFila extends Escalonador{
	
	// atributes
//	private List<ArrayList<Processo>> filasDeEspera;
	private Map<Core, ProcessQueue> coreAndQueue;
	private List<ProcessQueue> queues;
	private Integer nCores; 
	
	public AlfaOmegaMultiFila(Integer nCores){
		this.fila_espera = null;
//		this.filasDeEspera = new ArrayList<ArrayList<Processo>>();
		this.coreAndQueue = new HashMap<Core, ProcessQueue>();
		this.nCores = nCores;
		
		this.queues = new ArrayList<AlfaOmegaMultiFila.ProcessQueue>(nCores);
		for (int i = 0; i < nCores; i++) {
			queues.add(new ProcessQueue());
		}
		
	}

	@Override
	public void escalona(Core core, ArrayList<Saida> lista_saida) throws IOException {
		
		core.executa(lista_saida);
		
		ProcessQueue processQueue = this.queues.get(core.getId());
		
		if (core.disponivel()) {
			core.setProcesso(processQueue.allocateProcess());
		}
		
	}
	
	@Override
	public void controla( 
			Processador processador, // Array of Cores
			ArrayList<Saida> lista_saida ) throws IOException 
	{ //controla processos da lista de espera
		
		
		
		for(Core core : processador) { //verifica todos os cores do processador
			
			escalona(core, lista_saida); // os que estiverem disponiveis escalonam, os que nao estao continuam a execucao
		}
		
		if(!isEmpty()) //se a fila de espera nao estiver vazia
			for (ProcessQueue pq : this.queues) {
				pq.increaseWhaitTime();
			} // incrementa o tempo de espera de quem ficou na fila de espera
	}
	
	@Override
	public boolean isEmpty(){ // verifica se a fila de espera esta vazia
		boolean isEmpty = true;
		
		for(ProcessQueue pq : this.queues) {
			isEmpty = isEmpty && pq.isEmpty();
		}
		
		return isEmpty;
	}

	@Override
	public void queue(Processo processo) { // coloca o processo na fila de espera
		
		/*if (this.coreAndQueue.size() != 0) {
			ArrayList<ProcessQueue> pqs = new ArrayList<AlfaOmegaMultiFila.ProcessQueue>();
			pqs.addAll(this.coreAndQueue.values());
			Collections.sort(pqs);
			pqs.get(0).queueProcess(processo);
		}*/
		
		if (!this.queues.isEmpty()) {
			int idMenorFila = 0, lessWaitTime = 0;
			boolean flag = true;
			for (int i = 0; i < nCores; i++) {
				if((this.queues.get(i).getnProcess() <= lessWaitTime) || flag){
					lessWaitTime = this.queues.get(i).getWaitTime();
					idMenorFila = i;
					flag = false;
				}
			}

			this.queues.get(idMenorFila).queueProcess(processo);
		}
		
	}


	private class ProcessQueue implements Comparable<ProcessQueue>{
		private Integer nProcess;
		private Integer totalWaitTime;
		private List<Processo> processos;
		private Boolean priority;
		
		public ProcessQueue () {
			this.processos = new ArrayList<Processo>();
			this.nProcess = 0;
			this.totalWaitTime = 0;
			this.priority = true;
			
			this.updateStatus();
		}
		
		public Integer getnProcess() {
			return this.nProcess;
		}
		
		public Integer getWaitTime() {
			return this.totalWaitTime;
		}
		
		private void updateStatus(){
			this.nProcess = processos.size();
			this.totalWaitTime = 0;
			for (Processo processo : this.processos) {
				totalWaitTime += processo.getTempo_espera();
			}
		}
		
		public void queueProcess(Processo process) {
			this.processos.add(process);
			updateStatus();
		}
		
		public Processo allocateProcess () {
			if (!this.isEmpty()) {
				Processo aux;
				if (priority) {
					aux = processos.remove(0);
				} else {
					aux = processos.remove(processos.size() - 1);
				}
				
				switchPriority();
				updateStatus();
				return aux;
				
			}
			else { 
				return null;
			}
		}
		
		private void switchPriority() {
			this.priority = !this.priority;
		}
		
		public boolean isEmpty () {
			return this.processos.isEmpty();
		}
		
		public void increaseWhaitTime() {
			for (Processo processo : this.processos) {
				processo.incrementaTempo_espera();
			}
		}

		@Override
		public int compareTo(ProcessQueue o) {
			return this.nProcess.compareTo(o.getnProcess());
		}
	}
	

}
