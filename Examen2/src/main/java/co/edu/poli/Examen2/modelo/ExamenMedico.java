package co.edu.poli.Examen2.modelo;

public class ExamenMedico {
	private String codigo_identificacion;
	private String nombre_paciente;
	private String fecha_realizacion;
	private String costo_procedimiento;
	
	public ExamenMedico () {}
	
	public ExamenMedico (String codigo_identificacion, String nombre_paciente, String fecha_realizacion, String costo_procedimiento) {
		this.codigo_identificacion = codigo_identificacion;
		this.nombre_paciente = nombre_paciente;
		this.fecha_realizacion = fecha_realizacion;
		this.costo_procedimiento = costo_procedimiento;
	}

	public String getCodigo_identificacion() {
		return codigo_identificacion;
	}

	public void setCodigo_identificacion(String codigo_identificacion) {
		this.codigo_identificacion = codigo_identificacion;
	}

	public String getNombre_paciente() {
		return nombre_paciente;
	}

	public void setNombre_paciente(String nombre_paciente) {
		this.nombre_paciente = nombre_paciente;
	}

	public String getFecha_realizacion() {
		return fecha_realizacion;
	}

	public void setFecha_realizacion(String fecha_realizacion) {
		this.fecha_realizacion = fecha_realizacion;
	}

	public String getCosto_procedimiento() {
		return costo_procedimiento;
	}

	public void setCosto_procedimiento(String costo_procedimiento) {
		this.costo_procedimiento = costo_procedimiento;
	}
	
	
}
