import java.util.ArrayList;
import java.util.List;

public class RestauradorJefe extends Usuario {
    private List<Restauracion> restauraciones;

    public RestauradorJefe(int idUsuario, String nombre, String apellido, String email, String contraseña) {
        super(idUsuario, nombre, apellido, email, contraseña, "RestauradorJefe");
        this.restauraciones = new ArrayList<>();
    }

    public List<Restauracion> getRestauraciones() {
        return restauraciones;
    }
    public void IniciarRestauracion(Restauracion restauracion) {
        restauracion.getObra().setEstado("En Restauración");
        restauracion.iniciarRestauracion();
        restauraciones.add(restauracion);
        System.out.println("Restauración iniciada para la obra: " + restauracion.getObra().getTitulo() + " ' iniciada correctamente.");
    }

    public void FinalizarRestauracion(Restauracion restauracion) {
        restauracion.finalizarRestauracion();
        restauracion.getObra().setEstado("disponible");
        System.out.println("Restauración finalizada para la obra: " + restauracion.getObra().getTitulo() + " ' finalizada correctamente.");
        }
    
        public List<Restauracion> consultarHistorial() {
            if (restauraciones.isEmpty()) {
                System.out.println("No hay restauraciones registradas.");
            return new ArrayList<>();
        }
            System.out.println("Historial de Restauraciones: " + getNombre() + " " + getApellido() + ":");
            for (Restauracion restauracion : restauraciones) {
                System.out.println("- Obra: " + restauracion.getObra().getTitulo() + " | Tipo: " + restauracion.getTipo() + " | Estado: " + restauracion.getEstado() + " | Fecha Inicio: " + restauracion.getFechaInicio() + " | Fecha Fin: " + restauracion.getFechaFin());
            }
            return restauraciones;
        }

        @Override
        public String toString() {
            return "RestauradorJefe{" +
                    "nombre='" + getNombre() + '\'' +
                    ", apellido='" + getApellido() + "(ID: " + getIdUsuario() + ")}";
        }
}




