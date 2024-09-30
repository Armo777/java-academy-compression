### Кодирование текстового файла, при помощи алгоритма кодов Хаффмана.

[Описание алгоритма на Википедии](https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%B4_%D0%A5%D0%B0%D1%84%D1%84%D0%BC%D0%B0%D0%BD%D0%B0)

Реализовать консольное приложение на Java по распаковке и упаковке текста. 

Упаковка: _jcoursearchive -c -input <path_to_txt_file> -output <path_to_compressed_file>_

Распаковка: _jcousearchive -e -input <path_to_compressed_file> (текстовый вывод должен идти в консоль)_

Результатом проверки будет являться сжатие произведение Шекспира ["Ромео и Джульета"](./docs/romeo-and-juliet_Shakespeare.txt).

#### Архитектурные требования

- Не изменять интерфейс Archiver
- Редактировать класс Main только в случае необходимости, редактирование ради упрощения реализации не допускается
- Для настроек реализовать класс Settings
- Реализацию алгоритма вынести в отдельный пакет

#### Выполнение 

- Реализацию проводить в ветке дев
- Сделать форк (fork) репозитория в собственный профиль гитхаб/гитлаб
- Результатом выполнения должен быть Merge|Pull request