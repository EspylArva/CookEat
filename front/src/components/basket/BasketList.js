import React, { useContext } from 'react';
import { List } from '@material-ui/core';
import { Link } from 'react-router-dom';
import { ReceipesContext } from '../../contexts/Recipes/Recipes';
import BasketItem from './BasketItem';

function BasketList() {
    const { basketState, actions } = useContext(ReceipesContext);

    if(!basketState.basket || !basketState.basket.length) {
        return (
            <em>Vous n'avez pas de recette dans votre panier. <Link to="/search">Vous pouvez aller matcher ici</Link></em>
        )
    }

    return (
        <React.Fragment>
            <List>
                {
                    basketState.basket.map((recipe, index) => (
                        <BasketItem key={recipe.id} remove={actions.remove} {...recipe} />
                    ))
                }
            </List>
        </React.Fragment>
    )
}

export default BasketList;