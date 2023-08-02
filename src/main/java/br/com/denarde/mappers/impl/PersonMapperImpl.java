package br.com.denarde.mappers.impl;

import br.com.denarde.database.documents.PersonDocument;
import br.com.denarde.dtos.PersonDTO;
import br.com.denarde.mappers.PersonMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class PersonMapperImpl implements PersonMapper {

    private final ObjectMapper mapper;

    @Override
    public PersonDocument dtoToDocument(PersonDTO personDTO) {
        var person =  mapper.convertValue(personDTO, PersonDocument.class);
        person.setLastModifiedDate(LocalDateTime.now().toString());
        return person;
    }

    @Override
    public PersonDTO documentToDTO(PersonDocument personDocument) {
        return mapper.convertValue(personDocument, PersonDTO.class);
    }
}
