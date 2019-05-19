import React, {Component} from 'react';
import axios from "axios";


class ItemEditForm extends Component {

    emptystate = {
        isbn: "",
        title: "",
        author: "",
        numOfPages: ""
    }

    constructor(props) {
        super(props);
        this.state = this.emptystate;
    }

    handleChangeIsbn = (event) => this.setState({isbn: event.target.value});
    handleChangeTitle = (event) => this.setState({title: event.target.value});
    handleChangeAuthor = (event) => this.setState({author: event.target.value});
    handleChangePageNumber = (event) => this.setState({numOfPages: event.target.value});

    // componentWillReceiveProps(nextProps, nextContext) {
    //     this.setState(nextProps.item)
    // }


    handleSubmit = (event) => {
        this.setState({value: 'reset after submit'});
        event.preventDefault();
        console.log(this.state);
        const editedItem = {...this.state};
        //const editedItem =  {id: this.state.id, title: this.state.title};
        console.log(editedItem);


        axios.delete("api/books/" + this.state.isbn)
            .then(() => {
                this.setState({});
            })

        axios.post('http://localhost:8080/api/books', editedItem)
            .then(response => {
                console.log(response);
                this.setState({});

            })
            .catch(error => {
                window.alert("Nepavyko. Klaida: " + error);
            });


    };


    render() {
        return (
            <div className="row, App container-fluid">
                <div className="col-sm-6">
                    <form onSubmit={this.handleSubmit}>
                        <h4>Book Edit Form</h4>
                        <div className="form-group">
                            ISBN:<br/>
                            <input type="text" value={this.state.isbn}
                                   onChange={this.handleChangeIsbn}/><br/>
                            Title:<br/>
                            <input type="text" value={this.state.title}
                                   onChange={this.handleChangeTitle}/><br/>

                            Author:<br/>
                            <input type="text" value={this.state.author}
                                   onChange={this.handleChangeAuthor}/><br/>

                            Number of pages:<br/>
                            <input type="text" value={this.state.numOfPages}
                                   onChange={this.handleChangePageNumber}/><br/><br/>

                            <input type="submit" value="Save"/>
                        </div>
                    </form>
                </div>
                <div className="col-sm-6">
                    <img src={this.state.image}/>
                </div>
            </div>

        );
    }
}

export default ItemEditForm;
