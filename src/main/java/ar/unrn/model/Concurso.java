package ar.unrn.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
	private String id;
	private String nombre;
	private LocalDate fechaInicioInscripcion;
	private LocalDate fechaCierreInscripcion;

	public Concurso(String id, String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaCierreInscripcion) {
		this.id = id;
		this.nombre = nombre;
		this.fechaInicioInscripcion = fechaInicioInscripcion;
		this.fechaCierreInscripcion = fechaCierreInscripcion;
	}

	public Concurso(String id) {
		this.id = id;
	}

	public boolean sigueVigenteFechaInscripcion() {
		LocalDate fechaActual = LocalDate.now();
		return (fechaActual.equals(fechaInicioInscripcion) || fechaActual.isAfter(fechaInicioInscripcion)) &&
				(fechaActual.equals(fechaCierreInscripcion) || fechaActual.isBefore(fechaCierreInscripcion));
	}
	public String obtenerId(){
		return id;
	}
}
