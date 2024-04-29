# Vinyls
  
## Descripción  
  
Vinyls es una aplicación diseñada para coleccionistas de música que desean organizar, crear y conocer colecciones de música. Estas colecciones son repositorios de álbumes, canciones y artistas que contienen la información de cada uno.

## Ejecución

### Advertencia
La aplicación cuenta con una base de datos desplegada en una instancia en la nube. En caso de no estar en operación aparecerá el mensaje "Network error". Por temas de costos, esta instancia se encuentra en una máquina Spot, por lo que puede ser apagada en cualquier momento. En caso de ocurrir, por favor avisar a cualquier responsable del proyecto para encender el backend
de forma oportuna 

### Instalación del APK

Para instalar la aplicación directamente a través de un archivo APK, siga estos pasos:

1. **Habilitar la Instalación desde Fuentes Desconocidas:**

   En el dispositivo Android, ve a `Ajustes > Seguridad` y habilita la opción `Orígenes desconocidos` o `Instalar aplicaciones desconocidas`, dependiendo de la versión de tu sistema operativo. Esto permitirá la instalación de aplicaciones fuera de la Google Play Store.

2. **Transfiere el APK a tu Dispositivo:**

   Puedes transferir el archivo APK a tu dispositivo mediante un cable USB, descargarlo directamente desde el repositorio, o utilizar cualquier método de transferencia de archivos que prefieras.

3. **Instala el APK:**

   Navega hasta la ubicación del archivo APK en tu dispositivo. Toca el archivo APK y sigue las instrucciones en pantalla para completar la instalación.

4. **Abre la Aplicación:**

   Una vez instalada, puedes abrir la aplicación desde tu lista de aplicaciones. No necesitas realizar ningún paso adicional para comenzar a usar la aplicación.

---

### Construcción Local
  
#### Requisitos Previos  
  
Antes de comenzar, se debe tener instalado:  
- Android Studio  
- JDK (Java Development Kit) versión 11 o superior  
- Kotlin versión 1.X.X (se instala automáticamente con Android Studio)  
- Git para clonar el proyecto  
  
#### Configuración del Entorno  
  
1. **Descargar el último releas:**  
  
2. **Abrir el Proyecto en Android Studio:**  
  
Abre Android Studio y selecciona 'Open an existing project', navega a la carpeta donde clonaste el repositorio y selecciona el proyecto. Se debe escoger la carpeta llamada `vinilos_app`.  
  
3. **Instalar las Dependencias:**  
  
Android Studio debería encargarse automáticamente de descargar todas las dependencias necesarias especificadas en el archivo `build.gradle` del proyecto. Si necesitas realizar una acción manual, Android Studio te lo indicará.  
  
### Compilación y Ejecución  
  
1. **Selecciona un Dispositivo de Destino:**  
  
Puedes ejecutar la aplicación en un emulador o en un dispositivo físico. Para configurar un emulador, ve a Device Manager en Android Studio y crea un nuevo Virtual Device. Si prefieres usar un dispositivo físico, asegúrate de habilitar la opción de 'Depuración USB' en las opciones de desarrollador.  
  
2. **Ejecuta la Aplicación:**  
  
Haz clic en 'Run' y selecciona el dispositivo de destino. Android Studio compilará la aplicación y la instalará en el dispositivo seleccionado.

3. ** Ejecución de pruebas **
La aplicación cuenta con pruebas E2E escritas para funcionar. Cuándo todas las dependencias de Gradle estén creadas se puede navegar a la ruta `java/com/example/vinilos (androidTest)` y ejecutar las pruebas
