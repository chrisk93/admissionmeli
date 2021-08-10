#app Mercado Libre



# Requerimientos técnicos del proyecto

Implementación de app para el consumo de API developers.mercadolibre.com.ar/es_ar/items-y-busquedas,  hacer un buscador y listar los productos obtenidos, mostrando el detalle del producto seleccionado. Deseable implementación de API’s JSON, uso de arquitecturas, patrones de diseño, legibilidad de código, documentación y experiencia de usuario, manejo adecuado de versionamiento GIT.



 # Descripción de responsabilidades de las capas propuestas

## Capa de presentación 

actividades, adaptadores y view models, son responsables de interactuar con el usuario y mostrar los resultados de sus interacciones, ademas de efectuar los llamados a los repositorios de datos. 



Paquetes:

-adapters: paged adapters 

-view: actividades, fragments



## Capa de negocio/ acceso a datos

repositorio y api, componentes que son responsables del tratamiento de los datos y el manejo de la lógica de negocio. 



Paquetes:

-api: Servicios

-model: todos aquellos que permiten el procesamiento de datos.

-di: clases necesarias para la inyección de dependencias



## Librerias usadas

-AndroidX

-Retrofit

-Coroutines

-Glide

-viewbinding

-Hilt

-Navigation

