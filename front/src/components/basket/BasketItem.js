import React from 'react';
import { ListItem, ListItemText, ListItemAvatar, Avatar } from '@material-ui/core';

function BasketItem({ designation, list_gallery, total_price, prep_time }) {
    return (
        <ListItem>
            <ListItemAvatar>
                <Avatar alt={designation} src={list_gallery[0]} />
            </ListItemAvatar>
            <ListItemText
                primary={designation}
                secondary={`${total_price}€ - ${prep_time}min `}
            />
        </ListItem>
    )
}

export default BasketItem;