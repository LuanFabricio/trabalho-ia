package aima.core.ia.agenda_prioridade;

import java.util.List;
import java.util.Random;

public class Agenda {
	private List<Funcionario> funcionarios;
	private String[][] horarios;
	public int DIAS = 5;
	public int HORARIOS = 8;
	
	Agenda(List<Funcionario> funcionarios){
		this.funcionarios = funcionarios;
		horarios = new String[DIAS][HORARIOS];
		this.horarios = this.createParents();
	} 
	
	public void printAll() {
		printFuncionarios();
		printHorarios();
	}
	
	private void printFuncionarios() {
		for (Funcionario funcionario : this.funcionarios) {
			System.out.printf("Funcionario: %s\n\tDias: [ ", funcionario.getNome());
			boolean[][] horariosDisponiveis = funcionario.getHorariosDisponiveis(); 
			for (int i = 0 ; i < horariosDisponiveis.length ; i++) {
				for (int j = 0 ; j < horariosDisponiveis[i].length ; j++) {
					if (horariosDisponiveis[i][j]) {
						System.out.printf("dia: %d [%d]", i+1, j);
					}
				}
			}
			System.out.print("]\n");
		}
	}
	
	private void printHorarios() {
		for (int i = 0 ; i < this.horarios.length ; i++) {
			System.out.printf("Dia %d\n", i+1);
			String[] dia = this.horarios[i];
			for (String nomeFuncionario : dia) {
				System.out.println("\t"+nomeFuncionario);
			}
		}
	}
	
	private String[][] createParents(){
		return this.createParents(0, this.funcionarios.size());
	}
	
	private String[][] createParents(int inicio, int fim){
		String[][] horarios = new String[DIAS][HORARIOS];
		Random rand = new Random();
		for (int i = inicio ; i < fim ; i++) {
			Funcionario funcionario = funcionarios.get(i);
			int dia = rand.nextInt(DIAS);
			int horario = rand.nextInt(HORARIOS);
			while (horarios[dia][horario] != null) {
				dia = rand.nextInt(DIAS);
				horario = rand.nextInt(HORARIOS);
			}
			horarios = this.addFuncionario(horarios, dia, horario, funcionario.getNome());
		}
		return horarios;
	}
	
	private String[][] addFuncionario(String[][] horarios, int dia, int horario, String nomeFuncionario){
		horarios[dia][horario] = nomeFuncionario;
		return horarios;
	}
	
	public int getFuncionario(String name) {
		for (int i = 0 ; i < this.funcionarios.size() ; i++) {
			Funcionario funcionario = this.funcionarios.get(i);
			if (funcionario.getNome() == name) {
				return i;
			}
		}
		return -1;
	}
	
	private int calcScore(String[][] horarios) {
		int score = 1000;
		
		for (int i = 0 ; i < horarios.length ; i++) {
			int inicio = -1;
			int fim = -1;
			for(int j = 0 ; j < horarios[i].length ; j++) {
				if (horarios[i][j] != null && !horarios[i][j].isEmpty()) {
					if (inicio == -1) {
						inicio = j;
					}
					fim = j;
				}
			}
			score -= 1 + fim - inicio;
		}
		return score;
	}
	
	public List<Funcionario> getFuncionarios(){
		return this.funcionarios;
	}
	
	public void setHorarios(String[][] horarios) {
		this.horarios = horarios;
	}
	
	public String[][] getHorarios(){
		return this.horarios;
	}
}
