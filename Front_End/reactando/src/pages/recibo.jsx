import React from "react";
//import { Link } from "react-dom-router";
//import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import "../style/recibo.css";


const login = () =>  {
  return (
   <div className="frame">
    <p>O seu Bilhete!</p>
    <div className="recib">

    </div>
   {/* <Link to="/localizacao">
      <button  className="loc">
          Localizar em tempo real
      </button>
    </Link>*/}

    <button className="down">
        Download
    </button>
   </div>
  );
}

export default login;
