package com.daiqile.test.model;

/**
 * Created by Administrator on 2016/12/2.
 */
public class Register {

    /**
     * user_id : 16
     */

    private String user_id;
    private String status;
    private String message;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Register{" +
                "user_id='" + user_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
