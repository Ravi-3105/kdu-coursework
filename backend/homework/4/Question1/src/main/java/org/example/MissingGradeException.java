package org.example;

public class MissingGradeException extends Exception{
    private int studentId;
    MissingGradeException(int studentId){
        this.studentId = studentId;
    }
    public int getStudentId() {
        return studentId;
    }
}
