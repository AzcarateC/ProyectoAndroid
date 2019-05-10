# ProyectoAndroid
Seminario de Lenguaje 2019 Trabajo Practico

## Debug en Windows

Para probar la app y debuguearla podés usar un emulador o conectar un celular previo configurarlo para activar el modo desarrollador, es posible que se necesiten desactivar algunas configuraciones de seguridad de conexion USB en el modo desarrollador del celular.

```
1. Abre una consola y posicionate en la carpeta del proyecto
2. Utiliza el comando gradlew assembleDebug.
Se va a generar un APK para debuguear, ubicado en MyProject\app\build\outputs\apk\debug
3. Utiliza el comando gradlew installDebug.
Se va a compilar e instalar en un dispositivo conectado o emulador en ejecucion

Puede que tambien se requiera modificar el archivo local.properties con la direccion donde se encuentra el android SDK del usuario (modificar la linea sdk.dir=sdk.dir=C\:\\Users\\User-PC\\AppData\\Local\\Android\\Sdk )
```

Comandos basicos de git
```
git status
git add
git add .
git commit
git commit -m "Esto es un comentario de commit"
git push
```

Comandos basicos de gradle
```
gradlew assembleDebug
gradlew installDebug
```

Tambien es posible usar el emulador de forma independiente de Android Studio
https://stackoverflow.com/a/45522559/8877384
