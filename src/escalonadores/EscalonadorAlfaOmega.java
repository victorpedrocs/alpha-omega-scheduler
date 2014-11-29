package escalonadores;

import java.io.IOException;
import java.util.ArrayList;

import processos.Saida;
import simulador.Core;

public class EscalonadorAlfaOmega extends Escalonador{ // escalonador que intercala o modo como escalonara um processo, ora eh o primeiro, ora eh o ultimo
	
	private boolean prioridade; // determina qual processo da fila de espera sera pego, o primeiro ou o ultimo(true-primeiro, false-ultimo)
		
	public EscalonadorAlfaOmega(){ 
		
		prioridade= true; 				
	}
		
	public void escalona(Core core, ArrayList<Saida> lista_saida) throws IOException{
		
		core.executa(lista_saida);
		
		if(core.disponivel()){	
			
			if (fila_espera.size() == 0) // se nao ha processos na fila de espera para serem executados		
				return; // sai
			
			if (prioridade){ // se for pra escalonar o primeiro
				
				core.setProcesso(fila_espera.get(0)); //escalona um processo pra colocar no core
				fila_espera.remove(0); // tiro esse processo da fila de espera
				
			}else{ // se for pra escalonar o ultimo
				
				core.setProcesso( fila_espera.get(fila_espera.size()-1)); //escalona um processo pra colocar no core
				fila_espera.remove(fila_espera.size()-1); // tiro esse processo da fila de espera
			}			
			prioridade = !prioridade; // muda a prioridade (pegar o primeiro ou o ultimo)
		}		
	}
}