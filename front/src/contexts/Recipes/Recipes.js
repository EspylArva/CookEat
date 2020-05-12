import React, { createContext, useReducer } from 'react';
import searchReducer, { like as searchLike, dislike as searchDislike } from './searchReducer';
import basketReducer, { add } from './basketReducer';
import recipes from './recipes.json';

export const ReceipesContext = createContext();

export function ReceipesProvider({ children }) {
    const [searchState, searchDispatch] = useReducer(searchReducer, {
        recipes: recipes,
        liked: false,
    });
    const [basketState, basketDispatch] = useReducer(basketReducer, {
        basket: [],
    });

    const like = () => {
        const recipe = searchState.recipes[0];
        searchDispatch(searchLike());
        basketDispatch(add(recipe));
    }

    const dislike = () => {
        searchDispatch(searchDislike());
    }

    const actions = {
        like,
        dislike
    }

    return (
        <ReceipesContext.Provider value={{ searchState, searchDispatch, basketState, basketDispatch, actions }}>
            { children }
        </ReceipesContext.Provider>
    )
}