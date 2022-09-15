package com.pccwexam.user.domain.service;

import com.pccwexam.user.api.dto.UserDTO;
import com.pccwexam.user.domain.entity.UserDO;
import com.pccwexam.user.domain.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO register(UserDTO userDTO) {
        Optional<UserDO> exist = userRepository.findOneByEmail(userDTO.getEmail());
        if (exist.isPresent()) {
            userDTO.setExist(true);
            return userDTO;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));
        userDO.setCreateTime(new Date());
        userDO.activeUser();
        userDO = userRepository.save(userDO);
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<UserDO> userDOS = userRepository.findAll();
        if (CollectionUtils.isEmpty(userDOS)) {
            return new ArrayList<>();
        }
        return userDOS.stream().map(u -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(u, userDTO);
            return userDTO;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        Optional<UserDO> exist = userRepository.findById(id);
        if (!exist.isPresent()) {
            return null;
        }
        UserDO userDO = exist.get();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        Long id = userDTO.getId();
        if (id == null) {
            return null;
        }
        Optional<UserDO> exist = userRepository.findById(id);
        if (!exist.isPresent()) {
            return null;
        }
        UserDO userDO = exist.get();
        userDO.setNickname(userDTO.getNickname());
        userDO = userRepository.save(userDO);
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        Optional<UserDO> exist = userRepository.findById(id);
        if (!exist.isPresent()) {
            return false;
        }
        UserDO userDO = exist.get();
        userDO.deleteUser();
        userRepository.save(userDO);
        return true;
    }

    @Transactional
    public void deleteUsers(Set<Long> ids) {
        List<UserDO> userDOS = userRepository.findAllById(ids);
        if (!CollectionUtils.isEmpty(userDOS)) {
            userDOS.forEach(UserDO::deleteUser);
            userRepository.saveAll(userDOS);
        }
    }

}
