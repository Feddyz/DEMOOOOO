package com.example.demooooo.service;

import com.example.demooooo.dto.UserDTO;
import org.springframework.stereotype.Service;
@Service
public interface UserService {


        boolean reg(UserDTO userDTO);
        String login(UserDTO userDTO);

}
