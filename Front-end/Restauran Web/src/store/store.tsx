import { configureStore } from "@reduxjs/toolkit";
import {
  createRestaurantSuccess,
  CreateRestaurantSuccessAction,
} from "./actions";
import { Restaurant } from "../services/restaurantService";

export interface AppState {
  restaurants: Restaurant[];
}

const initialState: AppState = {
  restaurants: [],
};

const reducer = (
  state = initialState,
  action: CreateRestaurantSuccessAction
): AppState => {
  switch (action.type) {
    case "CREATE_RESTAURANT_SUCCESS":
      return {
        ...state,
        restaurants: [...state.restaurants, action.payload],
      };
    default:
      return state;
  }
};

const store = configureStore({ reducer });

export default store;
