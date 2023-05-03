package org.example;

import org.example.domain.Nota;
import org.example.domain.Pair;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.*;
import org.example.service.Service;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MockTest {

    private final Service service;
    private final TemaRepository temaRepository;
    private final NotaRepository notaRepository;
    private final StudentRepository studentRepository;

    public MockTest() {
        temaRepository = mock(TemaRepository.class);
        notaRepository = mock(NotaRepository.class);
        studentRepository = mock(StudentRepository.class);

        service = new Service(studentRepository, temaRepository, notaRepository);
    }

    @Test
    public void testAddStudentWithValidInputMock(){
        Student ana = new Student("10", "Ana", 934);
        when(studentRepository.save(ana)).thenReturn(ana);
        int result = service.saveStudent("10", "Ana", 934);
        verify(studentRepository).save(ana);
        Assert.assertEquals(1, result);
    }

    @Test
    public void testAddAssignmentAndStudentWithValidInputMock() {
        Student ana = new Student("10", "Ana", 934);
        Tema tema = new Tema("20", "desc", 12, 11);
        when(studentRepository.save(ana)).thenReturn(ana);
        when(temaRepository.save(tema)).thenReturn(tema);

        int result1 = service.saveStudent("10", "Ana", 934);
        int result2 = service.saveTema("20", "desc", 12, 11);

        verify(studentRepository).save(ana);
        verify(temaRepository).save(tema);

        Assert.assertEquals(1, result1);
        Assert.assertEquals(1, result2);
    }

    @Test
    public void testAddStudentAssignmentGradeMock() {
        Student ana = new Student("10", "Ana", 934);
        Tema tema = new Tema("20", "desc", 12, 11);
        Nota nota = new Nota(new Pair<>("10", "20"), 10, 12, "bravo");

        when(studentRepository.save(ana)).thenReturn(ana);
        when(temaRepository.save(tema)).thenReturn(tema);
        when(notaRepository.save(nota)).thenReturn(nota);
        when(studentRepository.findOne("10")).thenReturn(ana);
        when(temaRepository.findOne("20")).thenReturn(tema);

        int result1 = service.saveStudent("10", "Ana", 934);
        int result2 = service.saveTema("20", "desc", 12, 11);
        int result3 = service.saveNota("10", "20", 10, 12, "bravo");

        verify(studentRepository).save(ana);
        verify(temaRepository).save(tema);
        verify(notaRepository).save(nota);

        Assert.assertEquals(1, result1);
        Assert.assertEquals(1, result2);
        Assert.assertEquals(1, result3);
    }

}
