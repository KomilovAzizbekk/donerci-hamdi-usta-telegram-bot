package uz.mediasolutions.mdeliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mediasolutions.mdeliveryservice.entity.Role;
import uz.mediasolutions.mdeliveryservice.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);

}
