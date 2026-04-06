import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nombre;
    private String ubicacion;
    private List<ObraArte> obras;

    public Sala(String nombre, String ubicacion) {
        setNombre(nombre);
        setUbicacion(ubicacion);
        this.obras = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) this.nombre = nombre.trim();
        else throw new IllegalArgumentException("El nombre de la sala no puede estar vacío.");
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        if (ubicacion != null && !ubicacion.isBlank()) this.ubicacion = ubicacion.trim();
        else throw new IllegalArgumentException("La ubicación de la sala no puede estar vacía.");
    }

    public List<ObraArte> getObras() {
        return obras;
    }

    public void asignarObra(ObraArte obra) {
        if (obra != null) {
            obras.add(obra);
        } else {
            throw new IllegalArgumentException("La obra de arte no puede ser nula.");
        }
    }

    public void retirarObra(ObraArte obra) {
        if (obras.contains(obra)) {
            obras.remove(obra);        
            System.out.println("Obra '" + obra.getTitulo() + "' retirada de la sala '" + nombre + "'.");
        }
        else {
            System.out.println("La obra '" + obra.getTitulo() + "' no se encuentra en la sala '" + nombre + "'.");
        }
    }

    public void listarObras() {
        if (!obras.isEmpty()) {
            System.out.println("Obras en la sala '" + nombre + "' : ");
            for (ObraArte obra : obras) {
                System.out.println("- " + obra.getTitulo() + " (ID: " + obra.getIdObra() + ")");
            }
        } else {
            System.out.println("No hay obras asignadas a la sala '" + nombre + "'.");
        }
    }

    @Override
    public String toString() {
        return "Sala{" +
                "nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + "' | Numero de obras=" + obras.size() + "}";
    }
}
