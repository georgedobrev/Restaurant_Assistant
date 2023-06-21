import React, { useState, useEffect } from "react";
import { Text, View, FlatList, Image, ScrollView } from "react-native";
import { useRoute } from "@react-navigation/native";
import styles from "./stylesNotifications";
import { Client } from "@stomp/stompjs";
import { socketBaseURL, endpointRegister, topic } from "../../config.json";

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

    const onDisconnected = () => {
      return "Disconnected!!";
    };

    client = new Client({
      brokerURL: `${socketBaseURL}${endpointRegister}`,
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

export function Notifications2() {
  const [notifications, setNotifications] = useState([]);
  const [incomingRequests, setIncomingRequests] = useState();
  const [messages, setMessages] = useState([]);

  const route = useRoute();
  const { email, accessToken, user } = route.params;
  useWebSocket(setMessages);

  const getNotificationsByRestaurantId = async (restaurantId) => {
    try {
      const response = await fetch(
        `http://localhost:8080/notification/all/restaurant/1`
      );

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      } else {
        const notifications = await response.json();
        return notifications;
      }
    } catch (error) {
      console.error("Failed to fetch notifications: ", error);
      return [];
    }
  };

  useEffect(() => {
    getNotificationsByRestaurantId(1).then((data) => {
      setNotifications(data);
    });
  }, []);

  const deleteNotification = async (id) => {
    try {
      const response = await fetch(
        `http://localhost:8080/notification/delete/${id}`,
        { method: "DELETE" }
      );

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      // Remove notification from local state
      setNotifications((prev) =>
        prev.filter((notification) => notification.id !== id)
      );
    } catch (error) {
      console.error("Failed to delete notification: ", error);
    }
  };

  const handleRequestNotification = (request) => {
    setIncomingRequests((prevRequests) =>
      prevRequests.filter((prevReq) => prevReq.id !== request.id)
    );
  };

  const handleRequestClear = (request) => {
    setNotifications((prevNotifications) =>
      prevNotifications.filter((notification) => notification !== request)
    );
  };

  const handleDeleteAll = () => {
    setNotifications([]);
  };

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
                onPress={() => deleteNotification(notification.id)}
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
                    onPress={() => deleteNotification(notification.id)}
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
