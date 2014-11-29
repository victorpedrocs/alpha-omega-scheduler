package simulador;

import java.io.IOException;
import java.util.ArrayList;

import processos.Processo;
import processos.Saida;

public class Core {

	private boolean disponibilidade;
	private Processo processo;
	private Saida dados;
	private int id;

	public Core(int id){

		this.id= id;
		disponibilidade= true; // core esta disponivel
	}

	public void executa(ArrayList<Saida> lista_saida) throws IOException{ //continua a execucao do processo (passo o clock pois caso o processo finalize, ele guadara esta tempo pra futuramente ordenar os processos por ordem de termino
	
		if (processo != null){
			
			processo.decrementaDuracao(); // passa um ciclo do tempo de execucao do processo
			processo.incrementaTempo_cpu(); 
	
			if(processo.getDuracao()==0){ //se acabou de executar
						
				dados= new Saida(processo.getId(), id, processo.getTurnaround(), processo.getTempo_espera(), 
						processo.getTempo_resposta());
				lista_saida.add(dados);
				processo = null;
				disponibilidade= true;
			}
		}
	}
	
	public Saida saidaProcesso(){
		
		return dados;
	}
	
	public Processo getProcesso(){

		return processo;		
	}

	public void setProcesso(Processo processo){

		disponibilidade= false; // quando coloca um processo o core torna-se indisponivel
		this.processo= processo;		
	}

	public boolean disponivel(){

		return disponibilidade;
	}

	public int getId(){
		
		return id;
	}
}
