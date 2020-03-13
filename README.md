# DelphosCliente
Parte del cliente de la aplicación Delphos. Esta parte consiste en una aplicación Java Swing en la que en función del rol que tenga el usuario que se logea, podrá acceder a distintas vistas:
- Si es administrador, podrá acceder a la ventana de administración, en la que podrá activar usuarios, asignarles rol y asignarles cursos. De igual manera, va a poder crear cursos.
- Si es profesor, podrá visualizar los cursos de los que es profesor y poner la nota a los alumnos de ese curso.
- Si es alumno, podrá visualizar los profesores que le dan clase y ver la nota que le han puesto. Esta nota estará firmada, por lo que se podrá comprobar su autentincidad.
Todas las comunicaciones con el servidor se cifran de forma simétrica. 
