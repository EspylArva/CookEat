// Actions
export const PASS = "PASS";
export const FETCHING = "FETCHING";
export const SUCCESS = "SUCCESS";
export const FAILED = "FAILED"

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

export function success(recipes) {
    return {
        type: SUCCESS,
        payload: {
            recipes
        }
    }
}

export function failed(error) {
    return {
        type: FAILED,
        payload: {
            error
        }
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
        case FETCHING:
            return {
                ...state,
                fetching: true
            }
        case SUCCESS:
            return {
                ...state,
                fetching: false,
                error: undefined,
                recipes: action.payload.recipes
            }
        case FAILED:
            return {
                ...state,
                fetching: false,
                error: action.payload.error
            }
        default:
            return state;
    }
}