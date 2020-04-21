import React from 'react';
import Header from './components/header/Header';
import { Container } from '@material-ui/core';
import './App.css';


function App() {
  return (
    <div className="App">
      <Header />
      <Container className="container">
        <h1>Welcome on the first page of Cook'eat</h1>
      </Container>
    </div>
  );
}

export default App;
