package processos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorArquivo{

	BufferedReader br;
	private ArrayList<Processo> lista_entrada;

	public LeitorArquivo(String caminho) throws IOException{

		br= new BufferedReader(new FileReader(new File(caminho)));
		lista_entrada= new ArrayList<Processo>();
		le();
	}

	public void le() throws IOException{

		String linha;

		try{
			while((linha=br.readLine()) != null){ // pra cada linha do arquivo

				String vetorlinha[]= linha.split(","); // separo de acordo com as virgulas

				int id= Integer.parseInt(vetorlinha[0]);
				int tempo_chegada= Integer.parseInt(vetorlinha[1]);
				int duracao= Integer.parseInt(vetorlinha[2]);

				lista_entrada.add(new Processo(id, tempo_chegada, duracao));
			}
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		br.close();
	}

	public ArrayList<Processo> getLista_entrada() {
		
		return lista_entrada;
	}
}
