import React from 'react';
import { AppBar, Toolbar, Typography } from '@material-ui/core';
import style from './header.css';


function Header () {
    return (
        <header>
            <AppBar>
                <Toolbar>
                    <Typography variant="h6" color="inherit">
                        Cook'Eat
                    </Typography>
                </Toolbar>
            </AppBar>
        </header>
    );
}


export default Header;