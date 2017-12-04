package com.webMagic.service;

import com.github.pagehelper.PageInfo;
import com.webMagic.base.BaseServiceImpl;
import com.webMagic.dao.VideoDao;
import com.webMagic.model.VideoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by han on 2017/12/3.
 */
@Service
public class VideoService extends BaseServiceImpl<VideoModel> {

    @Autowired
    private VideoDao dao;

    @Override
    public Mapper<VideoModel> getMapper() {
        return dao;
    }

    public PageInfo<VideoModel> getDataList(VideoModel record) {
        return super.selectPage(record.getPage(), record.getRows(), record);
    }

}
