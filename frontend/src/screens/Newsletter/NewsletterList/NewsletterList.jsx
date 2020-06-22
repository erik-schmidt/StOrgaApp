import React, { useState, useEffect } from "react";
import { Text, View } from "react-native";
import { FlatList } from "react-native-gesture-handler";
import { getAllNews } from "../../../api/services/NewsletterService";
import { useNavigation } from "@react-navigation/native";
import Card from "../../../components/Card/Card";
import styles from "./NewsletterList.style";
import * as HttpStatus from "http-status-codes";

const NewsList = (route) => {
  const navigation = useNavigation();
  const [news, setNews] = useState([]);

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

  useEffect(() => {}, [route]);

  return (
    <View style={styles.container}>
      <FlatList
        data={news}
        renderItem={({ item }) => (
          <Card
            key={news.length}
            onPress={() =>
              navigation.navigate("NewsInformationModal", {
                news: item,
              })
            }
          >
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
