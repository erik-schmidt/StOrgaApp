import React, { useState, useEffect } from "react";
import {
  getAllCourses,
  deleteCourse,
} from "../../../api/services/courseService";
import { FontAwesome5 } from "@expo/vector-icons";

const CourseTable = () => {
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    getAllCourses().then((res) => {
      console.log(res.data);
      if (res !== undefined) {
        setCourses(res.data);
      }
    });
  }, []);

  useEffect(() => {}, [courses]);

  const onDeleteCourse = (courseId) => {
    deleteCourse(courseId).then((res) => {
      if (res !== undefined) {
        setCourses(courses.filter((course) => course !== res));
      }
    });
  };

  return (
    <table>
      <tr style={{ fontSize: 16 }}>
        <th>VERANSTALTUNG</th>
        <th>PROFESSOR</th>
        <th>ECTS</th>
        <th>NOTE</th>
        <th>SEMESTER</th>
      </tr>
      {courses.map((course) => {
        return (
          <tr style={{ textAlign: "center" }}>
            <td>{course.description}</td>
            <td>{course.professor}</td>
            <td>{course.ects}</td>
            <td>{courses.grade}</td>
            <td>{course.requiredSemester}</td>
            <td>
              <FontAwesome5.Button
                name="edit"
                color="black"
                backgroundColor="#ffff"
              />
            </td>
            <td>
              <FontAwesome5.Button
                name="trash-alt"
                color="black"
                backgroundColor="#ffff"
                onPress={() => onDeleteCourse(course.id)}
              />
            </td>
          </tr>
        );
      })}
    </table>
  );
};

export default CourseTable;
