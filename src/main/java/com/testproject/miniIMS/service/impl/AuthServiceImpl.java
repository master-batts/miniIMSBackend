package com.testproject.miniIMS.service.impl;

import com.testproject.miniIMS.dto.LoginDto;
import com.testproject.miniIMS.dto.RegisterDto;
import com.testproject.miniIMS.entity.Role;
import com.testproject.miniIMS.entity.User;
import com.testproject.miniIMS.exception.ApiErrorException;
import com.testproject.miniIMS.repository.RoleRepository;
import com.testproject.miniIMS.repository.UserRepository;
import com.testproject.miniIMS.security.JwtTokenProvider;
import com.testproject.miniIMS.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new ApiErrorException(HttpStatus.BAD_REQUEST,"This Username is already taken");
        }
        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ApiErrorException(HttpStatus.BAD_REQUEST,"This Email is already taken");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getUsername());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "new user registered successfully";
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail()
                , loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }
}
