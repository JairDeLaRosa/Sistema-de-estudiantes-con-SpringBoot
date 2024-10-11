package dlrv.estudiantes.servicio;

import dlrv.estudiantes.modelo.Estudiante;
import dlrv.estudiantes.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstudianteServicio implements IEstudianteServicio{

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes=estudianteRepositorio.findAll();
        return estudiantes;
    }

    @Override
    public Estudiante BuscarEstudiantePorId(Integer IdEstudiante) {
        Estudiante estudiante=estudianteRepositorio.findById(IdEstudiante).orElse(null);
        return estudiante;
    }

    @Override
    public void GuardarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.save(estudiante);
    }

    @Override
    public void Eliminar(Estudiante estudiante) {
        estudianteRepositorio.delete(estudiante);
    }
}
