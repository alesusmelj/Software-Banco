package objects;

public class Cliente {

	private int nroTurno;
	private String  nomenclatura;
	private int tiempoDeTramite; 
	private int tiempoDeEspera;
	
	public void setNroTurno (int turno){
		this.nroTurno = this.nroTurno + turno;
	}

	public void setTiempoDeTramite (int tiempoDeTramite){
		this.tiempoDeTramite = tiempoDeTramite;
	}

	public void setNomenclatura (String nomenclatura){
		this.nomenclatura = nomenclatura;
	}

	public void setTiempoDeEspera (int tiempoDeEspera){
		this.tiempoDeEspera = tiempoDeEspera;
	}

	public int getnroTurno () {
		return nroTurno;
	}

	public String getNomenclatura () {
		return this.nomenclatura;
	}

	public int getTiempoDeEspera (){
		return this.tiempoDeEspera;
	}

	public int getTiempoDeTramite (){
		return this.tiempoDeTramite;
	}
} 