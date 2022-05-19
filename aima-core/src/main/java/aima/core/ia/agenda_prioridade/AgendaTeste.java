package aima.core.ia.agenda_prioridade;

import java.util.ArrayList;
import java.util.List;

public class AgendaTeste {
	AgendaTeste(){
		List<Funcionario> funcionarios = new ArrayList<>();
		// [0, 1, 2]
		// [1, 2, 3]
		// [2, 3, 6]
		// [1, 7]
		// [1, 3, 5, 6]
		boolean d1[][] = {
				  // 0      1    	 2	      3		  4		  5       6      7
				  {true, 	true, 	true, 	false, 	false, 	false, false, false},
				  {false, 	true, 	true, 	true, 	false, 	false, false, false},
				  {false, 	false, 	true, 	true, 	false, 	false, true, false},
				  {false, 	true, 	false, 	false, 	false, 	false, false, true},
				  {false, 	true, 	false, 	true, 	false, 	true , true, false},
		};
		Funcionario f1 = new Funcionario("A", d1);
		// [1, 3, 5]
		// [1, 3, 5]
		// [1, 3, 5]
		// [1, 3, 5]
		// [1, 3, 5]
		boolean d2[][] = {
				  // 0      1     2      3     4      5     6      7
				  {false, true, false, true, false, true, false, false},
				  {false, true, false, true, false, true, false, false},
				  {false, true, false, true, false, true, false, false},
				  {false, true, false, true, false, true, false, false},
				  {false, true, false, true, false, true, false, false},
		};
		Funcionario f2 = new Funcionario("B", d2);
		// [1, 4]
		// [0, 4]
		// [0, 4]
		// [0, 4]
		// [0, 4]
		boolean d3[][] = {
				  // 0      1     2      3       4      5     6      7
				  {false,  true, false, false, true, false, false, false},
				  {true, false, false, false, true, false, false, false},
				  {true, false, false, false, true, false, false, false},
				  {true, false, false, false, true, false, false, false},
				  {true, false, false, false, true, false, false, false},
		};
		Funcionario f3 = new Funcionario("C", d3);
		funcionarios.add(f1);
		funcionarios.add(f2);
		funcionarios.add(f3);
		Agenda agenda = new Agenda(funcionarios);
//		agenda.printAll();
		Organizador org = new Organizador(agenda, 2, 100, 1500000);
		
//		Agenda agenda2 = org.createPar();
//		agenda2.printAll();
	}
	public static void main(String[] args) {
		new AgendaTeste();
	}
}