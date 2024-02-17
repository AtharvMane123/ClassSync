package com.class_sync.RecyclerViews;

import androidx.annotation.NonNull;

public class ViewStudentDatabase_RecyclerViewModelClass {
    String s_Class,email,name,rollNo;

    public ViewStudentDatabase_RecyclerViewModelClass() {
    }

    @NonNull
    public String getS_Class() {
        return s_Class;
    }

    public void setClass(String aClass) {
        s_Class = aClass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public ViewStudentDatabase_RecyclerViewModelClass(String aClass, String email, String name, String rollNo) {
        this.s_Class = aClass;
        this.email = email;
        this.name = name;
        this.rollNo = rollNo;
    }
}
