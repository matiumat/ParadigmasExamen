package co.edu.poli.Examen2.modelo;

public class ExamenOrina extends ExamenMedico {
	
	private boolean presencia_glucosa;
	private double nivel_ph;
	
	public ExamenOrina() {}
	
	public ExamenOrina(String codigo_identificacion, String nombre_paciente, String fecha_realizacion, double costo_procedimiento, boolean presencia_glucosa, double nivel_ph) {
		this.presencia_glucosa = presencia_glucosa;
		this.nivel_ph = nivel_ph;
	}

	public boolean isPresencia_glucosa() {
		return presencia_glucosa;
	}

	public void setPresencia_glucosa(boolean presencia_glucosa) {
		this.presencia_glucosa = presencia_glucosa;
	}

	public double getNivel_ph() {
		return nivel_ph;
	}

	public void setNivel_ph(double nivel_ph) {
		this.nivel_ph = nivel_ph;
	}
	

}
