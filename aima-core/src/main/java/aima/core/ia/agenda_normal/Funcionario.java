package aima.core.ia.agenda_normal;

public class Funcionario {
	public boolean[][] horariosDisponiveis;
	private String nome;
	private int horasDeTrabalho;
	private boolean vacinado;

	Funcionario(String nome, boolean[][] horariosDisponiveis, int horasDeTrabalho){
		this(nome, horariosDisponiveis, horasDeTrabalho, false);
	}	
	
	Funcionario(String nome, boolean[][] horariosDisponiveis, int horasDeTrabalho, boolean vacinado){
		this.nome = nome;
		this.horariosDisponiveis = horariosDisponiveis;
		this.horasDeTrabalho = horasDeTrabalho;
		this.vacinado = vacinado;
	}
	
	public boolean[][] getHorariosDisponiveis() {
		return this.horariosDisponiveis;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int getHorasDeTrabalho() {
		return this.horasDeTrabalho;
	}
	
	public boolean getVacinado() {
		return this.vacinado;
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
