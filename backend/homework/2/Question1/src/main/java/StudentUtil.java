import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentUtil {

    private static Logger log = LoggerFactory.getLogger(StudentUtil.class);
    public static double[] calculateGPA(int[] studentIdList, char[][]
            studentsGrades) {
        int n = studentIdList.length;
        int subjects;
        int i=0;
        double[] gpa =new double[n];
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
                else gpaTotal +=2;
            }
            gpa[i++]=gpaTotal/subjects;
        }
        return gpa;
    }
    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {

        //Already given function definition so to use the same function definition formulating the array;
        if (lower > higher) return {};
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
        char[][] studentsGrade =  { { 'A', 'A', 'A', 'B' }, { 'A', 'B', 'B' } };
        log.info("Student GPA Range: ");
        int[] studentInRange = getStudentsByGPA(3.2, 3.5, studentIdList, studentsGrade);
        if(studentInRange.length==0){
            for (int student : studentInRange) {
                log.info("" + student);
            }
        }
        else
            log.info("Range invalid");
        }
    }
}