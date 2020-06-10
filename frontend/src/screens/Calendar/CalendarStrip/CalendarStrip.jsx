import React, { useState, useEffect } from "react";
import CalendarStrip from "react-native-calendar-strip";
import styles from "./CalendarStrip.style";
import { Text, RefreshControl, View, FlatList, Dimensions } from "react-native";
import { useNavigation } from "@react-navigation/native";
import { getAppointments } from "../../../api/services/CalendarService";
import * as HttpStatus from "http-status-codes";
import Card from "../../../components/Card/Card";
import moment from "moment";
import "moment/locale/de";

const CalStrip = () => {
  const navigation = useNavigation();
  const [appointments, setAppointments] = useState([]);
  const [refreshing, setRefreshing] = useState(false);

  const onRefresh = () => {
    setRefreshing(true);
    getAppointments()
      .then((res) => {
        if (res.status === HttpStatus.OK) {
          setAppointments(res.data);
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
  useEffect(() => {
    getAppointments()
      .then((res) => {
        console.log(res);
        if (res.status === HttpStatus.OK) {
          setAppointments(res.data);
          console.log(res.data);
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

  useEffect(() => {}, [appointments]);
  moment.locale("de");

  return (
    <View style={styles.container}>
      <CalendarStrip
        style={styles.stripContainer}
        daySelectionAnimation={{
          type: "border",
          duration: 200,
          borderWidth: 1,
          borderHighlightColor: "#66CDAA",
        }}
        calendarColor={"white"}
        calendarHeaderStyle={{ color: "#66CDAA" }}
        highlightDateNumberStyle={{ color: "#66CDAA" }}
      />
      <View style={styles.container}>
        <FlatList
          syle={{ hight: Dimensions.get("window").height }}
          data={appointments}
          refreshControl={
            <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
          }
          renderItem={({ item }) => (
            <Card
              key={appointments.length}
              onPress={() =>
                navigation.navigate("CalendarInformationModal", {
                  appointment: item,
                })
              }
              onLongPress={() =>
                navigation.navigate("CalendarMenu", { appointment: item })
              }
            >
              <View style={styles.event}>
                <View style={styles.eventDuration}>
                  <View style={styles.durationContainer}>
                    <Text style={styles.dateText}>
                      {moment(item.entryStartDateAndTime).format("l")}
                    </Text>
                  </View>
                </View>
                <View style={styles.eventDuration}>
                  <View style={styles.durationContainer}>
                    <View style={styles.durationDot} />
                    <Text style={styles.durationText}>
                      {moment(item.entryStartDateAndTime).format("LT")}
                    </Text>
                  </View>
                  <View style={{ paddingTop: 10 }} />
                  <View style={styles.durationContainer}>
                    <View style={styles.durationDot} />
                    <Text style={styles.durationText}>
                      {moment(item.entryFinishDateAndTime).format("LT")}
                    </Text>
                  </View>
                  <View style={styles.durationDotConnector} />
                </View>
                <View style={styles.eventNote}>
                  <Text style={styles.eventText}>{item.name}</Text>
                </View>
              </View>
            </Card>
          )}
          keyExtractor={(item) => item.name}
        />
      </View>
    </View>
  );
};

export default CalStrip;
