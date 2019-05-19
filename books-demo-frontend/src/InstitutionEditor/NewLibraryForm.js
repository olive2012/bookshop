import React, {Component} from 'react';
import axios from 'axios';


class NewLibraryForm extends Component {
    constructor() {
        super();
        this.state = (
            {code: ""},
            {name: ""},
            {librarySize: ""},
            {hasReadingHall: ""}
        )
    }

    handleChangeCode = (event) => this.setState({code: event.target.value});
    handleChangeName = (event) => this.setState({name: event.target.value});
    handleChangeLibrarySize = (event) => this.setState({librarySize: event.target.value});
    handleChangeHasReadingHall = (event) => this.setState({hasReadingHall: event.target.value});


    handleSubmit = (event) => {
        this.setState({value: 'reset after submit'});
        event.preventDefault();
        console.log(this.state);
        const newLibrary = {...this.state};

        console.log(newLibrary);

        axios.post('http://localhost:8080/api/library', newLibrary)
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
            <div className="App container-fluid">
                <div className="form-group row">
                    <div className="col-sm-6" >
                    <form onSubmit={this.handleSubmit}>
                        <h4>New Library Form</h4>
                        Code ({this.state.code}):<br/>
                        <input className="form-control" type="text" value={this.state.code}
                               onChange={this.handleChangeCode}/><br/>

                        Name ({this.state.name}):<br/>
                        <input className="form-control" type="text" value={this.state.name}
                               onChange={this.handleChangeName}/><br/>

                        Library Size (Options - NATIONAL/STATE/CITY/COUNTY/TOWN) ({this.state.librarySize}):<br/>
                        <input className="form-control" type="text" value={this.state.librarySize}
                               onChange={this.handleChangeLibrarySize}/><br/>

                        Has Reading Hall? (Options - true/false) ({this.state.hasReadingHall}):<br/>
                        <input  className="form-control" type="text" value={this.state.hasReadingHall}
                               onChange={this.handleChangeHasReadingHall}/><br/>

                        <input type="submit" value="Save"/>
                    </form>
                    </div>
                </div>

            </div>
        );
    }
}

export default NewLibraryForm

//
// < select
// className = "form-control"
// id = "exampleFormControlSelect1" >
//     < option > 1 < /option>
// <option>2</option>
// < option > 3 < /option>
// <option>4</option>
// < option > 5 < /option>
// </select>

