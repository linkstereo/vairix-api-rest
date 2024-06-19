# vairix-api-rest

## HOW INSTALL

### INSTALACION DE LA DEPENDENCIA MARVEL-ADAPTER

   - Clonar el proyecto vairix-marvel-adapter
   - Ubicarse dentro del proyecto y ejecutar: 
```console
mvn clean install
```
- Ubicarse dentro de /target y ejecutar:
```console
   mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file  \
     -Dfile=marvel-adapter-0.0.1-SNAPSHOT.jar.original \
     -DgroupId=com.vairix.marveladapter -DartifactId=marvel-adapter-original \
     -Dversion=0.0.1 -Dpackaging=jar
```

### INSTALACION y EJECUCION DE VAIRIX-API-REST
- Clonar el proyecto vairix-api-rest
- Nos ubicamos dentro del proyecto /vairix-api-rest
- Armamos el api-rest ya con la dependencia correctamente instalada (verificamos en el pom que esto sea así):
- POM:
```xml
  <dependency>
    <groupId>com.vairix.marveladapter</groupId>
    <artifactId>marvel-adapter-original</artifactId>
    <version>0.0.1</version>
    <scope>compile</scope>
  </dependency>
```
- Luego instalamos vairis-api-rest
```console
mvn clean install
```
- Para levantar el proyecto ejecutamos el siguiente comando (es importante señalar que el servicio quedará activo en tanto la terminal siga activa o bien se envie ctrl+c):
```console
mvn spring-boot:run
```
- Una vez levantado, se pueden verificar los servicios con SWAGGER
```console
localhost:8080/swagger-ui/index.html
```

## AUTENTICACION DE LA APP COMO USUARIO
Aunque no lo vemos, de manera automatica el front hace un registro de un usuario dentro
del sistema y procede a usar la token para poder consumir el resto de los servicios.
