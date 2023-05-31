import React from "react";
import { Text, View, Image } from "react-native";
import { useRoute } from "@react-navigation/native";

const Dashboard = () => {
  const route = useRoute();
  const { email, accessToken, user } = route.params;

  return (
    <View>
      {accessToken && <Image source={{ uri: user?.profilePicture }} />}
      <Text>Welcome, {email}!</Text>
    </View>
  );
};

export default Dashboard;
