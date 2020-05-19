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
    });

    const fetchRecipes = async (id) => {
        if(searchState.fetching || searchState.recipes.length > 5) {
            return;
        }

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
        const id = localStorage.getItem('lastRecipeId');
        console.log(id)
        fetchRecipes(id);
    }, [])

    const like = () => {
        if (searchState.recipes.length !== 0) {
            const recipe = searchState.recipes[0];
            localStorage.setItem('lastRecipeId', searchState.recipes[1].id);
            searchDispatch(searchLike());
            cookeatDb.basketRecipes.put(recipe);
            fetchRecipes(searchState.recipes.slice(-1)[0].id);
        }
    }

    const dislike = () => {
        if (searchState.recipes.length !== 0) {
            localStorage.setItem('lastRecipeId', searchState.recipes[1].id);
            searchDispatch(searchDislike());
            fetchRecipes(searchState.recipes.slice(-1)[0].id);
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