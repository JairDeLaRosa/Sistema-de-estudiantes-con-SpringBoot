package dlrv.estudiantes.servicio;

import dlrv.estudiantes.modelo.Estudiante;

import java.util.List;

public interface IEstudianteServicio {
    public List<Estudiante> listarEstudiantes();
    public Estudiante BuscarEstudiantePorId(Integer IdEstudiante);
    public void GuardarEstudiante(Estudiante estudiante);
    public void Eliminar(Estudiante estudiante);
}
