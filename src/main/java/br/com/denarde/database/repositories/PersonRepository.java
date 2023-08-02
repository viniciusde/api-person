package br.com.denarde.database.repositories;

import br.com.denarde.database.documents.PersonDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<PersonDocument, String> {
    
}
