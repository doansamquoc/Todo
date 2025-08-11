package com.learn.Todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.learn.Todo.dto.response.UserResponse;
import com.learn.Todo.entity.User;
import com.learn.Todo.mapper.UserMapper;
import com.learn.Todo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    public void saveOrUpdateUser(OAuth2User oAuth2User) {

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String avatarUrl = oAuth2User.getAttribute("picture");

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setAvatarUrl(avatarUrl);
            userRepository.save(user);
        } else {
            user.setName(name);
            user.setAvatarUrl(avatarUrl);
        }

        userRepository.save(user);
    }

    public UserResponse getCurrentUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userMapper.toUserResponse(user);
    }
}
