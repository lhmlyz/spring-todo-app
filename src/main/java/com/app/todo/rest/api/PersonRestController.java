package com.app.todo.rest.api;

import com.app.todo.dto.AddressMapper;
import com.app.todo.dto.PersonDto;
import com.app.todo.dto.PersonMapper;
import com.app.todo.entity.Address;
import com.app.todo.entity.Person;
import com.app.todo.repository.jpa.AddressRepository;
import com.app.todo.repository.jpa.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonRestController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonRestController(PersonRepository personRepository ) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<PersonDto> personList() {
        List<PersonDto> personDtoList = new ArrayList<>();
        personRepository.findAll().forEach(person -> {
            personDtoList.add(PersonMapper.INSTANCE.personToDto(person));
        });
        return personDtoList;
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable(name = "id") Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            return optionalPerson.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found");
        }
    }

    @GetMapping("/page")
    public Page<PersonDto> personListAll(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortColumn", defaultValue = "id") String sortColumn,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection

    ) {
        PageRequest pageRequest = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.Direction.fromString(sortDirection),
                sortColumn);
        return personRepository.findAll(pageRequest).map(PersonMapper.INSTANCE::personToDto);

    }

    @PostMapping("/")
    public PersonDto addPerson(@RequestBody PersonDto personDto) {
        Person person = PersonMapper.INSTANCE.dtoToPerson(personDto);
        person.getAddress().setPerson(person);
        personRepository.save(person);
        return PersonMapper.INSTANCE.personToDto(person);
    }

    @PutMapping("/{id}")
    public PersonDto updatePerson(@PathVariable(name = "id") Long id, @RequestBody PersonDto personDto) {
        Person person = PersonMapper.INSTANCE.dtoToPerson(personDto);
        person.setId(id);
        personRepository.save(person);
        return PersonMapper.INSTANCE.personToDto(person);
    }

    @DeleteMapping("/{id}")
    public Person deletePerson(@PathVariable(name = "id") Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            personRepository.deleteById(id);
            return optionalPerson.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not found");
        }
    }
}
