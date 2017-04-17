package transferObject;

public class RegistroAcessoTO {
	private String data, cpfUsuario, nomeUsuario, nomeEmpresa, horarioEntrada, horarioSaida;

	public RegistroAcessoTO(String data, String cpfUsuario, String nomeUsuario, String nomeEmpresa,
			String horarioEntrada, String horarioSaida) {
		this.data = data;
		this.cpfUsuario = cpfUsuario;
		this.nomeUsuario = nomeUsuario;
		this.nomeEmpresa = nomeEmpresa;
		this.horarioEntrada = horarioEntrada;
		this.horarioSaida = horarioSaida;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(String horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public String getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(String horarioSaida) {
		this.horarioSaida = horarioSaida;
	}

	@Override
	public String toString() {
		return "RegistroAcessoTO [getData()=" + getData() + ", getCpfUsuario()=" + getCpfUsuario()
				+ ", getNomeUsuario()=" + getNomeUsuario() + ", getNomeEmpresa()=" + getNomeEmpresa()
				+ ", getHorarioEntrada()=" + getHorarioEntrada() + ", getHorarioSaida()=" + getHorarioSaida() + "]";
	}
	
}
