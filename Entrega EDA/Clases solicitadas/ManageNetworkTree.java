/*
 * Copyright (c) 2019.
 * Práctica final Estructuras de Datos y Algoritmos.
 * Creada por Andrei García Cuadra y Rebeca Ariño Olivares.
 * Todos los derechos reservados bajo licencia MIT.
 */

package phases.phase2;

import phases.EDALogger;
import phases.phase1.Student;
import phases.phase1.StudentsList;

import java.util.*;

public class ManageNetworkTree implements IManageNetworkTree {

    public static void main(String[] args) {
        StudentsList sL1 = new StudentsList();
        StudentsList sL2 = new StudentsList();
        for (int i = 0; i < 30; i++) {
            //sL1.addLast();
        }
    }

    /**
     * It takes an object of the StudentsTree class
     * and an object of the StudentsList class (phase 1),
     * and inserts each student from the list into the tree.
     * The method does not return anything.
     *
     * @param tree
     * @param list
     */
    public void copySocialNetwork(StudentsTree tree, StudentsList list) {
        if (null == tree || null == list) {
            EDALogger.warning("Input tree or list cannot be null.");
            return;
        }
        for (int i = 0; i < list.getSize(); i++) {
            Student student = list.getAt(i);
            EDALogger.info("Inserting student on new social network... " + student);
            tree.insertStudent(student);
        }
    }

    /**
     * This takes an object of the StudentsTree class and returns an object of the StudentsList class
     * containing all the students in the tree ordered by their email.
     * Hint: You can develop an auxiliary and recursive method
     * which takes a BSTNode object and a StudentsList object.
     * You cannot use any sort algorithm to sort the resulting list.
     * To obtain the list, you must traverse the tree (or subtree) in a recursive way.
     *
     * @return
     */
    public StudentsList getOrderedList(StudentsTree tree) {
        StudentsList sL = new StudentsList();
        if (null == tree) {
            EDALogger.warning("Input tree cannot be null.");
        } else {
            this.recursiveOrder(tree.root, sL);
        }
        return sL;
    }


    void recursiveOrder(BSTNode node, StudentsList sL) {

        // Base Case
        if (null == node || null == sL || null == node.oStudent) {
            EDALogger.warning("Input tree, list or student cannot be null.");
            return;
        }

        // If this is the last Node of its level
        boolean inserted = false;
        if (sL.getSize() == 0) {
            EDALogger.info("List size is 0, adding first student: " + node.oStudent.email);
            sL.insertAt(0, node.oStudent);
        }
        for (int i = 0; i < sL.getSize(); i++) {
            if (node.oStudent.email.compareTo(sL.getAt(i).email) <= 0 && sL.getIndexOf(node.oStudent) == -1) {
                EDALogger.info("Adding student sortered (index " + i + "): " + node.oStudent.email);
                sL.insertAt(i, node.oStudent);
                inserted = true;
                break;
            }
        }

        if (!inserted && sL.getIndexOf(node.oStudent) == -1) {
            EDALogger.info("Adding student at the end of the list: " + node.oStudent.email);
            sL.insertAt(sL.getSize(), node.oStudent);
        }

        // Recur for right subtree first, then left subtree
        recursiveOrder(node.right, sL);
        recursiveOrder(node.left, sL);
    }


    /**
     * This class has a parameter n as input and removes all students having a number of blocks equal or greater than n.
     *
     * @param num
     */
    public void deleteByNumberOfBlocks(StudentsTree tree, int num) {
        if (null == tree || num < 0) {
            EDALogger.warning("Input tree cannot be null, and number has to ge breater than 0.");
            return;
        }
        LinkedList<Student> studentsToDelete = this.deleteByNumberOfBlocks(tree.root, num);
        viewTree(tree.root);
        for (Student student : studentsToDelete) {
            EDALogger.info("Removed student: " + student.email);
            tree.removeStudent(student.email);
        }
    }

    /* WRAPPER CLASS */
    public LinkedList<Student> deleteByNumberOfBlocks(BSTNode node, int num) {
        LinkedList<Student> sLToDel = new LinkedList<>();
        if (null == node || num < 0) {
            EDALogger.warning("Input tree cannot be null, and number has to ge breater than 0.");
            return sLToDel;
        }
        this.deleteByNumberOfBlocks(node, num, sLToDel);
        return sLToDel;
    }

    /* RECURSIVE CLASS */
    private void deleteByNumberOfBlocks(BSTNode node, int num, LinkedList<Student> studentsToDelete) {
        /* EXCEPTION CASE */
        if (null == node || num < 0 || null == studentsToDelete) {
            EDALogger.warning("Input tree cannot be null, and number has to ge breater than 0. Even, list cannot be null too.");
            return;
        }
        /* BASE CASE: ADD IF blocks are less than num */
        if (node.oStudent.blocks >= num) {
            EDALogger.info("Deleting user exceding blocks: " + node.oStudent.email);
            studentsToDelete.add(node.oStudent);
        } else {
            EDALogger.info("Keeping user between limit blocks: " + node.oStudent.email);
        }
        /* ITERATE THEM IF CHILDS */
        if (node.left != null) {
            deleteByNumberOfBlocks(node.left, num, studentsToDelete);
        }
        if (node.right != null) {
            deleteByNumberOfBlocks(node.right, num, studentsToDelete);
        }
    }

    /* VIEW TREE: DEBUGGING PURPOSES */
    private List<List<BSTNode>> traverseLevels(BSTNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<BSTNode>> levels = new LinkedList<>();

        Queue<BSTNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            List<BSTNode> level = new ArrayList<>(nodes.size());
            levels.add(level);

            for (BSTNode node : new ArrayList<>(nodes)) {
                level.add(node);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
                nodes.poll();
            }
        }
        return levels;
    }

    void viewTree(BSTNode root) {
        // Check it
        if (root == null)
            return;

        List<List<BSTNode>> levels = traverseLevels(root);

        EDALogger.info("-------------- SHOWING TREE VERTICAL --------------");
        for (List<BSTNode> level : levels) {
            for (BSTNode node : level) {
                System.out.print(node.oStudent.email + " ");
            }
            System.out.println();
        }
        EDALogger.info("-------------- END TREE VERTICAL --------------");
    }
}
