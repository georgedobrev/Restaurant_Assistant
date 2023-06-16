import React from "react";
import { View, Text } from "react-native";
import { Client } from "@stomp/stompjs";

const SOCKET_URL = "ws://localhost:8080/ws-message";

class App_Stomp extends React.Component {
  constructor() {
    super();
    this.state = {
      messages: "You server message here.",
    };
  }

  componentDidMount() {
    let currentComponent = this;
    let onConnected = () => {
      console.log("Connected!!");
      client.subscribe("/topic/message", function (msg) {
        if (msg.body) {
          var jsonBody = JSON.parse(msg.body);
          if (jsonBody.message) {
            currentComponent.setState({ messages: jsonBody.message });
          }
        }
      });
    };

    let onDisconnected = () => {
      console.log("Disconnected!!");
    };

    const client = new Client({
      brokerURL: SOCKET_URL,
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
      onConnect: onConnected,
      onDisconnect: onDisconnected,
    });

    client.activate();
  }

  render() {
    return (
      <View>
        <Text style={{ marginTop: "30%" }}>{this.state.messages}</Text>
      </View>
    );
  }
}

export default App_Stomp;
