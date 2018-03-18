# DataConverter

Преобразование в HTML-код набор данных по определенному шаблону

Программа работает со следующими типами

  - properties
  - json

### Поддерживаемые форматы входящих данных:

```sh
Input:"<file1>;<file2>..." Output:"<output1>" Mode:"html"
```
### Пример использования:

```sh
java -jar converter.jar Input:"/path/to/file/input1.properties;/path/to/file/input2.json" Output:"/path/to/input" Mode:"html"
```

### Поддерживаемые теги:

| Тег | Перевод |
| ------ | ------ |
|fio|Фамилия Имя Отчество|
|dob|Дата рождения|
|email|Почтовый адрес|
|skype|Логин skype|
|phone|Телефон|
|vk|Профиль Вконтакте|
|avatar|URL адрес картинки профиля|
|target|Цель|
|experience|Опыт|
|education|Образование|
|additionalEducation|Дополнительное образование|
|skills|Навыки|
|codeExample|Ссылки на код|

### Пример конфигурационного файла формата *.properties:

```sh
fio="This your name"
dob="01.01.1970"
email="example@email.com","mysecondmail@email.com"
skype="live:skype"
phone="+7(999)999-99-99","+7(888)888-88-88"
vk="https://vk.com/"
avatar="https://example.com/images/crutch.img"
target="Тут Ваша цель. Поддерживается кириллица"
experience="Тут Ваш опыт работы"
education="Образование"
additionalEducation="Дополнительное образование"
skills="Навыки"
codeExample="https://github.com/twocookie/DataConverter/","https://example.com/code/storage"
```

### Пример конфигурационного файла формата *.json:

```sh
{
    "fio":"This your name",
    "dob":"01.01.1970",
    "email":[
        "example@email.com",
        "mysecondmail@email.com"
    ],
    "skype":"live:skype",
    "phone":[
        "+7(999)999-99-99",
        "+7(888)888-88-88"
    ],
    "vk":"https://vk.com/",
    "avatar":"https://example.com/images/crutch.img",
    "target":"Тут Ваша цель. Поддерживается кириллица",
    "experience":"Тут Ваш опыт работы",
    "education":"Образование",
    "additionalEducation":"Дополнительное образование",
    "skills":"Навыки",
    "codeExample":[
        "https://github.com/twocookie/DataConverter/",
        "https://example.com/code/storage"
    ]
}
```

### Нюансы:
  - Каждому output соответствует один режим генерации
  - Значения в исходном файле формата *.properties должны быть разграничены *","* без пробелов
  - Значения в исходном файле формата *.json должны распологаться в одну строчку. 
