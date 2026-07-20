package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.PersonRequestDto;
import com.example.library.project.dto.responses.PersonResponseDto;
import com.example.library.project.model.entities.Person;
import com.example.library.project.repositories.PersonRepository;
import com.example.library.project.services.interfaces.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {


    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonResponseDto save(PersonRequestDto personRequestDto) throws Exception {

        PersonResponseDto personResponseDto = new PersonResponseDto();
        Person person = new Person();

        personRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( personRequestDto == null ){
            throw new Exception("Person request object is null");
        }

        BeanUtils.copyProperties(personRequestDto, person);

        Person personSaved = personRepository.save(person);

        if( personSaved == null ){
            throw new Exception("Person saved is null");
        }

        BeanUtils.copyProperties(personSaved, personResponseDto);
        return personResponseDto;
    }

    @Override
    public PersonResponseDto update(PersonRequestDto personRequestDto) throws Exception {

        PersonResponseDto personResponseDto = new PersonResponseDto();
        Person person = new Person();

        if( personRequestDto == null ){
            throw new Exception("Person request object is null");
        }

        BeanUtils.copyProperties(personRequestDto, person);

        Person personUpdated = personRepository.save(person);

        if( personUpdated == null ){
            throw new Exception("Person saved is null");
        }

        BeanUtils.copyProperties(personUpdated, personResponseDto);
        return personResponseDto;
    }

    @Override
    public PersonResponseDto findById(Long id) {

        PersonResponseDto personResponseDto = new PersonResponseDto();
        Person findPersonById = personRepository.findById(id).orElse(null);

        BeanUtils.copyProperties(findPersonById, personResponseDto);
        return personResponseDto;
    }

    @Override
    public List<PersonResponseDto> findAll() {

        List<PersonResponseDto> personResponseDtoList = new ArrayList<>();
        List<Person> findAllPeople = personRepository.findAll();

        findAllPeople.stream()
                .forEach(person -> {
                    PersonResponseDto personResponseDto = new PersonResponseDto();
                    BeanUtils.copyProperties(person, personResponseDto);
                    personResponseDtoList.add(personResponseDto);
                });

        return personResponseDtoList;
    }
}