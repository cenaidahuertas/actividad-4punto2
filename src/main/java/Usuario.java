import java.util.List;  

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String rol; 

    public static final List<String> ROLES = List.of(
        "director", "encargado_catalogo", "visitante", "restaurador_jefe"
    );

    public Usuario(int idUsuario, String nombre, String apellido, String email, String contraseña, String rol) {
        setIdUsuario(idUsuario);
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        this.contraseña = contraseña;
        setRol(rol);
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        if (idUsuario > 0) this.idUsuario = idUsuario;
        else throw new IllegalArgumentException("El ID de usuario debe ser un número positivo.");
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) this.nombre = nombre.trim();
        else throw new IllegalArgumentException("El nombre no puede estar vacío.");
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        if (apellido != null && !apellido.isBlank()) this.apellido = apellido.trim();
        else throw new IllegalArgumentException("El apellido no puede estar vacío.");
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (email != null && !email.isBlank()) this.email = email.trim();
        else throw new IllegalArgumentException("El email no puede estar vacío.");
    }

    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        if (contraseña != null && !contraseña.isBlank()) this.contraseña = contraseña;
        else throw new IllegalArgumentException("La contraseña no puede estar vacía.");
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        if (ROLES.contains(rol)) this.rol = rol;
        else throw new IllegalArgumentException("El rol debe ser uno de los siguientes: " + ROLES);
    }

    public boolean autenticar(String email, String contraseña) {
        return this.email.equals(email) && this.contraseña.equals(contraseña);
    }

    // Acceso protegido a la contraseña para subclases y autenticación 
    protected String getContraseñaProtegida() {
        return contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

}


