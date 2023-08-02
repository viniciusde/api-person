package br.com.denarde.services.impl;

import br.com.denarde.constants.PersonType;
import br.com.denarde.database.documents.PersonDocument;
import br.com.denarde.database.repositories.PersonRepository;
import br.com.denarde.dtos.PersonDTO;
import br.com.denarde.exceptions.PersonAlreadyExistsException;
import br.com.denarde.exceptions.PersonNotFoundException;
import br.com.denarde.mappers.PersonMapper;
import br.com.denarde.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonDocument createsNewPerson(PersonDTO personDTO) {
        validatesPersonAlreadyExists(personDTO.getId());
        personDTO = checkAndProcessPersonType(personDTO);
        var person = personMapper.dtoToDocument(personDTO);
        person.setCreatedDate(LocalDateTime.now().toString());
        return personRepository.save(person);
    }

    @Override
    public PersonDocument updatesPerson(String id, PersonDTO personDTO) {
        var person = personRepository.findById(personDTO.getId()).orElseThrow(()-> new PersonNotFoundException(id));
        personDTO = checkAndProcessPersonType(personDTO);
        return personRepository.save(personMapper.dtoToDocument(personDTO));
    }

    @Override
    public void deletesPerson(String id) {
        var person = personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException(id));
        personRepository.delete(person);
    }

    @Override
    public List findAll() {
        return personRepository.findAll();
    }

    @Override
    public PersonDocument findById(String id) {
        return personRepository.findById(id).orElseThrow(()-> new PersonNotFoundException(id));
    }

    private void validatesPersonAlreadyExists(String id) {
        personRepository.findById(id).ifPresent(p -> { throw new PersonAlreadyExistsException(id);});
    }

    private PersonDTO checkAndProcessPersonType(PersonDTO personDTO) {
        if(ObjectUtils.isEmpty(personDTO.getType())) {
            return personDTO.withType(personDTO.getId().length() ==11 ? PersonType.CPF : PersonType.CNPJ);
        }
        return personDTO;
    }
}
