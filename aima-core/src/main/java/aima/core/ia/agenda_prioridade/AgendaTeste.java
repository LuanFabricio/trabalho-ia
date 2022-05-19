package aima.core.ia.agenda_prioridade;

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
		int horarios = 24;
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
		Funcionario f1 = new Funcionario("A", d1, horasDeTrabalho, 1);
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
		Funcionario f2 = new Funcionario("B", d2, horasDeTrabalho, 2);
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
		Funcionario f3 = new Funcionario("C", d3, horasDeTrabalho, 3);
		funcionarios.add(f1);
		funcionarios.add(f2);
		funcionarios.add(f3);
		Agenda agenda = new Agenda(funcionarios, dias, horarios);
		Organizador org = new Organizador(agenda, dias, horarios, 100, 1500000);
	}
	
	private void teste2() {
		int dias = 1;
		int horarios = 24;
		List<Funcionario> funcionarios = new ArrayList<>();
		boolean[][] h1 = Funcionario.createHorarios(dias, horarios);
		// Horarios disponiveis [3, 12, 18, 20, 21]
		for(int i = 0 ; i < dias ; i++) {
			h1[i][3] = true;
			h1[i][12] = true;
			h1[i][18] = true;
			h1[i][20] = true;
			h1[i][21] = true;
		}
		Funcionario f1 = new Funcionario("Alice", h1, 2);
		boolean[][] h2 = Funcionario.createHorarios(dias, horarios);
		// Horarios disponiveis [5, 8, 9, 13, 14, 20]
		for(int i = 0 ; i < dias ; i++) {
			h2[i][5] = true;
			h2[i][8] = true;
			h2[i][9] = true;
			h2[i][13] = true;
			h2[i][14] = true;
			h2[i][20] = true;
		}
		Funcionario f2 = new Funcionario("Bob", h2, 3);
		boolean[][] h3 = Funcionario.createHorarios(dias, horarios);
		// Horarios disponiveis [4, 7, 9, 12, 13, 20, 21, 22]
		for(int i = 0 ; i < dias ; i++) {
			h3[i][4] = true;
			h3[i][7] = true;
			h3[i][9] = true;
			h3[i][12] = true;
			h3[i][13] = true;
			h3[i][20] = true;
			h3[i][21] = true;
			h3[i][22] = true;
		}
		Funcionario f3 = new Funcionario("Charlie", h3, 1);
		boolean[][] h4 = Funcionario.createHorarios(dias, horarios);
		// Horarios disponiveis [0, 2, 3, 4, 5, 6, 18, 22]
		for(int i = 0 ; i < dias ; i++) {
			h4[i][0] = true;
			h4[i][2] = true;
			h4[i][3] = true;
			h4[i][4] = true;
			h4[i][5] = true;
			h4[i][6] = true;
			h4[i][18] = true;
			h4[i][22] = true;
		}
		Funcionario f4 = new Funcionario("David", h4, 2);
		boolean[][] h5 = Funcionario.createHorarios(dias, horarios);
		// Horarios disponiveis [1, 3, 6, 9, 10, 12, 13, 14, 17, 20]
		for(int i = 0 ; i < dias ; i++) {
			h5[i][1] = true;
			h5[i][3] = true;
			h5[i][6] = true;
			h5[i][9] = true;
			h5[i][10] = true;
			h5[i][12] = true;
			h5[i][13] = true;
			h5[i][14] = true;
			h5[i][17] = true;
			h5[i][20] = true;
		}
		Funcionario f5 = new Funcionario("Eve", h5, 4);
		funcionarios.add(f1);
		funcionarios.add(f2);
		funcionarios.add(f3);
		funcionarios.add(f4);
		funcionarios.add(f5);
		Agenda agenda = new Agenda(funcionarios, dias, horarios);
		Organizador org = new Organizador(agenda, dias, horarios, 1000, 25000000);
		
	}
}