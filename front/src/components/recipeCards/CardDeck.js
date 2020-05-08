import React, { useContext } from 'react';
import RecipeCard from './RecipeCard';
import { makeStyles } from '@material-ui/core/styles';
import { CSSTransition, TransitionGroup } from 'react-transition-group';
import './cardDeck.css';
import { ReceipesContext } from '../../contexts/Recipes/Recipes';

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
    const { searchState } = useContext(ReceipesContext);
    const classes = useStyle();

    if(!searchState.recipes) {
        return "Something is wrong, try to reload the page !"
    }

    return (
        <TransitionGroup className={`${className} last-${searchState.liked ? 'liked' : 'disliked'}`}>
            {
                [...searchState.recipes].reverse().map((recipe, index) => (
                    <CSSTransition 
                        key={index}
                        timeout={500}
                        classNames={`card`}
                    >
                        <RecipeCard className={classes.card} {...recipe} />
                    </CSSTransition>
                ))
            }
        </TransitionGroup>
    )
}

export default CardDeck;