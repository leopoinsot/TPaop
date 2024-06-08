package org.example;


import ar.unrn.database.ArchivoRegistro;
import ar.unrn.database.PersistenciaRegistro;
import ar.unrn.model.IApiRegistro;
import ar.unrn.model.RegistroArchivo;
import ar.unrn.ui.RegistroCiudadano;

import java.io.File;
import java.nio.file.Path;

public class Main {
	public static void main(String[] args) {
		IApiRegistro apiArchivo = new ArchivoRegistro(new File("C:\\Users\\leonr\\OneDrive\\Escritorio\\Inscriptos.txt"), new File("C:\\Users\\leonr\\OneDrive\\Escritorio\\Concursos.txt"));
		var ventanaDeRegistroDeCiudadano = new RegistroCiudadano(apiArchivo);
	}

}