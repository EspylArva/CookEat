import React, { useState } from 'react';
import { ListItem, ListItemText, ListItemAvatar, Avatar, ListItemSecondaryAction, IconButton } from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/Delete';

function BasketItem({ id, designation, list_gallery, total_price, prep_time, remove, onToggle }) {
    const [toggle, setToggle] = useState(false);

    function handleToggle() {
        setToggle(!toggle)
        onToggle(id);
    }

    return (
        <ListItem style={toggle ? {backgroundColor: 'rgba(63, 81, 181)', color: 'white'} : {}}>
            <ListItemAvatar>
                <Avatar onClick={handleToggle} alt={list_gallery[0].description} src={list_gallery[0].path} />
            </ListItemAvatar>
            <ListItemText
                primary={designation}
                secondary={`${total_price}€ - ${prep_time}min `}
            />
            <ListItemSecondaryAction>
                <IconButton onClick={() => remove(id)} edge="end" aria-label="delete">
                    <DeleteIcon />
                </IconButton>
            </ListItemSecondaryAction>
        </ListItem>
    )
}

export default BasketItem;