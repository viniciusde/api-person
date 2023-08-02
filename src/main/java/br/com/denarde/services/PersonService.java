package br.com.denarde.services;

import br.com.denarde.database.documents.PersonDocument;
import br.com.denarde.dtos.PersonDTO;

import java.util.List;

public interface PersonService {

    PersonDocument createsNewPerson(PersonDTO personDTO);
    PersonDocument updatesPerson(String id, PersonDTO personDTO);
    void deletesPerson(String id);
    List<PersonDocument> findAll();
    PersonDocument findById(String id);

}
