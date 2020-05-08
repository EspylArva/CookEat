import React from 'react';
import { makeStyles, styled} from '@material-ui/core';
//import './recipe.css';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import List from '@material-ui/icons/List';
import LocalGroceryStore from '@material-ui/icons/LocalGroceryStore';
import MenuBook from '@material-ui/icons/MenuBook';
import ingredientScreen from '../components/recipe/recipe_ingredientScreen.js';
import informationScreen from '../components/recipe/recipe_informationScreen.js'
import etapeScreen from '../components/recipe/recipe_etapeScreen.js'

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

const recipeName = "Tarte aux pommes de terre";

function switchPage(pageName){
  switch (pageName) {
    case 'informations':
      return (
        informationScreen
      )
    case 'ingredients':
      return(
        ingredientScreen
      )
    case 'etapes':
      return( 
        etapeScreen
        
      )
    default:
      break;
  }
}

function Recipe () {
    const classes = useStyles();
    const [value, setValue] = React.useState(0);
    
    return (
        <div>

            <div>
                <h1 className={classes.recipeTitle}>
                    {switchPage(value)}
                </h1>
            </div>
            <BottomNavigation
                value={value}
                onChange={(event, newValue) => {
                    setValue(newValue);
                }}
                showLabels
                className={classes.root}
                >
                    <BottomBarItem label="Informations" value="informations" icon={<MenuBook />}/>
                    <BottomBarItem label="Ingrédients" value="ingredients" icon={<LocalGroceryStore />} />
                    <BottomBarItem label="Etapes" value="etapes" icon={<List />}/>
            </BottomNavigation>
        </div>

        
    )
}


function Test () {
    const classes = useStyles();
    const [value, setValue] = React.useState(0);

    return (
        <BottomNavigation
        value={value}
        onChange={(event, newValue) => {
            setValue(newValue);
        }}
        showLabels
        className={classes.root}
        >
        <BottomNavigationAction label="Recents" icon={<MenuBook />} />
        <BottomNavigationAction label="Favorites" icon={<MenuBook />} />
        <BottomNavigationAction label="Nearby" icon={<MenuBook />} />
        </BottomNavigation>
  );
    
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