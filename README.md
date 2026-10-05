# Lolo Seasons (NeoForge 1.21.1) + GeckoLib + Curios

Сборка: `./gradlew build` (Java 21). Готовый jar: `build/libs/lolo_seasons-1.1.0.jar`.
Запуск для теста: `./gradlew runClient`. Нужен доступ к maven.neoforged.net, Maven Central, GeckoLib и Curios.

Если Gradle не находит GeckoLib или Curios — исправьте `geckolib_version` / `curios_version` в `gradle.properties`.
Эти версии я не смог проверить, в моей среде эти репозитории закрыты.

## Как добавить GeckoLib-модель NPC
1. `assets/lolo_seasons/geo/entity/<имя>.geo.json` (экспорт из Blockbench, формат Bedrock/GeckoLib)
2. `assets/lolo_seasons/animations/entity/<имя>.animation.json` (обязательны анимации `idle` и `walk`)
3. `assets/lolo_seasons/textures/entity/<имя>.png`
4. Имя совпадает с тем, что передано в `new NpcRenderer(ctx, "<имя>")` в `LoloSeasonsClient`.
Сейчас у wanderer и automaton стоит временная гуманоидная модель, её можно заменить своими файлами с теми же именами.

## Как сделать предмет для Curios
1. Наследуйте `LoloCurioItem` (пример: `ScarabAmuletItem`, метод `curioTick`).
2. Добавьте предмет в тег слота: `data/curios/tags/item/<слот>.json` (charm, necklace, ring, belt, back, head...).
3. Слот должен быть привязан к игроку: `data/curios/curios/entities/lolo_player.json`.

## Сборка на GitHub
После push во вкладке Actions запустится сборка. Готовый jar лежит в её результатах в разделе Artifacts (`lolo_seasons-jar`).
Если сборка красная, откройте лог шага `./gradlew build` и пришлите мне ошибку.
