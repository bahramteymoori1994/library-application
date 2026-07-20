package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.UserRequestDto;
import com.example.library.project.dto.responses.UserResponseDto;
import com.example.library.project.dto.views.UserViewResponseDto;
import com.example.library.project.model.entities.User;
import com.example.library.project.model.views.UserView;
import com.example.library.project.repositories.UserRepository;
import com.example.library.project.services.interfaces.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) throws Exception {

        UserResponseDto userResponseDto = new UserResponseDto();
        User user = new User();

        userRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( userRequestDto == null ){
            throw new Exception("User request object is null");
        }

        BeanUtils.copyProperties(userRequestDto, user);

        User userSaved = userRepository.saveAndFlush(user);

        if( userSaved == null ){
            throw new Exception("User saved object is null");
        }

        BeanUtils.copyProperties(userSaved, userResponseDto);
        return userResponseDto;
    }

    @Override
    public UserResponseDto update(UserRequestDto userRequestDto) throws Exception {

        UserResponseDto userResponseDto = new UserResponseDto();
        User user = new User();

        if( userRequestDto == null ){
            throw new Exception("User request object is null");
        }

        userResponseDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        BeanUtils.copyProperties(userRequestDto, user);

        User userUpdated = userRepository.save(user);

        if( userUpdated == null ){
            throw new Exception("User saved object is null");
        }

        BeanUtils.copyProperties(userUpdated, userResponseDto);
        return userResponseDto;
    }

    @Override
    public UserResponseDto findById(Long id) {

        UserResponseDto userResponseDto = new UserResponseDto();
        User findUserById = userRepository.findById(id).orElse(null);

        BeanUtils.copyProperties(findUserById, userResponseDto);
        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> findAll() {

        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        List<User> userList = userRepository.findAll();

        userList.stream()
                .forEach(user -> {
                    UserResponseDto userResponseDto = new UserResponseDto();
                    BeanUtils.copyProperties(user, userResponseDto);
                    userResponseDtoList.add(userResponseDto);
                });

        return userResponseDtoList;
    }

    @Override
    public List<UserViewResponseDto> findUsersView() {
        List<UserView> userList = userRepository.findUsersView();

        System.out.println("=== DEBUG: تعداد رکوردهای UserView = " + userList.size());

        if (!userList.isEmpty()) {
            System.out.println("نمونه اولین رکورد: " + userList.get(0).getPersonFirstName() + " " +
                    userList.get(0).getPersonLastName());
        }

        List<UserViewResponseDto> userResponseDtoList = new ArrayList<>();
        for (UserView user : userList) {
            UserViewResponseDto dto = new UserViewResponseDto();
            BeanUtils.copyProperties(user, dto);
            userResponseDtoList.add(dto);
        }
        return userResponseDtoList;
    }
}