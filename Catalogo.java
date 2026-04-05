import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private int id;
    private String nombre;
    private List<ObraArte> obras;

    public Catalogo(int id, String nombre) {
        setId(id);
        setNombre(nombre);
        this.obras = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) this.id = id;
        else throw new IllegalArgumentException("El ID del catálogo debe ser un número positivo.");
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) this.nombre = nombre.trim();
        else throw new IllegalArgumentException("El nombre del catálogo no puede estar vacío.");
    }
    public List<ObraArte> getObras() {
        return obras;
    }

    public void registrarObra(ObraArte obra) {
        if (obra != null) {
            obras.add(obra);
        } else {
            throw new IllegalArgumentException("La obra de arte no puede ser nula.");
        }
    }

    public ObraArte buscarObra(int idObra) {
        for (ObraArte obra : obras) {
            if (obra.getIdObra() == idObra) {
                return obra;
            }
        }
        return null; // Retorna null si no se encuentra la obra
    }

    public List<ObraArte> listarObras() {
        if (obras.isEmpty()) {
            System.out.println("No hay obras registradas en el catálogo.");
            return new ArrayList<>(obras); 
        }
        System.out.println("Obras en el catálogo:" + nombre + ":");
        for (ObraArte obra : obras) {
            System.out.println("- " + obra.getTitulo() + " | Autor:" + obra.getAutor() + "| Estado: " + obra.getEstado());
        }
        return obras;
    }

    @Override
    public String toString() {
        return "Catalogo{" + nombre + " | ID: " + id + " | Total Obras: " + obras.size() + "}";
    }
}   

