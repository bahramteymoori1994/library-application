package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.UserRequestDto;
import com.example.library.project.dto.responses.UserResponseDto;
import com.example.library.project.dto.views.UserViewResponseDto;
import com.example.library.project.model.entities.Role;
import com.example.library.project.model.entities.User;
import com.example.library.project.model.views.UserView;
import com.example.library.project.repositories.RoleRepository;
import com.example.library.project.repositories.UserRepository;
import com.example.library.project.services.interfaces.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository; // اضافه کردن RoleRepository
    private BCryptPasswordEncoder bCryptPasswordEncoder = new  BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto) throws Exception {

        if (userRequestDto == null) {
            throw new Exception("User request object is null");
        }

        User user = new User();

        // تنظیم تاریخ و زمان
        userRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()))
                .setCreatedBy("admin");

        // کپی properties
        BeanUtils.copyProperties(userRequestDto, user);

        // بارگذاری نقش‌ها از دیتابیس بر اساس IDها
        if (userRequestDto.getRoleIds() != null && !userRequestDto.getRoleIds().isEmpty()) {
            List<Role> roles = roleRepository.findAllById(userRequestDto.getRoleIds());
            user.setRoles(roles);
        }

        User userSaved = userRepository.saveAndFlush(user);

        if (userSaved == null) {
            throw new Exception("User saved object is null");
        }

        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(userSaved, userResponseDto);
        userResponseDto.setRoles(userSaved.getRoles());

        return userResponseDto;
    }

    @Override
    @Transactional
    public UserResponseDto update(UserRequestDto userRequestDto) throws Exception {

        if (userRequestDto == null) {
            throw new Exception("User request object is null");
        }

        // بررسی وجود کاربر
        User existingUser = userRepository.findById(userRequestDto.getUserId())
                .orElseThrow(() -> new Exception("User not found with id: " + userRequestDto.getUserId()));

        // به‌روزرسانی فیلدها
        existingUser.setUsername(userRequestDto.getUsername());
        existingUser.setPassword(userRequestDto.getPassword());
        existingUser.setEmail(userRequestDto.getEmail());
        existingUser.setPerson(userRequestDto.getPerson());

        // به‌روزرسانی نقش‌ها
        if (userRequestDto.getRoleIds() != null) {
            List<Role> roles = roleRepository.findAllById(userRequestDto.getRoleIds());
            existingUser.setRoles(roles);
        }

        User userUpdated = userRepository.save(existingUser);

        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(userUpdated, userResponseDto);
        userResponseDto.setRoles(userUpdated.getRoles());

        return userResponseDto;
    }

    @Override
    public UserResponseDto findById(Long id) {
        UserResponseDto userResponseDto = new UserResponseDto();
        User findUserById = userRepository.findById(id).orElse(null);

        if (findUserById != null) {
            BeanUtils.copyProperties(findUserById, userResponseDto);
            userResponseDto.setRoles(findUserById.getRoles());
        }

        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        List<User> userList = userRepository.findAll();

        userList.forEach(user -> {
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(user, userResponseDto);
            userResponseDto.setRoles(user.getRoles());
            userResponseDtoList.add(userResponseDto);
        });

        return userResponseDtoList;
    }

    @Override
    public List<UserViewResponseDto> findUsersView() {
        List<UserView> userList = userRepository.findUsersView();
        List<UserViewResponseDto> userResponseDtoList = new ArrayList<>();

        for (UserView user : userList) {
            UserViewResponseDto dto = new UserViewResponseDto();
            BeanUtils.copyProperties(user, dto);

            // پردازش rolesFarsi به لیست
            if (user.getRolesFarsi() != null && !user.getRolesFarsi().isEmpty()) {
                // اگر rolesFarsi به صورت comma-separated است
                String[] rolesArray = user.getRolesFarsi().split(",");
                List<String> rolesList = new ArrayList<>();
                for (String role : rolesArray) {
                    String trimmedRole = role.trim();
                    if (!trimmedRole.isEmpty()) {
                        rolesList.add(trimmedRole);
                    }
                }
                dto.setRolesList(rolesList);
            }

            userResponseDtoList.add(dto);
        }

        return userResponseDtoList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return user;
    }

    @Override
    public UserResponseDto findByUsername(String username) throws Exception {

        UserResponseDto userResponseDto = new UserResponseDto();
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }

        BeanUtils.copyProperties(user, userResponseDto);
        userResponseDto.setRoles(user.getRoles());

        return userResponseDto;
    }
}