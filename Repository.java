package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.sp.main.entity.ContactMessage;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
