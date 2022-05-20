package aima.core.ia.agenda_normal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Organizador {
	private Agenda agenda;
	private int maxIndividuos;
	private int tentativas;
	private int MIN = Integer.MIN_VALUE;
	private int DIAS;
	private int HORARIOS;
	Organizador(Agenda agenda, int dias, int horarios, int maxIndividuos, int tentativas){
		this.agenda = agenda;
		this.tentativas = tentativas;
		this.DIAS = dias;
		this.HORARIOS = horarios;
		organizar();
	}
	
	private void organizar() {
		Agenda[] melhores = new Agenda[2];
		melhores[0] = this.agenda;
		melhores[0].fitness = calcFitness(melhores[0]);
		melhores[1] = createPar();
		melhores[1].fitness = calcFitness(melhores[1]);
//		int[] fitness = { calcFitness(melhores[0]), calcFitness(melhores[1])};
		if (melhores[0].fitness < melhores[1].fitness) {
//			int fitnessTmp = fitness[0];
			Agenda melhoresTmp = melhores[0];
//			fitness[0] = fitness[1];
//			fitness[1] = fitnessTmp;
			melhores[0] = melhores[1];
			melhores[1] = melhoresTmp;
		}
		for (int i = 0 ; i < this.tentativas ; i++) {
			for (int j = 0 ; j < this.maxIndividuos ; j++) {
				Agenda novoIndividuo = join(melhores[0], createPar());
				novoIndividuo.fitness = calcFitness(novoIndividuo);
//				int individuoFitness = calcFitness(novoIndividuo);
				if (melhores[0].fitness < novoIndividuo.fitness) {
					melhores[1] = melhores[0];
//					fitness[1] = fitness[0];
					melhores[0] = novoIndividuo;
//					fitness[0] = individuoFitness;
				}
				else if (melhores[1].fitness < novoIndividuo.fitness) {
					melhores[1] = novoIndividuo;
//					fitness[1] = individuoFitness;
				}
			}
			Agenda novoIndividuo = join(melhores[0], melhores[1]);
			novoIndividuo.fitness = calcFitness(novoIndividuo);
//			int individuoFitness = calcFitness(novoIndividuo);
			if (melhores[0].fitness < novoIndividuo.fitness) {
				melhores[1] = melhores[0];
//				fitness[1] = fitness[0];
				melhores[0] = novoIndividuo;
//				fitness[0] = individuoFitness;
			}
			else if (melhores[1].fitness < novoIndividuo.fitness) {
				melhores[1] = novoIndividuo;
//				fitness[1] = individuoFitness;
			}
		}
		melhores[0].printAll();
		System.out.println(melhores[0].fitness);
		System.out.println(melhores[1].fitness);
	}
	
	public Agenda createPar() {
		Agenda newAgenda = new Agenda(this.agenda.getFuncionarios(), DIAS, HORARIOS);
		newAgenda.setHorarios(removeHorario(newAgenda));
		return newAgenda;
	}
	
	public int calcFitness(Agenda agenda) {
		int score = 0;
		HorarioNormal[][] horarios = agenda.getHorarios(); // removeHorario(agenda);
		List<Funcionario> funcionarios = agenda.getFuncionarios();
		int[][] horasTrabalhadas = criaHorasTrabalhadas(funcionarios.size()); 
		for (int dia = 0 ; dia < horarios.length ; dia++) {
			int fim = -1;
			for (int horario = 0 ; horario < horarios[dia].length ; horario++) {
				if (horarios[dia][horario] != null) {
					String[] funcionariosNomes = horarios[dia][horario].getNomes();
					score += 150 * funcionariosNomes.length;
					for (String funcionarioNome : funcionariosNomes) {
						int index = agenda.getFuncionario(funcionarioNome);
						Funcionario f = funcionarios.get(index);
						if (f.livre(dia, horario)){
							horasTrabalhadas[index][dia]++;
						}
						else {
							score = MIN;
						}
						fim = horario;
					}
				}
			}
			score += -fim * 100;
		}
		for (int funcionarioIndex = 0 ; funcionarioIndex < funcionarios.size() ; funcionarioIndex++) {
			int[] horasDoFuncionario = horasTrabalhadas[funcionarioIndex];
			Funcionario f = funcionarios.get(funcionarioIndex);
			for (int horas: horasDoFuncionario) {
				if (horas == f.getHorasDeTrabalho()) {
					score += 100;
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
		Agenda filho = new Agenda(this.agenda.getFuncionarios(), DIAS, HORARIOS);
		HorarioNormal[][] horariosFilho = new HorarioNormal[DIAS][HORARIOS];
		HorarioNormal[][] horariosAgenda1 = agenda1.getHorarios();
		HorarioNormal[][] horariosAgenda2 = agenda2.getHorarios();
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
	
	private HorarioNormal mutar(List<Funcionario> funcionarios) {
		HorarioNormal hr = new HorarioNormal();
		Random rand = new Random();
		int index = rand.nextInt(funcionarios.size());
		Funcionario funcionario = funcionarios.get(index);
		hr.addFuncionarioSePuder(funcionario);
		if(hr.getStatus()){
			int quantFuncionarios = rand.nextInt(funcionarios.size()-1);
			for (int i = 0 ; i < quantFuncionarios ; i++) {
				index = rand.nextInt(funcionarios.size());
				funcionario = funcionarios.get(index);
				hr.addFuncionarioSePuder(funcionario);
			}
		}
		return hr;
	}
	
	 private HorarioNormal[][] removeHorario(Agenda agenda){
		 HorarioNormal[][] horarios = agenda.getHorarios();
		 List<Funcionario> funcionarios = agenda.getFuncionarios();
		 int[][] horasTrabalhadas = criaHorasTrabalhadas(funcionarios.size());
		 for (int dia = 0 ; dia < DIAS ; dia++) {
			 for (int horario = 0 ; horario < HORARIOS ; horario++) {
				 if (horarios[dia][horario] != null) {
					 String[] nomes = horarios[dia][horario].getNomes();
					 for (String nome : nomes) {
						 int index = agenda.getFuncionario(nome);
						 Funcionario funcionario = funcionarios.get(index);
						 if (!funcionario.livre(dia, horario) 
								 || horasTrabalhadas[index][dia] == funcionario.getHorasDeTrabalho()) {
							 horarios[dia][horario].rmFuncionario(nome);
						 }
						 else {
							 horasTrabalhadas[index][dia]++;
						 }
					 }
				 }
			 }
		 }
		 return horarios;
	 }
}
