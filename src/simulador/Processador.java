package simulador;

import java.util.ArrayList;

public class Processador extends ArrayList<Core>{
	
	public Processador(int num_nucleos){ //so testei pra 2 nucleos
		
		criaNucleos(num_nucleos);		
	}

	private void criaNucleos(int num_nucleos) {

		for(int i=0; i<num_nucleos; i++)
			
			add(new Core(i));		
	}

	public boolean livre(){ // se todos os cores estao livres

		if(get(0).disponivel() && get(1).disponivel()) // so coloquei pros 2 nucleos que testei

			return true;
		else

			return false;
	}	
}
