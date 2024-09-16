package org.example;

public class StudentUtil {

    public static double[] calculateGPA(int[] studentIdList, char[][]
            studentsGrades) {
        int listLength = studentIdList.length;
        int subjects;
        int i=0;
        double[] gpa =new double[listLength];
        double gpaTotal;
        for(char[] student:studentsGrades){
            subjects = studentsGrades.length;
            gpaTotal = 0;
            for(char grade:student){
                if(grade == 'A'){
                    gpaTotal +=4;
                }
                else if(grade == 'B'){
                    gpaTotal += 3;
                }
                else if(grade == 'C') {gpaTotal +=2;}
                else {
                    try {
                        throw new MissingGradeException(studentIdList[i]);
                    } catch (MissingGradeException exception) {
                        Logging.print("checked exception: ".concat(String.valueOf(exception.getStackTrace())));
                        throw new InvalidDataException();
                    }
                }
            }
            gpa[i++]=gpaTotal/subjects;
        }
        if(listLength != i)
            throw new IllegalArgumentException("studentIdList & studentsGrades are out-of-sync. studentIdList.length: " + studentIdList.length + ", studentsGrades.length: " + studentsGrades.length);
        return gpa;
    }
    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {


        if (lower > higher) return new int[0];

        double[] gpa = calculateGPA(studentIdList, studentsGrades);
        int n = 0;
        for (double value : gpa) {         // Loop to find the length of the array
            if (value <= higher && value >= lower) {
                n++;
            }
        }
        int[] studentGPA = new int[n];
        int k = 0;
        int i;
        for (i = 0; i < gpa.length; i++) {
            if (gpa[i] <= higher && gpa[i] >= lower) {
                studentGPA[k++] = studentIdList[i];
            }
        }
        return studentGPA;
    }
    public static void main(String[] args){
        int[] studentIdList = {1001, 1002};
        char[][] studentsGrade =  { { 'P', 'A', 'A', 'B' }, { 'A', 'B', 'B' } };
        int[] studentInRange = getStudentsByGPA(3.2, 3.5, studentIdList, studentsGrade);
        if(studentInRange.length > 0){
            for (int student : studentInRange) {
                Logging.print(String.valueOf(student));
            }
        }
        else
            Logging.print("Range invalid");
    }
}
