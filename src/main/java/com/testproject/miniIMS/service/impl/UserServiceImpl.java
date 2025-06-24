package com.testproject.miniIMS.service.impl;

import com.testproject.miniIMS.dto.UserDto;
import com.testproject.miniIMS.entity.User;
import com.testproject.miniIMS.exception.ResourceNotFoundException;
import com.testproject.miniIMS.repository.UserRepository;
import com.testproject.miniIMS.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String principal = auth.getName();

        User user = userRepository.findByUsernameOrEmail(principal, principal)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username or email: " + principal));

        UserDto dto = modelMapper.map(user, UserDto.class);

        Set<String> roleNames = user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toSet());
        dto.setRoles(roleNames);

        return dto;
    }
    }
