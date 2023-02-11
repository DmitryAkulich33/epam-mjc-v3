package com.epam.service;

import com.epam.dao.UserRepository;
import com.epam.domain.Role;
import com.epam.domain.User;
import com.epam.exception.UserAlreadyExistsException;
import com.epam.model.dto.UserDto;
import com.epam.model.dto.UserToCreate;
import com.epam.service.mapper.UserDtoMapper;
import com.epam.util.PageableUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final RoleService roleService;

    @Override
    @Transactional
    public UserDto createUser(UserToCreate userToCreate) {
        Role role = roleService.getRoleByName("ROLE_USER");
        String login = userToCreate.getLogin().trim();
        checkForDuplicate(login);
        User user = User.builder()
                .login(login)
                .password(userToCreate.getPassword())
//                .password(encodePassword(userToCreate.getPassword()))
                .roles(List.of(role))
                .build();

        return userDtoMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageableUtil.getPageableWithoutSort(pageNumber - 1, pageSize, userRepository.count());

        return userDtoMapper.toUserDtoList(userRepository.findAll(pageable));
    }

    private void checkForDuplicate(String login) {
        userRepository.findUserByLoginIgnoreCase(login).ifPresent(news -> {
            throw new UserAlreadyExistsException("user.exists", login);
        });
    }

//    private String encodePassword(String password) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.encode(password);
//    }
}
