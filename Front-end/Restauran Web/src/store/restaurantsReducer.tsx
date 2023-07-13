// reducers/restaurantReducer.ts


const initialState: RestaurantState = {
  restaurants: [],
};

export const restaurantReducer = (
  state: RestaurantState = initialState,
  action: RestaurantAction
): RestaurantState => {
  switch (action.type) {
    case "ADD_RESTAURANT":
      return {
        ...state,
        restaurants: [...state.restaurants, action.payload.restaurantDto],
      };
    default:
      return state;
  }
};
