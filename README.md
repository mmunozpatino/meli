# Ejercicio ejemplo Android Nativo

Desarrollado por Mercedes Muñoz Patiño

## Objetivo
Este es un ejercicio realizado en Android Nativo (con Kotlin), que tiene como objetivo demostrarla capacidad de trabajo.

## Librerias de terceros usadas

 - **Gson** : librería para el uso de estructuras json en android
 - **Retrofit**: librería para el desarrollo de un cliente API REST, de una manera más rápida
 - **Kodein**: librería para la injección de dependencias del patrón MVVM
 - **Picasso**: librería que facilita el uso de imagenes

## Patrón de diseño aplicado

El patrón de diseño usado fue **MVVM**, con el objetivo de separar la lógica de negocio (en este caso la petición y la selección de un producto) de la interfaz de usuario. Permitiendo código más limpio y separado.  
El patrón fue aplicado usando corutinas para evitar recargar el hilo de ejecución principal y detener la aplicación mientras se ejecuta dicha petición.
En el ejemplo se puede observar el fragment "SearchFragment" tiene implementada toda la arquitectura desde el fragment, pasando por el viewModel, repositorio, DataSour y servicio. Mientras que los otros dos fragments comparten un sharedView model, esto se debió a que los mismos sólo necesitaban enviarse datos entre sí, sin peticiones a servidores ni nada.

## Navegación

La navegación en la aplicación fue implemtentada mediante un nav component, el cuál se puede observar en el archivo *mobile_navigation.xml*, con el objetivo de simplicar la navegación y conseguir un código más limpio para viajar entre fragments.

## Capturas
<img src="./home.png" width="200" height="400">
<img src="./search.png" width="200" height="400">
<img src="./result.png" width="200" height="400">