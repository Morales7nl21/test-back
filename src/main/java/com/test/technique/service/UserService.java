package com.test.technique.service;

import com.test.technique.dto.AddressDTO;
import com.test.technique.dto.UserDTO;
import com.test.technique.dto.UserResponseDTO;
import jakarta.validation.Valid;


import java.util.List;


public interface UserService {
    UserResponseDTO createUser(@Valid UserDTO userDTO);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long userId);
    UserResponseDTO updateUser(Long userId, @Valid UserDTO userDTO);


    UserResponseDTO addAddressToUser(Long userId, @Valid AddressDTO addressDTO);

    void deleteUser(Long userId);
}