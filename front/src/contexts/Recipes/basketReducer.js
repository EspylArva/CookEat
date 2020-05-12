// Actions
export const ADD = "ADD";
export const REMOVE = "REMOVE";

// Actions creators
export function add(recipe) {
    return {
        type: ADD,
        payload: {
            recipe,
        },
    }
}

export function remove(recipeId) {
    return {
        type: REMOVE,
        payload: {
            recipeId
        }
    }
}



// Reducer
export default function reducer(state, action) {
    switch (action.type) {
        case ADD:
            return {
                ...state,
                basket: [...state.basket, action.payload.recipe]
            };
        case REMOVE:
            return {
                ...state,
                basket: state.basket.filter(recipe => !(recipe.id === action.payload.recipeId))
            }
        default:
            return state;
    }
}