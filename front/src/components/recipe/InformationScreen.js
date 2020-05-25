import React from 'react';
import EuroIcon from '@material-ui/icons/Euro';
import HourglassEmptyIcon from '@material-ui/icons/HourglassEmpty';
import CalendarTodayIcon from '@material-ui/icons/CalendarToday';
import { Chip } from '@material-ui/core';
import { makeStyles } from '@material-ui/core';
import AwesomeSlider from 'react-awesome-slider';
import 'react-awesome-slider/dist/styles.css';
import {useParams} from "react-router-dom";
import AliceCarousel from 'react-alice-carousel';
import "react-alice-carousel/lib/alice-carousel.css";

const useStyles = makeStyles({
    chip:{
        backgroundColor: "white",
        padding: "5px",
        borderWidth: "2px",
        borderColor:"black",
        margin:'0 0 2% 0'
    },
    img:{
        minWidth:"100px",
        maxWidth:"1000px"
    },
    imgContainerDiv:{
        alignItems:"center",
        Display: 'flex',

    }
});
var months = [ "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", 
           "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre" ];

var Carousel = require('react-responsive-carousel').Carousel;

function InformationScreen(
    {designation,
    total_price,
    prep_time,
    list_gallery,
    start_season,
    end_season}
){
    let { recipeID } = useParams();
    const classes = useStyles();
    //make a max width 
    //<div data-src={list_gallery[0]}/>
    //{list_gallery.map((item) =>(<div>{item}</div>))}
    const period = "De " + months[start_season] + " à " + months[end_season]
    console.log(list_gallery)
    return(
        <div className={classes.imgContainerDiv}>

            <AliceCarousel  autoPlay autoPlayInterval="5000" className={classes.img}>
                {list_gallery.map((item) =>(
                    <img src={item.path} className={classes.img} />
                ))}
            </AliceCarousel>
            
            <Chip  className={classes.chip} label={prep_time + "min"} icon={<HourglassEmptyIcon />} variant="outlined"/><br/>
            <Chip  className={classes.chip} label={total_price} icon={<EuroIcon />} variant="outlined" /><br/>
            <Chip  className={classes.chip} label={period} icon={<CalendarTodayIcon />} variant="outlined" />
        </div>
    )
}
//
export default InformationScreen;