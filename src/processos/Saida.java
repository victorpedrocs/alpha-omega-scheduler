package processos;

public class Saida extends Dados implements Comparable<Saida>{

	private int idCore,
	tempo_turnaround,
	tempo_resposta;

	public Saida(int id, int idCore, int tempo_turnaround, int tempo_espera, int tempo_resposta){

		this.id= id;
		this.idCore= idCore;
		this.tempo_turnaround= tempo_turnaround;
		this.tempo_espera= tempo_espera;
		this.tempo_resposta= tempo_resposta;
	}

	public int getIdCore() {

		return idCore;
	}

	public int getTempo_turnaround() {

		return tempo_turnaround;
	}

	public int getTempo_resposta() {

		return tempo_resposta;
	}
	
	public int compareTo(Saida outraSaida){

		if(this.tempo_turnaround > outraSaida.getTempo_turnaround()) //se maior

			return 1;

		else if(this.tempo_turnaround < outraSaida.getTempo_turnaround()) //se menor

			return -1;

		else // se igual

			return 0;
	}

	public String toString(){

		return "------------------------------ "
				+ "\nProcesso: " + id + "\nExecutado no core " + idCore + "\nTempo de turnaround: " + tempo_turnaround + 
				"\nTempo de espera: " + tempo_espera + "\nTempo de resposta: " + tempo_resposta;
	}
}
