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
        height: "100%",
    }
})

function CardDeck({recipes, className}) {
    const classes = useStyle();

    if(!recipes) {
        return "Something is wrong, try to reload the page !"
    }

    return (
        <div className={className}>
            {
                [...recipes].reverse().map((recipe, index) => (
                    <RecipeCard key={index} className={classes.card} {...recipe} />
                ))
            }
        </div>
    )
}

export default CardDeck;