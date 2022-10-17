package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "userGender")
    private String userGender;


    @Column(name = "userNickname", nullable = false, unique = true)
    private String userNickname;

    @Column(nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

//    public User() {
//    }

    public User(String username, String userGender, String userNickname, String password, List<Role> roles) {
        this.password = password;
        this.username = username;
        this.userGender = userGender;
        this.userNickname = userNickname;
        this.roles = roles;
    }

    public User(String username, String userGender, String userNickname, String password) {
        this.password = password;
        this.username = username;
        this.userGender = userGender;
        this.userNickname = userNickname;
    }

    public User(long id, String username, String userGender, String userNickname, String password, String passwordConfirm, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.userGender = userGender;
        this.userNickname = userNickname;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
    }

    public User() {
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public String getRolesString() {
        StringBuilder str = new StringBuilder();
        for (Role role : roles) {
            str.append(role + " ");
        }
        return str.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return this.id;
    }

    public String getUserGender() {
        return this.userGender;
    }

    public String getUserNickname() {
        return this.userNickname;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPasswordConfirm() {
        return this.passwordConfirm;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public String toString() {
        return "User(id=" + this.getId() + ", username=" + this.getUsername() + ", userGender=" + this.getUserGender() + ", userNickname=" + this.getUserNickname() + ", password=" + this.getPassword() + ", passwordConfirm=" + this.getPasswordConfirm() + ", roles=" + this.getRoles() + ")";
    }

    public static class UserBuilder {
        private long id;
        private String username;
        private String userGender;
        private String nickname;
        private String password;
        private String passwordConfirm;
        private List<Role> roles;

        UserBuilder() {
        }

        public UserBuilder id(long id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder userGender(String userGender) {
            this.userGender = userGender;
            return this;
        }

        public UserBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder passwordConfirm(String passwordConfirm) {
            this.passwordConfirm = passwordConfirm;
            return this;
        }

        public UserBuilder roles(List<Role> roles) {
            this.roles = roles;
            return this;
        }

        public User build() {
            return new User(id, username, userGender, nickname, password, passwordConfirm, roles);
        }

        public String toString() {
            return String.format("User.UserBuilder(id=%d, username=%s, userGender=%s, userNickname=%s, password=%s, passwordConfirm=%s, roles=%s)", this.id, this.username, this.userGender, this.nickname, this.password, this.passwordConfirm, this.roles);
        }
    }
}
