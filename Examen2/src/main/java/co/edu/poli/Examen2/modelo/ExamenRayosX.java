package co.edu.poli.Examen2.modelo;

public class ExamenRayosX extends ExamenMedico {
	
	private String zona_cuerpo_analizada;
	private double nivel_radiacion;
	
	public ExamenRayosX() {}
	
	public ExamenRayosX(String codigo_identificacion, String nombre_paciente, String fecha_realizacion, double costo_procedimiento, String zona_cuerpo_analizada, double nivel_radiacion) {
		this.zona_cuerpo_analizada = zona_cuerpo_analizada;
		this.nivel_radiacion = nivel_radiacion;
	}

	public String getZona_cuerpo_analizada() {
		return zona_cuerpo_analizada;
	}

	public void setZona_cuerpo_analizada(String zona_cuerpo_analizada) {
		this.zona_cuerpo_analizada = zona_cuerpo_analizada;
	}

	public double getNivel_radiacion() {
		return nivel_radiacion;
	}

	public void setNivel_radiacion(double nivel_radiacion) {
		this.nivel_radiacion = nivel_radiacion;
	}
	

}
