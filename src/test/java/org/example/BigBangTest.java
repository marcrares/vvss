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

public class BigBangTest {

    private final Service service;

    public BigBangTest() {
        String filenameStudent = "studentitest.xml";
        String filenameTema = "temetest.xml";
        String filenameNota = "notetest.xml";

        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(new StudentValidator(), filenameStudent);
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(new TemaValidator(), filenameTema);
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(new NotaValidator(), filenameNota);
        this.service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void testAddStudent(){
        int result = service.saveStudent("10", "Ana", 934);

        Assert.assertEquals(1, result);
    }

    @Test
    public void testAddAssignment() {
        String assignmentId = String.valueOf(Math.round(Math.random() % 1000));
        int result = service.saveTema(assignmentId, "desc", 12, 11);

        Assert.assertEquals(1, result);
    }

    @Test
    public void testAddGrade() {
        int result = service.saveNota("1", "1", 10, 12, "bravo");
        Assert.assertEquals(1, result);
    }

    @Test
    public void testAddStudentAssignmentGrade() {
        int result1 = service.saveStudent("10", "Ana", 934);
        int result2 = service.saveTema("10", "desc", 12, 11);
        int result3 = service.saveNota("10", "10", 10, 12, "bravo");
        Assert.assertEquals(1, result1);
        Assert.assertEquals(1, result2);
        Assert.assertEquals(1, result3);
    }

}
