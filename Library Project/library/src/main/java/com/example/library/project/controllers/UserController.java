package com.example.library.project.controllers;

import com.example.library.project.dto.requests.UserRequestDto;
import com.example.library.project.dto.responses.PersonResponseDto;
import com.example.library.project.dto.responses.UserResponseDto;
import com.example.library.project.dto.views.UserViewResponseDto;
import com.example.library.project.services.interfaces.PersonService;
import com.example.library.project.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PersonService personService;

    public UserController(UserService userService, PersonService personService) {
        this.userService = userService;
        this.personService = personService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserViewResponseDto> users = userService.findUsersView();
        model.addAttribute("users", users);
        model.addAttribute("userDto", new UserRequestDto());
        return "user";
    }

    @PostMapping("/saveUser")
    public String save(@ModelAttribute("userDto") UserRequestDto userRequestDto, RedirectAttributes redirectAttributes){

        try{
            userService.save(userRequestDto);
            redirectAttributes.addFlashAttribute("message", "یوزر با موفقیت ثبت شد.");
        }
        catch(Exception exception){
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + exception.getMessage());
        }

        return "redirect:/user";
    }

    @PutMapping("/updateUser")
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponseDto update(@RequestBody UserRequestDto userRequestDto) throws Exception {
        return userService.update(userRequestDto);
    }

    @GetMapping("/findUserById/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponseDto findById(@PathVariable Long id) throws Exception {
        return userService.findById(id);
    }

    @GetMapping("/findAllUsers")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserResponseDto> findAll() throws Exception {
        return userService.findAll();
    }

    @GetMapping("/findAllPeople")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<PersonResponseDto> findAllPeople(){
        return personService.findAll();
    }
}