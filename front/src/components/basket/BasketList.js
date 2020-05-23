import React from 'react';
import { makeStyles,List } from '@material-ui/core';
import { CircularProgress } from '@material-ui/core';
import CloudOffIcon from '@material-ui/icons/CloudOff';
import { Link } from 'react-router-dom';
import BasketItem from './BasketItem';
import useCookeatDB from '../../hooks/useCookEatDB';

const useStyles = makeStyles({
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
});

function BasketList() {
    const classes  = useStyles();

    const [basket, loading, error, actions] = useCookeatDB('basketRecipes');

    if(error) {
        return (
            <div className={classes.stateIndicator}>
                <CloudOffIcon fontSize="large" />
                <em className={classes.stateIndicatorMessage}>Oups ! Il semblerait que nous n'avons pas réussi à récupérer le panier. Essayez de recharger la page.</em>
            </div>
        )
    }

    if(!basket || !basket.length) {
        if(loading) {
            return (
                <div className={classes.stateIndicator}>
                    <CircularProgress size={100} />
                    <em className={classes.stateIndicatorMessage}>Nous chargeons vos recettes sauvegardés sur votre système.</em>
                </div>
            )
        }

        

        return (
            <div className={classes.stateIndicator}>
                <em className={classes.stateIndicatorMessage}>Vous n'avez pas de recette dans votre panier. <Link to="/search">Vous pouvez aller matcher ici</Link></em>
            </div>
        )
    }

    return (
        <React.Fragment>
            <List>
                {
                    basket.map((recipe, index) => (
                        <BasketItem key={recipe.id} remove={actions.remove} {...recipe} />
                    ))
                }
            </List>
        </React.Fragment>
    )
}

export default BasketList;