# Leaning Spring
Repositório dedicado ao aprendizado do framework Spring

## Inicializar o banco Mysql utilizando docker
```sh
docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=escola mysql:8.0.20
```