package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouseproject.entity.Attachment;
import uz.pdp.warehouseproject.entity.AttachmentContent;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.AttachmentService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/attachment")
public class AttachmentController {


    @Autowired
    AttachmentService attachmentService;

    @GetMapping(value = "/download/{id}")
    public void getAttachment(@PathVariable Integer id,HttpServletResponse response) throws IOException {
        attachmentService.addFileToResponse(id,response);
    }

    @PostMapping("/upload")
    public Result addAttachment(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
       return attachmentService.saveAttachmentContent(multipartHttpServletRequest);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteAttachment(@PathVariable Integer id){
        return attachmentService.deleteAttachment(id);
    }

    @GetMapping("/info/{id}")
    public List<Attachment> getAttachmentInfo(@PathVariable Integer id){
        Attachment infoAttachment = attachmentService.getInfoAttachment(id);
        if (infoAttachment != null){
            return Collections.singletonList(infoAttachment);
        }
        return Collections.emptyList();
    }

}
