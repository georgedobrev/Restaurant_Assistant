import React, { useState, useEffect } from "react";
import { Text, View, FlatList, Image, ScrollView } from "react-native";
import { useRoute } from "@react-navigation/native";
import styles from "./stylesNotifications";
import { Client } from "@stomp/stompjs";
import { websocketBaseURL, endpointRegister, topic } from "../../config.json";
import {
  deleteNotifications,
  getNotifications,
} from "../services/notificationsService";

const useWebSocket = (setMessages) => {
  useEffect(() => {
    let client = null;

    const onConnected = () => {
      client.subscribe(`${topic}`, (msg) => {
        if (msg.body) {
          const jsonBody = JSON.parse(msg.body);
          if (jsonBody.message) {
            setMessages((prevMessages) => [...prevMessages, jsonBody.message]);
          }
        }
      });
    };

    //TODO error message
    const onDisconnected = () => {
      return "Disconnected!!";
    };

    client = new Client({
      brokerURL: `${websocketBaseURL}${endpointRegister}`,
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: onConnected,
      onDisconnect: onDisconnected,
    });

    client.activate();

    return () => {
      client && client.deactivate();
    };
  }, []);
};

export function Notifications() {
  const [notifications, setNotifications] = useState([]);
  const [messages, setMessages] = useState([]);

  const route = useRoute();
  const { email, accessToken, user } = route.params;
  useWebSocket(setMessages);

  useEffect(() => {
    getNotifications().then((data) => {
      setNotifications(data);
    });
  }, []);

  const deleteNot = async (id) => {
    try {
      await deleteNotifications(id);
      setNotifications((prev) =>
        prev.filter((notification) => notification.id !== id)
      );
    } catch (error) {
      //TODO display error message from back-end
      return "Failed to delete notification: ", error;
    }
  };

  useEffect(() => {
    //TODO dynamic restaurant notifications
    getNotifications(1).then((data) => {
      setNotifications(data);
    });
  }, []);

  const renderNoNotificationsMessage = () => {
    return <Text>No notifications</Text>;
  };

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        {accessToken && <Image source={{ uri: user?.profilePicture }} />}
        <Text style={styles.welcomeMsg}>Welcome, {email}!</Text>
      </View>

      <View style={styles.content}>
        <Text style={styles.sectionTitle}>Latest Notifications:</Text>
        <View>
          {messages.map((message, index) => (
            <View style={styles.requestItem} key={index}>
              <Text style={styles.messageText}>{message}</Text>
              <Text
                style={styles.approveButton}
                onPress={() => deleteNotifications(notification.id)}
              >
                Approve
              </Text>
            </View>
          ))}
        </View>
        <ScrollView style={{ height: "25%" }}>
          <Text style={styles.sectionTitleOldNotifications}>
            Old Notifications:
          </Text>
          {notifications.length === 0
            ? renderNoNotificationsMessage()
            : [...notifications].reverse().map((notification, index) => (
                <View style={styles.requestItem} key={index}>
                  <Text style={styles.requestText}>
                    {`${notification.requestType} request`}
                  </Text>
                  <Text style={styles.tableNumberText}>
                    Table: {notification.appTable.tableNumber}
                  </Text>
                  <Text style={styles.timeText}>
                    Time: {notification.createdAt}
                  </Text>

                  <Text
                    style={styles.approveButton}
                    onPress={() => deleteNotifications(notification.id)}
                  >
                    Approve
                  </Text>
                </View>
              ))}
        </ScrollView>
      </View>
    </View>
  );
}
