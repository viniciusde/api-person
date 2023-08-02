package br.com.denarde.database.documents;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Document("person")
public class PersonDocument extends BaseDocument{

    private String id;
    private String name;
    private String type;

}
