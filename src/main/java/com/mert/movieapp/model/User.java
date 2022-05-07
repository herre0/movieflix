package com.mert.movieapp.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends Person {

    private String username;
    private String password;
    private String userType;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Movie> favorites;
    private Date lastLogin;

    public User(int id, String name, String surname, Date birthDate, String username, String password, String userType,
            List<Movie> favorites, Date lastLogin) {
        super(id, name, surname, birthDate);
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.favorites = favorites;
        this.lastLogin = lastLogin;
    }
    
    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Movie> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Movie> favorites) {
        this.favorites = favorites;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    


    


}
