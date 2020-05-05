import React from 'react';
import CardDeck from '../components/recipeCards/CardDeck';
import MatchButtons from '../components/matchButtons/MatchButtons';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
    root: {
        position: "relative",
        width: "100%",
        height: "calc(100vh - 80px)", // 100vh - the size of the header
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
    const receipes = [
        {
            prep_time: 90,
            total_price: 7,
            designation: "Tomates Mozzarella",
            list_gallery: ["https://assets.afcdn.com/story/20200324/2045151_w980h638c1cx3191cy2127cxt0cyt0cxb6381cyb4254.jpg"]
        },
        {
            prep_time: 2,
            total_price: 3,
            designation: "Autre chose",
            list_gallery: ["https://assets.afcdn.com/story/20200324/2045151_w980h638c1cx3191cy2127cxt0cyt0cxb6381cyb4254.jpg"]
        },
    ]

    return (
        <div className={classes.root}>
            <CardDeck
                recipes={receipes}
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