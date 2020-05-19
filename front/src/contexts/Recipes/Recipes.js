import React, { createContext, useReducer, useEffect } from 'react';
import
searchReducer,
{
    like as searchLike,
    dislike as searchDislike,
    FETCHING,
    success,
    failed
} from './searchReducer';
import cookeatDb from '../../indexedDb/cookeatDb';
import { configs } from '../../configs/configs'

export const ReceipesContext = createContext();

export function ReceipesProvider({ children }) {
    const [searchState, searchDispatch] = useReducer(searchReducer, {
        recipes: new Array(),
        liked: false,
        fetching: false,
        error: undefined,
    });

    const fetchRecipes = async (id) => {
        searchDispatch({type: FETCHING});
        try {
            const response = await fetch(`${configs.apiUrl}/recipes?id=${id ? id : 100}&quantity=${configs.match.quantity}`);
            const recipes = await response.json();
            searchDispatch(success(recipes));
        } catch (e) {
            searchDispatch(failed(e))
        }
    }

    useEffect(() => {
        fetchRecipes();
    }, [])

    const like = () => {
        if (searchState.recipes.length !== 0) {
            const recipe = searchState.recipes[0];
            searchDispatch(searchLike());
            cookeatDb.basketRecipes.put(recipe);

            if(searchState.recipes.length < 5) {
                fetchRecipes(searchState.recipes.slice(-1)[0].id);
            }
        }
    }

    const dislike = () => {
        if (searchState.recipes.length !== 0) {
            searchDispatch(searchDislike());
            
            if(searchState.recipes.length < 5) {
                fetchRecipes(searchState.recipes.slice(-1)[0].id);
            }
        }
    }

    const actions = {
        like,
        dislike
    }

    return (
        <ReceipesContext.Provider value={{ searchState, searchDispatch, actions }}>
            {children}
        </ReceipesContext.Provider>
    )
}