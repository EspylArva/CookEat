import React from 'react';
import { ListItem, ListItemText, ListItemAvatar, Avatar } from '@material-ui/core';

function BasketItem({ designation, list_gallery }) {
    return (
        <ListItem>
            <ListItemAvatar>
                <Avatar alt={designation} src={list_gallery[0]} />
            </ListItemAvatar>
            <ListItemText
                primary={designation}
            />
        </ListItem>
    )
}

export default BasketItem;