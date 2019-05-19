import React, {Component} from 'react';
import Item from "./Item";

class ItemList extends Component {

    render() {

        let itemsToShow = this.props.itemsToShow;

        return (
            <div>
                {/* Su map pagalba irasome "for" cikla, kuris tures atvaizduoti pasikartojancias produktu korteles*/}
                {this.props.itemsToShow
                    .map( thisItem => <Item key={thisItem.id} item={thisItem} deleteHandler={this.props.deleteHandler}/>)}
            </div>
        );
    }
}

export default ItemList;