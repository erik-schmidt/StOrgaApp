import React, { useState, useEffect } from "react";
import { Text, View, RefreshControl } from "react-native";
import { FlatList } from "react-native-gesture-handler";
import { getAllNews } from "../../../api/services/NewsletterService";
import Card from "../../../components/Card/Card";
import Toast from "../../../components/Toast/Toast";
import styles from "./NewsletterList.style";
import { useNavigation, useRoute } from "@react-navigation/native";

const NewsList = (props) => {
  const [showModal, setShowModal] = useState(false);
  const [news, setNews] = useState(props.news);
  const navigation = useNavigation();
  const route = useRoute();
  const [refreshing, setRefreshing] = useState(false);

  const onRefresh = () => {
    setRefreshing(true);
    getAllNews()
      .then((res) => {
        if (res != undefined) {
          setNews(res.data);
          setRefreshing(false);
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        setShowModal(true);
        setRefreshing(false);
        setTimeout(() => {
          setShowModal(false);
        }, 5000);
      });
  };
  useEffect(() => {
    getAllNews()
      .then((res) => {
        if (res != undefined) {
          setNews(res.data);
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        setShowModal(true);
        setTimeout(() => {
          setShowModal(false);
        }, 5000);
      });
  }, []);

  useEffect(() => {
    getAllNews()
      .then((res) => {
        if (res != undefined) {
          setNews(res.data);
        } else {
          throw new Error();
        }
      })
      .catch((err) => {
        setShowModal(true);
        setTimeout(() => {
          setShowModal(false);
        }, 5000);
      });
  }, [route]);

  const News = [
    {
      title: "Titel1",
      message: "Erster Eintrag",
      author: "Alexa Krepp",
      published: "20.05.2020",
    },
    {
      title: "Titel2",
      message: "Zweiter Eintrag",
      author: "Alexa Krepp",
      published: "21.05.2020",
    },
  ];

  return (
    <View style={styles.container}>
      <FlatList
        data={News}
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
        renderItem={({ item }) => (
          <Card key={News.length}>
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
      <Toast
        showModal={showModal}
        color="red"
        text="Keine Verbindung zum Server"
      />
    </View>
  );
};
export default NewsList;
