package com.group3.backend.service.studentService;


import com.group3.backend.model.Student;
import com.group3.backend.service.studentService.StudentDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository

public class StudentDaoImpl implements StudentDao {

    //ToDo : JdbcTemplate, überprüfen was anstelle des Templates rein kommt
    public StudentDaoImpl (NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    NamedParameterJdbcTemplate template;

    @Override
    public List<Student> findAll {
        return template.query("select * from student", new StudentRowMapper());
    }
    @Override

    public void insertStudent(Student stud) {

        final String sql = "insert into student(matrNr, studentPrename, studentFamilyname, degreeCourse, courseList, taskList, grade, calenderEntries) values(:matrNr, :studentPrename, :studentFamilyname, :degreeCourse, :courseList, :taskList, :grade, :calenderEntries)";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()

                .addValue("matrNr", stud.getMatrNr())

                .addValue("studentPrename", stud.getStudentPrename())

                .addValue("studentFamilyname", stud.getStudentFamilyname())

                .addValue("degreeCourse", stud.getDegreeCourse())

                .addValue("courseList", stud.getCourseList())

                .addValue("taskList", stud.getTaskList())

                .addValue("grade", stud.getGrade())

                .addValue("calenderEntries", stud.getCalenderEntries());

        template.update(sql,param, holder);

    }

    @Override

    public void updateStudent(Student stud) {


        final String sql = "update student set studentPrename=:studentPrename, studentFamilyname=:studentFamilyname, degreeCourse=:degreeCourse, courseList=:courseList, taskList=:taskList, grade=:grade where matrNr=:matrNr";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()

                .addValue("matrNr", stud.getMatrNr())

                .addValue("studentPrename", stud.getStudentPrename())

                .addValue("studentFamilyname", stud.getStudentFamilyname())

                .addValue("degreeCourse", stud.getDegreeCourse())

                .addValue("courseList", stud.getCourseList())

                .addValue("taskList", stud.getTaskList())

                .addValue("grade", stud.getGrade())

                .addValue("calenderEntries", stud.getCalenderEntries());

        template.update(sql,param, holder);

    }

    @Override

    public void executeUpdateStudent(Student stud) {

        Map<String,Object> map=new HashMap<String,Object>();

        map.put("matrNr", stud.getMatrNr());

        map.put("studentPrename", stud.getStudentPrename());

        map.put("studentFamilyname", stud.getStudentFamilyname());

        map.put("degreeCourse", stud.getDegreeCourse());

        map.put("courseList", stud.getCourseList());

        map.put("taskList", stud.getTaskList());

        map.put("grade", stud.getGrade());

        map.put("calenderEntries", stud.getCalenderEntries());

        final String sql = "update student set studentPrename=:studentPrename, studentFamilyname=:studentFamilyname, degreeCourse=:degreeCourse, courseList=:courseList, taskList=:taskList, grade=:grade where matrNr=:matrNr";
        template.execute(sql,map,new PreparedStatementCallback<Object>() {

            @Override

            public Object doInPreparedStatement(PreparedStatement ps)

                    throws SQLException, DataAccessException {

                return ps.executeUpdate();

            }

        });

    }

    @Override

    public void deleteStudent(Student stud) {

        final String sql = "delete from student where matrNr=:matrNr";

        Map<String,Object> map=new HashMap<String,Object>();

        map.put("matrNr", stud.getMatrNr());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {

            @Override

            public Object doInPreparedStatement(PreparedStatement ps)

                    throws SQLException, DataAccessException {

                return ps.executeUpdate();

            }

        });

    }
}
