import React, { useState } from "react";
import { Text, View, FlatList, Image } from "react-native";
import { useRoute } from "@react-navigation/native";
import styles from "./stylesNotifications";
import requests from "./mock";

export function Notifications() {
  const [notifications, setNotifications] = useState([]);
  const [incomingRequests, setIncomingRequests] = useState(requests);

  const route = useRoute();
  const { email, accessToken, user } = route.params;

  const handleRequestNotification = (request) => {
    const notification = `${request.request} - ${request.table}`;
    setNotifications((prevNotifications) => [
      ...prevNotifications,
      notification,
    ]);

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

  const renderIncomingRequestItem = ({ item }) => (
    <View style={styles.requestItem}>
      <Text style={styles.requestText}>{item.request}</Text>
      <Text>Table: {item.table}</Text>
      <Text>Time: {item.time}</Text>
      <Text
        style={styles.requestButton}
        onPress={() => handleRequestNotification(item)}
      >
        Approve
      </Text>
    </View>
  );

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        {accessToken && <Image source={{ uri: user?.profilePicture }} />}
        <Text style={styles.welcomeMsg}>Welcome, {email}!</Text>
      </View>
      <View style={styles.content}>
        <Text style={styles.sectionTitle}>Latest Notifications:</Text>
        {incomingRequests.length === 0 ? (
          renderNoNotificationsMessage()
        ) : (
          <FlatList
            data={incomingRequests}
            keyExtractor={(item) => item.id.toString()}
            renderItem={renderIncomingRequestItem}
          />
        )}
        {notifications.length > 0 && (
          <>
            <Text style={styles.sectionTitle}>Approved Notifications:</Text>
            <FlatList
              data={notifications}
              keyExtractor={(item, index) => index.toString()}
              renderItem={({ item }) => (
                <View style={styles.notificationItem}>
                  <Text>{item}</Text>
                  <Text
                    style={styles.clearButton}
                    onPress={() => handleRequestClear(item)}
                  >
                    Clear
                  </Text>
                </View>
              )}
            />
            <Text style={styles.deleteAllButton} onPress={handleDeleteAll}>
              Delete All
            </Text>
          </>
        )}
      </View>
    </View>
  );
}
