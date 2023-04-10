import React, { useEffect, useCallback } from "react";
import Map from "../maps/Map";
import GameBanner from "./components/GameBanner";
import PlayerInfoCard from "./components/PlayerInfoCard";
import PlayerOrderButtons from "./components/PlayerOrderButtons";
import { Container, Row, Col } from "react-bootstrap";
import axios from "axios";
import { useLocation } from 'react-router-dom';
import { useContext } from "react";
import { AuthContext } from "../auth/AuthProvider";
import { OrderContext } from "./context/OrderProvider";

const GameView = () => {
  const { user } = useContext(AuthContext);
  const { orders } = useContext(OrderContext);
  console.log("orders in GameView: ", orders);
  const [game, setGame] = React.useState();
  const [isLoading, setIsLoading] = React.useState(true);
  const location = useLocation();
  const gameId = location.state.gameId;
  // const gamePlayerMap = location.state.gamePlayerMap;
  // console.log(gamePlayerMap)
  // const [player, setPlayer] = React.useState(gamePlayerMap[gameId]);

  const fetchGame = useCallback(async () => {
    try {
      const config = {
        headers: { Authorization: `Bearer ${user.accessToken}` }
      }
      let response = await axios.get(`getGame/${gameId}`, config);
      console.log(`Current game: ${response.data}`);
      setGame(response.data);
      setIsLoading(false);
    } catch (error) {
      console.log(error);
    }
  }, [gameId, user.accessToken])

  useEffect(() => {
    fetchGame();
  }, [fetchGame]);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  return (
    <Container>
      <Row>
        <Col md={9}>
          <GameBanner view="home" />
          <Map game={game} />
        </Col>
        <Col md={3}>
          <PlayerInfoCard game={game} />
          <br />
          <PlayerOrderButtons gameId={gameId} />
        </Col>
      </Row>
    </Container>
  );
};

export default GameView;
