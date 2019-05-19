import React, {Component} from 'react';
import axios from 'axios';


class NewItemForm extends Component {
    constructor() {
        super();
        this.state = (
                {isbn: ""},
                {title: ""},
                {author: ""},
                {numOfPages: ""}
                )
    }

    handleChangeIsbn = (event) => this.setState({isbn: event.target.value});
    handleChangeTitle = (event) => this.setState({title: event.target.value});
    handleChangeAuthor = (event) => this.setState({author: event.target.value});
    handleChangePageNumber = (event) => this.setState({numOfPages: event.target.value});



    handleSubmit = (event) => {
        this.setState({value: 'reset after submit'});
        event.preventDefault();
        console.log(this.state);
        const newItem =  {...this.state};

        console.log(newItem);

        axios.post('http://localhost:8080/api/books', newItem)
            .then(response => {
                console.log(response);
                this.setState({});

            })
            .catch(error => {
                window.alert("Nepavyko. Klaida: " + error);
            });


        // this.props.history.push(/ProductDisplay) - 2 komponentu.
    };

    render() {
        return (
             <div className="App container-fluid">
            <form onSubmit={this.handleSubmit}>
                <h4>New Product Form</h4>
                ISBN ({this.state.isbn}):<br/>
                <input type="text" value={this.state.isbn}
                       onChange={this.handleChangeIsbn}/><br/>

                Title ({this.state.title}):<br/>
                <input type="text" value={this.state.title}
                       onChange={this.handleChangeTitle}/><br/>

                Author ({this.state.author}):<br/>
                <input type="text" value={this.state.author}
                       onChange={this.handleChangeAuthor}/><br/>

                Number of pages ({this.state.numOfPages}):<br/>
                <input type="text" value={this.state.price}
                       onChange={this.handleChangePageNumber}/><br/>

                <input type="submit" value="Save"/>
            </form>
                 </div>
        );
    }
}

export default NewItemForm