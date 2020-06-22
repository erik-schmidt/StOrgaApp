import React, { useState, useEffect } from "react";
import { View, Picker } from "react-native";
import { getAllCourses, addCourse } from "../../../api/services/CourseService";
import styles from "./AddCourseModal.style";
import AppModal from "../../../components/AppModal/AppModal";
import AppButton from "../../../components/AppButton/AppButton";
import * as HttpStatus from "http-status-codes";
import AuthContext from "../../../constants/AuthContext";
import { useFocusEffect } from "@react-navigation/native";

const AddCourseModal = ({ navigation }) => {
  const [courses, setCourses] = useState([]);
  const [selectedCourse, setSelectedCourse] = useState(null);
  const { signOut } = React.useContext(AuthContext);

  useFocusEffect(
    React.useCallback(() => {
      getAllCourses()
        .then((res) => {
          if (res.status === HttpStatus.OK) {
            setCourses(res.data);
          } else if (res.status === HttpStatus.UNAUTHORIZED) {
            signOut();
          } else {
            throw new Error(res.data);
          }
        })
        .catch((err) => {
          alert(err);
        });
    })
  );

  useEffect(() => {
    getAllCourses()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setCourses(res.data);
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  }, []);

  const onSave = () => {
    addCourse(selectedCourse)
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          navigation.navigate("Fächer");
        } else {
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  };

  return (
    <View style={styles.container}>
      <AppModal header="Kurs beitreten" height={450} width={300}>
        <Picker
          selectedValue={selectedCourse}
          style={styles.picker}
          onValueChange={(itemValue) => {
            setSelectedCourse(itemValue);
          }}
        >
          {courses.map((course) => {
            return <Picker.Item label={course.description} value={course} />;
          })}
        </Picker>
        <AppButton onPress={() => onSave()} text="Speichern" />
        <AppButton onPress={() => navigation.pop()} text="Abbrechen" />
      </AppModal>
    </View>
  );
};

export default AddCourseModal;
