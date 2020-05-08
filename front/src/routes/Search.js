import React, { useContext } from 'react';
import CardDeck from '../components/recipeCards/CardDeck';
import MatchButtons from '../components/matchButtons/MatchButtons';
import { makeStyles } from '@material-ui/core/styles';
import { ReceipesContext } from '../contexts/Recipes/Recipes';

const useStyles = makeStyles({
    root: {
        position: "relative",
        width: "100%",
        height: "calc(100vh - 80px)", // 100vh - the size of the header
        overflow: "hidden",
    },
    deck: {
        position: "relative",
        height: "calc(100% - 90px)", // minus the size of buttons
    },
    buttons: {
        margin: "10px auto"
    }
});

function Search() {
    const classes = useStyles();
    const { searchState } = useContext(ReceipesContext);

    return (
        <div className={classes.root}>
            <CardDeck
                recipes={searchState.recipes}
                className={classes.deck}
            />
            <MatchButtons
                className={classes.buttons}
                height={'70px'}
            />
        </div>
    )

}

export default Search;