import { useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { storedUserId, storedJWT } from "../constants";

const QRCodePage = () => {
  const navigate = useNavigate();
  const { hashedUrl } = useParams();

  useEffect(() => {
    handleQRCodeVerification();
  }, []);

  const userIdAsNum = storedUserId ? parseInt(storedUserId) : null;

  const handleQRCodeVerification = () => {
    const requestBody = storedUserId ? { id: userIdAsNum } : 0;
    console.log(requestBody);

    axios
      .post(`http://localhost:8080/qrcodes/${hashedUrl}`, requestBody, {
        headers: {
          Authorization: `Bearer ${storedJWT}`,
        },
      })
      .then((response) => {
        navigate("/menu");
      })
      .catch((error) => {
        console.error(error);
        navigate("/login?fromQRCode=true"); // Include the query parameter indicating it's from QR code
      });
  };

  return <div>QR code</div>;
};

export default QRCodePage;
