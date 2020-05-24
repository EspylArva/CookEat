import React from 'react';
import { Link } from 'react-router-dom';
import { AppBar, Toolbar, Typography } from '@material-ui/core';


function Header () {
    return (
        <header>
            <AppBar>
                <Toolbar>
                    <Link to="/" style={{ textDecoration: 'none', color: 'inherit' }}>
                        <Typography variant="h6" color="inherit">
                            Cook'Eat
                        </Typography>
                    </Link>
                </Toolbar>
            </AppBar>
        </header>
    );
}


export default Header;