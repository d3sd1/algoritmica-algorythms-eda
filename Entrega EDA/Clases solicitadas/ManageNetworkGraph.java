/*
 * Copyright (c) 2019.
 * Práctica final Estructuras de Datos y Algoritmos.
 * Creada por Andrei García Cuadra y Rebeca Ariño Olivares.
 * Todos los derechos reservados bajo licencia MIT.
 */

package phases.phase3;

import phases.EDALogger;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class ManageNetworkGraph implements IManageNetworkGraph {


    public LinkedList<String> students;
    LinkedList<LinkedList<Integer>> lst_of_lstAdjacents;

    public static void main(String[] args) {
        // NOT USED
    }

    public ManageNetworkGraph(String[] students) {
        EDALogger.info("Creatings students and base adjacent list -> " + Arrays.toString(students));
        this.students = new LinkedList<>();
        for (int k = 0; k < students.length; k++) {
            this.students.add(students[k]);
        }

        lst_of_lstAdjacents = new LinkedList<>();
        int num = this.students.size();
        for (int i = 0; i < num; i++) {
            lst_of_lstAdjacents.addLast(new LinkedList<>());
        }
    }

    @Override
    public void show() {
        System.out.print("Student names: ");
        for (Iterator<String> i = this.students.iterator(); i.hasNext(); ) {
            String curStudent = i.next();
            System.out.print(curStudent + (i.hasNext() ? ", " : ""));
        }
        System.out.println();
    }

    /**
     * It takes taking one student (email) as input and
     * adds it to social network.
     */
    @Override
    public void addStudent(String student) {
        if (null == student) {
            EDALogger.warning("Student cannot be null.");
            return;
        }
        if (-1 != this.getIndex(student)) {
            EDALogger.warning("Student cannot be duplicated on list.");
            return;
        }
        this.students.addLast(student);
    }

    /**
     * It takes two students (emails) as input and creates a friendship
     * relation between them. Keep in mind that friendship relation is a symmetric relationship.
     *
     * @param studentA
     * @param studentB
     */
    @Override
    public void areFriends(String studentA, String studentB) {

        if (null == studentA || null == studentB) {
            EDALogger.warning("Students cannot be null.");
            return;
        }

        int studentAIdx = this.getIndex(studentA);
        int studentBIdx = this.getIndex(studentB);
        if (-1 == studentAIdx || -1 == studentBIdx) {
            EDALogger.warning("Students has to be pre-added before friending them. Some students were not found on relation: " + studentA + " - " + studentB);
            return;
        }

        /* Check if they were friends already, passing student A or B doesn't matters (but we have to check either on loop.*/
        int[] friends = this.getAdjacents(studentAIdx);
        for (int friendIdx : friends) {
            if (friendIdx == studentBIdx) {
                EDALogger.warning("They were friends previously... " + studentAIdx + " and " + studentBIdx + " (" + studentA + " - " + studentB + ")");
                return;
            }
        }

        EDALogger.info("Added adjacent list (simmetric) between " + studentAIdx + " and " + studentBIdx + " (" + studentA + " - " + studentB + ")");
        this.addEdge(studentAIdx, studentBIdx, false);
    }

    /**
     * This takes a student (email), and returns an object of LinkedList<String>,
     * which contains the emails of his/her direct friends.
     *
     * @param studentA
     * @return
     */
    @Override
    public LinkedList<String> getDirectFriends(String studentA) {
        LinkedList<String> lDirectFriends = new LinkedList<>();
        if (null == studentA) {
            EDALogger.warning("Student cannot be null.");
            return lDirectFriends;
        }
        int studentIdx = this.getIndex(studentA);
        if (-1 == studentIdx) {
            EDALogger.error("Student has to be added before getting direct friends.");
            return lDirectFriends;
        } else {
            EDALogger.info("Student found on ID " + studentIdx);
        }
        LinkedList<Integer> studentsIdx = this.lst_of_lstAdjacents.get(this.getIndex(studentA));
        for (int itStudentIdx : studentsIdx) {
            lDirectFriends.add(this.students.get(itStudentIdx));
        }
        EDALogger.info("Direct friends found " + Arrays.toString(lDirectFriends.toArray()));
        return lDirectFriends;
    }

    @Override
    public LinkedList<Integer> depth(int i) {
        LinkedList<Integer> path = new LinkedList<>();
        return depth(i, new boolean[this.lst_of_lstAdjacents.size()], path);
    }


    @Override
    public LinkedList<Integer> depth(int i, boolean[] visited, LinkedList<Integer> path) {
        LinkedList<Integer> iFriends = this.lst_of_lstAdjacents.get(i);
        path.add(i);
        visited[i] = true;
        for (Integer friendIdx : iFriends) {
            if (visited[friendIdx] == false) {
                depth(friendIdx, visited, path);
            }
        }
        return path;
    }


    @Override
    public LinkedList<String> suggestedFriends(String studentA) {

        LinkedList<String> lSuggestedFriends = new LinkedList<>();
        if (null == studentA) {
            EDALogger.warning("Student cannot be null.");
            return lSuggestedFriends;
        }

        int studentIdx = this.getIndex(studentA);
        if (-1 == studentIdx) {
            EDALogger.error("Student has to be added before getting suggested friends.");
            return lSuggestedFriends;
        } else {
            EDALogger.info("Student found on ID " + studentIdx);
        }
        LinkedList<Integer> depthStudents = this.depth(studentIdx);
        LinkedList<Integer> directFriends = this.lst_of_lstAdjacents.get(studentIdx);
        for (Integer depthStudentId : depthStudents) {
            if (depthStudentId != studentIdx && -1 == directFriends.indexOf(depthStudentId)) {
                lSuggestedFriends.add(this.students.get(depthStudentId));
            }
        }
        EDALogger.info("Suggested friends found " + Arrays.toString(lSuggestedFriends.toArray()));

        return lSuggestedFriends;
    }


    @Override
    public int[] getAdjacents(int studentIdx) {
        LinkedList<Integer> adjacents = new LinkedList<>();
        if (-1 == studentIdx || null == this.lst_of_lstAdjacents.get(studentIdx)) {
            EDALogger.error("Student has to be added before getting adjacents friends.");
            return adjacents.stream().mapToInt(i -> i).toArray();
        }
        adjacents = this.lst_of_lstAdjacents.get(studentIdx);
        return adjacents.stream().mapToInt(i -> i).toArray();
    }


    @Override
    public int getIndex(String student) {
        if (null == student) {
            return -1;
        }
        return this.students.indexOf(student);
    }


    @Override
    public String checkVertex(int i) {
        if (i < 0 || i < this.students.size()) {
            EDALogger.warning("Index out of bounds.");
            return null;
        }
        return this.students.get(i);
    }

    @Override
    public void addEdge(int indexA, int indexB, boolean directed) {

        if (null != this.checkVertex(indexA) || null != this.checkVertex(indexB)) {
            EDALogger.warning("Students index have to be on list.");
            return;
        }
        EDALogger.info("Adding new edge: " + indexA + " - " + indexB);
        if (null == this.lst_of_lstAdjacents.get(indexA)) {
            this.lst_of_lstAdjacents.add(indexA, new LinkedList<>());
        }
        this.lst_of_lstAdjacents.get(indexA).add(indexB);
        if (!directed) {
            if (null == this.lst_of_lstAdjacents.get(indexB)) {
                this.lst_of_lstAdjacents.add(indexB, new LinkedList<>());
            }
            EDALogger.info("Adding simmetric new edge: " + indexB + " - " + indexA);
            this.lst_of_lstAdjacents.get(indexB).add(indexA);
        }
    }
}
