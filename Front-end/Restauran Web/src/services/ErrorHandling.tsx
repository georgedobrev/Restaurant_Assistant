export function getServerErrorMessage(error: any): string {
  const serverErrorMessage = error.response?.data?.message;
  const serverErrorDetails = error.response?.data?.details;

  if (serverErrorMessage && serverErrorDetails) {
    return `${serverErrorMessage} - ${serverErrorDetails}`;
  } else if (serverErrorMessage) {
    return serverErrorMessage;
  } else {
    return "An error occurred.";
  }
}
