import React, { createContext, useReducer } from 'react';
import searchReducer, { like as searchLike, dislike as searchDislike } from './searchReducer';
import basketReducer, { add, remove as removeRecipe } from './basketReducer';
import cookeatDb from '../../indexedDb/cookeatDb';
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

    const remove = (recipeId) => {
        basketDispatch(removeRecipe(recipeId));
    }

    const actions = {
        like,
        dislike,
        remove
    }

    return (
        <ReceipesContext.Provider value={{ searchState, searchDispatch, basketState, basketDispatch, actions }}>
            { children }
        </ReceipesContext.Provider>
    )
}