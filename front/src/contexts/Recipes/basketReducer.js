// Actions
export const ADD = "ADD";

// Actions creators
export function add(recipe) {
    return {
        type: ADD,
        payload: {
            recipe,
        },
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
        default:
            return state;
    }
}