# Введение в эволюционные вычисления

## Цель работы
Целью данной лабораторной работы является получение студентом
представления об возможностях применения эволюционных алгоритмов для
решения различных классов задач и программных средств для их разработки.

## Выполнение работы
Для выполнения работы были установлены Eclipse Temurin JDK версии 17.0.6 и Watchmaker framework версии 0.7.1, а также Apache Maven версии 3.9.0.

## Bits count
Было произведено по 5 запусков генетического алгоритма подсчёта битов для выбранных количеств битов (20, 50 и 80 битов). Результаты представлены в таблице ниже.

| Размерность | Run 1 | Run 2 | Run 3 | Run 4 | Run 5 | **Среднее** | 
| :--- | --- | --- | --- | --- | --- | --- |
| 20 | 37 | 13 | 27 | 17 | 26 | **24** |
| 50 | 2856 | 2656 | 1769 | 2699 | 2451 | **2486.2** |
| 80 | 36655 | 90361 | 55972 | 56066 | 51781 | **58167** |

Решения в представленном алгоритме представлены в виде битовых строчек (```BitString``` из библиотеки [Uncommons Maths](https://maths.uncommons.org/api/overview-summary.html)). Подсчётом единичных битов в строке из популяции определяется близость полученных решений к верному.

## Traveling salesman problem
Был произведён запуск генетического алгоритма с предустановленными параметрами для решения задачи коммивояжёра для всех доступных городов. Результат представлен ниже.

```console
[Evolution (pop: 300, gen: 100, elite: 3, Truncation Selection (50%))]
ROUTE: Helsinki -> Berlin -> Vienna -> Athens -> Rome -> Madrid -> Lisbon -> Dublin -> London -> Paris -> Luxembourg -> Brussels -> Amsterdam -> Copenhagen -> Stockholm -> Helsinki
TOTAL DISTANCE: 10494.0km
(Search Time: 0.043 seconds)
```

Уменьшение рахмера популяции со 100 до 5 привело к уменьшению времени работы с 0.043 секунды до 0.003. Однако, при этом, было найдёно менее оптимальное решение (14244 km вместо 10494). Результат представлен ниже.

```console
[Evolution (pop: 5, gen: 100, elite: 3, Truncation Selection (50%))]
ROUTE: Helsinki -> Amsterdam -> Brussels -> Paris -> Lisbon -> Luxembourg -> Copenhagen -> Berlin -> Vienna -> Athens -> Rome -> Madrid -> London -> Dublin -> Stockholm -> Helsinki
TOTAL DISTANCE: 14244.0km
(Search Time: 0.003 seconds)
```

При увеличении популяции до 100 результат работы алгоритма (11342 km) приближается к наиболее оптимальному полученному результату (10494, при размере популяции 300). Результат представлен ниже.

```console
[Evolution (pop: 100, gen: 100, elite: 3, Truncation Selection (50%))]
ROUTE: London -> Dublin -> Lisbon -> Madrid -> Rome -> Athens -> Vienna -> Luxembourg -> Brussels -> Berlin -> Helsinki -> Stockholm -> Copenhagen -> Amsterdam -> Paris -> London
TOTAL DISTANCE: 11342.0km
(Search Time: 0.018 seconds)
```

При сохранении размера популяции 100 и увеличении количества поколений до 1000 получается повысить стаблильность работы алгоритма (получение общего расстояния, в большинстве случаев, в 10494 km). Однако, в редких случаях наблюдадись решения с дистанциями 11296.0 km или 11250.0 km. Результаты представлены ниже.

```console
[Evolution (pop: 100, gen: 1000, elite: 3, Truncation Selection (50%))]
ROUTE: Amsterdam -> Brussels -> Luxembourg -> Paris -> London -> Dublin -> Lisbon -> Madrid -> Rome -> Athens -> Vienna -> Berlin -> Helsinki -> Stockholm -> Copenhagen -> Amsterdam
TOTAL DISTANCE: 10494.0km
(Search Time: 0.139 seconds)
```

```console
[Evolution (pop: 100, gen: 1000, elite: 3, Truncation Selection (50%))]
ROUTE: Madrid -> Rome -> Athens -> Vienna -> Luxembourg -> Paris -> Brussels -> Berlin -> Helsinki -> Stockholm -> Copenhagen -> Amsterdam -> London -> Dublin -> Lisbon -> Madrid
TOTAL DISTANCE: 11296.0km
(Search Time: 0.146 seconds)
```

```console
[Evolution (pop: 100, gen: 1000, elite: 3, Truncation Selection (50%))]
ROUTE: Copenhagen -> Stockholm -> Helsinki -> Berlin -> Vienna -> Athens -> Rome -> Luxembourg -> Amsterdam -> Brussels -> Paris -> Madrid -> Lisbon -> Dublin -> London -> Copenhagen
TOTAL DISTANCE: 11250.0km
(Search Time: 0.142 seconds)
```

При возвращении к стартовым настройкам и отключении мутации качество работы алгоритма ухудшилось. Но при этом уменьшилось время работы в сравнении с оригиналом. Пример результата работы алгоритма представлен ниже.

```console
[Evolution (pop: 300, gen: 100, elite: 3, Truncation Selection (50%))]
ROUTE: Amsterdam -> Copenhagen -> Stockholm -> Helsinki -> Berlin -> Vienna -> Athens -> Rome -> Luxembourg -> Brussels -> Paris -> Madrid -> Lisbon -> Dublin -> London -> Amsterdam
TOTAL DISTANCE: 10976.0km
(Search Time: 0.03 seconds)
```

Отключении кроссовера при включённой мутации повлияло на работу алгоритма меньше. Однако, случаи с менее оптимальным найденным решением присутствуют (пример такого решения ниже).

```console
[Evolution (pop: 300, gen: 100, elite: 3, Truncation Selection (50%))]
ROUTE: Lisbon -> Dublin -> London -> Paris -> Luxembourg -> Rome -> Athens -> Vienna -> Berlin -> Helsinki -> Stockholm -> Copenhagen -> Amsterdam -> Brussels -> Madrid -> Lisbon
TOTAL DISTANCE: 11237.0km
(Search Time: 0.032 seconds)
```

## Mona Lisa
Был произведён запуск генетического алгоритма для решения задачи аппроксимации полигонами картины Моно Лизы. Результаты представлены ниже.

| Решение | Итерация | Фитнесс | Кол-во полигонов | Кол-во вершин | Рисунок |
| :--- | --- | --- | --- | --- | --- |
| плохое | 10693 | 242786 | 23 | 137 | ![image](https://user-images.githubusercontent.com/29786176/220460285-a8f0e637-b57d-4098-90c0-7d2a078f5606.png) |
| среднее | 100193 | 189774 | 39 | 280 | ![image](https://user-images.githubusercontent.com/29786176/220461113-db156c98-df5a-413f-9a5f-d2e8ccb01271.png) |
| хорошее | 208856 | 178182 | 47 | 362 | ![image](https://user-images.githubusercontent.com/29786176/220462438-a2df047b-a2c8-469d-9efd-845120681611.png) |

## Ответы на вопросы
**Q: К какому типу по структуре решений относится каждая из рассмотренных задач?**

**A:** bits count – бинарный,  traveling salesman problem – комбинаторный, Mono Lisa – целочисленный.

**Q: Как закодированы решения в задаче коммивояжёра?**

**A:** Решения закодированы в виде списков с названиями городов (```List<String>```).

**Q: Что является генотипом, а что фенотипом в задаче воспроизведения картины?**

**A:** Генотипом является список цветных полигонов ```List<ColouredPolygon>```, фенотипом – соответствующее растровое изображение ```Raster```.
