package ngocvt.local.ngocvt.databases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class seed implements CommandLineRunner {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (isUsersEmpty()) {
            System.out.println("Seeding users table...");
            String passwordEncode = passwordEncoder.encode("123456");
            entityManager.createNativeQuery(
                            "INSERT INTO users " +
                                    "(name, email, password, phone, address, image) " +
                                    "VALUES (:name, :email, :password, :phone, :address, :image)"
                    )
                    .setParameter("name", "admin")
                    .setParameter("email", "admin@gmail.com")
                    .setParameter("password", passwordEncode)
                    .setParameter("phone", "123")
                    .setParameter("address", "123")
                    .setParameter("image", "123")
                    .executeUpdate();

        }

        System.out.println("Seeding database...");
        // Add your seeding logic here
    }

    private boolean isUsersEmpty() {
        Long count = (Long) entityManager.createQuery("SELECT COUNT(id) FROM User").getSingleResult();
        return count == 0;
    }
}
