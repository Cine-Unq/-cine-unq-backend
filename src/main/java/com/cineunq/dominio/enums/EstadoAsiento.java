package com.cineunq.dominio.enums;


//RESERVADO es cuando un cliente compro el asiento
//LIBRE cuando esta disponible para la compra
//OCUPADO es cuando el cliente ya esta en la funcion(osea le escanearon el QR)
public enum EstadoAsiento {
    //Desde el cliente en la vista Cinema aparecen los asientos con el estado RESERVADO y LIBRE
    //Desde el admin en la vista Cinema aparecen los asientos con el estado OCUPADO y LIBRE
    RESERVADO,LIBRE,OCUPADO
}
