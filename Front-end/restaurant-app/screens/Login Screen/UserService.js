async function fetchUserInfo(accessToken) {
  if (accessToken) {
    const response = await fetch("https://www.googleapis.com/userinfo/v2/me", {
      headers: { Authorization: `Bearer ${accessToken}` },
    });
    const userInfo = await response.json();
    return userInfo;
  }
}

export default {
  fetchUserInfo,
};
