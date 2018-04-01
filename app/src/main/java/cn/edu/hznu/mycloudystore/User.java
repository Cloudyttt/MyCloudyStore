package cn.edu.hznu.mycloudystore;

import java.io.Serializable;

/**
 * Created by Cloudy on 2017/12/8.
 */

public class User implements Serializable {
    private int id;
    private String username;
    private String telephone;
    private String password;

    public User(int id, String username, String telephone, String password) {
        this.id = id;
        this.username = username;
        this.telephone = telephone;
        this.password = password;
    }

    public User(String username, String telephone, String password) {
        this.username = username;
        this.telephone = telephone;
        this.password = password;
    }

    public User(String telephone, String password) {
        this.telephone = telephone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
