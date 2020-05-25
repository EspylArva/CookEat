import React from 'react';


function IngredientScreen(
    {ingredients}
){
    return(
        <div>
            {ingredients.map((item, index) =>(
               <div>
                   <h4>{item.designation}</h4>
                   <p>{item.price_per_unit} {item.unit}</p>
               </div>
            ))
            }
        </div>
    )
}

export default IngredientScreen;