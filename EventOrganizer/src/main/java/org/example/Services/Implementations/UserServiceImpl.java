package org.example.Services.Implementations;

import org.example.DTOs.UserDTO;
import org.example.Entities.User;
import org.example.Mappers.DTOToEntityMapper;
import org.example.Mappers.EntityToDTOMapper;
import org.example.Repositories.UserRepository;
import org.example.Services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = DTOToEntityMapper.toUser(userDTO);
        user = userRepository.save(user);
        return EntityToDTOMapper.toUserDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return EntityToDTOMapper.toUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(EntityToDTOMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUserName(userDTO.getUserName());
        existingUser.setRole(userDTO.getRole());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        User updatedUser = userRepository.save(existingUser);
        return EntityToDTOMapper.toUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
