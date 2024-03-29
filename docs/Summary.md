### Отчет по итогам автоматизации.  
Был протестирован сервис покупки тура, где можно оформить покупку тура двумя способами: дебетовой картой или в кредит по данным дебетовой карты. 
  

**Что было запланировано:**  
* Написать позитивные и негативные сценарии тестов работы сервиса для тестирования способов покупки через карту и через кредит;
* Протестировать поля форм на валидность значений;
* Протестировать поддержку двух заявленных СУБД: PostgreSQL и MySQL.

**Что было сделано:**  
* Подтверждено взаимодействие с двумя СУБД (MySQL и PostgreSQL).  
* Проверена корректность работы приложения.  
* Проверено API для банковских сервисов (Payment Gate, и Credit Gate).    

___
#### Сработавшие риски:  
* Были проблемы с поиском CSS селекторов (отсутствие уникальных css-селекторов для тестирования);
* Сложности с версиями плагинов и зависимостей;
* Сложность запуска приложения для конфигурации с разными базами данных.
___
#### Общий итог затраченного времени. 
* Составление плана по автоматизации тестирования - 6 часов.     
* Процесс автоматизации (подготовка ТО, написание тестов, прогон и тд) - 55 часов (на 5 часов больше запланированного).    
* Подготовка отчётных документов по итогам автоматизированного тестирования - 5 часов.     
* Подготовка отчётных документов по итогам автоматизации - 3 часа.
   
_Итого - 69 часов._

Дополнительное время на решение непредвиденных ситуаций - из 13 часов израсходовано было только 5 часов (в процессе автоматизации). 

**Итого:**      
[Было запланировано](https://github.com/AlinaKostromina/qa_diploma/blob/main/docs/Plan.md) - 81 час.    
[Фактически ушло](https://github.com/AlinaKostromina/qa_diploma/blob/main/docs/Summary.md) - 69 часов.
