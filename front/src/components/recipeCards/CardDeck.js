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
    const { searchState, actions } = useContext(ReceipesContext);
    const classes = useStyle();

    if(!searchState.recipes || !searchState.recipes.length) {
        return (
            <div className={`${className}`}>
                Something went wrong, try to reload the page !
            </div>
        )
    }

    const showedRecipes = [...searchState.recipes].slice(0, 3); // Limiting number of rendered cards for performance issues

    return (
        <TransitionGroup className={`${className} last-${searchState.liked ? 'liked' : 'disliked'}`}>
            {
                showedRecipes.reverse().map((recipe, index) => (
                    <CSSTransition 
                        key={recipe.id}
                        timeout={500}
                        classNames={`card`}
                    >
                        <RecipeCard className={classes.card} like={actions.like} dislike={actions.dislike} {...recipe} />
                    </CSSTransition>
                ))
            }
        </TransitionGroup>
    )
}

export default CardDeck;