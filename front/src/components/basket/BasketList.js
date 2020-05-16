import React from 'react';
import { List } from '@material-ui/core';
import { Link } from 'react-router-dom';
import BasketItem from './BasketItem';
import useCookeatDB from '../../hooks/useCookEatDB';

function BasketList() {
    const [basket, loading, error, actions] = useCookeatDB('basketRecipes');

    if(error) {
        return (
            <em>Il y a eu une erreur lors du chargement du panier. En attendant <Link to="/search">vous pouvez aller matcher ici</Link></em>
        )
    }

    if(!basket || !basket.length) {
        if(loading) {
            return (
                <em>Nous chargeons vos recettes sauvagés depuis votre téléphone</em>
            )
        }

        return (
            <em>Vous n'avez pas de recette dans votre panier. <Link to="/search">Vous pouvez aller matcher ici</Link></em>
        )
    }

    return (
        <React.Fragment>
            <List>
                {
                    basket.map((recipe, index) => (
                        <BasketItem key={recipe.id} remove={undefined} {...recipe} />
                    ))
                }
            </List>
        </React.Fragment>
    )
}

export default BasketList;