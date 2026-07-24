package com.example.library.project.services.interfaces;

import com.example.library.project.dto.requests.PersonRequestDto;
import com.example.library.project.dto.responses.PersonResponseDto;

import java.util.List;

public interface PersonService extends AbstractBaseService<PersonRequestDto, PersonResponseDto> {

    List<PersonResponseDto> findAllPeopleSpecification(PersonRequestDto personRequestDto);
}