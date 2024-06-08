package ar.unrn.model;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
public class RegistroArchivo implements Registro{
	private File fileMetodos;

	public RegistroArchivo() {
		this.fileMetodos = new File("C:\\Users\\leonr\\OneDrive\\Escritorio\\GuardadoLlamadasAMetodo.txt");
	}

	@Override
	//@Before("@annotation.ar.unrn.model.Log)")
	@Before("execution(@Log * *(..))")
	public void guardarMetodoLlamado(JoinPoint joinPoint) {
		// Obtener el nombre del método
		String nombreMetodo = joinPoint.getSignature().getName();

		// Obtener los parámetros del método y formatearlos
		Object[] args = joinPoint.getArgs();
		String variables = (args != null && args.length > 0) ?
				Arrays.stream(args)
						.map(Object::toString)
						.collect(Collectors.joining("|")) : "sin parametros";

		// Obtener la fecha y hora actual
		LocalDateTime now = LocalDateTime.now();
		String fechaHora = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

		// Formatear la entrada de log
		String formatoRegistro = String.format("\"%s\", \"%s\", \"%s\"", nombreMetodo, variables, fechaHora);

		// Guardar la entrada de log en un archivo de texto
		try {
			Files.write(
					Paths.get(fileMetodos.getPath()),
					(formatoRegistro + System.lineSeparator()).getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RuntimeException("Error al escribir en el archivo de texto", e);
		}
	}
}
