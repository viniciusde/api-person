package br.com.denarde.dtos;

import br.com.denarde.constants.PersonType;
import lombok.Builder;
import lombok.Data;
import lombok.With;

@Data
@With
@Builder
public class PersonDTO {

    private String id;
    private String name;
    private PersonType type;

}
