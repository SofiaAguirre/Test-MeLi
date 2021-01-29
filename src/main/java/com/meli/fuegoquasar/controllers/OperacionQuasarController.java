package com.meli.fuegoquasar.controllers;

import com.meli.fuegoquasar.models.MessageReq;
import com.meli.fuegoquasar.models.MessageRes;
import com.meli.fuegoquasar.models.MessageSplitReq;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE)
public class OperacionQuasarController {

    @PostMapping("/topsecret")
    public MessageRes postAllSatellitesMessages(@RequestBody MessageReq messageReq){
        //Idealmente se recibe el mensaje junto con la distancia y nombre de cada uno de los satelites previamente declarados
        //Se devuelve (en caso de poderse descifrar el mensaje y la posicion) una respuesta.
        return new MessageRes();
    }

    @PostMapping("/topsecret_split/{satellite}")
    public void postSatelliteMessage(@PathVariable String satellite, @RequestBody MessageSplitReq messageSplitReq){
        //Retorna 200 si el formato es correcto, caso contrario excepcion BAD REQUEST
    }

    @GetMapping("/topsecret_split")
    public MessageRes getTopSecretMessage(){
        //Implementar Cache para guardar registros de mensajes
        //Cada vez que haya un mensaje (incompleto) de cada uno de los satelites y el mismo sea consumido(completo) se restablecerá la caché, dejandola limpia para nuevos mensajes
        return new MessageRes(); //Se retorna el mensaje entero junto con la posicion (de ser posible), sino excepcion con BAD REQUEST
    }
}
