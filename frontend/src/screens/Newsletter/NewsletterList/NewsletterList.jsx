import React, { useState, useEffect } from "react";
import { Text, View, RefreshControl } from "react-native";
import { FlatList } from "react-native-gesture-handler";
import { getAllNews } from "../../../api/services/NewsletterService";
import Card from "../../../components/Card/Card";
import styles from "./NewsletterList.style";
import * as HttpStatus from "http-status-codes";
import { useFocusEffect } from "@react-navigation/native";
import AuthContext from "../../../constants/AuthContext";

const NewsList = () => {
  const [news, setNews] = useState([]);
  const [refreshing, setRefreshing] = useState(false);
  const { signOut } = React.useContext(AuthContext);

  useFocusEffect(
    React.useCallback(() => {
      getAllNews()
        .then((res) => {
          if (res.status === HttpStatus.OK) {
            setNews(res.data);
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
    getAllNews()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setNews(res.data);
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

  const onRefresh = () => {
    setRefreshing(true);
    getAllNews()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setNews(res.data);
          setRefreshing(false);
        } else if (res.status === HttpStatus.UNAUTHORIZED) {
          signOut();
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
      <FlatList
        data={news}
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
        renderItem={({ item }) => (
          <Card key={news.length}>
            <View>
              <Text style={styles.newsHeader}>{item.title}</Text>
            </View>
            <View style={styles.cardText}>
              <Text style={styles.newsMessage}>{item.message} </Text>
            </View>
            <View style={styles.cardText}>
              <Text style={styles.boldText}>
                publiziert am {item.published}, von {item.author}
              </Text>
            </View>
          </Card>
        )}
        keyExtractor={(item) => item.title}
      />
    </View>
  );
};
export default NewsList;
