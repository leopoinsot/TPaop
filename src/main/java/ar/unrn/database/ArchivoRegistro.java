package ar.unrn.database;
import ar.unrn.model.Concurso;
import ar.unrn.model.IApiRegistro;
import ar.unrn.model.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArchivoRegistro implements IApiRegistro {
	private File fileInscripciones;
	private File fileConcursos;

	public ArchivoRegistro(File fileInscripciones, File fileConcursos){
		this.fileInscripciones = fileInscripciones;
		this.fileConcursos = fileConcursos;
	}

	@Override
	@Log
	public void saveInscription(String nombre, String apellido, String telefono, String email, String id) throws RuntimeException{
		String formatoRegistro = nombre + "," + apellido + "," + telefono + "," + email + "," + id;
		try {
			Files.write(
					Paths.get(fileInscripciones.getPath()),
					formatoRegistro.getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("error al escribir en el archivo de texto",e);
		}
	}

	@Override
	@Log
	public List<Concurso> todosLosConcursos() {
		List<Concurso> concursos = new ArrayList<>();
		try {
			var scanner = new Scanner(fileConcursos);
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				// Dividimos la línea en sus componentes usando la coma como delimitador
				String[] partes = linea.split(",");
				// Parseamos las fechas
				var formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				var fechaInicioInscripcion = LocalDate.parse(partes[2], formatter);
				var fechaCierreInscripcion = LocalDate.parse(partes[3], formatter);
				// Creamos un objeto Concurso con la información de la línea y las fechas parseadas
				Concurso concurso = new Concurso(partes[0], partes[1], fechaInicioInscripcion, fechaCierreInscripcion);
				// Agregamos el concurso a la lista
				concursos.add(concurso);
			}
			scanner.close(); // Cerramos el scanner después de usarlo
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return concursos;
	}
}