package aima.core.ia.agenda;

public class Funcionario {
	public boolean[][] horariosDisponiveis;
	private String nome;
	Funcionario(String nome, boolean[][] horariosDisponiveis){
		this.nome = nome;
		this.horariosDisponiveis = horariosDisponiveis;
	}
	
	public boolean[][] getHorariosDisponiveis() {
		return this.horariosDisponiveis;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public boolean livre(int dia, int horario) {
		return this.horariosDisponiveis[dia][horario];
	}
	
	public static boolean[][] createHorarios(int dias, int horarios){
		boolean[][] newHorarios = new boolean[dias][horarios];
		for (int dia = 0 ; dia < dias ; dia++) {
			for (int horario = 0 ; horario < horarios ; horarios++) {
				newHorarios[dia][horario] = false;
			}
		}
		return newHorarios;
	}
}
