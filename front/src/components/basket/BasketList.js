import React, { useContext } from 'react';
import { List, ListItem, ListItemText } from '@material-ui/core';
import { ReceipesContext } from '../../contexts/Recipes/Recipes';

function BasketList() {
    const { basketState } = useContext(ReceipesContext);

    return (
        <React.Fragment>
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

export default BasketList;