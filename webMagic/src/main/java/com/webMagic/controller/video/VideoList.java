package com.webMagic.controller.video;

import com.webMagic.model.MessageObject;
import com.webMagic.model.VideoModel;
import com.webMagic.service.VideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * api 查询视频信息
 * Created by han on 2017/12/3.
 */
@RestController
@RequestMapping("/api/video")
@Api(value = "VideoList", description="查询视频信息")
public class VideoList {
    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public MessageObject getAllVideos(
            @RequestParam(value = "page", required = false) Integer pages
    ) {
        MessageObject msg;
        if (pages == null) {
            pages = 1;
        }
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pages - 1, 10000, sort);
        try{
            //查詢
            List<VideoModel> videos = new ArrayList<>();
            VideoModel model = new VideoModel();
            model.setTitle("76");
            VideoModel videoModel=videoService.selectByPrimaryKey(1);
            videos.add(videoModel);
            msg = MessageObject.createSuccessMessage(videos);
        }catch (Exception e){
            msg = MessageObject.createErrorMessage();
        }
        return msg;
    }

    @RequestMapping(value = "/save" ,method = RequestMethod.GET)
    public MessageObject save() {
        MessageObject msg;
        try{
            //查詢
            VideoModel model = new VideoModel();
            model.setTitle("76");
            model.setUrl("url");
            model.setAutor("Autor");
            model.setAutorurl("autorurl");
            model.setId(Integer.valueOf(System.currentTimeMillis()%Integer.MAX_VALUE+""));
            model.setViewkey("Viewkey");
            int id =videoService.insert(model);
            msg = MessageObject.createSuccessMessage(id);
        }catch (Exception e){
            e.printStackTrace();
            msg = MessageObject.createErrorMessage();
        }
        return msg;
    }
}
