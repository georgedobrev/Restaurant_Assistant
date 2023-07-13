import { createRestaurant, Restaurant, RestaurantObj } from "../services/restaurantService";
import { ThunkAction } from "redux-thunk";
import { RootState } from "./store";
import { Dispatch, Action } from "redux";


export type AppThunk<ReturnType = void> = ThunkAction<
  ReturnType,
  RootState,
  unknown,
  Action<string>
>;


export const CREATE_RESTAURANT_SUCCESS = "CREATE_RESTAURANT_SUCCESS";

export interface CreateRestaurantSuccessAction {
  type: typeof CREATE_RESTAURANT_SUCCESS;
  payload: Restaurant;
}

export const createRestaurantSuccess = (
  restaurant: Restaurant
): CreateRestaurantSuccessAction => ({
  type: CREATE_RESTAURANT_SUCCESS,
  payload: restaurant,
});

export const createNewRestaurant = (restaurantData: RestaurantObj): AppThunk => {
  return async (dispatch: Dispatch<CreateRestaurantSuccessAction>) => {
    try {
      const response = await createRestaurant(restaurantData);
      dispatch(createRestaurantSuccess(response.restaurantDto));
    } catch (error) {
      console.error("Error creating restaurant:", error);
    }
  };
};

