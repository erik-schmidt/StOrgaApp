import React, { useState, useEffect } from "react";
import styles from "./CourseScreen.style";
import { Text, View } from "react-native";
import { FlatList } from "react-native-gesture-handler";
import Card from '../../components/CardList/Card'
import SeperatedText from "../../components/SeperatedText/SeperatedText";

const CourseScreen = ({ route }) => {
  const [courses, setCourses] = useState([]);

  useEffect(() => {}, []);

  useEffect(() => {
    console.log("Current Courses", courses);
  }, [courses]);

  return (
    <View style={styles.container}>
        <FlatList
          data={courses}
          renderItem={({ item }) => (
            <Card>
              <SeperatedText title="Name:" content={item.name}/>
              <SeperatedText title="Raum:" content={item.raum}/>
              <SeperatedText title="Professor:" content={item.professor}/>
              <SeperatedText title="ECTS:" content={item.etcs}/>
            </Card>
          )}
          keyExtractor={item => item.id}
        />
    </View>
  );
};

export default CourseScreen;
