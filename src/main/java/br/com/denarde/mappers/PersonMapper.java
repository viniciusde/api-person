package br.com.denarde.mappers;

import br.com.denarde.database.documents.PersonDocument;
import br.com.denarde.dtos.PersonDTO;

public interface PersonMapper {

    PersonDocument dtoToDocument(PersonDTO personDTO);
    PersonDTO documentToDTO(PersonDocument personDocument);
}
