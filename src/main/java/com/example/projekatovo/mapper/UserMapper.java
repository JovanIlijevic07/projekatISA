package com.example.projekatovo.mapper;

import com.example.projekatovo.entities.User;
import com.example.projekatovo.models.RegisterUserModel;
import com.example.projekatovo.models.UserModel;
import com.example.projekatovo.models.UserPageModel;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserModel toModel(User user) {
        if (user == null) {
            return null;
        }
        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public static User toEntity(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        User user = new User();
        user.setId(userModel.getId());
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        user.setEmail(userModel.getEmail());
        return user;
    }
    public static User toEntity(RegisterUserModel userModel, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return user;
    }

    public static List<UserModel> toModelList(List<User> users) {
        return users.stream()
                .map(UserMapper::toModel)
                .collect(Collectors.toList());
    }
    public static UserPageModel toModelPagedList(Page<User> pageEntity) {
        return UserPageModel.builder()
                .Users(toModelList(pageEntity.getContent()))
                .totalPages(pageEntity.getTotalPages())
                .totalElements(pageEntity.getTotalElements())
                .build();
    }
}
