## serverside-developer-test
**serverside developer test** - это тестовое задание для superkassa.ru.

## Функиональные требования
В процессе обработки необходимо атомарно увеличить на величину add значение
поля current столбца obj строки идентифицируемой id в таблице и вернуть полученое значение через API.

## Технологии
Java 11, Spring Boot, Maven, Docker, Postgres

## Запуск приложения в docker

`mvn clean install`
`docker-compose up`

## Работа с приложением
Приложение работает на порту 8081.

##### GET запрос.
Ответ удовлетворяет JSON схеме:
`{
"id": <number>,
"current": <number>
}`
Запрос в командой строке:
`curl -X GET http://localhost:8081/api/v1/user/<number>`

##### POST запрос.
Запрос удовлетворяет JSON схеме:
`{
"id": <number>,
"add": <number>
}`
Ответ удовлетворяет JSON схеме:
`{
"current": <number>
}`
Запрос в командой строке:
`curl -X POST -H "Content-type: application/json" -d "{\"id\": <number>, \"add\": <number>}" http://localhost:8081/api/v1/user/modify`


