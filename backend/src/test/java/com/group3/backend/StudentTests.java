package com.group3.backend;

import com.group3.backend.exceptions.Course.CourseWithoutRecommendedSemesterException;
import com.group3.backend.exceptions.StudentNameException;
import com.group3.backend.model.Student;
import com.group3.backend.service.StudentService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@SpringBootTest
class StudentTests {

    @Autowired
    private StudentService studentService;
    private Random random = new Random();

    /**
     * deleteStudents
     * delete all students out of repository for a clean test
     */
    @BeforeEach
    void deleteStudents(){
        if(studentService.getAllStudents().getStatusCode().equals(HttpStatus.OK)){
            List<Student> studentSet = (List<Student>)studentService.getAllStudents().getBody();
            for(Student student : studentSet){
                studentService.deleteStudent(student.getMatrNr());
            }
        }
    }

    /**
     * deleteStudentsAfter
     * After every test clean the db
     */
    @AfterEach
    void deleteStudentsAfter(){
        if(studentService.getAllStudents().getStatusCode().equals(HttpStatus.OK)){
            List<Student> studentSet = (List<Student>)studentService.getAllStudents().getBody();
            for(Student student : studentSet){
                studentService.deleteStudent(student.getMatrNr());
            }
        }
    }

    /**
     * testAddingStudent
     * Add one student to repository and check its attributes
     */
    @Test
    void testCreateStudent() {
        Student student = getNeverUsedStudentObject();
        String oldMatrNr = student.getMatrNr();
        //check to short matriculation number exception
        student.setMatrNr(oldMatrNr.substring(0,4));
        Assertions.assertEquals(studentService.createStudent(student).getBody(),
                MatriculationNumberException.class + " Matriculation Number has not the right length. " +
                        "It must be exactly 6 units long. Only numbers are allowed");
        //check to long matriculation number exception
        student.setMatrNr(oldMatrNr+"0");
        Assertions.assertEquals(studentService.createStudent(student).getBody(),
                MatriculationNumberException.class + " Matriculation Number has not the right length. " +
                        "It must be exactly 6 units long. Only numbers are allowed");
        //check letters in matriculation number exception
        student.setMatrNr(oldMatrNr.substring(0,5)+"A");
        Assertions.assertEquals(studentService.createStudent(student).getBody(),
                MatriculationNumberException.class + " In matricular number are no letters allowed. " +
                        " Take care of the allowed length of 6 units");
        //set right matriculation number again
        student.setMatrNr(oldMatrNr);

        //check name not to small exception
        student.setStudentPrename("A");
        Assertions.assertEquals(studentService.createStudent(student).getBody(),
                StudentNameException.class +  " Prename must be between 2 an 50 letters");
        //check name not to big exception
        student.setStudentPrename("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Assertions.assertEquals(studentService.createStudent(student).getBody(),
                StudentNameException.class +  " Prename must be between 2 an 50 letters");
        //check that no numbers are in prename
        student.setStudentPrename("Max 1");
        Assertions.assertEquals(
                StudentNameException.class +  " Numbers are not allowed in Prename", studentService.createStudent(student).getBody());
        student.setStudentPrename("Max");
        //check name not to small exception
        student.setStudentFamilyname("A");
        Assertions.assertEquals(studentService.createStudent(student).getBody(),
                StudentNameException.class +  " Familyname must be between 2 an 50 letters");
        //check name not to big exception
        student.setStudentFamilyname("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Assertions.assertEquals(studentService.createStudent(student).getBody(),
                StudentNameException.class +  " Familyname must be between 2 an 50 letters");
        //check that no numbers are in prename
        student.setStudentFamilyname("Musterm1ann");
        Assertions.assertEquals(
                StudentNameException.class +  " Numbers are not allowed in Familyname", studentService.createStudent(student).getBody());
        student.setStudentFamilyname("Mustermann");

        //check current semester is greater than 1
        student.setCurrentSemester(0);
        Assertions.assertEquals(studentService.createStudent(student).getBody(),
                CourseWithoutRecommendedSemesterException.class +  " Semester can not be smaller then 1 and not be bigger than 15");
        //check current semester is lowert than 15
        student.setCurrentSemester(16);
        Assertions.assertEquals(studentService.createStudent(student).getBody(),
                CourseWithoutRecommendedSemesterException.class +  " Semester can not be smaller then 1 and not be bigger than 15");
        student.setCurrentSemester(1);

        //check successful create of valid student
        Student student1 = createDefaultStudentAndAddToRepo();
        List<Student> studentSet = (List<Student>)studentService.getAllStudents().getBody();
        Assertions.assertFalse(studentSet.isEmpty());
        Student student2 = (Student)studentService.getStudentByNumber(student1.getMatrNr()).getBody();
        checkStudentsAreSame(student1, student2);
    }

    /**
     * testGetStudent
     * after insert a Student get the student by matriculation number back from db
     */
    @Test
    void testGetStudentByNumber(){
        Student student = getNeverUsedStudentObject();
        String oldMatrNr = student.getMatrNr();
        //check to short matriculation number exception
        student.setMatrNr(oldMatrNr.substring(0,4));
        Assertions.assertEquals(studentService.getStudentByNumber(student.getMatrNr()).getBody(),
                MatriculationNumberException.class + " Matriculation Number has not the right length. " +
                "It must be exactly 6 units long. Only numbers are allowed");
        //check to long matriculation number exception
        student.setMatrNr(oldMatrNr+"0");
        Assertions.assertEquals(studentService.getStudentByNumber(student.getMatrNr()).getBody(),
                MatriculationNumberException.class + " Matriculation Number has not the right length. " +
                        "It must be exactly 6 units long. Only numbers are allowed");
        //check letters in matriculation number exception
        student.setMatrNr(oldMatrNr.substring(0,5)+"A");
        Assertions.assertEquals(studentService.getStudentByNumber(student.getMatrNr()).getBody(),
                MatriculationNumberException.class + " In matricular number are no letters allowed. " +
                        " Take care of the allowed length of 6 units");
        //set right matriculation number again
        student.setMatrNr(oldMatrNr);
        //check read an matriculation number which not exists Exception
        Assertions.assertEquals(MatriculationNumberException.class + " There is no student with matriculation number: "
                + student.getMatrNr(), studentService.getStudentByNumber(student.getMatrNr()).getBody());
        //check successful read
        Student student1 = createDefaultStudentAndAddToRepo();
        Student student2 = (Student)studentService.getStudentByNumber(student1.getMatrNr()).getBody();
        checkStudentsAreSame(student1, student2);
    }

    /**
     * testGetAllStudents
     * create a list with students, then read them from database
     * because the list is filled sequenzially we can just compare each student in a row
     */
    @Test
    void testGetAllStudents(){
        //check error message read empty db
        Assertions.assertEquals(studentService.getAllStudents().getBody(),"Error: There are no students saved");
        List<Student> studentList = createDefaultStudentsAndAddToRepo();
        List<Student> studentList1 = (List<Student>)studentService.getAllStudents().getBody();
        for(int i = 0;  i < studentList.size(); i++){
            checkStudentsAreSame(studentList.get(i),studentList1.get(i));
        }
    }

    /**
     * testUpdateStudent()
     * check that a student can be updated if the fiven matriculation number is already saved in db
     * all attributes except matriculation number can be successfully changed
     */
    @Test
    void testUpdateStudent(){
        Student studentNu = getNeverUsedStudentObject();
        //check read an matriculation number which not exists Exception
        Assertions.assertEquals(MatriculationNumberException.class + " There is no student with matriculation number: "
                + studentNu.getMatrNr(), studentService.updateStudent(studentNu).getBody());
        Student studentU = createDefaultStudentAndAddToRepo();
        //check name not to small exception
        studentU.setStudentPrename("A");
        Assertions.assertEquals(studentService.updateStudent(studentU).getBody(),
                StudentNameException.class +  " Prename must be between 2 an 50 letters");
        //check name not to big exception
        studentU.setStudentPrename("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Assertions.assertEquals(studentService.updateStudent(studentU).getBody(),
                StudentNameException.class +  " Prename must be between 2 an 50 letters");
        //check that no numbers are in prename
        studentU.setStudentPrename("Max 1");
        Assertions.assertEquals(
                StudentNameException.class +  " Numbers are not allowed in Prename", studentService.updateStudent(studentU).getBody());
        studentU.setStudentPrename("Max");
        //check name not to small exception
        studentU.setStudentFamilyname("A");
        Assertions.assertEquals(studentService.updateStudent(studentU).getBody(),
                StudentNameException.class +  " Familyname must be between 2 an 50 letters");
        //check name not to big exception
        studentU.setStudentFamilyname("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Assertions.assertEquals(studentService.updateStudent(studentU).getBody(),
                StudentNameException.class +  " Familyname must be between 2 an 50 letters");
        //check that no numbers are in prename
        studentU.setStudentFamilyname("Musterm1ann");
        Assertions.assertEquals(
                StudentNameException.class +  " Numbers are not allowed in Familyname", studentService.updateStudent(studentU).getBody());
        studentU.setStudentFamilyname("Mustermann");
        //check current semester is greater than 1
        studentU.setCurrentSemester(0);
        Assertions.assertEquals(studentService.updateStudent(studentU).getBody(),
                CourseWithoutRecommendedSemesterException.class +  " Semester can not be smaller then 1 and not be bigger than 15");
        //check current semester is lowert than 15
        studentU.setCurrentSemester(16);
        Assertions.assertEquals(studentService.updateStudent(studentU).getBody(),
                CourseWithoutRecommendedSemesterException.class +  " Semester can not be smaller then 1 and not be bigger than 15");
        studentU.setCurrentSemester(1);

        List<Student> studentListBeforeUpdate = createDefaultStudentsAndAddToRepo();
        //Student which should be updated;
        Student student = studentListBeforeUpdate.get(random.nextInt(studentListBeforeUpdate.size()));
        Student updateInformationStudent = getNeverUsedStudentObject();
        //check that the matriculation number can not be found in update
        Assertions.assertEquals(studentService.updateStudent(updateInformationStudent).getBody(),
                MatriculationNumberException. class +" There is no student with matriculation number: "
                        + updateInformationStudent.getMatrNr());
        //Set matriculation number of old student to new sutdnet object for successful update
        updateInformationStudent.setMatrNr(student.getMatrNr());
        studentService.updateStudent(updateInformationStudent);
        Student updatedStudent = (Student)studentService.getStudentByNumber(updateInformationStudent.getMatrNr()).getBody();
        //checkIfNewInformations except matriculation number is inside;
        checkStudentsAreSame(updatedStudent, updateInformationStudent);
    }

    /**
     * testDeleteStudent
     * test that deletion by matriculation number is successfull
     * for this load a few students in the database and check if after deletion is one item less in database
     * and check that after deletion you can not find the matriculation number of the deleted student in the
     * database anymore
     */
    @Test
    void testDeleteStudent(){
        Student studentNu = getNeverUsedStudentObject();
        //check read an matriculation number which not exists Exception
        Assertions.assertEquals(MatriculationNumberException.class + " There is no student with matriculation number: "
                + studentNu.getMatrNr(), studentService.deleteStudent(studentNu.getMatrNr()).getBody());
        List<Student> studentListBeforeDelete = createDefaultStudentsAndAddToRepo();
        Student student = studentListBeforeDelete.get(random.nextInt(studentListBeforeDelete.size()));
        studentService.deleteStudent(student.getMatrNr());
        List<Student> studentListAfterDelete = (List<Student>) studentService.getAllStudents().getBody();
        //check that the list has now one item less
        Assertions.assertEquals(studentListBeforeDelete.size()-1, studentListAfterDelete.size());
        //check that you can not find student in db anymore
        Assertions.assertEquals(studentService.getStudentByNumber(student.getMatrNr()).getBody(),
                MatriculationNumberException.class + " There is no student with matriculation number: " + student.getMatrNr());
    }

    /**
     * testDoubleInsertionOfStudent
     * test that you can not insert a new student with the same matriculation number than an exisiting one
     */
    @Test
    void testDoubleInsertionOfStudent(){
        Student student = createDefaultStudentAndAddToRepo();
        Assertions.assertEquals(studentService.createStudent(student).getBody(), MatriculationNumberException.class
                +" Matriculation number "+student.getMatrNr()+" already used");
    }

    /**
     * createDefaultStudentsAndAddToRepo()
     * create a list of default students adn insert into the database
     * @return List<Student>
     */
    private List<Student> createDefaultStudentsAndAddToRepo(){
        Student student0 = new Student("202480", "Liyan", "Fu-Wacker",
                "AIB", 7);
        Student student1 = new Student("202481", "Chris", "Wacker",
                "AIB", 4);
        Student student2 = new Student("202482", "Max", "Mustermann",
                "AIB", 2);
        studentService.createStudent(student0);
        studentService.createStudent(student1);
        studentService.createStudent(student2);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student0);
        studentList.add(student1);
        studentList.add(student2);
        return studentList;
    }

    /**
     * createDefaultStudentAndAttToRepo
     * create a default student object and save it in database
     * @return Student
     */
    private Student createDefaultStudentAndAddToRepo(){
        Student student = new Student("202479", "John", "Doe",
                "AIB", 7);
        studentService.createStudent(student);
        return student;
    }

    /**
     * getNeverUsedStudentObject
     * return a studten which is shure never used in database
     * @return
     */
    private Student getNeverUsedStudentObject(){
        Student student = new Student("100000", "Fritz", "Mueller",
                "AIB", 1);
        return student;
    }

    /**
     * checkStudentsAreSame
     * check if the given Students have exactly the same attributes
     * Because Matriculation number is unique, we can check if we get the same object than we saved
     * @param student
     * @param student1
     */
    private void checkStudentsAreSame(Student student, Student student1){
        Assertions.assertEquals(student1.getMatrNr(), student.getMatrNr());
        Assertions.assertEquals(student1.getStudentPrename(), student.getStudentPrename());
        Assertions.assertEquals(student1.getStudentFamilyname(), student.getStudentFamilyname());
        Assertions.assertEquals(student1.getFieldOfStudy(), student.getFieldOfStudy());
        Assertions.assertEquals(student1.getCurrentSemester(), student.getCurrentSemester());
    }
}