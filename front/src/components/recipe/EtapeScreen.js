import React from 'react';
import { makeStyles } from '@material-ui/core';


const useStyles = makeStyles({
    mainDiv:{
        margin: '0 0 10% 0'
    }
})

function EtapeScreen(
    {list_steps}
){
    const classes = useStyles();
    {list_steps.map((item, index) =>(
        console.log(item)
    ))
    }
    return(
        <div className={classes.mainDiv}>
            {list_steps.map((item, index) =>(
               <div>
                   <h4>Etape {item.step_order + 1}</h4>
                   {item.description}
               </div>
            ))
            }
        </div>
    )
}
export default EtapeScreen;