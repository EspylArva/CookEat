import React, { createContext, useReducer } from 'react';
import searchReducer from './searchReducer';

export const ReceipesContext = createContext();

export function ReceipesProvider({ children }) {
    const [searchState, searchDispatch] = useReducer(searchReducer, {
        recipes: [
            {
                id: 10,
                prep_time: 90,
                total_price: 7,
                designation: "Tomates Mozzarella",
                list_gallery: ["https://assets.afcdn.com/story/20200324/2045151_w980h638c1cx3191cy2127cxt0cyt0cxb6381cyb4254.jpg"]
            },
            {
                id: 11,
                prep_time: 2,
                total_price: 3,
                designation: "Brick",
                list_gallery: ["https://assets.afcdn.com/recipe/20170412/60294_w300h400c1cx2619cy1746.jpg"]
            },
            {
                id: 11,
                prep_time: 2,
                total_price: 3,
                designation: "Naan à la vache kiri",
                list_gallery: ["https://assets.afcdn.com/teaser/20200506/0__12_w300h400c1.jpg"]
            },
            {
                id: 11,
                prep_time: 2,
                total_price: 3,
                designation: "Smoothie fraise",
                list_gallery: ["https://assets.afcdn.com/teaser/20200504/0__7_w300h400c1.jpg"]
            },
            {
                id: 11,
                prep_time: 2,
                total_price: 3,
                designation: "Autre chose",
                list_gallery: ["https://assets.afcdn.com/story/20200324/2045151_w980h638c1cx3191cy2127cxt0cyt0cxb6381cyb4254.jpg"]
            },
        ]
    });

    return (
        <ReceipesContext.Provider value={{ searchState, searchDispatch }}>
            { children }
        </ReceipesContext.Provider>
    )
}