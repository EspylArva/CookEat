import React from 'react';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import LocalDiningIcon from '@material-ui/icons/LocalDining';
import RoundButton from './RoundButton';
import { makeStyles } from '@material-ui/core/styles';
import { red, green } from '@material-ui/core/colors';

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

    return (
        <div style={{height: height}} className={`${classes.root} ${className}`}>
            <RoundButton backgroundColor="red" color="white" height={height}>
                <DeleteIcon fontSize="large" />
            </RoundButton>
            <RoundButton backgroundColor="green" color="white" height={height}>
                <LocalDiningIcon fontSize="large" />
            </RoundButton>
        </div>
    )
}

export default MatchButtons;