package ar.unrn.database;

import ar.unrn.model.*;

import java.util.ArrayList;
import java.util.List;

public class PersistenciaRegistro implements IApiRegistro {
	private InscripcionDAO inscripcionDAO = new InscripcionDAOJDBC();
	private ConcursoDAO concursoDAO= new ConcursoDAOJDBC();


	@Override
	public void saveInscription(String nombre, String apellido, String telefono, String email, String id) {
		Ciudadano ciudadano = new Ciudadano(nombre, apellido, telefono, email);
		Concurso concurso = new Concurso(id);
		Inscripcion inscripcion = new Inscripcion(ciudadano, concurso);
		inscripcionDAO.create(inscripcion);
	}

	@Override
	public List<Concurso> todosLosConcursos() {
		return (ArrayList) concursoDAO.findAll();
	}
}
