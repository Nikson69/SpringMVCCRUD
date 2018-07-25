package ru.nikita.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "login",unique=true)
    private String login;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private String role;
    public User(){

    }
    public User(long id, String name, String login, String password, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        }

    public User(String name, String login, String password) {
        this.setId(-1);
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = "user";
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogin() {
            return login;
        }
    public String getPassword() {
            return password;
        }
    public String getName() {
            return name;
        }
    public long getId() {
            return id;
        }
    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, login, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
