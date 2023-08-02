package br.com.denarde.controllers;

import br.com.denarde.dtos.PersonDTO;
import br.com.denarde.mappers.PersonMapper;
import br.com.denarde.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping("/v1/persons")
    public List<PersonDTO> findAll(){
        return personService.findAll().stream()
                .map(person -> personMapper.documentToDTO(person))
                .collect(Collectors.toList());
    }

    @GetMapping("/v1/persons/{id}")
    public PersonDTO findById(@PathVariable String id){
        return personMapper.documentToDTO(personService.findById(id));
    }

    @PostMapping("/v1/persons")
    public PersonDTO createsNewPerson(@RequestBody PersonDTO personDTO) {
        return personMapper.documentToDTO(personService.createsNewPerson(personDTO));
    }

    @PutMapping("/v1/persons/{id}")
    public PersonDTO updatesPerson(@PathVariable String id, @RequestBody PersonDTO personDTO) {
        return personMapper.documentToDTO(personService.updatesPerson(id, personDTO));
    }

    @DeleteMapping("/v1/persons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletesPerson(@PathVariable String id) {
        personService.deletesPerson(id);
    }

}
