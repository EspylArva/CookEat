import React, { useState, useMemo, useCallback, useEffect } from 'react';
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

const ORIGIN = {x: 0, y: 0}

function RecipeCard({
  designation,
  prep_time,
  total_price,
  list_gallery,
  className,
  like, 
  dislike
}) {
  const classes = useStyles();
  const [draggingState, setDraggingState] = useState({
    isDragging: false,
    origin: ORIGIN,
    translation: ORIGIN
  });
  
  const styles = useMemo(() => ({
    cursor: draggingState.isDragging ? 'grabbing' : 'grab',
    transform: `translate(${draggingState.translation.x}px, ${draggingState.translation.y}px)`,
    transition: draggingState.isDragging ? 'none' : 'transform 500ms',
  }), [draggingState.isDragging, draggingState.translation]);

  const handleMouseDown = useCallback(({clientX, clientY}) => {
    setDraggingState(state => ({
      ...state,
      isDragging: true,
      origin: {x: clientX, y: clientY}
    }))
  }, [])

  const handleMouseMove = useCallback(({clientX, clientY}) => {
    const translation = {x: clientX - draggingState.origin.x, y: clientY - draggingState.origin.y};

    setDraggingState(state => ({
      ...state,
      translation
    }))
  }, [draggingState.origin]);

  const handleMouseUp = useCallback(() => {
    setDraggingState(state => ({
      ...state,
      isDragging: false
    }))
    
  }, []);

  const handleTouchStart = useCallback(({ targetTouches }) => {
    const { pageX, pageY } = targetTouches[0];

    setDraggingState(state => ({
      ...state,
      isDragging: true,
      origin: {x: pageX, y: pageY}
    }))
  }, [])

  const handleTouchMove = useCallback(({ targetTouches }) => {
    const { pageX, pageY } = targetTouches[0];

    const translation = {x: pageX - draggingState.origin.x, y: pageY - draggingState.origin.y};

    setDraggingState(state => ({
      ...state,
      translation
    }))
  }, [draggingState.origin]);

  const handleTouchEnd = useCallback(() => {
    setDraggingState(state => ({
      ...state,
      isDragging: false
    }))
    
  }, []);

  useEffect(() => {
    if(draggingState.isDragging) {
      // Mouse events
      window.addEventListener('mousemove', handleMouseMove);
      window.addEventListener('mouseup', handleMouseUp);
      // Touch events
      window.addEventListener('touchmove', handleTouchMove);
      window.addEventListener('touchend', handleTouchEnd)
    } else {
      // Mouse events
      window.removeEventListener('mousemove', handleMouseMove);
      window.removeEventListener('mouseup', handleMouseUp);
      // Touch events
      window.removeEventListener('touchmove', handleTouchMove);
      window.removeEventListener('touchend', handleTouchEnd)

      if(draggingState.translation.x > 100) {
        like();
      } else if(draggingState.translation.x < -100) {
        dislike();
      } else {
        setDraggingState(state => ({...state, translation: ORIGIN}))
      }
    }
  }, [draggingState.isDragging, handleMouseMove, handleMouseUp, handleTouchMove, handleTouchEnd]);


  return (
    <div className={className} onTouchStart={handleTouchStart} onMouseDown={handleMouseDown}>
      <Card
        style={styles}
        elevation={5} 
        className={classes.root}
      >
        <CardMedia
            className={classes.media}
            image={list_gallery[0].path}
            title={list_gallery[0].description}
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