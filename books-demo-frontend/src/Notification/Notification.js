import React from 'react';

const Notification = (props) => {
    return (
        <div className="alert alert-danger alert-dismissible fade show">
            <button type="button" className="close" data-dismiss="alert">&times;</button>
            {props.children}
        </div>
    );
};

export default Notification;
