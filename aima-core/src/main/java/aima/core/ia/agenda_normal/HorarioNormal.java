package aima.core.ia.agenda_normal;

import java.util.ArrayList;
import java.util.List;

public class HorarioNormal {
	private List<Funcionario> funcionarios;
	private boolean vacinaStatus = false;
	
	public HorarioNormal() {
		this.funcionarios = new ArrayList<>();
	}
	
	public boolean vazio() {
		return funcionarios.size() == 0;
	}
	
	public void addFuncionario(Funcionario f) {
		funcionarios.add(f);
	}
	
	public void addFuncionarioSePuder(Funcionario f) {
		if (podeAdicionar(f)) {
			addFuncionario(f);
			setStatus(f.getVacinado());
		}
	}
	
	public void rmFuncionario(String nome) {
		for (int i = 0 ; i < this.funcionarios.size() ; i++) {
			Funcionario f = this.funcionarios.get(i);
			if (nome.equals(f.getNome())) {
//				System.out.println("Removindo (Tamanho anterior " + this.funcionarios.size() + ")");
				this.funcionarios.remove(i);
//				System.out.println("Removido " + this.funcionarios.size());
			}
		}
	}
	
	public boolean podeAdicionar(Funcionario f) {
		if (vazio()) {
			return true;
		}
		else if (vacinaStatus && f.getVacinado() 
				&& !repetido(f)) {
			return true;
		}
		return false;
	}
	
	private boolean repetido(Funcionario novoFuncionario) {
		for (Funcionario f : this.funcionarios) {
			if(novoFuncionario.getNome().equals(f.getNome())) {
				return true;
			}
		}
		return false;
	}
	
	public void setStatus(boolean status) {
		this.vacinaStatus = status;
	}
	
	public boolean getStatus() {
		return this.vacinaStatus;
	}
	
	public String[] getNomes(){
		String[] nomes = new String[this.funcionarios.size()];
		for (int i = 0 ; i < this.funcionarios.size() ; i++) {
			Funcionario f = funcionarios.get(i);
			nomes[i] = f.getNome();
		}
		return nomes;
	}
}
