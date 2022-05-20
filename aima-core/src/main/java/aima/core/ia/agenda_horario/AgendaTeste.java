package aima.core.ia.agenda_horario;

import java.util.ArrayList;
import java.util.List;

public class AgendaTeste {
	public static void main(String[] args) {
		AgendaTeste teste = new AgendaTeste();
//		teste.teste1();
		teste.teste2();
	}
	
	private void teste1(){
		int dias = 5;
		int horarios = 10;
		int horasDeTrabalho = 2;
		List<Funcionario> funcionarios = new ArrayList<>();
		boolean d1[][] = Funcionario.createHorarios(dias, horarios);
		// Dia 1 - Horarios disponiveis [0, 1, 2]
		d1[0][0] = true;
		d1[0][1] = true;
		d1[0][2] = true;
		// Dia 2 - Horarios disponiveis [1, 2, 3]
		d1[1][1] = true;
		d1[1][2] = true;
		d1[1][3] = true;
		// Dia 3 - Horarios disponiveis [2, 3, 6]
		d1[2][2] = true;
		d1[2][3] = true;
		d1[2][6] = true;
		// Dia 4 - Horarios disponiveis [1, 7]
		d1[3][1] = true;
		d1[3][7] = true;
		// Dia 5 - Horarios disponiveis [1, 3, 5, 6]	
		d1[4][1] = true;
		d1[4][3] = true;
		d1[4][5] = true;
		d1[4][6] = true;
		Funcionario f1 = new Funcionario("A", d1, horasDeTrabalho);
		boolean d2[][] = Funcionario.createHorarios(dias, horarios);
		// Dia 1 - Horarios disponiveis [1, 3, 5]
		d2[0][1] = true;
		d2[0][3] = true;
		d2[0][5] = true;
		// Dia 2 - Horarios disponiveis [1, 3, 5]
		d2[1][1] = true;
		d2[1][3] = true;
		d2[1][5] = true;
		// Dia 3 - Horarios disponiveis [1, 3, 5]
		d2[2][1] = true;
		d2[2][3] = true;
		d2[2][5] = true;
		// Dia 4 - Horarios disponiveis [1, 3, 5]
		d2[3][1] = true;
		d2[3][3] = true;
		d2[3][5] = true;
		// Dia 5 - Horarios disponiveis [1, 3, 5]
		d2[4][1] = true;
		d2[4][3] = true;
		d2[4][5] = true;
		Funcionario f2 = new Funcionario("B", d2, horasDeTrabalho);
		boolean d3[][] = Funcionario.createHorarios(dias, horarios);
		// Dia 1 - Horarios disponiveis [1, 4]
		d3[0][1] = true;
		d3[0][4] = true;
		// Dia 2 - Horarios disponiveis [0, 4]
		d3[1][0] = true;
		d3[1][4] = true;
		// Dia 3 - Horarios disponiveis [0, 4]
		d3[2][0] = true;
		d3[2][4] = true;
		// Dia 4 - Horarios disponiveis [0, 4]
		d3[3][0] = true;
		d3[3][4] = true;
		// Dia 5 - Horarios disponiveis [0, 4]
		d3[4][0] = true;
		d3[4][4] = true;
		Funcionario f3 = new Funcionario("C", d3, horasDeTrabalho);
		funcionarios.add(f1);
		funcionarios.add(f2);
		funcionarios.add(f3);
		Agenda agenda = new Agenda(funcionarios, dias, horarios);
		Organizador org = new Organizador(agenda, dias, horarios, 100, 1500000);
	}
	
	private void teste2() {
		int dias = 1;
		int horarios = 10;
		List<Funcionario> funcionarios = new ArrayList<>();
		boolean[][] h1 = Funcionario.createHorarios(dias, horarios);
		// Horarios disponiveis [12]
		for(int i = 0 ; i < dias ; i++) {
			h1[i][4] = true;
		}
		Funcionario f1 = new Funcionario("Alice", h1, 1);
		boolean[][] h2 = Funcionario.createHorarios(dias, horarios);
		// Horarios disponiveis [8, 9, 13, 14]
		for(int i = 0 ; i < dias ; i++) {
			h2[i][1] = true;
			h2[i][2] = true;
			h2[i][6] = true;
			h2[i][7] = true;
		}
		Funcionario f2 = new Funcionario("Bob", h2, 2);
		boolean[][] h3 = Funcionario.createHorarios(dias, horarios);
		// Horarios disponiveis [7, 9, 12, 13]
		for(int i = 0 ; i < dias ; i++) {
			h3[i][0] = true;
			h3[i][2] = true;
			h3[i][5] = true;
			h3[i][6] = true;
		}
		Funcionario f3 = new Funcionario("Charlie", h3, 1);
		boolean[][] h4 = Funcionario.createHorarios(dias, horarios);
		// Horarios disponiveis [0, 2, 3, 4, 5, 6, 18, 22]
		for(int i = 0 ; i < dias ; i++) {
			
		}
		Funcionario f4 = new Funcionario("David", h4, 0);
		boolean[][] h5 = Funcionario.createHorarios(dias, horarios);
		// Horarios disponiveis [9, 10, 12, 13, 14]
		for(int i = 0 ; i < dias ; i++) {
			h5[i][2] = true;
			h5[i][3] = true;
			h5[i][5] = true;
			h5[i][6] = true;
			h5[i][7] = true;
		}
		Funcionario f5 = new Funcionario("Eve", h5, 4);
		funcionarios.add(f1);
		funcionarios.add(f2);
		funcionarios.add(f3);
//		funcionarios.add(f4);
		funcionarios.add(f5);
		Agenda agenda = new Agenda(funcionarios, dias, horarios);
		Organizador org = new Organizador(agenda, dias, horarios, 2500, 35000000);
		
	}
}