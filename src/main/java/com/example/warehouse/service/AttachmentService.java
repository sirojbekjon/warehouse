package com.example.warehouse.service;

import com.example.warehouse.entity.Attachment;
import com.example.warehouse.entity.AttachmentContent;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.AttachmentContentRepository;
import com.example.warehouse.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;


    public Result uploadFIleDb(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
       //fileni bazaga saqlash holati
        if (file!=null){
            Attachment attachment = new Attachment();
            attachment.setName(file.getName());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            attachment.setFileOriginalName(file.getOriginalFilename());
            attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setBytes(file.getBytes());
            attachmentContentRepository.save(attachmentContent);

            return new Result("file saved",true);
        }
            return new Result("file not saved",false);
    }




    public void getFileFromDbByIdService(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){

            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);

            if (contentOptional.isPresent()){
                AttachmentContent attachmentContent = contentOptional.get();

                response.setHeader("Content-Disposition","attachment; filename=\""+attachment.getFileOriginalName()+"\"");
                response.setContentType(attachment.getContentType());

                FileCopyUtils.copy(attachmentContent.getBytes(),response.getOutputStream());

            }

        }

    }


    //fileni serverga saqlash holati
    private final static String uploadDirectory="Upload";

    public Result uploadFileServerService(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        if (!fileNames.hasNext())
            return new Result("file not found",false);
        else
        while (fileNames.hasNext()) {
        MultipartFile file = request.getFile(fileNames.next());
        if (file!=null){
            Attachment attachment1 = new Attachment();
            attachment1.setFileOriginalName(file.getOriginalFilename());
            attachment1.setSize(file.getSize());
            attachment1.setContentType(file.getContentType());

            String orginalName = file.getOriginalFilename();
            String[] split = orginalName.split("\\.");
            String name = UUID.randomUUID().toString()+"."+split[split.length-1];
            attachment1.setName(name);
            attachmentRepository.save(attachment1);

            Path path = Paths.get(uploadDirectory+"/"+name);
            Files.copy(file.getInputStream(),path);

        }
        }
            return new Result("Files saved",true);
    }




    public void getFileById(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            response.setHeader("Content-Disposition","attachment;filename=\""+attachment.getFileOriginalName()+"\"");
            response.setContentType(attachment.getContentType());
            FileInputStream fileInputStream = new FileInputStream(uploadDirectory+"/"+attachment.getName());
            FileCopyUtils.copy(fileInputStream,response.getOutputStream());
        }
//            return optionalAttachment;

    }






}
