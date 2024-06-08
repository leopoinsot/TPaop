package ar.unrn.model;

public class Ciudadano {
	private String dni;
	private String nombre;
	private String apellido;
	private String Email;
	private String telefono;

	public Ciudadano(String dni, String nombre, String apellido, String email, String telefono) throws RuntimeException{
		validarDatos(dni,nombre, apellido, email, telefono);
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		Email = email;
		this.telefono = telefono;
	}

	public Ciudadano(String nombre, String apellido, String email, String telefono) throws RuntimeException{
		validarDatos(dni,nombre, apellido, email, telefono);
		this.nombre = nombre;
		this.apellido = apellido;
		Email = email;
		this.telefono = telefono;
	}


	private void validarDatos(String dni, String nombre, String apellido, String email, String telefono) throws RuntimeException{

		if ("".equals(nombre)) {
			throw new RuntimeException("Nombre no puede ser vacio");
		}
		if ("".equals(apellido)) {
			throw new RuntimeException("apellido no puede ser vacio");
		}
		if ("".equals(dni)) {
			throw new RuntimeException("dni no puede ser vacio");
		}
		if(checkEmail(email)){
			throw new RuntimeException("email debe ser válido");
		}
		if(checkPhone(telefono)){
			throw new RuntimeException("El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
		}
	}
	private boolean checkEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
	private boolean checkPhone(String telefono) {
		String regex = "\\d{4}-\\d{6}";
		return telefono.matches(regex);
	}
	public String obtenerNombre() {
		return nombre;
	}

	public String obtenerApellido() {
		return apellido;
	}

	public String obtenerEmail() {
		return Email;
	}

	public String obtenerTelefono() {
		return telefono;
	}
}
