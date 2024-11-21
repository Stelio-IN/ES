import React from "react";
import "../style/pagamento.css";
//import { Link } from "react-dom-router";
//import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';

const login = () =>  {
  return (
   <div className="frame">
    <div className="card">

    </div>

    <div className="card-details">
        <input type="text" placeholder="Numero do cartao"/>
        <input type="text" placeholder="Nome do proprietario"/>
        <input type="text" placeholder="Documento de identidade"/>
        <input type="text" placeholder="Data expiracao" style={{width: '90px'}}/>
        <input type="text" placeholder="Cvv"  style={{width: '50px'}}/>
    </div>
   {/* <Link to="/recibo">
      <div className="pagar">
      </div>
    </Link>*/}
   </div>
  );
}

export default login;
