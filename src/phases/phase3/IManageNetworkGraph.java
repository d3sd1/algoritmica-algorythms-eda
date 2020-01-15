/*
 * Copyright (c) 2019.
 * Práctica final Estructuras de Datos y Algoritmos.
 * Creada por Andrei García Cuadra y Rebeca Ariño Olivares.
 * Todos los derechos reservados bajo licencia MIT.
 */

package phases.phase3;

import java.util.LinkedList;

public interface IManageNetworkGraph {
    int getIndex(String student);

    String checkVertex(int index);

    void addStudent(String student);

    void areFriends(String studentA, String studentB);

    LinkedList<String> getDirectFriends(String studentA);

    int[] getAdjacents(int i);

    LinkedList<String> suggestedFriends(String studentA);

    LinkedList<Integer> depth(int i);

    LinkedList<Integer> depth(int i, boolean[] visited, LinkedList<Integer> path);

    void show();

    void addEdge(int indexA, int indexB, boolean directed);
}