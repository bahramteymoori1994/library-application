package com.example.library.project.services.interfaces;

import com.example.library.project.dto.requests.UserRequestDto;
import com.example.library.project.dto.responses.UserResponseDto;
import com.example.library.project.dto.views.UserViewResponseDto;

import java.util.List;

public interface UserService extends AbstractBaseService<UserRequestDto, UserResponseDto>{

    List<UserViewResponseDto> findUsersView();
    UserResponseDto findByUsername(String username) throws Exception;
}