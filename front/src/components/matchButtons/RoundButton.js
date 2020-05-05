import React from 'react';
import IconButton from '@material-ui/core/IconButton';

import { makeStyles } from '@material-ui/core/styles';

const useStyle = makeStyles({
    button: {
        boxShadow: "3px 7px 10px #888888",
    }
})


function RoundButton({ children, height, color, backgroundColor }) {
    const classes = useStyle();

    return (
        <IconButton style={{height: height, width: height, backgroundColor, color}} className={classes.button} aria-label="delete">
            { children }
        </IconButton>
    )
}

export default RoundButton;