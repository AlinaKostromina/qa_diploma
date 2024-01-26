# Дипломный проект по курсу «Тестировщик ПО»
___

### Документация:
[План по автоматизации тестирования](https://github.com/AlinaKostromina/qa_diploma/blob/main/docs/Plan.md)  
[Отчет по итогам тестирования](https://github.com/AlinaKostromina/qa_diploma/blob/main/docs/Report.md)     
[Отчет по итогам автоматизации](https://github.com/AlinaKostromina/qa_diploma/blob/main/docs/Summary.md)    

---
### О проекте:
Данная дипломная работа представляет собой автоматизацию тестирования [сервиса](https://github.com/netology-code/qa-diploma), взаимодействующего с СУБД и API Банка.  
Приложение предлагает купить тур по определённой цене с помощью двух способов:  
* оплата по дебетовой карте; 
* выдача кредита по данным банковской карты. 
 
Приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:
* сервису платежей;
* кредитному сервису.
___

### **Инструкция по запуску тестов:**

_Предварительная подготовка к тестированию:_
1. Установить IntelliJ Idea. Ознакомиться с документацией можно на официальном [сайте](https://www.jetbrains.com/ru-ru/idea/).
2. Установить Docker Desktop. Ознакомиться с документацией можно на официальном [сайте](https://www.docker.com/get-started).
3. Установить git под вашу ОС, [документация](https://git-scm.com/book/ru/v2/Введение-Установка-Git) по установке.
4. Установить браузер Google Chrome для удобного тестирования (желательно последняя версия 120.0.6099.234 (Официальная сборка), (arm64)).

_Для запуска тестов необходимо:_
1. С помощью команды `git clone https://github.com/AlinaKostromina/qa_diploma` скачать проект себе локально.
2. Открыть проект на установленной заранее IntelliJ Idea.
3. Развернуть контейнеры с помощью команды `docker-compose up` в терминале IntelliJ Idea.
4. В соседней вкладке запустить приложение командой   
`java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar` - для БД Postgresql   
или   
`java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar` - для БД MysSQL  
5. Запустить тесты командой  
`./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app` -  для БД MysSQL  
или  
`./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app` - для БД Postgresql  
6. Посмотреть отчет о прохождении тестов командой `gradlew allureServe`

___

### **Окончание тестов и остановка работы контейнеров:**

1. Прервать выполнение SUT по Ctrl+C (или закрытием окна терминала).
2. Остановить контейнеры командой `docker-compose down`.
