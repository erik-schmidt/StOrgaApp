package com.group3.backend;

import com.group3.backend.exceptions.MatriculationNumberException;
import com.group3.backend.model.Student;
import com.group3.backend.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
     * testAddingStudent
     * Add one student to repository and check its attributes
     */
    @Test
    void testAddStudent() {
        Student student = createDefaultStudentAndAddToRepo();
        List<Student> studentSet = (List<Student>)studentService.getAllStudents().getBody();
        Assertions.assertFalse(studentSet.isEmpty());
        Student student1 = (Student)studentService.getStudentByNumber(student.getMatrNr()).getBody();
        checkStudentsAreSame(student, student1);
    }

    /**
     * testGetStudent
     * after insert a Student get the student by matriculation number back from db
     */
    @Test
    void testGetStudentByNumber(){
        Student student = createDefaultStudentAndAddToRepo();
        Student student1 = (Student)studentService.getStudentByNumber(student.getMatrNr()).getBody();
        checkStudentsAreSame(student, student1);
    }

    /**
     * testGetAllStudents
     * create a list with students, then read them from database
     * because the list is filled sequenzially we can just compare each student in a row
     */
    @Test
    void testGetAllStudents(){
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
                +" : Matriculation number "+student.getMatrNr()+" already used");
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
        Student student = new Student("202481", "Liyan", "Fu-Wacker",
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