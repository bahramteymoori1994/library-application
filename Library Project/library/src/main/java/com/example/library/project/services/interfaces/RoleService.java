package com.example.library.project.services.interfaces;

import com.example.library.project.dto.requests.RoleRequestDto;
import com.example.library.project.dto.responses.RoleResponseDto;

import java.util.List;

public interface RoleService extends AbstractBaseService<RoleRequestDto, RoleResponseDto>{
    List<RoleResponseDto> searchRoles(String term);
}