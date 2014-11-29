package processos;

public class Processo extends Dados implements Comparable<Processo>{

	private int tempo_chegada, // tempo que ele chega pra ser executado
	duracao, // tempo para executar
	tempo_cpu; // tempo que dura executando dentro do core, deve ser igual ao tempo de duracao
	// tempo de resposta eh o mesmo do turnarround
			
	public Processo(int id, int tempo_chegada, int duracao){
	
		this.id= id;
		this.tempo_chegada= tempo_chegada;
		this.duracao= duracao;		
	}
	
	public int getId(){
		
		return id;
	}

	public int getDuracao() {
		
		return duracao;
	}

	public void decrementaDuracao(){
		
		duracao--;
	}
	
	public void incrementaTempo_espera(){
		
		tempo_espera++;
	}
	
	public int getTurnaround(){ // tempo de duracao + espera
		
		return tempo_espera + tempo_cpu;
	}
	
	public int getTempo_resposta(){
		
		return getTurnaround();
	}
	
	public int getTempo_cpu(){
		
		return tempo_cpu;
	}
	
	public void incrementaTempo_cpu() {
		
		tempo_cpu++;
	}

	public int getTempo_chegada() {
		
		return tempo_chegada;
	}
	
	public int compareTo(Processo outro){

		if(id > outro.getId()) //se maior

			return 1;

		else if(id < outro.getId()) //se menor

			return -1;

		else // se igual

			return 0;
	}

	public String toString(){

		return "------------------------------ "
				+ "\nProcesso: " + id + "\nTempo de duracao: " + duracao;
	}
}
