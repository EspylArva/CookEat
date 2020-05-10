import React from 'react';

//get the steps with the ID
const etape1 = "Faire bouillir une casserole d'eau"
const etape2 = "Verser les coquillettes dans l'eau bouillante et les laisser cuire pendant 10 minutes"
const etape3 = "Egouter les pâtes et les servir dans une assiette"
const etape4 = "Recouvrir les pâtes d'un max de gruyère"

const stepList = [etape1, etape2, etape3, etape4]

function getEtapeScreen(){
    return(
        <div>
            {stepList.map((item, index) =>(
                <div>
                    <h4>Etape {index + 1}</h4>
                    <p>{item}</p>
                </div>
            ))
            }
        </div>
    )
}
export default getEtapeScreen;