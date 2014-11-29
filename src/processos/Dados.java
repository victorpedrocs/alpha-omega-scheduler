package processos;

public abstract class Dados {
	
	protected int id, //tempo em que permanece na fila de espera para ser executado
	tempo_espera;
	
	public int getId(){
		
		return id;
	}
	
	public int getTempo_espera(){
		
		return tempo_espera;
	}
			
}
