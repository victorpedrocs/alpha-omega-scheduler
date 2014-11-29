package simulador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import escalonadores.Escalonador;
import processos.EscritorArquivo;
import processos.LeitorArquivo;
import processos.Processo;
import processos.Saida;

public class Simulador {

	Processador processador; // processador
	Escalonador escalonador; // escalonador alfa-omega	
	LeitorArquivo leitor; // le o arquivo com os processos
	EscritorArquivo escritor; // cria um arquivo com as caracteristicas de execucao dos processos	
	private ArrayList<Processo> lista_entrada;	// processos que vieram do arquivo
	private ArrayList<Saida> lista_saida;	// dados com as caracteristicas de execucao dos processos
	private int ciclo_clock; // tempo de ciclo de clock

	public Simulador(LeitorArquivo leitor, EscritorArquivo escritor, Escalonador escalonador, int cores) throws FileNotFoundException{ 
		
		processador= new Processador(cores);
		
		this.leitor= leitor;		
		this.escritor= escritor;
		this.escalonador= escalonador;

		lista_entrada= leitor.getLista_entrada();
		lista_saida= new ArrayList<Saida>();

		ciclo_clock=0; // inicializa clock
	}

	public void execucao() throws IOException{ // de acordo com o ciclo de clock novos processos serao executados nos cores

		while(!(processador.livre() && entradaVazia() && escalonador.esperaVazia())){			

			seleciona();
			escalonador.controla(processador, lista_saida); //escalona pro processador
			ciclo_clock++; // aumentarao os ciclos de clock ja passados
		}			
		
		escritor.setFila_saida(saidaProcessador()); //manda as saidas do processamento para a escrita
		escritor.escreve(); //manda escrever a saida dos dados dos processos
		imprimeSaida(); //imprime a saida dos dados dos processos
	} 

	public void seleciona(){ // pega da lista de entrada e manda pra fila de espera

		for(int i=0; i<lista_entrada.size(); i++) { //verifica todos os processos da entrada
			
			if(lista_entrada.get(i).getTempo_chegada() == ciclo_clock){ //se o tempo de chegada deles for o tempo do ciclo de clock

				escalonador.esperar(lista_entrada.get(i)); //coloco na fila de espera
				lista_entrada.remove(i); // remove processo da lista de entrada
				i--;
			}			
		}
	}

	public ArrayList<Saida> saidaProcessador(){ // retorna os dados dos processos

		return lista_saida;
	}

	public ArrayList<Processo> getLista_entrada() { // pega a lista de processos que vieram do arquivo

		return lista_entrada;
	}

	public boolean entradaVazia(){ // verifica se nao ha mais processos do arquivo para irem pra espera

		if(lista_entrada.size() == 0)		

			return true;			
		else			

			return false;
	}	

	public void imprimeSaida(){ // imprime os dados de saida dos processos
		
		for(int i=0; i<lista_saida.size(); i++)
			
			System.out.println(lista_saida.get(i));
	}
}
