package ngocvt.local.ngocvt.modules.users.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long user_catalogue_id;
    private String name;
    private String email;
    private String password;

    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUserCatalogueId() {
        return user_catalogue_id;
    }

    public void setUserCatalogueId(Long user_catalogue_id) {
        this.user_catalogue_id = user_catalogue_id;
    }


    public String getName() {
        return name;
    }
     public void setName(String name) {
        this.name = name;
    }

}
