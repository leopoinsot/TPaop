package ar.unrn.model;

import java.util.List;

public interface IApiRegistro {
	void saveInscription(String nombre, String apellido, String telefono, String email, String id);
	List<Concurso> todosLosConcursos();
}
