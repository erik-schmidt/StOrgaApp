import React, { useState, useEffect } from "react";
import { Text, View, SectionList, RefreshControl } from "react-native";
import styles from "./HomeScreen.style";
import { getHomescreenItems } from "../../api/services/HomeService";
import * as HttpStatus from "http-status-codes";
import AuthContext from "../../constants/AuthContext";

const HomeScreen = () => {
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

  return (
    <View style={styles.container}>
      <SectionList
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
        sections={[{ title: "", data: latestItems }]}
        keyExtractor={(item, index) => index}
        renderItem={({ item }) => (
          <View
            style={{
              borderTopWidth: 1,
              borderTopColor: "lightgrey",
            }}
          >
            <Text style={{ fontSize: 20, fontWeight: "bold", margin: 10 }}>
              {item.title}
            </Text>
            <Text style={{ textAlign: "center", margin: 15 }}>{item.data}</Text>
          </View>
        )}
      />
    </View>
  );
};

export default HomeScreen;
