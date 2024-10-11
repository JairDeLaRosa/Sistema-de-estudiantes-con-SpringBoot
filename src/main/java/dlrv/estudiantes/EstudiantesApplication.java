package dlrv.estudiantes;

import dlrv.estudiantes.modelo.Estudiante;
import dlrv.estudiantes.servicio.EstudianteServicio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {
	@Autowired
	private EstudianteServicio estudianteServicio;
	private static final Logger logger= LoggerFactory.getLogger(EstudiantesApplication.class);
	String nl=System.lineSeparator();
	public static void main(String[] args) {
		logger.info("***Iniciando Aplicación***");
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("***Aplicación Finalizada***");
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scaner=new Scanner(System.in);
		var opcion=0;
		do {
			menu();
			try {
				opcion=Integer.parseInt(scaner.nextLine());
			} catch (Exception e) {
				logger.info("Error: ");
			}
			funcionesAPP(opcion,scaner);
		}while (opcion!=6);
	}
	public void menu(){
		logger.info(nl+"""
                ***Sistema de estudiantes***
                1. Agregar estudiante.
                2. Listar estudiantes.
                3. Buscar estudiante
                4. Editar estudiante.
                5. Eliminar estudiante.
                6. Salir.
                """);
		logger.info("Inserte una opción: ");
	}
	public void funcionesAPP(int opcion, Scanner scanner){
		switch (opcion){
			case 1->{
				var estudiante =new Estudiante();
				logger.info("Inserte nombre: ");
				estudiante.setEstudiante(scanner.nextLine());
				logger.info("Inserte apellido: ");
				estudiante.setApellido(scanner.nextLine());
				logger.info("Inserte telefono: ");
				estudiante.setTelefono(scanner.nextLine());
				logger.info("Inserte email: ");
				estudiante.setEmail(scanner.nextLine());
				estudianteServicio.GuardarEstudiante(estudiante);
				logger.info("Estudiante "+estudiante+nl+"Agregardo Exitosamente!");
			}
			case 2->{
				logger.info(nl+"Listado de estudiantes: ");
				List<Estudiante> estudiantes=estudianteServicio.listarEstudiantes();
				estudiantes.forEach((estudiante->logger.info(estudiante.toString()+nl)));
			}
			case 3->{
				logger.info("Inserte ID del estudiante:");
				var id=Integer.parseInt(scanner.nextLine());
				Estudiante estudiante=estudianteServicio.BuscarEstudiantePorId(id);
				if(estudiante!=null){
					logger.info("Estudiante "+estudiante+nl+"Encontrado");
				}else {
					logger.info("Estudiante no encontrado.");
				}
			}
			case 4->{
				var estudiante =new Estudiante();
				logger.info("Inserte Id del estudiante a actualizar:");
				var id=Integer.parseInt(scanner.nextLine());
				if(estudianteServicio.BuscarEstudiantePorId(id)!=null){
					estudiante.setIdestudiante(id);
					logger.info("Inserte nombre: ");
					estudiante.setEstudiante(scanner.nextLine());
					logger.info("Inserte apellido: ");
					estudiante.setApellido(scanner.nextLine());
					logger.info("Inserte telefono: ");
					estudiante.setTelefono(scanner.nextLine());
					logger.info("Inserte email: ");
					estudiante.setEmail(scanner.nextLine());
					estudianteServicio.GuardarEstudiante(estudiante);
					logger.info("Estudiante "+estudiante+nl+"Actualizado Exitosamente!");
				}else {
					logger.info("El estudiante no existe.");
				}
			}
			case 5->{
				logger.info("Inserte Id del estudiante a eliminar:");
				var id=Integer.parseInt(scanner.nextLine());
				var estudiante=estudianteServicio.BuscarEstudiantePorId(id);
				if (estudiante!=null){
					estudianteServicio.Eliminar(estudiante);
					logger.info("Estudiante "+estudiante+nl+"Ha sido eliminado.");
				}
			}
			case 6->{
				System.out.println("Haste luego...");
			}
			default -> {
				System.out.println("Opcion incorrecta");
			}
		}
	}
}
