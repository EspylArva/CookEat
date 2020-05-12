import React from 'react';
import { ListItem, ListItemText, ListItemAvatar, Avatar, ListItemSecondaryAction, IconButton } from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/Delete';

function BasketItem({ id, designation, list_gallery, total_price, prep_time, remove }) {
    return (
        <ListItem>
            <ListItemAvatar>
                <Avatar alt={designation} src={list_gallery[0]} />
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