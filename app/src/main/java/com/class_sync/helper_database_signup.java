package com.class_sync;

public class helper_database_signup {

    String email,name,password;



    public helper_database_signup(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


}
