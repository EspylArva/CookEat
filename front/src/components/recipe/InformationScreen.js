import React from 'react';
import EuroIcon from '@material-ui/icons/Euro';
import HourglassEmptyIcon from '@material-ui/icons/HourglassEmpty';
import { Chip } from '@material-ui/core';
import { makeStyles } from '@material-ui/core';
import AwesomeSlider from 'react-awesome-slider';
import 'react-awesome-slider/dist/styles.css';

const total_price = 2;
const prep_time = 15;

const useStyles = makeStyles({
    chip:{
        backgroundColor: "white",
        padding: "5px",
        borderWidth: "2px",
        borderColor:"black"
    },
    img:{
        margin:"0 0 10% 0",
        maxWidth:"100px"
    }
});


function InformationScreen(){
    const classes = useStyles();

    //make a max width
    return(
        <div>
            <AwesomeSlider className={classes.img}> 
                <div data-src="/local_test_res/coquillettes_1.jpg" />
                <div data-src="/local_test_res/coquillettes_2.jpg" />
                <div data-src="/local_test_res/coquillettes_3.jpg" />
            </AwesomeSlider>
            <Chip  className={classes.chip} label={prep_time + "min"} icon={<HourglassEmptyIcon />} variant="outlined"/><br/>
            <Chip  className={classes.chip} label={total_price} icon={<EuroIcon />} variant="outlined" />
        </div>
    )
}

export default InformationScreen;