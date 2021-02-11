 
# Operación Fuego de Quasar

## Challenge técnico para MercadoLibre

🌸 [Enunciado](https://drive.google.com/file/d/1N6oZX8jjiutOrIxnnLNZzdWAfxQ0Yq1R/view?usp=sharing)

🌸 [Documentacion en Swagger](https://meli-challenge-sofia.herokuapp.com/swagger-ui.html)

🌸 [LinkedIn](https://www.linkedin.com/in/aguirresofia/)

🌸 [Email](sofiaaguirre1@hotmail.com)

### Introducción 📋

Como jefe de comunicaciones rebelde, tu misión es crear un programa en Java que retorne
la fuente y contenido del mensaje de auxilio. Para esto, cuentas con tres satélites que te
permitirán triangular la posición, ¡pero cuidado! el mensaje puede no llegar completo a cada
satélite debido al campo de asteroides frente a la nave.

### Servicios 🚀 

#### POST /api/topsecret

https://meli-challenge-sofia.herokuapp.com/api/topsecret

Servicio que recibe y procesa la información de los distintos Satélites para retornar (en caso de ser posible) 
la fuente y contenido del mensaje de auxilio.

##### Request body

```
{
  "satellites": [
    {
      "name": "kenobi",
      "distance": 100,
      "message": [
        "este",
        "",
        "",
        "mensaje",
        ""
      ]
    },
    {
      "name": "skywalker",
      "distance": 115.5,
      "message": [
        "",
        "es",
        "",
        "",
        "secreto"
      ]
    },
    {
      "name": "sato",
      "distance": 142.7,
      "message": [
        "este",
        "",
        "un",
        "",
        ""
      ]
    }
  ]
}
```

##### Response Body (200 OK)

```
{
    "position": {
        "x": 53.02806374563455,
        "y": -33.10069590083003
    },
    "message": "este es un mensaje secreto"
}
```

##### Response Body (404 NOT FOUND)

```
{
    "timestamp": "2021-02-11T23:20:59.271+00:00",
    "message": "ERROR: All required satellites are not present on request",
    "details": "uri=/api/topsecret"
}
```
