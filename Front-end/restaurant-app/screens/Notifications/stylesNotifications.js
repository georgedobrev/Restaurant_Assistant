import { StyleSheet } from "react-native";
import globalStyles from "../globalStyles";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: globalStyles.brownColor,
  },
  header: {
    paddingVertical: 20,
    paddingHorizontal: 16,
    backgroundColor: globalStyles.brownColor,
  },
  headerText: {
    fontSize: 24,
    fontWeight: "bold",
    color: globalStyles.bgColor,
  },
  content: {
    flex: 1,
    backgroundColor: globalStyles.bgColor,
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
    backgroundColor: globalStyles.lightColor,
    padding: 16,
    borderRadius: 8,
    marginBottom: 0,
    borderWidth: 1,
    borderColor: globalStyles.lightColor,
    shadowColor: globalStyles.blackColor,
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 1,
    elevation: 2,
  },
  requestText: {
    fontSize: 18,
    fontWeight: "bold",
    marginBottom: 4,
  },
  requestButton: {
    alignSelf: "flex-end",
    color: globalStyles.orangeColor,
    fontWeight: "bold",
  },
  notificationItem: {
    backgroundColor: globalStyles.lightColor,
    padding: 12,
    borderRadius: 6,
    marginBottom: 8,
    flexDirection: "row",
    justifyContent: "space-between",
    borderColor: globalStyles.lightColor,
    shadowColor: globalStyles.blackColor,
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 1,
    elevation: 2,
  },
  clearButton: {
    color: globalStyles.redColor,
    fontWeight: "bold",
  },
  deleteAllButton: {
    marginTop: 10,
    alignSelf: "center",
    color: globalStyles.redColor,
    marginBottom: 16,
  },
  welcomeMsg: {
    color: globalStyles.bgColor,
    marginLeft: 20,
    fontSize: 16,
    fontWeight: "bold",
  },
});

export default styles;
