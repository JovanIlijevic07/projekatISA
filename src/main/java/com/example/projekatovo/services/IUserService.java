package com.example.projekatovo.services;

import com.example.projekatovo.models.RegisterUserModel;
import com.example.projekatovo.models.UserModel;
import com.example.projekatovo.models.UserPageModel;

import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IUserService {
    List<UserModel> findAll();
    UserPageModel findPagedList(PageRequest pageRequest);
    UserModel create(UserModel model);
    UserModel update(UserModel model);

    void delete(Integer userId);

}