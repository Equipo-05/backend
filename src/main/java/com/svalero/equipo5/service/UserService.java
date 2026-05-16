package com.svalero.equipo5.service;

import com.svalero.equipo5.domain.User;
import com.svalero.equipo5.dto.in.RegisterInDto;
import com.svalero.equipo5.dto.out.UserOutDto;
import com.svalero.equipo5.exception.UserNotFoundException;
import com.svalero.equipo5.repository.GrantRepository;
import com.svalero.equipo5.domain.Grant;
import com.svalero.equipo5.dto.in.GrantInDto;
import com.svalero.equipo5.exception.GrantNotFoundException;
import com.svalero.equipo5.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<UserOutDto> findAllV2() {
        List<User> users = userRepository.findAll();
        List<UserOutDto> useroutDtos = users.stream().map(user -> modelMapper.map(user, UserOutDto.class)).toList();
        return useroutDtos;
    }


    public User findUserById(long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserOutDto findUserByIdV2(long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        UserOutDto  userOutDto = modelMapper.map(user, UserOutDto.class);
        return userOutDto;
    }



    public User modifyUser(long id, RegisterInDto user) throws UserNotFoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        String currentPassword = existingUser.getPassword();

        modelMapper.map(user, existingUser);
        existingUser.setId(id);
        existingUser.setActive(user.isActive());

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            existingUser.setPassword(currentPassword);
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
