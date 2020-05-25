import React, { useContext } from 'react';
import RecipeCard from './RecipeCard';
import { makeStyles } from '@material-ui/core/styles';
import { CircularProgress } from '@material-ui/core';
import CloudOffIcon from '@material-ui/icons/CloudOff';
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
    },
    stateIndicator: {
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "center",
        height: "100%"
    },
    stateIndicatorMessage: {
        margin: "20px",
        fontSize: "1.2em",
        fontWeight: "bold",
        color: "gray",
        textAlign: "center"
    }
})

function CardDeck({recipes, className}) {
    const { searchState, actions } = useContext(ReceipesContext);
    const classes = useStyle();

    if(searchState.fetching && (!searchState.recipes || !searchState.recipes.length)) {
        return (
            <div className={`${className}`}>
                <div className={classes.stateIndicator}>
                    <CircularProgress size={100} />
                    <em className={classes.stateIndicatorMessage}>On vous laisse encore un peu mijoter avant la découverte de votre prochain âme sœur !</em>
                </div>
            </div>
        )
    }

    if(!searchState.recipes || !searchState.recipes.length) {
        return (
            <div className={`${className}`}>
                <div className={classes.stateIndicator}>
                    <CloudOffIcon fontSize="large" />
                    <em className={classes.stateIndicatorMessage}>Oups ! Il semble que vous n'arrivez pas à communiquer avec notre serveur. Essayer de recharger la page.</em>
                </div>
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