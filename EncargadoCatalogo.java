import java.util.List;

public class EncargadoCatalogo extends Usuario {
    private Catalogo catalogo;

    public EncargadoCatalogo(int idUsuario, String nombre, String apellido, String email, String contraseña, Catalogo catalogo) {
        super(idUsuario, nombre, apellido, email, contraseña, "EncargadoCatalogo");
        setCatalogo(catalogo);
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        if (catalogo != null) this.catalogo = catalogo;
        else throw new IllegalArgumentException("El catálogo debe ser una instancia válida de Catalogo.");
    }

    public void registrarObra(ObraArte obra) {
        catalogo.registrarObra(obra);
        System.out.println("Obra '" + obra.getTitulo() + "' registrada correctamente.");
    }

    public void editarObra(int idObra, String titulo, String autor, String periodo, String estado) {
        ObraArte obra = catalogo.buscarObra(idObra);
        if (obra == null) {
            System.out.println("No se encontro ninguna obra con el ID: " + idObra + ".");
        return;
        }
        if (titulo != null) obra.setTitulo(titulo);
        if (autor != null) obra.setAutor(autor);
        if (periodo != null) obra.setPeriodo(periodo);
        if (estado != null) obra.setEstado(estado);
        System.out.println("Obra con ID " + idObra + " editada correctamente.");
    }

    public void eliminarObra(int idObra) {
        ObraArte obra = catalogo.buscarObra(idObra);
        if (obra == null) {
            System.out.println("No se encontro ninguna obra con el ID: " + idObra + ".");
            return;
        }
        catalogo.getObras().remove(obra);
        System.out.println("Obra '" + obra.getTitulo() + "' eliminada correctamente.");
    }

    public void clasificarObra(int idObra, String periodo) {
        ObraArte obra = catalogo.buscarObra(idObra);
        if (obra == null) {
            System.out.println("No se encontro ninguna obra con el ID: " + idObra + ".");
            return;
        }
        obra.setPeriodo(periodo);
        System.out.println("Obra '" + obra.getTitulo() + "' clasificada en el periodo '" + periodo + "'.");
    }

    public void asignarSala(int idObra, Sala sala) {
        ObraArte obra = catalogo.buscarObra(idObra);
        if (obra == null) {
            System.out.println("No se encontro ninguna obra con el ID: " + idObra + ".");
            return;
        }
        sala.asignarObra(obra);
        System.out.println("Obra '" + obra.getTitulo() + "' asignada a la sala '" + sala.getNombre() + "'.");
    }   

    public List<ObraArte> listarObrasPorSala(Sala sala) {
        List<ObraArte> obras = sala.getObras();
        if (obras.isEmpty()) {
            System.out.println("La sala '" + sala.getNombre() + "' no tiene obras asignadas.");
        return obras;
        }
        System.out.println("Obras en la sala '" + sala.getNombre() + "':");
        for (ObraArte o : obras) {
            System.out.println("- " + o.getTitulo() + " por " + o.getAutor());
        }
        return obras;
    }

    @Override
    public String toString() {
        return "EncargadoCatalogo{" + getNombre() + " " + getApellido() + "(ID: " + getIdUsuario() + ")}";
    }
}



