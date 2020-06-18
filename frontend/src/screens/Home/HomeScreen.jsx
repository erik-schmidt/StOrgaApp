import React, { useState, useEffect, useContext } from "react";
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
      navigation.navigate("Fächer");
    } else if (title === "Kalendereintrag") {
      navigation.navigate("Kalender");
    } else if (title === "Noteneintrag") {
      navigation.navigate("Noten");
    } else if (title === "Links") {
      navigation.navigate("");
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
            <View
              style={{
                borderTopWidth: 1,
                borderTopColor: "lightgrey",
              }}
            >
              <Text style={{ fontSize: 20, fontWeight: "bold", margin: 10 }}>
                {item.title}
              </Text>
              <Text style={{ textAlign: "center", margin: 15 }}>
                {item.data}
              </Text>
            </View>
          </TouchableOpacity>
        )}
      />
    </View>
  );
};

export default HomeScreen;
