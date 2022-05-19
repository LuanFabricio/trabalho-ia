package aima.core.ia.agenda_prioridade;

import java.util.List;
import java.util.Random;

public class Agenda {
	private List<Funcionario> funcionarios;
	private String[][] horarios;
	public int fitness;
	public int DIAS;
	public int HORARIOS;
	
	Agenda(List<Funcionario> funcionarios, int dias, int horarios){
		this.funcionarios = funcionarios;
		this.DIAS = dias;
		this.HORARIOS = horarios;
		this.horarios= new String[DIAS][HORARIOS];
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
						System.out.printf("dia: %d [%d] ", i+1, j);
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
			for (int j = 0 ; j < HORARIOS ; j++) {
				String nomeFuncionario = this.horarios[i][j];
				System.out.println("\t"+(j+1)+" "+nomeFuncionario);
			}
		}
	}
	
	private String[][] createParents(){
		String[][] horarios = new String[DIAS][HORARIOS];
		Random rand = new Random();
		for (int i = 0; i < funcionarios.size(); i++) {
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
