package ru.kata.spring.boot_security.demo.forms;

public class UserCreateApi {
    private long id;
    private String username;
    private String userGender;
    private String userNickname;
    private String password;
    private String roles;

    public UserCreateApi(long id, String username, String userGender, String userNickname, String password, String roles) {
        this.id = id;
        this.username = username;
        this.userGender = userGender;
        this.userNickname = userNickname;
        this.password = password;
        this.roles = roles;
    }

    public UserCreateApi() {
    }

    public static UserFormCreateApiBuilder builder() {
        return new UserFormCreateApiBuilder();
    }

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
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

    public String getRoles() {
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

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String toString() {
        return String.format("UserFormCreateApi(id=%d, username=%s, userGender=%s, userNickname=%s, password=%s, roles=%s)", this.getId(), this.getUsername(), this.getUserGender(), this.getUserNickname(), this.getPassword(), this.getRoles());
    }

    public static class UserFormCreateApiBuilder {
        private long id;
        private String username;
        private String userGender;
        private String userNickname;
        private String password;
        private String roles;

        UserFormCreateApiBuilder() {
        }

        public UserFormCreateApiBuilder id(long id) {
            this.id = id;
            return this;
        }

        public UserFormCreateApiBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserFormCreateApiBuilder userGender(String userGender) {
            this.userGender = userGender;
            return this;
        }

        public UserFormCreateApiBuilder userNickname(String userNickname) {
            this.userNickname = userNickname;
            return this;
        }

        public UserFormCreateApiBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserFormCreateApiBuilder roles(String roles) {
            this.roles = roles;
            return this;
        }

        public UserCreateApi build() {
            return new UserCreateApi(id, username, userGender, userNickname, password, roles);
        }

        public String toString() {
            return String.format("UserFormCreateApi.UserFormCreateApiBuilder(id=%d, username=%s, userGender=%s, userNickname=%s, password=%s, roles=%s)", this.id, this.username, this.userGender, this.userNickname, this.password, this.roles);
        }
    }
}
