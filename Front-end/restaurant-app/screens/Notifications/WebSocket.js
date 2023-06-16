import React, { useState, useEffect } from "react";
import { View, Text } from "react-native";
import { Client } from "@stomp/stompjs";
import { socketBaseURL, endpointRegister, topic } from "../../config.json";

const WebSocket = () => {
  const [messages, setMessages] = useState("Your server message here.");

  useEffect(() => {
    let client = null;

    const onConnected = () => {
      client.subscribe(`${topic}`, (msg) => {
        if (msg.body) {
          const jsonBody = JSON.parse(msg.body);
          if (jsonBody.message) {
            setMessages(jsonBody.message);
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

  return (
    <View>
      <Text>{messages}</Text>
    </View>
  );
};

export default WebSocket;
