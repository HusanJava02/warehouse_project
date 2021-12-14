package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.warehouseproject.entity.AttachmentContent;
import uz.pdp.warehouseproject.entity.Category;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {



}
