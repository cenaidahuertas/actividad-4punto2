import java.util.List;

public class Visitante extends Usuario {
    public Visitante(int idUsuario, String nombre, String apellido, String email, String contraseña) {
        super(idUsuario, nombre, apellido, email, contraseña, "visitante");
    }

    public List<ObraArte> consultarObrasPorSala(Sala sala) {
        List<ObraArte> obras = sala.getObras();
        if (obras.isEmpty()) {
            System.out.println("La sala '" + sala.getNombre() + "' no tiene obras asignadas.");
            return obras;
        }
        System.out.println("Obras en la sala '" + sala.getNombre() + "':");
        for (ObraArte o : obras) {
            System.out.println("- " + o.getTitulo() + " | Autor:" + o.getAutor() + " | Periodo:" + o.getPeriodo());
        }
        return obras;
    }

    @Override
    public String toString() {
        return "Visitante{" + getNombre() + " " + getApellido() + "(Email: " + getEmail() + ")}";
    }
}



