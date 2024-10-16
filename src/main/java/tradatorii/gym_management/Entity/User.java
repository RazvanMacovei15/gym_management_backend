package tradatorii.gym_management.Entity;
import jakarta.persistence.*;



@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name",nullable = false,unique = true)
    private String name;

    @Column(name="email",nullable=false,unique=true)
    private String email;

    public User(){}
    public User(String name,String email)
    {
        this.name=name;
        this.email=email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
