import React, { Suspense, lazy } from 'react';
import Header from './components/header/Header';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { Container } from '@material-ui/core';
import './App.css';

const Home = lazy(() => import('./routes/Home'));
const Search = lazy(() => import('./routes/Search'));
const Basket = lazy(() => import('./routes/Basket'));


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <Container className="container">
          <Suspense fallback={<div>Loading...</div>}>
            <Switch>
              <Route path='/search' component={Search} />
              <Route path='/basket' component={Basket} />
              <Route path ='/' component={Home} />
            </Switch>
          </Suspense>
        </Container>
      </BrowserRouter>
    </div>
  );
}

export default App;
