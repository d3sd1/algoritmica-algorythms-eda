/*
 * Copyright (c) 2019.
 * Práctica final Estructuras de Datos y Algoritmos.
 * Creada por Andrei García Cuadra y Rebeca Ariño Olivares.
 * Todos los derechos reservados bajo licencia MIT.
 */

package phases.phase1;

import phases.EDALogger;

import java.time.LocalDate;


public class ManageNetworkList implements IManageNetworkList {

    public static void main(String[] args) {
        StudentsList sL1 = new StudentsList();
        StudentsList sL2 = new StudentsList();
        for (int i = 0; i < 30; i++) {
            //sL1.addLast();
        }
    }

    /**
     * auxiliary method to insert student in a sorted way. (BY EMAIL)
     *
     * @param lst
     * @param newStudent
     * @param opc
     */
    public static void sortedInsert(StudentsList lst, Student newStudent, int opc) {

        boolean ascOrder = false;
        switch (opc) {
            case 1:
                /* ASC ORDER */
                ascOrder = true;
                break;

            case 2:
                /* DESC ORDER */
                ascOrder = false;
                break;

            default:
                EDALogger.error("Unrecognized option: " + opc);
                return;
        }
        if (lst == null || lst.header == null) {
            EDALogger.warning("Input list cannot be null.");
            return;
        }

        EDALogger.info("Initializing sorted insert...");
        /*
        Bubble or either algorythm not really needed.
        We just iterate over list, and check student by Student.
        W/O this aux method, it could've been done w/ bubble.
         */
        DNode newNode = new DNode(newStudent);
        DNode aux;
        for (DNode node = lst.header.next; node != lst.trailer; node = node.next) {
            Student checkStudent = node.elem;
            if (checkStudent.email.compareTo(newStudent.email) < 0 && ascOrder ||
                    checkStudent.email.compareTo(newStudent.email) > 0 && !ascOrder) {
                EDALogger.info("Inserted " + checkStudent.email + " in ordered way.");
                aux = node.next;
                node.next = newNode;
                newNode.next = aux;
            }
        }
    }

    /**
     * The methods must join twost and returns a new list containing
     * first the students from the first list followed by the students from the second list.
     *
     * @param lst1
     * @param lst2
     * @return
     */
    public StudentsList union(StudentsList lst1, StudentsList lst2) {
        StudentsList resultList = new StudentsList();

        /*
         * Iterate over union, then add it to result list. It could be StudentsList lst ... w/ iundefined param length
         * but since it's just 2 params, we don't need to worry about redundancy.
         * */
        if (null != lst1) {
            EDALogger.info("Merging list 1...");
            hola(lst1, resultList);
        }
        if (null != lst2) {
            EDALogger.info("Merging list 2...");
            hola(lst2, resultList);
        }

        return resultList;
    }

    private void hola(StudentsList lst1, StudentsList resultList) {
        for (DNode node = lst1.header.next; node != lst1.trailer; node = node.next) {
            if (null != node.elem && resultList.getIndexOf(node.elem) == -1) {
                EDALogger.info("Adding student to merge: " + node.elem.email);
                resultList.addLast(node.elem);
            } else if (null != node.elem) {
                EDALogger.info("Could not add student to merge (duplicated): " + node.elem.email);
            }
        }
    }

    /**
     * 3. This methods takes a social network as input and an integer parameter opc so that:
     * - If opc =1: the method must return a StudentsList containing all the students residing
     * in the same city that the campus where they are studying.
     * - If opc =2: the method must return a StudentsList containing all the students residing
     * in cities different that the one where their campus is located.
     *
     * @param lst
     * @param opc
     * @return
     */
    public StudentsList getCampusCity(StudentsList lst, int opc) {

        StudentsList l = new StudentsList();
        boolean sameCampusNeeded = false;
        switch (opc) {
            case 1:
                sameCampusNeeded = true;
                break;

            case 2:
                sameCampusNeeded = false;
                break;

            default:
                EDALogger.error("Unrecognized option: " + opc);
                return l;
        }
        if (lst == null || lst.header == null) {
            EDALogger.warning("Input list cannot be null.");
            return l;
        }
        for (DNode node = lst.header.next; node != lst.trailer; node = node.next) {
            Student student = node.elem; // Aux var just for clarifying. More info on memory.
            if (student != null &&
                    (student.city.equalsIgnoreCase(student.campus.name()) && sameCampusNeeded ||
                            !student.city.equalsIgnoreCase(student.campus.name()) && !sameCampusNeeded)) {
                l.addLast(student);
            }
        }
        return l;

    }

    /**
     * 4. This method takes a social network as input and a integer parameter opc so that:
     * - If opc=1, the method returns a list of students sorted by ascending
     * order according to their full name.
     * - If opc=2, the method returns a lit of students sorted by descending
     * order according to their full name.
     * Note 1. You must implement your own sort method based on some of the sorted algorithms (such as bubble, sort).
     * Note 2: Remember that you cannot modify or extend the StudentsList class. Therefore, if you need an auxiliary method that help you to sort the list, please include this method into the ManageNetwork class.
     * Note 3. The input list cannot be modified. The method must return a new list where the students are sorted.
     *
     * @param lst
     * @param opc
     * @return
     */
    public StudentsList orderBy(StudentsList lst, int opc) {

        StudentsList sortedList = new StudentsList();
        switch (opc) {
            case 1:
                /* ASC ORDER */
                EDALogger.info("Ordering list ASC...");
                break;

            case 2:
                /* DESC ORDER */
                EDALogger.info("Ordering list DESC...");
                break;

            default:
                EDALogger.error("Unrecognized option: " + opc);
                return lst;
        }
        if (lst == null || lst.header == null) {
            EDALogger.warning("Input list cannot be null.");
            return lst;
        }
        for (DNode node = lst.header.next; node != lst.trailer; node = node.next) {
            sortedInsert(sortedList, node.elem, opc);
        }

        return sortedList;
    }

    /**
     * This methods takes a social network (that is an object of StudentsList class)
     * and a city name as input and returns a list containing all the students
     * (that is, an object of the StudentsList class) who live in that city.
     *
     * @param lst
     * @param city
     * @return
     */
    public StudentsList locateByCity(StudentsList lst, String city) {
        StudentsList l = new StudentsList();
        if (lst == null || lst.header == null) {
            EDALogger.warning("Input list cannot be null.");
            return l;
        }
        for (DNode node = lst.header.next; node != lst.trailer; node = node.next) {
            Student student = node.elem;
            if (student != null && city.equals(student.city)) {
                l.addLast(student);
            } else if (student != null) {
                EDALogger.info("The student does not match wanted city: " + student.email);
            }
        }
        return l;

    }

    /**
     * This takes a social network (an object of the StudentsList class) and two dates
     * and returns the list of all students from the input list
     * whose registration dates are in the interval of input dates.
     * Please, take into account the following comments:
     * - start <= end.
     * - Both dates are included into the interval.
     * - The order in the resulting list must be the same that in the input list.
     *
     * @param lst
     * @param start
     * @param end
     * @return
     */
    public StudentsList getStudentsByDateInterval(StudentsList lst, LocalDate start, LocalDate end) {
        StudentsList resultList = new StudentsList();
        if (lst == null || lst.header == null) {
            EDALogger.warning("Input list cannot be null.");
            return resultList;
        }
        if (start == null || end == null) {
            EDALogger.warning("Input dates cannot be null.");
            return resultList;
        }
        for (DNode node = lst.header.next; node != lst.trailer; node = node.next) {
            if (node.elem != null && node.elem.date_sign_in.compareTo(start) >= 0 && node.elem.date_sign_in.compareTo(end) <= 0) {
                EDALogger.info("Adding user to interval: " + node.elem.email);
                resultList.addLast(node.elem);
            } else if (null != node.elem) {
                EDALogger.info("User does not match the interval: " + node.elem.email);
            }
        }
        return resultList;
    }
}