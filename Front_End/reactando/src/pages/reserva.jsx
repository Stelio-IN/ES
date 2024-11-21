import React from "react";
import "../style/reserva.css";

const login = () =>  {
  return (
   <div className="frame">
   <div className="reser">

   </div>

   <div className="reserva">
    <p>Reserva</p>
    <input type="text" placeholder="Local partida"/>
    <input type="text" placeholder="Local destino"/>
    <input type="text" placeholder="Data da viagem"/>
    <input type="text" placeholder="Ponto de subida"/>
   </div>

   <button className="">
    Reservar
   </button>
   </div>
  );
}

export default login;
