import React, { useContext, useState, useEffect } from 'react';
import { List } from '@material-ui/core';
import { Link } from 'react-router-dom';
import { ReceipesContext } from '../../contexts/Recipes/Recipes';
import BasketItem from './BasketItem';
import cookeatDb from '../../indexedDb/cookeatDb';

function BasketList() {
    //const { basketState, actions } = useContext(ReceipesContext);
    const [basketState, setBasketState] = useState(undefined);

    useEffect(() => {
        (async () => {
            setBasketState(await cookeatDb.basketRecipes.toArray())
        })()
    }, [])

    if(!basketState || !basketState.length) {
        return (
            <em>Vous n'avez pas de recette dans votre panier. <Link to="/search">Vous pouvez aller matcher ici</Link></em>
        )
    }

    return (
        <React.Fragment>
            <List>
                {
                    basketState.map((recipe, index) => (
                        <BasketItem key={recipe.id} remove={undefined} {...recipe} />
                    ))
                }
            </List>
        </React.Fragment>
    )
}

export default BasketList;