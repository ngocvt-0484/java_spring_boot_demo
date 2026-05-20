package ngocvt.local.ngocvt.modules.users.repositories;
import ngocvt.local.ngocvt.modules.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
