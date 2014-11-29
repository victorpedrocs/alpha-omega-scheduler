package processos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EscritorArquivo{
	
	BufferedWriter bw;
	private ArrayList<Saida> fila_saida;
	
	public EscritorArquivo(String caminho) throws IOException{
		
		bw= new BufferedWriter(new FileWriter(new File(caminho)));
	}
	
	public void escreve() throws IOException{

		for(int i=0; i<fila_saida.size(); i++){
			
			bw.write(fila_saida.get(i).getId() + "," + fila_saida.get(i).getIdCore() + "," 
			+ fila_saida.get(i).getTempo_turnaround() +	"," + fila_saida.get(i).getTempo_espera() + "," 
					+ fila_saida.get(i).getTempo_resposta());
			bw.newLine();
		}
		
		bw.write(""+calculaTempoMedio()); 
		
		bw.close();
	}
	
	public ArrayList<Saida> getFila_saida(){
		
		return fila_saida;
	}
	
	public void setFila_saida(ArrayList<Saida> fila_saida){
		
		this.fila_saida= fila_saida;	
	}
	
	public double calculaTempoMedio(){
		
		double aux=0;
		
		for(int i=0; i<fila_saida.size(); i++)			
			aux+= fila_saida.get(i).getTempo_resposta();
		
		aux/= fila_saida.size(); //faz a media do tempo de resposta de todos os processos
		
		return aux;
	}
}
