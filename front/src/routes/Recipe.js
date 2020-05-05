import React from 'react';
import { makeStyles, styled} from '@material-ui/core';
//import './recipe.css';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import RestoreIcon from '@material-ui/icons/Restore';
import List from '@material-ui/icons/List';
import LocalGroceryStore from '@material-ui/icons/LocalGroceryStore';
import MenuBook from '@material-ui/icons/MenuBook';

const useStyles = makeStyles({

    root: {
        backgroundColor: "cyan",
        background:'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
        position:"fixed",
        bottom:0,
        left: 0,
        right:0,
    },
    footer: {
        backgroundColor:'#669933',
        textAlign:'center',
        clear: 'both'
    },
    label:{
        color:"white",
    },
    recipeTitle:{
        textAlign: "center",
    },
    rooot: {
        width: 500,
      },
  });


const BottomBarItem = styled(BottomNavigationAction)({
    color:"white",
});

const recipeName = "Tarte aux pommes de terre"
/*
const [value, setValue] = React.useState('nearby');
  
const handleChange = (event, newValue) => {
    setValue(newValue);
};  
*/
function Recipe () {
    const classes = useStyles();
    return (
        <div>

            <div>
                <h1 className={classes.recipeTitle}>
                    {recipeName}
                </h1>
            </div>
            <BottomNavigation
                showLabels
                className={classes.root} 
                //value={value} onChange={handleChange}
                >
                    <BottomBarItem label="Informations" value="informations" icon={<MenuBook />}/>
                    <BottomBarItem label="Ingrédients" value="ingredient" icon={<LocalGroceryStore />} />
                    <BottomBarItem label="Etapes" value="etapes" icon={<List />}/>
            </BottomNavigation>
        </div>

        
    )
}


function Test () {
    const classes = useStyles();
    return (
        <div>

            <div class="content">
            <h1>Sticky Footer with Negative Margin 1</h1>
            <p><button id="add">Add Content</button></p>
            <div class="push"></div>
            </div>

            <footer class="footer">
            Footer 
            </footer>
        </div>
        
    )
}

export default Recipe;

/** 
import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from "@bit/mui-org.material-ui.styles";
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';

const styles = {
  root: {
    width: 500
  }
};

class LabelBottomNavigation extends React.Component {
  state = {
    value: 'recents'
  };

  handleChange = (event, value) => {
    this.setState({ value });
  };

  render() {
    const { classes } = this.props;
    const { value } = this.state;

    return <BottomNavigation value={value} onChange={this.handleChange} className={classes.root}>
        <BottomNavigationAction label="Recents" value="recents"  />
        <BottomNavigationAction label="Favorites" value="favorites"  />
        <BottomNavigationAction label="Nearby" value="nearby"  />
        <BottomNavigationAction label="Folder" value="folder" />
      </BottomNavigation>;
  }
}

LabelBottomNavigation.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(LabelBottomNavigation);
*/