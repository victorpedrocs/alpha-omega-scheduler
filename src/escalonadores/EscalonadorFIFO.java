package escalonadores;

import java.io.IOException;
import java.util.ArrayList;

import processos.Saida;
import simulador.Core;

public class EscalonadorFIFO extends Escalonador { // escalonador que pega o primeiro processo da fila de espera

	public void escalona(Core core, ArrayList<Saida> lista_saida) throws IOException{
		
		core.executa(lista_saida);
		
		if (core.disponivel()) { // se o core esta disponivel

			if (fila_espera.size() == 0) // se nao ha processos na fila de espera para serem executados

				return; // sai

			core.setProcesso(fila_espera.get(0)); //escalona um processo pra colocar no core
			fila_espera.remove(0); // tiro esse processo da fila de espera
		}		
	}
}