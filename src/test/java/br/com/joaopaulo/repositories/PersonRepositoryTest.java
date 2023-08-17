package br.com.joaopaulo.repositories;

import br.com.joaopaulo.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;
    @DisplayName("test Given Person Object When Save then Returns Saved Person")
    @Test
    void testGivenPersonObject_WhenSave_thenReturnsSavedPerson(){
        //Give
        Person person = new Person("João Paulo", "Bonetti", "Paraíba", "Male", "joao@gmail.com");
        //When
        Person savedPerson = repository.save(person);
        //Then
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
    }

    @DisplayName("test Given Person Object When FindAll then Return Person List")
    @Test
    void testGivenPersonObject_WhenFindAll_thenReturnPersonList(){
        //Give
        Person person = new Person("João Paulo", "Bonetti", "Paraíba", "Male", "joao@gmail.com");
        Person person2 = new Person("Maria Luisa", "Pimentel", "Paraíba", "Female", "maria@gmail.com");

        repository.save(person);
        repository.save(person2);

        //When

        List<Person> personList = repository.findAll();

        //Then
        assertNotNull(personList);
        assertEquals(2, personList.size());
    }

    @DisplayName("test Given Person Object When FindByID then Return Person Object")
    @Test
    void testGivenPersonObject_WhenFindByID_thenReturnPersonObject(){
        //Give
        Person person = new Person("João Paulo", "Bonetti", "Paraíba", "Male", "joao@gmail.com");
        repository.save(person);
        //When
        Person savedPerson = repository.findById(person.getId()).get();
        //Then
        assertNotNull(savedPerson);
        assertEquals(person.getId(), savedPerson.getId());
    }

}