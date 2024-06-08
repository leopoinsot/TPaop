package ar.unrn.model;

public class Inscripcion {
	private Ciudadano ciudadano;
	private Concurso concurso;

	public Inscripcion(Ciudadano ciudadano, Concurso concurso) {
		this.ciudadano = ciudadano;
		this.concurso = concurso;
	}
	public Ciudadano obtenerCiudadano(){
		return ciudadano;
	}
	public Concurso obtenerConcurso(){
		return concurso;
	}
}
