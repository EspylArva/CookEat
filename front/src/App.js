import React from 'react';
import Header from './components/header/Header';
import Home from './routes/Home';
import { BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';
import { Container } from '@material-ui/core';
import './App.css';


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header />
        <Container className="container">
          <Switch>
            <Route path ='/' component={Home} />
          </Switch>
        </Container>
      </BrowserRouter>
    </div>
  );
}

export default App;
