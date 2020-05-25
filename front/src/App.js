import React, { Suspense, lazy } from 'react';
import Header from './components/header/Header';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';
import { Container } from '@material-ui/core';
import './App.css';

const Home = lazy(() => import('./routes/Home'));
const Recipe = lazy(() => import('./routes/Recipe'));
const Search = lazy(() => import('./routes/Search'));
const Basket = lazy(() => import('./routes/Basket'));


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <Container className="container">
          <Suspense fallback={<div></div>}>
            <Switch>
              <Route path = '/recipe/:recipeID' component={Recipe}/>
              <Route path='/search' component={Search} />
              <Route path='/basket' component={Basket} />
              <Redirect path ='/' to='/search' />
            </Switch>
          </Suspense>
        </Container>
      </BrowserRouter>
    </div>
  );
}

export default App;
