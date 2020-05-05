import React from 'react';
import { Card, CardMedia, CardActionArea, Typography, Chip, CardContent } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import EuroIcon from '@material-ui/icons/Euro';
import HourglassEmptyIcon from '@material-ui/icons/HourglassEmpty';

const useStyles = makeStyles({
  root: {
    margin: "0 auto",
    position: "relative",
    width: "100%",
    height: "100%",
    color: "white",
    display: "flex",
    flexDirection: "column",
    justifyContent: "flex-end",
  },
  media: {
    position: "absolute",
    top: 0,
    left: 0,
    width: "100%",
    height: "100%",
  },
  chipsList: {
    display: 'flex',
    justifyContent: 'start',
    flexWrap: 'wrap',
    '& > *': {
      margin: 3,
    },
  },
  content: {
    background: 'linear-gradient(rgba(0, 0, 0, 0), 30%, rgba(0, 0, 0, 0.8))',
  },
  chip: {
    backgroundColor: "white",
    padding: "5px",
  }
});

function RecipeCard({
  designation,
  prep_time,
  total_price,
  list_gallery,
  className
}) {
  const classes = useStyles();

  return (
    <div className={className}>
      <Card
        elevation={10} 
        className={classes.root}
      >
        <CardMedia
            className={classes.media}
            image={list_gallery[0]}
            title={designation}
          />
        <CardActionArea>
          <CardContent className={classes.content}>
            <Typography variant="h3" component="h2">
              {designation}
            </Typography>
            <div className={classes.chipsList}>
              <Chip className={classes.chip} label={prep_time + "min"} icon={<HourglassEmptyIcon />} />
              <Chip className={classes.chip} label={total_price} icon={<EuroIcon />} />
            </div>
          </CardContent>
        </CardActionArea>
      </Card>
    </div>
  )
}

export default RecipeCard;