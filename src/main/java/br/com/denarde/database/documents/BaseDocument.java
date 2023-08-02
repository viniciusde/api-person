package br.com.denarde.database.documents;

import lombok.Data;

@Data
public abstract class BaseDocument {

    private String createdDate;
    private String lastModifiedDate;
}
