import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavBar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import EpigramComponent from './Epigram';

class Home extends Component {
    render() {
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                <EpigramComponent />
                </Container>
            </div>
        );
    }
}

export default Home;