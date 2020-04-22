# Пример работы с Hibernate

## Подготовка
1. Установить [Docker](https://docs.docker.com/engine/install/) (Нужно установить CE - Community Edition)
    * Для Linux не забыть выполнить [Post-installation steps](https://docs.docker.com/engine/install/linux-postinstall/)
2. Установить [docker-compose](https://docs.docker.com/compose/install/)

## Запуск
### 1. Запустить базу данных MySQL и adminer:
Запуск docker контейнеров осуществляется командой:
```
docker-compose up -d
```
Проверить запущены ли образа можно командой `docker ps -a`. На экране отобразится примерно следующее:
```
CONTAINER ID        IMAGE               COMMAND                  CREATED              STATUS              PORTS                    NAMES
b3919fbcd350        mysql:5.6           "docker-entrypoint.s…"   About a minute ago   Up About a minute   0.0.0.0:3306->3306/tcp   mysql
0fea4d4c0930        adminer             "entrypoint.sh docke…"   About a minute ago   Up About a minute   0.0.0.0:8086->8080/tcp   adminer
```
MySQL будет доступен по адресу `192.164.12.2:3306` для adminer либо `localhost:3306` из локальной сети.

Интерфейс к БД будет доступен по адресу `http://localhost:8086`

Остановить docker контейнеры можно командой:
```
docker-compose down
```

### 2. Импортировать проект в Idea
При импорте проекта выбрать Gradle в качестве сборщика

### 3. Запуск
Запускать без параметров. Main class - `com.example.uni.hibernate.Application`