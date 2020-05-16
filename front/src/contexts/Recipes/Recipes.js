import React, { createContext, useReducer } from 'react';
import searchReducer, { like as searchLike, dislike as searchDislike } from './searchReducer';
import cookeatDb from '../../indexedDb/cookeatDb';
import recipes from './recipes.json';

export const ReceipesContext = createContext();

export function ReceipesProvider({ children }) {
    const [searchState, searchDispatch] = useReducer(searchReducer, {
        recipes: recipes,
        liked: false,
    });

    const like = () => {
        if(searchState.recipes.length !== 0) {
            const recipe = searchState.recipes[0];
            searchDispatch(searchLike());
            cookeatDb.basketRecipes.put(recipe);
        }
    }

    const dislike = () => {
        if(searchState.recipes.length !== 0) {
            searchDispatch(searchDislike());
        }
    }

    const actions = {
        like,
        dislike
    }

    return (
        <ReceipesContext.Provider value={{ searchState, searchDispatch, actions }}>
            { children }
        </ReceipesContext.Provider>
    )
}