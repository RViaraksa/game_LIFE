**Игра "Жизнь"** - клеточный автомат, придуманный математиком Джоном Конвеем. Реализация данного автомата похожа на развитие популяции примитивных организмов. Несмотря на свое простоту, игра до сих пор привлекает внимание ученных разных наук.
Правила
_Место действия игры - клеточное поле_. Размер, которого вы можете указать сами;
_Поколение - это одна итерация изменения поля;_
Каждая клетка может иметь два состояния: заполнена (жива), пуста (мертва);
У каждой клетки есть соседи - 8 соседних клеток;
В пустой клетке, рядом с которой ровно три заполненные клетки, зарождается жизнь;
Если у заполненной клетки три или два заполненных соседа, то она продолжает быть заполненной.
В противном случае, если соседей меньше двух или больше трёх, клетка умирает ("от одиночества" или "от перенаселённости").