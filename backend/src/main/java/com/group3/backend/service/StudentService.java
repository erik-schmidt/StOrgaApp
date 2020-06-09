package com.group3.backend.service;

import com.group3.backend.exceptions.CheckMatrNrClass;
import com.group3.backend.exceptions.Course.CourseWithoutRecommendedSemesterException;
import com.group3.backend.exceptions.MatrNrWrongLengthException;
import com.group3.backend.exceptions.MatrNrWrongSyntaxException;
import com.group3.backend.exceptions.Student.StudentDoesntMatchToMatrNrException;
import com.group3.backend.exceptions.Student.StudentMatrNrIsAlreadyUsedException;
import com.group3.backend.model.Student;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.GradeCourseMappingRepository;
import com.group3.backend.repository.StudentRepository;
import com.group3.backend.security.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class StudentService extends CheckMatrNrClass {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private GradeCourseMappingRepository gradeCourseMappingRepository;
    private Logger logger = LoggerFactory.getLogger(StudentService.class);
    private PasswordEncoder passwordEncoder;
    private JwtTokenService jwtTokenService;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository,
                          GradeCourseMappingRepository gradeCourseMappingRepository, PasswordEncoder passwordEncoder,
                          JwtTokenService jwtTokenService) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.gradeCourseMappingRepository = gradeCourseMappingRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    /**
     * Is used to test the reachability of the service.
     * Called by "/ping".
     * @return
     *          "reachable" to represent that the service can be reached.
     */
    public String ping(){
        return "reachable";
    }

    /**
     * Is used to get all {@link Student} objects from the repository.
     * Is called by "/get".
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a list of
     *          the {@link Student} objects in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> getAllStudents(){
        try{
            List<Student> studentList = studentRepository.findAll();
            if(studentList.isEmpty()){
                logger.error("Error while reading all Students: There are no students saved");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There are no students saved");
            }
            logger.info("Students successfully read");
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    /**
     * Is used to get a specific {@link Student} from the repository.
     * Is called by "/{matNr}/get".
     * @param matNr
     *              The matrNr of the {@link Student} you want to get.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          {@link Student} objects in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> getStudentByNumber(String matNr){
        try{
            checkMatriculationNumber(matNr);
            if(checkMatricularNumberIsFree(matNr)){
                throw new StudentDoesntMatchToMatrNrException("There is no student with matriculation number: " + matNr);
            }
            Student st = studentRepository.findByMatrNr(matNr);
            logger.info("Student: " + matNr + " successfully read");
            return ResponseEntity.status(HttpStatus.OK).body(st);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    /**
     * Is used to create a new {@link Student} and safe him in the repository.
     * Is called by "/create".
     * @param student
     *                  The {@link Student} you want to safe.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          {@link Student} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> createStudent(Student student){
        Student st = new Student();
        try {
            if(!(checkMatricularNumberIsFree(student.getMatrNr()))){
                throw new StudentMatrNrIsAlreadyUsedException("Matriculation number " + student.getMatrNr() + " already used");
            }
            checkMatricularNumberIsFree(student.getMatrNr());
            st.setMatrNr(student.getMatrNr());
            if (checkName(student.getStudentPrename(), "Prename")){
                st.setStudentPrename(student.getStudentPrename());
            }
            if (checkName(student.getStudentFamilyname(), "Familyname")){
                st.setStudentFamilyname(student.getStudentFamilyname());
            }
            if (checkCurrentSemester(student.getCurrentSemester())){
                st.setCurrentSemester(student.getCurrentSemester());
            }
            st.setFieldOfStudy(student.getFieldOfStudy());
            st.setUsername(student.getUsername());
            st.setPassword(passwordEncoder.encode(student.getPassword()));
            studentRepository.saveAndFlush(st);
            logger.info("Student: " + st.getMatrNr() + " " + st.getStudentPrename() + " " +
                    st.getStudentFamilyname() + " successfully saved");
            return ResponseEntity.status(HttpStatus.OK).body(st);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " "+ e.getMessage());
        }
    }

    /**
     * Is used to delete a {@link Student} from the repository.
     * Is called by "/{matrNr}/delete".
     * @param matNr
     *              The matrNr of the {@link Student} you want to delete.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the deleted
     *          {@link Student} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> deleteStudent(String matNr){
        try{
            checkMatriculationNumber(matNr);
            if(checkMatricularNumberIsFree(matNr)){
                throw new StudentDoesntMatchToMatrNrException("There is no student with matriculation number: " + matNr);
            }
            Student st = studentRepository.findByMatrNr(matNr);
            studentRepository.delete(st);
            logger.info("Student: " + matNr + " successfully deleted");
            return ResponseEntity.status(HttpStatus.OK).body(st);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    /**
     * Is used to update a safed {@link Student}.
     * Is called by "/update".
     * @param student
     *                  The {@link Student} who should replace the old version.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the updated
     *          {@link Student} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> updateStudent(Student student) {
        try {
            if(checkMatricularNumberIsFree(student.getMatrNr())){
                throw new StudentDoesntMatchToMatrNrException("There is no student with matriculation number: " + student.getMatrNr());
            }
            Student st = studentRepository.findByMatrNr(student.getMatrNr());
            //Matriculation number should not be changed form student -> only set
            //st.setMatrNr(student.getMatrNr());
            if (checkName(student.getStudentPrename(), "Prename")){
                st.setStudentPrename(student.getStudentPrename());
            }
            if (checkName(student.getStudentFamilyname(), "Familyname")){
                st.setStudentFamilyname(student.getStudentFamilyname());
            }
            if (checkCurrentSemester(student.getCurrentSemester())){
                st.setCurrentSemester(student.getCurrentSemester());
            }
            st.setFieldOfStudy(student.getFieldOfStudy());
            studentRepository.save(st);
            logger.info("Student: " + student.getMatrNr() +" " + student.getStudentPrename() +" "+ student.getStudentFamilyname() + " successfully updated");
            return ResponseEntity.status(HttpStatus.OK).body(st);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    /**
     * Is used to check if a matrNr is already used by a safed {@link Student}.
     * @param matrNr
     *                  The matrNr you want to check.
     * @return
     *          Returns true if the matrNr is not used by a saved {@link Student} yet.
     *          Returns false if not.
     * @throws Exception
     *                      Is thrown if there are any problems with the matrNr.
     */
    private boolean checkMatricularNumberIsFree(String matrNr){
        try {
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr format");
            }
        }
        catch (Exception e){
            return false;
        }
        Student st = studentRepository.findByMatrNr(matrNr);
        if(st == null){
           return true;
         }
        return false;
    }

    /**
     * Is used to check the syntax of a name.
     * @param name
     *              The name you want to check.
     * @param kindOfname
     *                      The type of name (Used for more detailed exceptions).
     * @return
     *          Returns true if the syntax of the name is valid.
     * @throws MatrNrWrongLengthException
     *                                      Is thrown if the syntax of the name is not valid.
     *                                      Happens if either the name is not between 2-50 letters and/or
     *                                      the name contains a numeric.
     */
    private boolean checkName(String name, String kindOfname) throws Exception {
        if(name.length()<2||name.length()>50){
            throw new MatrNrWrongLengthException(kindOfname + " must be between 2 an 50 letters");
        }
        boolean found = false;
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                found = true;
            }
            if (found) {
                throw new MatrNrWrongSyntaxException("Numbers are not allowed in " + kindOfname );
            }
        }
        return true;
    }

    /**
     * Is used to check the value of a currentSemester.
     * @param currentSemester
     *                          The value you want to check.
     * @return
     *          Returns true if the value is valid.
     * @throws CourseWithoutRecommendedSemesterException
     *                                                      Is thrown if the value is not valid.
     *                                                      Happens if the value isn't between 1-15.
     */
    private boolean checkCurrentSemester(int currentSemester)throws Exception{
        if(currentSemester<1||currentSemester>15){
            throw new CourseWithoutRecommendedSemesterException("Semester can not be smaller then 1 and not be bigger than 15");
        }
        return true;
    }


    /*public ResponseEntity loginStudent(AuthenticationRequest authenticationRequest){
        JwtResponse response = studentRepository.findOneByUsername(authenticationRequest.getUsername())
                .filter(account ->  passwordEncoder.matches(authenticationRequest.getPassword(), account.getPassword()))
                .map(account -> new JwtResponse(jwtService.generateJwt(authenticationRequest.getUsername())))
                .orElseThrow(() ->  new EntityNotFoundException("Account not found"));
        return new ResponseEntity(response, HttpStatus.OK);
    }*/
}
