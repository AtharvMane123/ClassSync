package com.class_sync;

import androidx.annotation.NonNull;

public class helper_database_signup {

    String email,name,password,StudentClass,RollNO;






    public helper_database_signup(String email, String name, String password,String RollNO,String Class) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.RollNO = RollNO;
        this.StudentClass = Class;



    }
    public String getEmail() {
        return email;
    }

    public String getStudentClass() {
        return StudentClass;
    }
    public String getRollNO() {
        return RollNO;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


}
