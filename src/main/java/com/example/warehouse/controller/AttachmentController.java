package com.example.warehouse.controller;

import com.example.warehouse.entity.Attachment;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/file")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result addFile(MultipartHttpServletRequest request) throws IOException {
        Result result = attachmentService.uploadFileServerService(request);
        return result;
    }

    @GetMapping("/downloadFileDb")
    public void getFileFromDbById(@PathVariable Integer id,HttpServletResponse response) throws IOException {
        attachmentService.getFileFromDbByIdService(id,response);
    }

    @GetMapping("/download/{id}")
    public void getFileById(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.getFileById(id, response);
    }

}
