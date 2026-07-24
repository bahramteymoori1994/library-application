package com.example.library.project.controllers;

import com.example.library.project.dto.requests.PersonRequestDto;
import com.example.library.project.dto.responses.PersonResponseDto;
import com.example.library.project.services.interfaces.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getAllPeople(Model model){

        List<PersonResponseDto> persons = findAllPeople();

        model.addAttribute("persons", persons);
        model.addAttribute("personDto", new PersonRequestDto());

        return "person";
    }

    @PostMapping("/savePerson")
    public String savePerson(@ModelAttribute("personDto") PersonRequestDto personRequestDto, RedirectAttributes redirectAttributes) {
        try {
            personService.save(personRequestDto);
            redirectAttributes.addFlashAttribute("message", "شخص با موفقیت ثبت شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + e.getMessage());
        }
        return "redirect:/person";
    }


    @PutMapping("/updatePerson")
    @ResponseStatus(value = HttpStatus.OK)
    public PersonResponseDto update(@RequestBody PersonRequestDto personRequestDto) throws Exception {
        return personService.update(personRequestDto);
    }

    @GetMapping("/findPersonById/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public PersonResponseDto findPersonById(@PathVariable Long id) throws Exception {
        return personService.findById(id);
    }


    @GetMapping("/findAllPeople")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<PersonResponseDto> findAllPeople(){
        return personService.findAll();
    }

    @PostMapping("/findAllPeopleSpecification")
    public List<PersonResponseDto> findAllPeopleSpecification(@RequestBody PersonRequestDto personRequestDto){
        return personService.findAllPeopleSpecification(personRequestDto);
    }
}