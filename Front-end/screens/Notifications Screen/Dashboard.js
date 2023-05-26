import React, { useState } from "react";
import { StyleSheet, Text, View, SafeAreaView, FlatList } from "react-native";

export function Dashboard() {
  const [notifications, setNotifications] = useState([]);
  const [incomingRequests, setIncomingRequests] = useState([
    { id: 1, request: "Request a menu", table: "Table 5", time: "10:30 AM" },
    { id: 2, request: "Request a bill", table: "Table 3", time: "12:15 PM" },
    { id: 3, request: "Ask for a waiter", table: "Table 2", time: "2:45 PM" },
    { id: 4, request: "Ask for a waiter", table: "Table 2", time: "2:45 PM" },
  ]);

  const handleRequestNotification = (request) => {
    const notification = `${request.request} - ${request.table}`;
    setNotifications((prevNotifications) => [
      ...prevNotifications,
      notification,
    ]);
    setIncomingRequests((prevRequests) =>
      prevRequests.filter((req) => req.id !== request.id)
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
        <Text style={styles.headerText}>Styling to be applied</Text>
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

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#615149",
  },
  header: {
    paddingVertical: 20,
    paddingHorizontal: 16,
    backgroundColor: "#615149",
  },
  headerText: {
    fontSize: 24,
    fontWeight: "bold",
    color: "white",
  },
  content: {
    flex: 1,
    backgroundColor: "white",
    paddingHorizontal: 30,
    borderTopLeftRadius: 40,
    borderTopRightRadius: 40,
    paddingTop: 20,
  },
  sectionTitle: {
    fontSize: 20,
    fontWeight: "bold",
    marginTop: 0,
    marginBottom: 10,
  },
  requestItem: {
    backgroundColor: "#F6F1E9",
    padding: 16,
    borderRadius: 8,
    marginBottom: 0,
    borderWidth: 1,
    borderColor: "#E6E0D5",
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 1,
    elevation: 2,
  },
  requestText: {
    fontSize: 18,
    fontWeight: "bold",
    marginBottom: 5,
  },
  requestButton: {
    alignSelf: "flex-end",
    color: "#FF8400",
    fontWeight: "bold",
  },
  notificationItem: {
    backgroundColor: "#F6F1E9",
    padding: 12,
    borderRadius: 6,
    marginBottom: 8,
    flexDirection: "row",
    justifyContent: "space-between",
    borderColor: "#E6E0D5",
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 1,
    elevation: 2,
  },
  clearButton: {
    color: "red",
    fontWeight: "bold",
  },
  deleteAllButton: {
    marginTop: 10,
    alignSelf: "center",
    color: "red",
    marginBottom: 15,
  },
});
