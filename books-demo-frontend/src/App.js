import React, {Component} from 'react';
import './App.css';
import axios from 'axios';
import ItemList from "./BookEditor/ItemList";
import Item from "./BookEditor/Item";
import Notification from "./Notification/Notification";
import Visual1 from "./Visual1/Visual1";
// import {Doughnut} from 'react-chartjs-2';

class App extends Component {
    state = {
        books: [],
        libraries: [],
        bookshops: []
    }

    componentDidMount() {
        this.refreshData();
    }

    refreshData = () => {
        axios.get("/api/books")
            .then(result => {
                    this.setState({books: result.data})
                }
            );

        axios.get("/api/library")
            .then(result => {
                    this.setState({libraries: result.data})
                }
            );

        axios.get("/api/bookshop")
            .then(result => {
                    this.setState({bookshops: result.data})
                }
            );

    }

    deleteBook = (isbn) => {
        axios.delete("api/books/" + isbn)
            .then(() => {
                this.refreshData()
            })
    }

    deleteBookShop = (code) => {
        axios.delete("api/bookshop/" + code)
            .then(() => {
                this.refreshData()
            })
    }


    deleteLibrary = (code) => {
        axios.delete("api/library/" + code)
            .then(() => {
                this.refreshData()
            })
    }


    render() {
        return (
            <div className="App container-fluid m-3">
                <div>
                    <div className="jumbotron jumbotron-fluid">
                        <h1 className="display-4">Book institutions' administration service </h1>
                        <p className="lead">Welcome.</p>
                    </div>
                </div>


                <div className="row">
                    <div className="col-4 border border-success mr-5">


                        <h3>Libraries</h3>
                        {this.state.libraries.length < 5 ?
                            <Notification>Not enough libraries!</Notification> : ""}
                        <table className="table table-striped">

                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Code</th>
                                <th>Size</th>
                                <th>Reading Hall</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.state.libraries.map(item =>
                                <tr>
                                    <td>{item.name}</td>
                                    <td>{item.code}</td>
                                    <td><span className="badge badge-secondary">{item.librarySize} </span></td>
                                    <td>{item.hasReadingHall ? "📖" : "-"}</td>
                                    <td>
                                        <button className="btn btn-success mt-2"
                                                onClick={() => this.deleteLibrary(item.code)}>Delete
                                        </button>
                                    </td>
                                </tr>)}
                            </tbody>
                        </table>

                    </div>
                    <div className="col-4 border border-success mr-5">
                        <h3>Bookshops</h3>
                        {this.state.bookshops.length < 5 ?
                            <Notification>Not enough bookshops!</Notification> : ""}

                        <table className="table table-striped">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Code</th>
                                <th>Size</th>
                                <th>Accept cards</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.state.bookshops.map(item =>
                                <tr>
                                    <td>{item.name}</td>
                                    <td>{item.code}</td>
                                    <td><span className="badge badge-secondary">{item.bookshopSize}</span></td>
                                    <td>{item.acceptsCards ? "💳" : "-"}</td>
                                    <td>
                                        <button className="btn btn-success mt-2"
                                                onClick={() => this.deleteBookShop(item.code)}>Delete
                                        </button>
                                    </td>
                                </tr>)}
                            </tbody>
                        </table>

                    </div>

                    <div className="col-2 border border-success">
                        <h3>Actions</h3>

                        <button onClick={this.onClickHandler} type="button" className="btn btn-success mt-2">
                            Add book to an institution
                        </button>
                        <br/>
                        <button onClick={this.onClickHandler} type="button" className="btn btn-success mt-2">
                            Delete book from an institution
                        </button>
                        <br/>
                        <button onClick={this.onClickHandler} type="button" className="btn btn-success mt-2">
                            Add new book
                        </button>
                        <br/>
                        <button onClick={this.onClickHandler} type="button" className="btn btn-success mt-2">Add
                            new institution
                        </button>

                    </div>


                </div>

                <div className="row pt-5">
                    <h3>Book List</h3>


                    <div className="col-sm-12">
                        <ItemList itemsToShow={this.state.books} deleteHandler={this.deleteBook}/>
                    </div>


                </div>
                <div>
                    <Visual1 books={this.state.books}/>
                </div>
            </div>


        );
    }


}

export default App;
