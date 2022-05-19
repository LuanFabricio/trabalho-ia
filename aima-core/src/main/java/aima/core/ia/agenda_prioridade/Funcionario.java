package aima.core.ia.agenda_prioridade;

import java.util.List;

public class Funcionario {
	public boolean[][] horariosDisponiveis;
	private String nome;
	public int[] contador;
	Funcionario(String nome, boolean[][] horariosDisponiveis){
		this.nome = nome;
		this.horariosDisponiveis = horariosDisponiveis;
		int[] contador = { 0, 0, 0, 0, 0 };
		this.contador = contador;
	}
	
	public boolean[][] getHorariosDisponiveis() {
		return this.horariosDisponiveis;
	}
	
	public String getNome() {
		return this.nome;
	}
}
