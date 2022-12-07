package com.volunteers.controller.admin;

import com.volunteers.util.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {
//    @PostMapping("/upload")
//    public Object upload(MultipartFile img){
//        //获取文件名
//        String fileName = img.getOriginalFilename();
//        //获取文件后缀名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        //重新生成文件名
//        fileName = UUID.randomUUID()+suffixName;
//        //指定本地文件夹存储图片，写到需要保存的目录下
//        String filePath = "D:\\demo\\volunteers\\src\\main\\resources\\static\\lovelist\\images\\";
//        try {
//            //将图片保存到static文件夹里
//            img.transferTo(new File(filePath+fileName));
//            //返回提示信息
//            return new Message(0,"上传成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Message(-1,"上传失败");
//        }
//    }

    @PostMapping("/upload")
    public String UploadPicture(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        if(file.isEmpty()){
            // 这里是我自定义的异常，可省略
//            throw new NotFindException();
        }
        // 上传文件/图像到指定文件夹（这里可以改成你想存放地址的相对路径）
        File savePos = new File("src/main/resources/static/lovelist/images");
        if(!savePos.exists()){  // 不存在，则创建该文件夹
            savePos.mkdir();
        }
        // 获取存放位置的规范路径
        String realPath = savePos.getCanonicalPath();
        // 上传该文件/图像至该文件夹下
        file.transferTo(new File(realPath+"/"+file.getOriginalFilename()));
        model.addAttribute("fileName",file.getOriginalFilename());
        return "/admin/WriteLoveList";
    }

    @PostMapping("/uploadNotice")
    public String UploadNotice(@RequestParam("file") MultipartFile file,Model model) throws IOException {
        if(file.isEmpty()){
            // 这里是我自定义的异常，可省略
//            throw new NotFindException();
        }
        // 上传文件/图像到指定文件夹（这里可以改成你想存放地址的相对路径）
        File savePos = new File("src/main/resources/static/notice/images");
        if(!savePos.exists()){  // 不存在，则创建该文件夹
            savePos.mkdir();
        }
        // 获取存放位置的规范路径
        String realPath = savePos.getCanonicalPath();
        // 上传该文件/图像至该文件夹下
        file.transferTo(new File(realPath+"/"+file.getOriginalFilename()));
        model.addAttribute("fileName",file.getOriginalFilename());
        return "/admin/WriteNotice";
    }

}
