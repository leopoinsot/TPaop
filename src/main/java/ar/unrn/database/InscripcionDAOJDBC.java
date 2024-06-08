package ar.unrn.database;

import ar.unrn.model.Inscripcion;
import ar.unrn.model.InscripcionDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InscripcionDAOJDBC implements InscripcionDAO {

	@Override
	public void create(Inscripcion inscripcion) {
		// Proporciona los detalles de conexión a la base de datos
		String url = "jdbc:mysql://localhost:3306/sistemasinscripcionesconcursosobjetos2";
		String username = "LeonelAriel";
		String password = "villa2015";
		try {
			// Establece la conexión a la base de datos
			Connection conn = DriverManager.getConnection(url, username, password);

			// Prepara la sentencia SQL para la inserción
			PreparedStatement statement = conn.prepareStatement("INSERT INTO inscripciones(apellido, nombre, email, telefono, ID_Concurso) VALUES (?, ?, ?, ?, ?)");

			// Establece los valores de los parámetros en la sentencia SQL
			statement.setString(1, inscripcion.obtenerCiudadano().obtenerApellido());
			statement.setString(2,  inscripcion.obtenerCiudadano().obtenerNombre());
			statement.setString(3,  inscripcion.obtenerCiudadano().obtenerEmail());
			statement.setString(4,  inscripcion.obtenerCiudadano().obtenerTelefono());
			statement.setString(5,  inscripcion.obtenerConcurso().obtenerId());
			// Ejecuta la inserción
			statement.executeUpdate();

			// Cierra la conexión y la declaración
			statement.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error al conectar con la base de datos", e);
		}
	}
}
