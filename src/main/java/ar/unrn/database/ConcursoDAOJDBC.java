package ar.unrn.database;
import ar.unrn.model.Concurso;
import ar.unrn.model.ConcursoDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConcursoDAOJDBC implements ConcursoDAO {

	public List<Concurso> findAll() {
		String url = "jdbc:mysql://localhost:3306/sistemasinscripcionesconcursosobjetos2";
		String username = "LeonelAriel";
		String password = "villa2015";
		List<Concurso> concursos = new ArrayList<>();
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, username, password);
			PreparedStatement statement = conn.prepareStatement("SELECT ID, nombre, fechaAperturaInscripcion, fechaCierreInscripcion FROM concursos");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String id = resultSet.getString("ID");
				String nombre = resultSet.getString("nombre");
				LocalDate fechaInicioInscripcion = resultSet.getDate("fechaAperturaInscripcion").toLocalDate();
				LocalDate fechaCierreInscripcion = resultSet.getDate("fechaCierreInscripcion").toLocalDate();

				Concurso concurso = new Concurso(id, nombre, fechaInicioInscripcion, fechaCierreInscripcion);
				concursos.add(concurso);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al conectar con la base de datos", e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException("Error al cerrar la conexión a la base de datos", e);
				}
			}
		}

		return concursos;
	}
}
