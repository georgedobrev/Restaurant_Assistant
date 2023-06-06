import React from "react";
import { View, Text, ImageBackground } from "react-native";
import { useFonts } from "expo-font";

const ImageSection = () => {
  const [fontsLoaded] = useFonts({
    MarckScript: require("../../assets/fonts/MarckScript-Regular.ttf"),
  });

  const imageSectionStyles = {
    container: {
      flex: 0.55,
      alignItems: "center",
    },
    imageBackground: {
      width: "110%",
      height: "110%",
    },
    overlayText: {
      fontSize: 60,
      fontWeight: "bold",
      color: "white",
      textAlign: "center",
      marginTop: "25%",
      fontFamily: fontsLoaded ? "MarckScript" : "Arial",
      fontStyle: "italic",
    },
  };

  return (
    <View style={imageSectionStyles.container}>
      <ImageBackground
        style={imageSectionStyles.imageBackground}
        source={require("../../assets/restaurant-banner.jpeg")}
        resizeMode="cover"
        imageStyle={{ opacity: 0.66 }}
      >
        <Text style={imageSectionStyles.overlayText}>Food Paradise</Text>
      </ImageBackground>
    </View>
  );
};

export default ImageSection;
