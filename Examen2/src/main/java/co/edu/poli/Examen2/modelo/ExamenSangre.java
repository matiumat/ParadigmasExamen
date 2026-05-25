package co.edu.poli.Examen2.modelo;

public class ExamenSangre extends ExamenMedico {
	
	private String rh;
	private String tipo_sangre;
	
	public ExamenSangre(){}
	
	public ExamenSangre(String codigo_identificacion, String nombre_paciente, String fecha_realizacion, double costo_procedimiento, String rh, String tipo_sangre) {
		this.rh = rh;
		this.tipo_sangre = tipo_sangre;
	}

	public String getRh() {
		return rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}

	public String getTipo_sangre() {
		return tipo_sangre;
	}

	public void setTipo_sangre(String tipo_sangre) {
		this.tipo_sangre = tipo_sangre;
	}
	

}
