// Actions
export const PASS = "PASS";


// Reducer
export default function reducer(state, action) {
    switch (action.type) {
        case PASS:
            return {
                ...state,
                recipes: state.recipes.slice(1)
            };
        default:
            return state;
    }
}