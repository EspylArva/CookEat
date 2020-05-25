import React from 'react';


function EtapeScreen(
    {list_steps}
){
    {list_steps.map((item, index) =>(
        console.log(item)
    ))
    }
    return(
        <div>
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