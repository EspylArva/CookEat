import React from 'react';
import EuroIcon from '@material-ui/icons/Euro';
import HourglassEmptyIcon from '@material-ui/icons/HourglassEmpty';
import { Chip, CardMedia } from '@material-ui/core';
import { makeStyles } from '@material-ui/core';
import AwesomeSlider from 'react-awesome-slider';

const total_price = 2;
const prep_time = 15;
/*
const useStyles = makeStyles({
    chip:{
        backgroundColor: "white",
        padding: "5px",
    }
});
*/

function getInformationScreen(){
    //const classes = useStyles();
    return(
        <div>
            <CardMedia
            image="coquillettes_1.jpg">

            </CardMedia>
            <AwesomeSlider>
                <div data-src="./components/local_test_res/coquillettes_2.jpg" />
            
            </AwesomeSlider>
            <p icon={<EuroIcon/>}> 2</p>
            <Chip label={prep_time + "min"} icon={<HourglassEmptyIcon />} />
            <Chip label={total_price} icon={<EuroIcon />} />
        </div>
    )
}

export default getInformationScreen;