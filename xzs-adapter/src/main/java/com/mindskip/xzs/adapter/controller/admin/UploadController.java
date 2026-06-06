package com.mindskip.xzs.adapter.controller.admin;

import com.mindskip.xzs.adapter.controller.BaseController;
import com.mindskip.xzs.exception.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/upload")
public class UploadController extends BaseController {
    @Value("${xzs.upload.file-path:./upload}")
    private String uploadPath;

    @Value("${xzs.upload.url-prefix:/api/upload}")
    private String urlPrefix;

    @PostMapping("/file")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        requireRole(1, 3);
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
        File dest = new File(uploadPath, fileName);
        dest.getParentFile().mkdirs();
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            return Result.fail(1, "上传失败");
        }
        return Result.ok(urlPrefix + "/" + fileName);
    }

    @PostMapping("/image")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file) {
        requireRole(1, 3);
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();
        File dest = new File(uploadPath, fileName);
        dest.getParentFile().mkdirs();
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            return Result.fail(1, "上传失败");
        }
        return Result.ok(urlPrefix + "/" + fileName);
    }
}
