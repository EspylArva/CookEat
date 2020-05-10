import React from 'react';
import EuroIcon from '@material-ui/icons/Euro';
import HourglassEmptyIcon from '@material-ui/icons/HourglassEmpty';
import { Chip, CardMedia } from '@material-ui/core';
import { makeStyles } from '@material-ui/core';
import AwesomeSlider from 'react-awesome-slider';
import 'react-awesome-slider/dist/styles.css';


const total_price = 2;
const prep_time = 15;

const useStyles = makeStyles({
    chip:{
        backgroundColor: "white",
        padding: "5px",
    }
});


function getInformationScreen(){
    //const classes = useStyles();
    return(
        <div>
            <CardMedia
            >

            </CardMedia>
            <AwesomeSlider>
                <div data-src="/local_test_res/coquillettes_1.jpg" />
                <div data-src="/local_test_res/coquillettes_2.jpg" />
                <div data-src="/local_test_res/coquillettes_3.jpg" />
            </AwesomeSlider>
            <Chip label={prep_time + "min"} icon={<HourglassEmptyIcon />} />
            <Chip label={total_price} icon={<EuroIcon />} />
        </div>
    )
}

export default getInformationScreen;