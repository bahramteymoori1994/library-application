package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.RoleRequestDto;
import com.example.library.project.dto.responses.RoleResponseDto;
import com.example.library.project.model.entities.Role;
import com.example.library.project.repositories.RoleRepository;
import com.example.library.project.services.interfaces.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final String CACHE_NAME = "roles";

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleResponseDto save(RoleRequestDto roleRequestDto) throws Exception {

        RoleResponseDto roleResponseDto = new RoleResponseDto();
        Role role = new Role();

        roleRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( roleRequestDto == null ){
            throw new Exception("Role request object is null");
        }

        BeanUtils.copyProperties(roleRequestDto, role);

        Role roleSaved = roleRepository.save(role);

        if( roleSaved == null ){
            throw new Exception("Role saved object is null");
        }

        BeanUtils.copyProperties(roleSaved, roleResponseDto);
        return roleResponseDto;
    }

    @Override
    public RoleResponseDto update(RoleRequestDto roleRequestDto) throws Exception {

        RoleResponseDto roleResponseDto = new RoleResponseDto();
        Role role = new Role();

        if( roleRequestDto == null ){
            throw new Exception("Role request object is null");
        }

        BeanUtils.copyProperties(roleRequestDto, role);

        Role roleUpdated = roleRepository.save(role);

        if( roleUpdated == null ){
            throw new Exception("Role updated object is null");
        }

        BeanUtils.copyProperties(roleUpdated, roleResponseDto);
        return roleResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "#id")
    public RoleResponseDto findById(Long id) {

        RoleResponseDto roleResponseDto = new RoleResponseDto();
        Role findRoleById = roleRepository.findById(id).orElse(null);

        BeanUtils.copyProperties(findRoleById, roleResponseDto);
        return roleResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public List<RoleResponseDto> findAll() {

        List<RoleResponseDto> roleResponseDtoList = new ArrayList<>();
        List<Role> findAllRoles = roleRepository.findAll();

        findAllRoles.stream()
                .forEach(role -> {
                    RoleResponseDto roleResponseDto = new RoleResponseDto();
                    BeanUtils.copyProperties(role, roleResponseDto);
                    roleResponseDtoList.add(roleResponseDto);
                });

        return roleResponseDtoList;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public List<RoleResponseDto> searchRoles(String term) {
        List<Role> roles;

        if (term == null || term.trim().isEmpty()) {
            roles = roleRepository.findAll();
        } else {
            roles = roleRepository.findByEnglishRoleTitleContainingIgnoreCaseOrFarsiRoleTitleContainingIgnoreCase(
                    term.trim(), term.trim());
        }

        return roles.stream().map(role -> {
            RoleResponseDto dto = new RoleResponseDto();
            BeanUtils.copyProperties(role, dto);
            return dto;
        }).collect(Collectors.toList());
    }
}