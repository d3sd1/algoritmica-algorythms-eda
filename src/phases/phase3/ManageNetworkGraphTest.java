/*
 * Copyright (c) 2019.
 * Práctica final Estructuras de Datos y Algoritmos.
 * Creada por Andrei García Cuadra y Rebeca Ariño Olivares.
 * Todos los derechos reservados bajo licencia MIT.
 */

package phases.phase3;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;


public class ManageNetworkGraphTest {

    public static final String[] students = {"fran",
            "luisa", "manu", "ricky", "david",
            "maria", "elena", "juan", "isabel", "pedro", "edu"};

    public static final String[] studentsEmpty = {};

    public static final String[] graphWithAna = {"fran",
            "luisa", "manu", "ricky", "david",
            "maria", "elena", "juan", "isabel", "pedro", "edu", "ana"};

    public static final String[] graphWithoutPedro = {"fran",
            "luisa", "manu", "ricky", "david",
            "maria", "elena", "juan", "isabel", "nostudent", "edu"};

    public static final String[] friendsOfLuisa = {"manu", "isabel"};

    public static final String[] suggestedFriendsOfLuisa = {"ricky", "edu"};

    LinkedList<String> lDirectFriendsEmpty = new LinkedList<String>();

    ManageNetworkGraph graph = new ManageNetworkGraph(students);

    @Before
    public void setUp() throws Exception {

        graph.areFriends("luisa", "manu");
        graph.areFriends("luisa", "isabel");
        graph.areFriends("isabel", "ricky");
        graph.areFriends("pedro", "juan");
        graph.areFriends("ricky", "edu");

    }


    @Test
    public void testAddStudent() {

        graph.addStudent(null);
        assertEquals("Check testaddStudent (null element).", true,
                Arrays.equals(graph.students.toArray(), ManageNetworkGraphTest.students));

        graph.addStudent("ana");
        assertEquals("Check testaddStudent.", true,
                Arrays.equals(graph.students.toArray(), ManageNetworkGraphTest.graphWithAna));

    }

    @Test
    public void testAreFriends() {

        graph.areFriends(null, null);
        assertEquals("Check testAreFriends (null elements).", true,
                Arrays.equals(graph.students.toArray(), ManageNetworkGraphTest.students));

        graph.areFriends(null, "juan");
        assertEquals("Check testAreFriends (first argument null).", true,
                Arrays.equals(graph.students.toArray(), ManageNetworkGraphTest.students));

        graph.areFriends("juan", null);
        assertEquals("Check testAreFriends (second argument null).", true,
                Arrays.equals(graph.students.toArray(), ManageNetworkGraphTest.students));

        graph.areFriends("marcos", "dani");
        assertEquals("Check testAreFriends (students not included in graph).", true,
                Arrays.equals(graph.students.toArray(), ManageNetworkGraphTest.students));


    }

    @Test
    public void testGetDirectFriends() {

        LinkedList<String> lDirectFriends = graph.getDirectFriends(null);
        assertEquals("Check tesGetDirectFriends (null elements).", true,
                Arrays.equals(lDirectFriends.toArray(), lDirectFriendsEmpty.toArray()));

        LinkedList<String> lDirectFriends1 = graph.getDirectFriends("marcos");
        assertEquals("Check tesGetDirectFriends (student not included in graph).", true,
                Arrays.equals(lDirectFriends1.toArray(), ManageNetworkGraphTest.studentsEmpty));

        LinkedList<String> lDirectFriends2 = graph.getDirectFriends("luisa");
        assertEquals("Check tesGetDirectFriends", true,
                Arrays.equals(lDirectFriends2.toArray(), ManageNetworkGraphTest.friendsOfLuisa));

    }

    @Test
    public void testSuggestedFriends() {

        LinkedList<String> lSuggestedFriends = graph.suggestedFriends(null);
        assertEquals("Check testSuggestedFriends (null elements).", true,
                Arrays.equals(lSuggestedFriends.toArray(), lDirectFriendsEmpty.toArray()));

        LinkedList<String> lSuggestedFriends1 = graph.suggestedFriends("marcos");
        assertEquals("Check testSuggestedFriends (student not included in graph).", true,
                Arrays.equals(lSuggestedFriends1.toArray(), ManageNetworkGraphTest.studentsEmpty));

        LinkedList<String> lSuggestedFriends2 = graph.suggestedFriends("luisa");


        System.out.println("direct friends manu: " + Arrays.toString(graph.getDirectFriends("manu").toArray()));
        System.out.println("direct friends isabel: " + Arrays.toString(graph.getDirectFriends("isabel").toArray()));

        System.out.println("DEBERIA SALIR PUES: ricky (no ricky y edu), ya que edu no es un amigo directo, sino de tercer grado por Ricky. Si se llegase a este grado, " +
                "se tendría que cambiar el output igualmente.");

        System.out.println("expected: " + Arrays.toString(lSuggestedFriends2.toArray()));

        System.out.println("got: " + Arrays.toString(ManageNetworkGraphTest.suggestedFriendsOfLuisa));
        assertEquals("Check testSuggestedFriends", true,
                Arrays.equals(lSuggestedFriends2.toArray(), ManageNetworkGraphTest.suggestedFriendsOfLuisa));

    }
}
