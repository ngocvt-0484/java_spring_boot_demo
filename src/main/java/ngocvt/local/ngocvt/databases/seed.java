package ngocvt.local.ngocvt.databases;
<<<<<<< HEAD
=======
import ngocvt.local.ngocvt.modules.users.repositories.UserRepository;
>>>>>>> 4a8c04b (Add seed fake data)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ngocvt.local.ngocvt.modules.users.entities.User;

@Component
public class seed implements CommandLineRunner {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (isUsersEmpty()) {
            System.out.println("Seeding users table...");
            String passwordEncode = passwordEncoder.encode("123456");

            // Cach 1:
//            entityManager.createNativeQuery(
//                            "INSERT INTO users " +
//                                    "(name, email, password, phone, address, image) " +
//                                    "VALUES (:name, :email, :password, :phone, :address, :image)"
//                    )
//                    .setParameter("name", "admin")
//                    .setParameter("email", "admin@gmail.com")
//                    .setParameter("password", passwordEncode)
//                    .setParameter("phone", "123")
//                    .setParameter("address", "123")
//                    .setParameter("image", "123")
//                    .executeUpdate();
            // Cach 2: Su dung Entity User
//                User user = new User();
//                user.setName("admin2");
//                user.setEmail("admin2@gmail.com");
//                user.setPassword(passwordEncode);
//                user.setPhone("123");
//                user.setAddress("123");
//                user.setImage("123");
//                entityManager.persist(user);
            // Cach 3: Su dung Repository
                User user1 = new User("admin4", "a@gmail.com", passwordEncode, "1", "1", "1");
                User user = new User();
                user.setName("admin3");
                user.setEmail("admin3@gmail.com");;
                user.setPassword(passwordEncode);
                user.setPhone("123");
                user.setAddress("123");
                user.setImage("123");
                userRepository.save(user);
                userRepository.save(user1);
        }

        System.out.println("Seeding database...");
        // Add your seeding logic here
    }

    private boolean isUsersEmpty() {
        Long count = (Long) entityManager.createQuery("SELECT COUNT(id) FROM User").getSingleResult();
        return count == 0;
    }
}
