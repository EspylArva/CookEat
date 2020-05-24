import React from 'react';

function EtapeScreen(
    {list_steps}
){
    return(
        <div>
            {list_steps.map((item, index) =>(
                <div>
                    <p>{item}</p>
                </div>
            ))
            }
        </div>
    )
}
export default EtapeScreen;