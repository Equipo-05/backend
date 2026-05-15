package com.svalero.equipo5.service;

import com.svalero.equipo5.domain.User;
import com.svalero.equipo5.exception.UserNotFoundException;
import com.svalero.equipo5.repository.GrantRepository;
import com.svalero.equipo5.domain.Grant;
import com.svalero.equipo5.dto.in.GrantInDto;
import com.svalero.equipo5.exception.GrantNotFoundException;
import com.svalero.equipo5.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User findUserById(long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }



    public User modifyUser(long id, User user) throws UserNotFoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        modelMapper.map(user, existingUser);
        existingUser.setId(id);
        return  userRepository.save(existingUser);
    }

    public void deleteUser(long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
