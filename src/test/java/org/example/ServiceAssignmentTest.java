package org.example;

import org.example.repository.NotaXMLRepository;
import org.example.repository.StudentXMLRepository;
import org.example.repository.TemaXMLRepository;
import org.example.service.Service;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.junit.Assert;
import org.junit.Test;

public class ServiceAssignmentTest {

    private final Service service;

    public ServiceAssignmentTest() {
        String filenameStudent = "studentitest.xml";
        String filenameTema = "temetest.xml";
        String filenameNota = "notetest.xml";

        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(new StudentValidator(), filenameStudent);
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(new TemaValidator(), filenameTema);
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(new NotaValidator(), filenameNota);
        this.service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void testAddAssignmentWithInvalidId() {
        int result = service.saveTema(null, "desc", 5, 3);

        Assert.assertEquals(0, result);
    }

    @Test
    public void testAddAssignmentWithInvalidDescription() {
        String assignmentId = String.valueOf(Math.round(Math.random() % 1000));
        int result = service.saveTema(assignmentId, null, 5, 3);

        Assert.assertEquals(0, result);
    }

    @Test
    public void testAddAssignmentWithInvalidDeadline() {
        String assignmentId = String.valueOf(Math.round(Math.random() % 1000));
        int result = service.saveTema(assignmentId, "desc", 16, 3);

        Assert.assertEquals(0, result);
    }

    @Test
    public void testAddAssignmentWithInvalidStartline() {
        String assignmentId = String.valueOf(Math.round(Math.random() % 1000));
        int result = service.saveTema(assignmentId, "desc", 14, 0);

        Assert.assertEquals(0, result);
    }

    @Test
    public void testAddAssignmentWithValidInput() {
        String assignmentId = String.valueOf(Math.round(Math.random() % 1000));
        int result = service.saveTema(assignmentId, "desc", 12, 11);

        Assert.assertEquals(1, result);
    }


}
