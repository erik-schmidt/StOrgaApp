import React, { useState, useEffect, useCallback } from "react";
import { Text, View, TouchableOpacity } from "react-native";
import { FlatList } from "react-native-gesture-handler";
import { getAllNews } from "../../../api/services/NewsletterService";
import { useNavigation } from "@react-navigation/native";
import Card from "../../../components/Card/Card";
import styles from "./NewsletterList.style";
import * as HttpStatus from "http-status-codes";
import { useFocusEffect } from "@react-navigation/native";
import AuthContext from "../../../constants/AuthContext";

const NewsList = (route) => {
  const navigation = useNavigation();
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

  /*useEffect(() => {
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

  useEffect(() => {}, [route]);*/

  const OpenLinkCard = ({ id, title, item, link, message, published }) => {
    let url = link;
    const handlePress = useCallback(async () => {
      if (!link.includes("http://") || !link.includes("https://")) {
        url = "https://" + link;
      }
      const supported = await Linking.canOpenURL(url);

      if (supported) {
        await Linking.openURL(url);
      } else {
        alert(`Don't know how to open this URL: ${url}`);
      }
    }, [url]);
    return (
      <TouchableOpacity
        onPress={() =>
          navigation.navigate("NewsInformationModal", {
            news: item,
          })
        }
      >
        <View>
          <Text style={styles.newsHeader}>{title}</Text>
        </View>
        <View style={styles.cardText}>
          <Text style={styles.newsMessage}>{message} </Text>
        </View>
        <View style={styles.cardText}>
          <Text style={styles.boldText}>
            publiziert am {published}, von {author}
          </Text>
        </View>
        <View style={styles.cardText}>
          <Text style={styles.boldText}>{urlLink}</Text>
        </View>
      </TouchableOpacity>
    );
  };

  return (
    <View style={styles.container}>
      <FlatList
        data={news}
        renderItem={({ item }) => (
          <OpenLinkCard
            id={item.id}
            title={item.title}
            item={item}
            link={item.urlLink}
            author={item.author}
            message={item.message}
            published={item.published}
          />
        )}
        keyExtractor={(item) => item.id}
      />
    </View>
  );
};
export default NewsList;
