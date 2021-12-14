package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Attachment;
import uz.pdp.warehouseproject.entity.AttachmentContent;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {

}
