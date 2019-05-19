import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {Switch, Redirect, Route} from 'react-router';
import {BrowserRouter, Link} from 'react-router-dom';
import ItemEditForm from "./BookEditor/ItemEditForm";
import NewItemForm from "./BookEditor/NewItemForm";
import NewLibraryForm from "./InstitutionEditor/NewLibraryForm";


var NoMatch = (props) => {
    var goApp = () => props.history.push("/");
    return <div>Route did not match
        <button onClick={goApp}>Go Home</button></div>;
};

var DemonstruotiNavigacija = (props) => {
    var goHome = () => props.history.push("/");
    return (
        <div>
            At route: {props.location.pathname}
            <button onClick={goHome}>Go Home</button>
            <pre>
        {JSON.stringify(props, null, 2)}
      </pre>
        </div>);
};

var AppContainer = (props) => {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div>
                <Link to='/'>Home</Link> |&nbsp;
                <Link to='/books'>Books</Link> |&nbsp;
                <Link to={`/products/${127}`}>Product by no</Link> |&nbsp;
                <Link to='/edit-form'>Book Edit Form</Link> |&nbsp;
                <Link to='/new-item-form'>New Book Form</Link> |&nbsp;
                <Link to='/non-existant'>Non Existant</Link>
            </div>
            </nav>
            {props.children}
        </div>
    );
};


ReactDOM.render((
    <BrowserRouter>
        <AppContainer>
            <Switch>
                <Route exact path='/' component={App}/>
                <Route path="/products/:id" component={DemonstruotiNavigacija}/>
                <Route path="/books" component={App}/>
                <Route path="/edit-form" component={ItemEditForm}/>
                <Route path="/new-item-form" component={NewItemForm}/>
                <Route path="/new-library-form" component={NewLibraryForm}/>
                <Route path="*" component={NoMatch}/>
                <Route component={NoMatch}/>
            </Switch>
        </AppContainer>
    </BrowserRouter>
), document.getElementById('root'));


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
