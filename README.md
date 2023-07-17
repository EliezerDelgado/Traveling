istoria de Usuario

Introducción

En el desarrollo de software se utilizan metodologías Scrum/Kanban para la coordinación entre los equipos. Para el desarrollo de
las prácticas vamos a hacerlas siguiendo el ejercicio como si nos encontráramos en una empresa que sigue dicha metodología.
El objetivo no es conocer en profundidad la metodología, pero sí vamos a realizar las actividades como si fueran Historias de
usuario.

Las historias de usuario, son pequeñas descripciones de los requerimientos de un cliente y están formadas por los siguientes
elementos:

• Título

• Descripción

• Criterios de aceptación

Actividad 1. Iniciar un app ->

-Versión de Android > 80% -> Andorid 6.0 Marshmallow.

-Nombre de la App -> Travelling.

-Usar el dominio de la empresa www.travelWorld.es -> com.travel_world.traveling.

-Subirlo a GitHub.

Actividad 2. Vistas declarativas XML

-Construir Onboarding

-Construir Home

-Uso de style, attributes, drawables, animaciones y  CoordinatorLayout

Actividad 3. Vistas complejas  dinámicas

-Añadidos estilos y atributos

-Login Activity con ConstraintLayouty Data binding.

-usuario y password son Inputs layouts

-Se muestra un SnackBar con get new y create new

ACTIVIDAD 4. Intents implícitos

-Sign Up cuenta con un ConstraintLayout y con ViewBinding.

-Los 3 widgets para Name LastName y AgesRange son Inputs layouts y con control de errores

-El botón Confirm SignUp estará desactivado hasta que tenga el apellido y el nombre correctamente introducido.

-Los tres input muestras sus errores correctamente.

-En la imagen hay un icono de cámara, y si pulsa a la imagen entras en la cámara

-El botón "Ver Condiciones" lleva a la web de developers

ACTIVIDAD 5. Activities – Intents explícitos

-El botón de login, por defecto debe estar deshabilitado hasta tengamos escrito username y password.

-Se accede al home correctamente.

-El botón "Create new" lleva al Activity Registro

-El botón "Me apunto" lleva al Activity Login

-Si acertamos username y password el boton "Login->" nos lleva a la Activity Home

-En la pantalla Home nos llega un log con el nombre con el tag "EXTRAS_SUCCESS"