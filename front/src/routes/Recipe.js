import React from 'react';
import { makeStyles, styled } from '@material-ui/core';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import List from '@material-ui/icons/List';
import LocalGroceryStore from '@material-ui/icons/LocalGroceryStore';
import MenuBook from '@material-ui/icons/MenuBook';
import IngredientScreen from '../components/recipe/IngredientScreen.js';
import InformationScreen from '../components/recipe/InformationScreen.js'
import EtapeScreen from '../components/recipe/EtapeScreen.js'
import { Link, useParams, Redirect } from 'react-router-dom';
import { Route, Switch } from 'react-router-dom';
import useRecipe from '../hooks/useRecipe';


const useStyles = makeStyles({

  root: {
    backgroundColor: "cyan",
    background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
    position: "fixed",
    bottom: 0,
    left: 0,
    right: 0,
    zIndex:10,
  },
  footer: {
    backgroundColor: '#669933',
    textAlign: 'center',
    clear: 'both'
  },
  label: {
    color: "white",
  },
  recipeTitle: {
    textAlign: "center",
  },
  default: {
    maxWidth: "1000px"
  },
  bottomDiv:{
    height: "50px"
  }
});

function Recipe() {
  const {recipeID} = useParams() 
  const [recipe, online, loading, offlineloading, error, load] = useRecipe(recipeID);
  const classes = useStyles();
  const [value, setValue] = React.useState(0);

  if(!recipe) {
    return ('No recipe found')
  }

  return (
    <div>
      <div>
        <h2 className={classes.recipeTitle}>
          {recipe.designation}
        </h2>
        <div>
          <Switch>
            <Route path='/recipe/:recipeID/informations' component={() => <InformationScreen {...recipe}/>} />
            <Route path='/recipe/:recipeID/ingredients' component={() => <IngredientScreen {...recipe}/>}/>
            <Route path='/recipe/:recipeID/etape' component={() => <EtapeScreen {...recipe}/>}/>
            <Redirect to='/recipe/:recipeID/informations' path='/recipe/:recipeID'/>
          </Switch>

        </div>
      </div>
      <div className={classes.bottomDiv}></div>
      <BottomNavigation
        value={value}
        onChange={(event, newValue) => {
          setValue(newValue);

        }}

        showLabels
        className={classes.root}
      >
        <BottomNavigationAction
          component={Link}
          to={`/recipe/${recipeID}/informations`}
          icon={<MenuBook />}
          label="Information"
           />
        <BottomNavigationAction
          component={Link}
          to={`/recipe/${recipeID}/ingredients`}
          icon={<LocalGroceryStore />}
          label="Ingredient" />
        <BottomNavigationAction
          component={Link}
          to={`/recipe/${recipeID}/etape`}
          icon={<List />}
          label="Etape" />
      </BottomNavigation>
    </div>
  )
}

export default Recipe;