package aima.core.ia.agenda_prioridade;

import java.util.List;
import java.util.Random;

public class Organizador {
	private Agenda agenda;
	private int horasTrabalhadas;
	private int maxIndividuos;
	private int tentativas;
	private int MAX_LOOP = 100;
	private int MIN = Integer.MIN_VALUE;
	private int DIAS = 5;
	private int HORARIOS = 8;
	Organizador(Agenda agenda, int horasTrabalhadas, int maxIndividuos, int tentativas){
		this.agenda = agenda;
		this.horasTrabalhadas = horasTrabalhadas;
		this.tentativas = tentativas;
//		int bestScore = calcFitness(agenda);
//		for(int i = 0 ; i < 999999 ; i++) {
//			agenda = createPar();
//			int score = calcFitness(agenda);
//			if (bestScore < score) {
//				bestScore = score;
//			}
//		}
//		System.out.printf("Best fitness %d\n", bestScore);
		organizar();
	}
	
	private void organizar() {
		Agenda[] melhores = new Agenda[2];
		melhores[0] = this.agenda;
		melhores[1] = createPar();
		int[] fitness = { calcFitness(melhores[0]), calcFitness(melhores[1])};
		if (fitness[0] < fitness[1]) {
			int fitnessTmp = fitness[0];
			Agenda melhoresTmp = melhores[0];
			fitness[0] = fitness[1];
			fitness[1] = fitnessTmp;
			melhores[0] = melhores[1];
			melhores[1] = melhoresTmp;
		}
		for (int i = 0 ; i < this.tentativas ; i++) {
			for (int j = 0 ; j < this.maxIndividuos ; j++) {
				Agenda novoIndividuo = join(melhores[0], createPar());
				int individuoFitness = calcFitness(novoIndividuo);
				if ( fitness[0] < individuoFitness) {
					melhores[1] = melhores[0];
					fitness[1] = fitness[0];
					melhores[0] = novoIndividuo;
					fitness[0] = individuoFitness;
				}
				else if ( fitness[1] < individuoFitness) {
					melhores[1] = novoIndividuo;
					fitness[1] = individuoFitness;
				}
			}
			Agenda novoIndividuo = join(melhores[0], melhores[1]);
			int individuoFitness = calcFitness(novoIndividuo);
			if ( fitness[0] < individuoFitness) {
				melhores[1] = melhores[0];
				melhores[0] = novoIndividuo;
				fitness[0] = individuoFitness;
				fitness[1] = fitness[0];
			}
			else if ( fitness[1] < individuoFitness) {
				melhores[1] = novoIndividuo;
				fitness[1] = individuoFitness;
			}
		}
		melhores[1].printAll();
		System.out.println(fitness[0]);
		System.out.println(fitness[1]);
	}
	
	public Agenda createPar() {
		Agenda newAgenda = new Agenda(this.agenda.getFuncionarios());
		newAgenda.setHorarios(removeHorario(newAgenda));
		return newAgenda;
	}
	
	public int calcFitness(Agenda agenda) {
		int score = 0;
		String[][] horarios = agenda.getHorarios(); // removeHorario(agenda);
		List<Funcionario> funcionarios = agenda.getFuncionarios();
		int[][] horasTrabalhadas = criaHorasTrabalhadas(funcionarios.size()); 
		for (int dia = 0 ; dia < horarios.length ; dia++) {
			int fim = -1;
			for (int horario = 0 ; horario < horarios[dia].length ; horario++) {
				if (horarios[dia][horario] != null) {
					String funcionarioNome = horarios[dia][horario];  
					int index = agenda.getFuncionario(funcionarioNome);
					Funcionario f = funcionarios.get(index);
					boolean[][] funcionarioHorario = f.getHorariosDisponiveis();
					if (funcionarioHorario[dia][horario]) {
						horasTrabalhadas[index][dia]++;
						score += 100;
					}
					else {
						score = MIN;
					}
					fim = horario;
				}
			}
			score += -fim * 100;
		}
		for (int[] horasDoFuncionario : horasTrabalhadas) {
			for (int horas: horasDoFuncionario) {
				if (horas == this.horasTrabalhadas) {
					score += 1500;
				}
				else {
					score = MIN;
				}
			}
		}
		return score;
	}
	
	private int[][] criaHorasTrabalhadas(int quantFuncionarios){
		int[][] horasTrabalhadas = new int[quantFuncionarios][DIAS];
		for (int i = 0 ; i < quantFuncionarios ; i++) {
			for (int j = 0 ; j < DIAS ; j ++ ) {
				horasTrabalhadas[i][j] = 0;
			}
		}
		return horasTrabalhadas;
	}
	
	private Agenda join(Agenda agenda1, Agenda agenda2) {
		Random rand = new Random();
		List<Funcionario> funcionarios = agenda1.getFuncionarios();
		Agenda filho = new Agenda(this.agenda.getFuncionarios());
		String[][] horariosFilho = new String[DIAS][HORARIOS];
		String[][] horariosAgenda1 = agenda1.getHorarios();
		String[][] horariosAgenda2 = agenda2.getHorarios();
		for (int dia = 0 ; dia < DIAS ; dia++) {
			for (int horario = 0 ; horario < HORARIOS ; horario++) {
				double mute = rand.nextDouble();
				if (mute < 0.45) {
					horariosFilho[dia][horario] = horariosAgenda1[dia][horario];
				}
				else if (mute < 0.9) {
					horariosFilho[dia][horario] = horariosAgenda2[dia][horario];
				}
				else {
					horariosFilho[dia][horario] = mutar(funcionarios);
				}
			}
		}
		filho.setHorarios(horariosFilho);
		filho.setHorarios(removeHorario(filho));
		return filho;
	}
	
	private String mutar(List<Funcionario> funcionarios) {
		Random rand = new Random();
		int index = rand.nextInt(funcionarios.size());
		Funcionario funcionario = funcionarios.get(index);
		return funcionario.getNome();
	}
	
	 private String[][] removeHorario(Agenda agenda){
		 String[][] horarios = agenda.getHorarios();
		 List<Funcionario> funcionarios = agenda.getFuncionarios();
		 int[][] horasTrabalhadas = criaHorasTrabalhadas(funcionarios.size());
		 for (int dia = 0 ; dia < DIAS ; dia++) {
			 for (int horario = 0 ; horario < HORARIOS ; horario++) {
				 if (horarios[dia][horario] != null) {
					 String nome = horarios[dia][horario];
					 int index = agenda.getFuncionario(nome);
					 Funcionario funcionario = funcionarios.get(index);
					 boolean[][] funcionarioHorarios = funcionario.getHorariosDisponiveis();
					 if (!funcionarioHorarios[dia][horario] 
							 || horasTrabalhadas[index][dia] == this.horasTrabalhadas) {
						 horarios[dia][horario] = null;
					 }
					 else {
						 horasTrabalhadas[index][dia]++;
					 }
				 }
			 }
		 }
		 return horarios;
	 }
}
