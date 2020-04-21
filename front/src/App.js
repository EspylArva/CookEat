import React, { Suspense, lazy } from 'react';
import Header from './components/header/Header';
import { BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';
import { Container } from '@material-ui/core';
import './App.css';

const Home = lazy(() => import('./routes/Home'));


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <Container className="container">
          <Suspense fallback={<div>Loading...</div>}>
            <Switch>
              <Route path ='/' component={Home} />
            </Switch>
          </Suspense>
        </Container>
      </BrowserRouter>
    </div>
  );
}

export default App;
