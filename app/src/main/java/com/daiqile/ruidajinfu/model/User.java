package com.daiqile.ruidajinfu.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/9/3.
 */
public class User {
    /**
     * status : 1
     * user : {"username":"apptest","password":"08622aba0c3e6b01d209cea0220c13bb","id":"15"}
     */

    private String status;

    @SerializedName("user")
    private UserInfo user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public static class UserInfo {
        /**
         * username : apptest
         * password : 08622aba0c3e6b01d209cea0220c13bb
         * id : 15
         */

        private String username;
        private String password;
        private int id;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
