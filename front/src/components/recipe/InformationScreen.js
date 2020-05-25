import React from 'react';
import EuroIcon from '@material-ui/icons/Euro';
import HourglassEmptyIcon from '@material-ui/icons/HourglassEmpty';
import CalendarTodayIcon from '@material-ui/icons/CalendarToday';
import { Chip } from '@material-ui/core';
import { makeStyles } from '@material-ui/core';
import AliceCarousel from 'react-alice-carousel';
import "react-alice-carousel/lib/alice-carousel.css";
import CloseIcon from '@material-ui/icons/Close';
import DoneIcon from '@material-ui/icons/Done';

const useStyles = makeStyles({
    chip:{
        backgroundColor: "white",
        padding: "5px",
        borderWidth: "2px",
        borderColor:"black",
        margin:'0 0 2% 0'
    },
    imgContainerDiv:{
        left: '50%',
        top: '50%',
    },
    sliderimg:{
        width: '100%',
        height: '500px',
        objectFit: 'cover'
    },
    closeIconCSS:{
        color: 'red'
    },
    doneIconCSS:{
        color: 'green'
    }
});
var months = [ "January", "February", "March", "April", "May", "June", 
           "July", "August", "September", "October", "November", "December" ];
var monthsNb = [0, 1, 2, 3, 4 , 5, 6, 7, 8, 9 , 10 , 11];
var authorizedMonths = []

function InformationScreen(
    {
    total_price,
    prep_time,
    list_gallery,
    start_season,
    end_season}
){
    const classes = useStyles();
    let newDate = new Date()
    let date = newDate.getDate();
    let currentMonth = newDate.getMonth();
    var goodMonth;



    if(currentMonth >= start_season && currentMonth <= end_season){
        goodMonth = true;
    }else{
        goodMonth = false
    }

    const period = "From " + months[start_season] + " to " + months[end_season]
    console.log(start_season, end_season, goodMonth)
    return(
        <div className={classes.imgContainerDiv}>
            <hr/>
            <Chip  className={classes.chip} label={prep_time + "min"} icon={<HourglassEmptyIcon />} variant="outlined"/><span>&emsp;</span>
            <Chip  className={classes.chip} label={total_price} icon={<EuroIcon />} variant="outlined" /><span>&emsp;</span>
            {(goodMonth)
                ? (<Chip  className={classes.chip} label={period} icon={<DoneIcon className={classes.doneIconCSS}/>} variant="outlined" />)
                : ( <Chip  className={classes.chip} label={period} icon={<CloseIcon className={classes.closeIconCSS}/>} variant="outlined" /> )
            }
            
               
              
            <hr/>
 
            
            <AliceCarousel  autoPlay autoPlayInterval="5000" className={classes.img}>
                {list_gallery.map((item) =>(
                    <img src={item.path} className={classes.sliderimg} />
                ))}
            </AliceCarousel>
            
            
        </div>
    )
}
export default InformationScreen;