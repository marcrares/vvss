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

public class ServiceTest {

    private final Service service;

    public ServiceTest() {
        String filenameStudent = "studenti.xml";
        String filenameTema = "teme.xml";
        String filenameNota = "note.xml";

        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(new StudentValidator(), filenameStudent);
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(new TemaValidator(), filenameTema);
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(new NotaValidator(), filenameNota);
        this.service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void testAddStudentWithInvalidInput() {
        int result = service.saveStudent(null, "1231", 111);

        Assert.assertEquals(1, result);
    }

    @Test
    public void testAddStudentWithValidInput(){
        String studentId = String.valueOf(Math.random() % 1000);
        int result = service.saveStudent(studentId, "Ana", 934);

        Assert.assertEquals(0, result);
    }
}
