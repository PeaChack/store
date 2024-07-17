## Техническое задание
*Автор: Булыга Дмитрий*
### Описание проекта

Необходимо разработать e-commerce приложение для управления товарами и категориями с использованием Spring Boot и PostgreSQL. Приложение должно предоставлять REST API для выполнения CRUD операций с сущностями "Категория" и "Продукт".

### Сущности

#### Category (Категория)
**Описание:** Категория представляет собой группу продуктов.

**Поля:**
- `id` (Long): Уникальный идентификатор категории. Автоматически генерируется.
- `name` (String): Название категории.
- `description` (String): Описание категории.

#### Product (Продукт)
**Описание:** Продукт представляет собой товар, который принадлежит определенной категории.

**Поля:**
- `id` (Long): Уникальный идентификатор продукта. Автоматически генерируется.
- `name` (String): Название продукта.
- `description` (String): Описание продукта.
- `price` (BigDecimal): Цена продукта.
- `stockQuantity` (Integer): Количество продукта на складе.
- `category` (Category): Ссылка на категорию, к которой принадлежит продукт.

### Требования к функционалу

#### Управление категориями:

- Создание новой категории.
- Получение списка всех категорий.
- Получение деталей конкретной категории по ее идентификатору.
- Обновление информации о категории.
- Удаление категории.

#### Управление продуктами:

- Создание нового продукта и привязка его к существующей категории.
- Получение списка всех продуктов.
- Получение деталей конкретного продукта по его идентификатору.
- Обновление информации о продукте.
- Удаление продукта.

## Зависимости проекта
В ходе реализации тз были использованы следующие зависимости:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.liquibase</groupId>
        <artifactId>liquibase-core</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.restdocs</groupId>
        <artifactId>spring-restdocs-mockmvc</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
</dependencies>
```

### Описание зависимостей

1. **Spring Boot Starter Data JPA**:
   - `artifactId: spring-boot-starter-data-jpa`
   - Используется для работы с JPA и Hibernate, предоставляет функциональность для взаимодействия с базой данных через ORM.

2. **Liquibase Core**:
   - `artifactId: liquibase-core`
   - Используется для управления схемой базы данных, позволяет применять изменения к базе данных с помощью миграций.

3. **PostgreSQL**:
   - `artifactId: postgresql`
   - Драйвер для подключения к базе данных PostgreSQL.

4. **Lombok**:
   - `artifactId: lombok`
   - Упрощает разработку, автоматически генерируя код для геттеров, сеттеров, конструкторов и других часто используемых методов. Опционально включается в проект.

5. **Spring Boot Starter Test**:
   - `artifactId: spring-boot-starter-test`
   - Включает набор инструментов для тестирования, таких как JUnit, Mockito, и Spring TestContext Framework.

6. **Spring REST Docs MockMvc**:
   - `artifactId: spring-restdocs-mockmvc`
   - Используется для генерации документации REST API из тестов с использованием MockMvc.

7. **Spring Boot Starter Web**:
   - `artifactId: spring-boot-starter-web`
   - Включает необходимую функциональность для создания веб-приложений, такие как встроенный сервер Tomcat и поддержка REST API.

8. **Spring Boot Starter Validation**
   - `artifactId: spring-boot-starter-validation`
   - Необходим для поддержки валидации посредством аннотаций.

## Инструкция по запуску

1. **Клонирование репозитория:**
   ```bash
   git clone <URL-репозитория>
   ```

2. **Переход в директорию проекта:**
   ```bash
   cd <имя_проекта>
   ```

3. **Сборка и запуск приложения:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Настройка базы данных:**
    - Убедитесь, что PostgreSQL установлен и запущен.
    - Создайте базу данных и укажите параметры подключения в `application.yaml`.

5. **Проверка REST API:**
      - Импортируйте коллекцию Postman и выполните все запросы для проверки функциональности API.
   
   
[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/33531922-9d5b3def-5e32-4d09-9e7e-054d5064833e?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D33531922-9d5b3def-5e32-4d09-9e7e-054d5064833e%26entityType%3Dcollection%26workspaceId%3D23460960-7ce2-47ab-bf27-c3a89a0fe679)