import React, { useState } from 'react';
import { makeStyles, List, CircularProgress, Snackbar, Dialog, DialogTitle, ListItem, ListItemText, Container } from '@material-ui/core';
import CloudOffIcon from '@material-ui/icons/CloudOff';
import CloseIcon from '@material-ui/icons/Close';
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
    },
    snackbar: {
        cursor: 'pointer'
    }
});

function BasketList() {
    const classes  = useStyles();

    const [basket, loading, error, actions] = useCookeatDB('basketRecipes');
    const [ checkedItems, setCheckedItems ] = useState(new Array());
    const [ isShoppingListOpen, setIsShoppingListOpen ] = useState(false);

    function handleItemToggle(id) {
        if(checkedItems.includes()) {
            setCheckedItems(checkedItems.filter(element => id !== element))
        } else {
            setCheckedItems([...checkedItems, id])
        }
    }

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
                        <BasketItem onToggle={handleItemToggle} key={recipe.id} remove={actions.remove} {...recipe} />
                    ))
                }
            </List>
            <Snackbar
                className={classes.snackbar}
                open={checkedItems.length != 0 && !isShoppingListOpen}
                anchorOrigin={{ 
                    vertical: "bottom", 
                    horizontal: "center"
                }}
                message={`Découvrez votre liste de course !`}
                onClick={() => {setIsShoppingListOpen(true)}}
            />            
            <Dialog fullScreen onClose={() => {setIsShoppingListOpen(false)}} aria-labelledby="simple-dialog-title" open={isShoppingListOpen}>
                <CloseIcon
                    aria-label="close"
                    fontSize="large"
                    onClick={() => {setIsShoppingListOpen(false)}}
                />
                <Container>
                    <DialogTitle id="simple-dialog-title">
                        Liste de course
                    </DialogTitle>
                    <List>
                        {
                            basket
                            .reduce((accumulator, recipe) => {
                                if(checkedItems.includes(recipe.id)) {
                                    return [...accumulator, ...recipe.ingredients]
                                }
                                return accumulator
                            }, new Array())
                            .map(ingredient => (
                                <ListItem>
                                    <ListItemText 
                                        primary={ingredient.designation}
                                        secondary={`${ingredient.quantity || ''} ${ingredient.unit || ''}`}
                                    />
                                </ListItem>
                            ))
                        }
                    </List>
                </Container>
            </Dialog>
        </React.Fragment>
    )
}

export default BasketList;