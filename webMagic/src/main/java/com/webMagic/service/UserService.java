package com.webMagic.service;

import com.webMagic.base.BaseServiceImpl;
import com.webMagic.dao.UserDao;
import com.webMagic.dao.VideoDao;
import com.webMagic.model.UserModel;
import com.webMagic.model.VideoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by han on 2017/12/3.
 */
@Service
public class UserService extends BaseServiceImpl<UserModel> {

    @Autowired
    private UserDao dao;

    @Override
    public Mapper<UserModel> getMapper() {
        return dao;
    }

}
