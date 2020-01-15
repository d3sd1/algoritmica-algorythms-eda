/*
 * Copyright (c) 2019.
 * Práctica final Estructuras de Datos y Algoritmos.
 * Creada por Andrei García Cuadra y Rebeca Ariño Olivares.
 * Todos los derechos reservados bajo licencia MIT.
 */

package phases.phase2;
import phases.phase1.Student;

public interface IBSTree {

	
	public Student findStudent(String username);
	
	public void insertStudent(Student student);
	
	public void removeStudent(String username);
	
	
	public int getSize();

	public int getHeight();
	
	
}
