package br.edu.infnet.devops.service;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import br.edu.infnet.devops.exceptions.ResourceNotFoundException;
import br.edu.infnet.devops.model.Person;
import br.edu.infnet.devops.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {

        logger.info("Buscar todas as pessoas");

        return repository.findAll();
    }

    public Person findById(Long id) {

        logger.info("Buscar uma pessoa");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));
    }

    public Person create(Person person) {

        logger.info("Criar uma pessoa");

        Optional<Person> savedPerson = repository.findByEmail(person.getEmail());
        if(savedPerson.isPresent()) {
            throw new ResourceNotFoundException(
                    "Já existe uma pessoa com este email: " + person.getEmail());
        }

        return repository.save(person);
    }

    public Person update(Person person) {

        logger.info("Atualizar uma pessoa");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {

        logger.info("Apagar uma pessoa");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));
        repository.delete(entity);
    }
}