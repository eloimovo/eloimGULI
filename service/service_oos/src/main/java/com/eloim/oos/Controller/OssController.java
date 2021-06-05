package com.eloim.oos.Controller;

import com.eloim.commonutils.Result;
import com.eloim.oos.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/oss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);

        //返回r对象
        return Result.ok().data("url",url).message("文件上传成功");
    }
}
