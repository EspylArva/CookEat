import React from 'react';
import RecipeCard from './RecipeCard';
import { makeStyles } from '@material-ui/core/styles';

const useStyle = makeStyles({
    card: {
        position: "absolute",
        top: 0,
        left: "50%",
        transform: "translate(-50%, 0)",
        width: "100%",
        maxWidth: 450,
        height: "calc(100% - 90px)",
    }
})

function CardDeck({recipes}) {
    const classes = useStyle();

    if(!recipes) {
        return "Something is wrong, try to reload the page !"
    }

    return (
        recipes.reverse().map(recipe => (
            <RecipeCard className={classes.card} {...recipe} />
        ))
    )
}

export default CardDeck;