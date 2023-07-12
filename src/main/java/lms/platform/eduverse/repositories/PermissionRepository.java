package lms.platform.eduverse.repositories;

import lms.platform.eduverse.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByRole(String role);
}
