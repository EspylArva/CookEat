// Actions
export const PASS = "PASS";

// Actions creators
export function like() {
    return {
        type: PASS,
        payload: {
            liked: true,
        },
    }
}

export function dislike() {
    return {
        type: PASS,
        payload: {
            liked: false,
        },
    }
}



// Reducer
export default function reducer(state, action) {
    switch (action.type) {
        case PASS:
            return {
                ...state,
                recipes: state.recipes.slice(1),
                liked: action.payload.liked
            };
        default:
            return state;
    }
}