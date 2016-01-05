package database.dataSets;

import javax.persistence.*;
import org.jetbrains.annotations.NotNull;
import database.dataSets.PlayerDataSet;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "User")
public class UserDataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;


    /*@OneToOne(mappedBy = "playerId")*/
    @OneToOne @MapsId
    @Cascade(CascadeType.ALL)
    private PlayerDataSet player;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    @NotNull
    private String email;

    public UserDataSet(@NotNull String login, @NotNull String password, @NotNull String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    @NotNull
    public String getEmail() {
        return email;
    }
}
