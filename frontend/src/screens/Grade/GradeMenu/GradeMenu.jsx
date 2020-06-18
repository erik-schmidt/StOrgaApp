import React, { useState } from "react";
import { View, Text } from "react-native";
import styles from "./GradeMenu.style";
import { deleteGrade } from "../../../api/services/GradeService";
import AppModal from "../../../components/AppModal/AppModal";
import * as HttpStatus from "http-status-codes";
import AppButton from "../../../components/AppButton/AppButton";
import AuthContext from "../../../constants/AuthContext";

const GradeMenu = ({ navigation, route }) => {
  const [grade, setGrade] = useState(route.params?.grade);
  const { signOut } = React.useContext(AuthContext);

  const onDeleteGrade = () => {
    deleteGrade(grade.courseNumber)
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          navigation.navigate("Noten", { gradeDeleted: true });
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
        } else {
          throw new Error(res.status);
        }
      })
      .catch((err) => {
        alert(err);
      });
  };

  return (
    <View style={styles.container}>
      <AppModal
        header="Note löschen?"
        description={grade.courseNumber}
        width={250}
        height={250}
      >
        <Text style={styles.textStyle}>Note: {grade.grade}</Text>
        <AppButton text="Löschen" onPress={() => onDeleteGrade()} color="red" />
        <AppButton text="Abbrechen" onPress={() => navigation.pop()} />
      </AppModal>
    </View>
  );
};

export default GradeMenu;
