import React, {Component, useState, useEffect } from 'react';
import {Navbar, NavbarBrand, Container, NavbarToggler, NavbarCollapse, NavbarText } from 'reactstrap';
import {Link} from 'react-router-dom';
import logo from './logo.svg';
import './App.css';

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return <Navbar  dark expand="md" style={{ backgroundColor: '#131F48' }}>
            <Container>
            <NavbarBrand tag={Link} to="/"><img src={logo} className="App-logo" alt="logo"  style={{height: 40,width: 40}} />Random Epigram</NavbarBrand>
            <NavbarToggler />
            <NavbarText>
            </NavbarText>
            </Container>
        </Navbar>;
    }
}