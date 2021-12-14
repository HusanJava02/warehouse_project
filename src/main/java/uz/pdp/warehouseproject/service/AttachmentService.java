package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouseproject.entity.Attachment;
import uz.pdp.warehouseproject.entity.AttachmentContent;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.repository.AttachmentContentRepository;
import uz.pdp.warehouseproject.repository.AttachmentRepository;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class AttachmentService {
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public Result saveAttachmentContent(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {

        Iterator<String> filename = multipartHttpServletRequest.getFileNames();

        if (filename.hasNext()) {
            String nameOfFile = filename.next();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(nameOfFile);
            if (multipartFile != null) {
                byte[] bytes = multipartFile.getBytes();
                Attachment attachment = new Attachment(null, multipartFile.getOriginalFilename(), multipartFile.getSize(), multipartFile.getContentType());
                Attachment savedAttachment = attachmentRepository.save(attachment);
                AttachmentContent attachmentContent = new AttachmentContent(null, bytes, savedAttachment);
                attachmentContentRepository.save(attachmentContent);
                return new Result(true, "successfuly added file");
            } else return new Result(false, "not found multipartfile");
        } else return new Result(false, "file name not found");

    }

    public Result deleteAttachment(Integer id) {
        if (attachmentContentRepository.existsById(id)) {
            attachmentContentRepository.deleteById(id);
            attachmentRepository.deleteById(id);
            return new Result(true, "successfully deleted");
        } else return new Result(false, "not found attachment");

    }

    public AttachmentContent getAttachmentContentById(Integer attachmentId) {
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(attachmentId);
        if (optionalAttachmentContent.isPresent()) {
            return optionalAttachmentContent.get();
        }
        return null;
    }


    public void addFileToResponse(Integer id, HttpServletResponse response) {
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(id);
        if (optionalAttachmentContent.isPresent()) {
            AttachmentContent attachmentContent = optionalAttachmentContent.get();
            Attachment attachment = attachmentContent.getAttachment();
            if (attachment != null) {
                response.setHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContentType());
                try (ServletOutputStream outputStream = response.getOutputStream()) {
                    byte[] bytes = attachmentContent.getBytes();
                    FileCopyUtils.copy(bytes,outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public Attachment getInfoAttachment(Integer id) {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            return attachment;
        }else return null;
    }
}
