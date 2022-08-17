# Backend Productos

<p align='justify'>Este proyecto esta destinado a la creación de una servicio REST para realizar las 4 operacioens básicas de un crud, para posteriormente ser consumudi mediante un proyecto en Angular y generar una página web de e-commerce. Las propiedades de nuestra aplicación se encuentran en el archivo <a href="./src/main/resources/application.properties" target="__blank">application.properties</a>.</p>
Actualmente, se han implementado las funcionalidades para la entidad Producto. El diagrama de clases es el siguiente:

<br>
<br>

<div align='center'>
    <img src='./Diagrama de clases.png'>
    <figcaption>Diagrama de clases aplicación</figcaption>
</div>

<br>

Los **endpoints** para nuestro backend son los siguientes:

    1) Listar productos: GET http://localhost:3000/api/productos/lista
    2) Listar producto por ID: GET http://localhost:3000/api/productos/lista/{id}
    3) Listar productos por rango de precios: GET http://localhost:3000/api/productos/rango?precioMin={valorMin}&precioMax={valorMax}
    4) Agregar Producto: POST http://localhost:3000/api/productos
    5) Editar un producto: PATCH http://localhost:3000/api/productos/{id}
    5) Eliminar un producto: DELETE http://localhost:3000/api/productos/{id}

Para los endpoints 4 y 5 es necesario añadir los parametros del objeto que se desea añadir y eliminar al cuerpo de la petición espectivamente, así como lo indica la siguiente figura:

```
    {
        "precio": 50000,
        "codigo": "PR-001",
        "imagen": "https://ibb.co/hYrPbFr",
        "nombre": "Fuente generica 450 W",
        "stock": 200,
        "categoria": "PC",
        "descripcion": "Componente de ordenador xxxxx"
    }
```
