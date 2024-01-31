package com.test.technique.service.impl;
import com.test.technique.dto.UserDTO;
import com.test.technique.dto.UserResponseDTO;
import com.test.technique.entity.User;
import com.test.technique.exception.DuplicateEmailException;
import com.test.technique.exception.InvalidInputException;
import com.test.technique.exception.UserNotFoundException;
import com.test.technique.mapper.UserMapper;
import com.test.technique.repository.UserRepository;

import com.test.technique.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Implementation of the {@link UserService} interface that provides CRUD operations for managing users.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user.
     *
     * @param userDTO The DTO containing user information.
     * @return The created user.
     * @throws InvalidInputException If the provided UserDTO is null.
     */
    @Transactional
    public UserResponseDTO createUser(@Valid UserDTO userDTO) {
        if (userDTO == null) {
            throw new InvalidInputException("Invalid input: UserDTO is null");
        }

        // Verificar si el correo ya está en uso
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateEmailException("Email is already in use");
        }

        User user = UserMapper.INSTANCE.dtoToEntity(userDTO);
        User savedUser = userRepository.save(user);

        return UserMapper.INSTANCE.entityToResponseDTO(savedUser);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return List of users.
     */
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAllOrderedByLastnames();
        return users.stream()
                .map(UserMapper.INSTANCE::entityToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a user by ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The user with the specified ID.
     * @throws UserNotFoundException If no user is found with the given ID.
     */
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long userId) {
        User user = userRepository.findByIdOrderedByLastnames(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        return UserMapper.INSTANCE.entityToResponseDTO(user);
    }

    /**
     * Updates user information.
     *
     * @param userId   The ID of the user to update.
     * @param userDTO  The DTO containing updated user information.
     * @return The updated user.
     * @throws UserNotFoundException If no user is found with the given ID.
     */
    @Transactional
    public UserResponseDTO updateUser(Long userId, @Valid UserDTO userDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        userDTO.setName(existingUser.getName());
        userDTO.setFathersLastname(existingUser.getFathersLastname());
        userDTO.setMothersLastname(existingUser.getMothersLastname());
        User updatedUser = UserMapper.INSTANCE.dtoToEntity(userDTO);
        updatedUser.setId(existingUser.getId());
        User savedUser = userRepository.save(updatedUser);

        return UserMapper.INSTANCE.entityToResponseDTO(savedUser);
    }


    /**
     * Deletes a user by ID.
     *
     * @param userId The ID of the user to delete.
     * @throws UserNotFoundException If no user is found with the given ID.
     */
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete,
                        () -> {
                            throw new UserNotFoundException("User not found with id: " + userId);
                        });
    }
}