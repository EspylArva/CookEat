import React, { useContext } from 'react';
import DeleteIcon from '@material-ui/icons/Delete';
import LocalDiningIcon from '@material-ui/icons/LocalDining';
import RoundButton from './RoundButton';
import { makeStyles } from '@material-ui/core/styles';
import { ReceipesContext } from '../../contexts/Recipes/Recipes';
import { PASS } from '../../contexts/Recipes/searchReducer';

const useStyle = makeStyles({
    root: {
        width: "100%",
        maxWidth: 450,
        display: "flex",
        flexDirection: "row",
        justifyContent: "space-around",
        alignItems: "center"
    },
    button: {
        boxShadow: "3px 7px 10px #888888",
    }
})

function MatchButtons({ className, height }) {
    const classes = useStyle();
    const { searchDispatch } = useContext(ReceipesContext);

    return (
        <div style={{height: height}} className={`${classes.root} ${className}`}>
            <RoundButton
                onClick={() => searchDispatch({ type: PASS })}
                backgroundColor="red" 
                color="white"
                height={height}
            >
                <DeleteIcon fontSize="large" />
            </RoundButton>
            <RoundButton
                onClick={() => searchDispatch({ type: PASS })}
                backgroundColor="green"
                color="white"
                height={height}
            >
                <LocalDiningIcon fontSize="large" />
            </RoundButton>
        </div>
    )
}

export default MatchButtons;