import React, { useContext } from 'react';
import { List, ListItem, ListItemText } from '@material-ui/core';
import { ReceipesContext } from '../contexts/Recipes/Recipes';

function Basket() {
    const { basketState } = useContext(ReceipesContext);

    return (
        <React.Fragment>
            <h1>Panier</h1>
            <List>
                {
                    basketState.basket.map((recipe, index) => (
                        <ListItem key={index}>
                            <ListItemText
                                primary={recipe.designation}
                            />
                        </ListItem>
                    ))
                }
            </List>
        </React.Fragment>
    )
}

export default Basket;