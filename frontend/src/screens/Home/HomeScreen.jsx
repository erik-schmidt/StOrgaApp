import React, { useState, useEffect } from "react";
import { Text, View, SectionList, RefreshControl } from "react-native";
import styles from "./HomeScreen.style";
import { getHomescreenItems } from "../../api/services/HomeService";
import * as HttpStatus from "http-status-codes";
import AuthContext from "../../constants/AuthContext";
import { TouchableOpacity } from "react-native-gesture-handler";

const HomeScreen = ({ navigation }) => {
  const [latestItems, setLatestItems] = useState([]);
  const [refreshing, setRefreshing] = useState();
  const { signOut } = React.useContext(AuthContext);

  const onRefresh = () => {
    setRefreshing(true);
    getHomescreenItems()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setLatestItems(res.data);
          setRefreshing(false);
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
        } else {
          setRefreshing(false);
          throw new Error(res.data);
        }
      })
      .catch((err) => {
        alert(err);
      });
  };

  useEffect(() => {
    getHomescreenItems()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setLatestItems(res.data);
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

  const screenNavigation = (title) => {
    if (title === "Beigetretene Kurse") {
      navigation.navigate("Vorlesungen");
    } else if (title === "Kalendereintrag") {
      navigation.navigate("Kalender");
    } else if (title === "Noteneintrag") {
      navigation.navigate("Noten");
    } else if (title === "Links") {
      navigation.navigate("Wichtige Links");
    } else if (title === "Nächste Unterrichtsstunde") {
      navigation.navigate("Stundenplan");
    }
  };

  return (
    <View style={styles.container}>
      <SectionList
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
        sections={[{ title: "", data: latestItems }]}
        keyExtractor={(item, index) => item + index}
        renderItem={({ item }) => (
          <TouchableOpacity onPress={() => screenNavigation(item.title)}>
            <View style={styles.areaContainer}>
              <Text style={styles.title}>{item.title}</Text>
              <Text style={styles.data}>{item.data}</Text>
            </View>
          </TouchableOpacity>
        )}
      />
    </View>
  );
};

export default HomeScreen;
