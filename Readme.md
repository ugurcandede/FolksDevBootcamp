[![](./img/logo.png "FolksDev & Kod Gemisi")](https://github.com/ugurcandede/)

# FolksDev & Kod Gemisi SpringBoot Bootcamp

Bootcamp süresince geliştirilen spring boot projesine ait repodur.

## [Ödev 7](https://github.com/Folksdev-camp/folksdev-ugurcandede/commit/fe699c9f8a9cd9ecfbef3df79d5a11cbeb3ab084)

Blog projenize ait servislerin `unit test`lerini oluşturun. **Code coverage 100%** yapın.

[![](./img/service-coverage.PNG "Odev 7 Code Coverage")](https://github.com/ugurcandede/)

## [Ödev 6](https://github.com/Folksdev-camp/folksdev-ugurcandede/commit/fe699c9f8a9cd9ecfbef3df79d5a11cbeb3ab084)

Blog projenizin servislerini oluşturun (CRUD), contoller ile çalışmasını sağlayın.
Bir SQL dosyası oluşturarak, uygulama ayağı kalkarken `flyway` kullanarak veri girişi yapmasını sağlayın.

<details>
<summary>CRUD Sorgu Açıklamaları</summary>

- **UserController**

| Metod | Adres | Açıklama | 
|:----:|:----:|:----:|
| GET | localhost:8080/v1/user | Bütün kullanıcıları listeler |
| GET | localhost:8080/v1/user/0 | ID 0 olan kullanıcıyı getirir |
| POST | localhost:8080/v1/user | Kullanıcı oluşturur |

- **PostController**

| Metod | Adres | Açıklama |
|:----:|:----:|:----:|
| GET | localhost:8080/v1/post | Bütün gönderileri listeler |
| GET | localhost:8080/v1/post/0 | ID 0 olan gönderiyi getirir |
| POST | localhost:8080/v1/post/0 | ID 0 olan kullanıcıya ait gönderi oluşturur |

- **CommentController**

| Metod | Adres | Açıklama |
|:----:|:----:|:----:|
| GET | localhost:8080/v1/comment | Bütün yorumları listeler |
| GET | localhost:8080/v1/comment/0 | ID 0 olan yorumu getirir |
| POST | localhost:8080/v1/comment | Yorum oluşturur |



</details>

<details>
<summary>PostController CRUD Sorguları</summary>

| Metod | Adres | Açıklama |
|:----:|:----:|:----:|
| POST | localhost:8080/v1/post | Gönderi oluşturur |

```json
{
    "title": "Post Title",
    "body": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed a diam consectetur.",
    "status": "DRAFT"
}
```
---

| Metod | Adres | Açıklama |
|:----:|:----:|:----:|
| GET | localhost:8080/v1/post | Bütün gönderileri listeler |

```json
[
  {
    "id": "9e68a3e6-c531-4321-962f-0d333b5142ca",
    "title": "Hello",
    "body": "Hello Folksie!~",
    "creationDate": "2021-11-05T17:42:50.383426",
    "status": "PUBLISHED",
    "user": {
      "id": "7d5ddf73-0a64-43b0-b62b-2cd3dab5f2be",
      "username": "ugurcandede",
      "email": "ugur@dede.com",
      "displayName": "Ugurcan Dede"
    },
    "comments": []
  },
  {
    "id": "001250ab-b76b-4f89-9dad-2d1e64719a17",
    "title": "Lorem Ipsum",
    "body": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed a diam consectetur.",
    "creationDate": "2021-11-05T17:42:50.542691",
    "status": "PUBLISHED",
    "user": {
      "id": "3403a2c4-241f-4a2b-8386-62c360d22ffe",
      "username": "nemesisce",
      "email": "cagridursun@folksdev",
      "displayName": "Cagri Dursun"
    },
    "comments": [
      {
        "id": "1fa4ddee-9453-47df-ad0e-bc78e9d71dae",
        "body": "Hi Kod Gemisi",
        "creationDate": "2021-11-05T17:42:50.542691"
      }
    ]
  }
]
```

---

| Metod | Adres | Açıklama |
|:----:|:----:|:----:|
| GET | localhost:8080/v1/post/9e68a3e6-c531-4321-962f-0d333b5142ca | ID 9e68a3e6-c531-4321-962f-0d333b5142ca olan gönderiyi getirir |

```json
{
  "id": "9e68a3e6-c531-4321-962f-0d333b5142ca",
  "title": "Hello",
  "body": "Hello Folksie!~",
  "creationDate": "2021-11-05T17:42:50.383426",
  "status": "PUBLISHED",
  "user": {
    "id": "7d5ddf73-0a64-43b0-b62b-2cd3dab5f2be",
    "username": "ugurcandede",
    "email": "ugur@dede.com",
    "displayName": "Ugurcan Dede"
  },
  "comments": []
}
```

</details>

### Flyway
```bash
  INFO 11164 --- [  restartedMain] o.f.c.internal.license.VersionPrinter    : Flyway Community Edition 7.7.3 by Redgate
  INFO 11164 --- [  restartedMain] o.f.c.i.database.base.DatabaseType       : Database: jdbc:postgresql://localhost:5432/blog (PostgreSQL 14.0)
  WARN 11164 --- [  restartedMain] o.f.c.internal.database.base.Database    : Flyway upgrade recommended: Flyway upgrade recommended: org.flywaydb.core.internal.database.postgresql.PostgreSQLDatabaseType@7131dad0 14.0 is newer than this version of Flyway and support has not been tested. The latest supported version of org.flywaydb.core.internal.database.postgresql.PostgreSQLDatabaseType@7131dad0 is 13.
  INFO 11164 --- [  restartedMain] o.f.core.internal.command.DbValidate     : Successfully validated 5 migrations (execution time 00:00.021s)
  INFO 11164 --- [  restartedMain] o.f.core.internal.command.DbMigrate      : Current version of schema "public": 1
  INFO 11164 --- [  restartedMain] o.f.core.internal.command.DbMigrate      : Migrating schema "public" to version "1.1 - create user"
  INFO 11164 --- [  restartedMain] o.f.c.i.s.DefaultSqlScriptExecutor       : 0 rows affected
  INFO 11164 --- [  restartedMain] o.f.core.internal.command.DbMigrate      : Migrating schema "public" to version "1.2 - create post"
  INFO 11164 --- [  restartedMain] o.f.c.i.s.DefaultSqlScriptExecutor       : 0 rows affected
  INFO 11164 --- [  restartedMain] o.f.core.internal.command.DbMigrate      : Migrating schema "public" to version "1.3 - create comment"
  INFO 11164 --- [  restartedMain] o.f.c.i.s.DefaultSqlScriptExecutor       : 0 rows affected
  INFO 11164 --- [  restartedMain] o.f.core.internal.command.DbMigrate      : Migrating schema "public" to version "1.4 - insert user"
  INFO 11164 --- [  restartedMain] o.f.c.i.s.DefaultSqlScriptExecutor       : 1 rows affected
  INFO 11164 --- [  restartedMain] o.f.core.internal.command.DbMigrate      : Successfully applied 4 migrations to schema "public", now at version v1.4 (execution time 00:00.154s)
```

## [Ödev 5](https://github.com/Folksdev-camp/folksdev-ugurcandede/commit/99c9f829cd6d939b417f027c6e3492ac47d87b5b)

Blog projesine ait modellere birer repository oluşturarak `CommandLineRunner` sınıfında bu repositoryleri kullanın.

```
CommentedPost:
    Post(id = f6c4b575-32f5-4f77-ade2-042332bb85b7, title = Hello, body = Hello Folksie!~, createDate = 2021-10-29T14:37:56.793229,
            user = username = ugurcandede, email = ugur@dede.com, displayName = Ugurcan Dede, isActive = false)
```

```
ID -> 95151a40-8614-4429-a44e-f622b80359b9
	 Data -> username = ugurcandede , email = ugur@dede.com , displayName = Ugurcan Dede , isActive = false
ID -> d430371a-f085-4845-9702-60e41e105c73
	 Data -> username = nemesisce , email = cagridursun@folksdev , displayName = Cagri Dursun , isActive = false
```

## [Ödev 4](https://github.com/Folksdev-camp/folksdev-ugurcandede/commit/fa8bee2d48a4ddfa08bfd7a10572f1960c25e55d)

Blog projesine ait `Entry`, `Comment` ve `User` modelleri oluşturup aralarındaki ilişkiyi kurun.

[![](./img/odev-4.png "Odev 4 DB İlişki Diagramı")](https://github.com/ugurcandede/)

## [Ödev 3](https://github.com/Folksdev-camp/folksdev-ugurcandede/commit/adbf59c670c8a2c897cb7cb2887bae8df5d0cdd0)

Projenize ait bir veritabanı oluşturun, blog ve yorumları çekebileceğiniz bir sorgu yazın. Yazdığınız sorgu kodunu
projedeki `resource` kısmına SQL dosyası olarak ekleyin.

> resource'a veritabanı dump olarak eklendi.

[![](./img/odev3-diagram.png "Odev 3 DB Diagram")](https://github.com/ugurcandede/)

<details>
<summary>SQL Tablo Oluşturma Kodları </summary>

"User" Tablosu Oluşturma

```sql
create table if not exists "user"
(
    id           varchar not null,
    email        varchar,
    display_name varchar,
    constraint user_pk
    primary key (id)
);
```

---

"Posts" Tablosu Oluşturma

```sql
create table if not exists posts
(
    id           varchar,
    author_id    varchar,
    post_title   varchar,
    post_content varchar,
    post_date    date,
    constraint posts_user_id_fk
    foreign key (author_id) references "user"
);
```

---

"Comments" Tablosu Oluşturma

```sql
create table if not exists comments
(
    id        varchar not null,
    post_id   varchar,
    author_id varchar,
    content   varchar,
    constraint comments_pk
    primary key (id),
    constraint comments_user_id_fk
    foreign key (author_id) references "user",
    constraint comments_posts_id_fk
    foreign key (post_id) references posts (id)
);
```

---

`INSERT` Komutları

```sql
INSERT INTO public."user" (id, email, display_name)
VALUES ('1', 'ugur@dede.com', 'Ugurcan Dede'),
       ('2', 'cagri@folksdev.com', 'Cagri Dursun');
```

```sql
INSERT INTO public.posts (id, author_id, post_title, post_content, post_date)
VALUES ('2', '2', 'Hello', 'Hello Folksie!~', '2021-10-21');
```

```sql
INSERT INTO public.comments (id, post_id, author_id, content)
VALUES ('1', '2', '1', 'Hi Kod Gemisi');
```

</details>

<details>
<summary>SQL Sorguları</summary>

```sql
SELECT u.display_name AS "User", c.content AS "Comment"
FROM "user" AS u
         INNER JOIN comments AS c ON c.author_id = u.id
```

|     User     |    Comment    |
| :----------: | :-----------: |
| Ugurcan Dede | Hi Kod Gemisi |

---

```sql
SELECT u.display_name AS "User",
       p.post_title   AS "Post Title",
       p.post_content AS "Post Content",
       p.post_date    AS "Post Date"
FROM "user" AS u
         INNER JOIN posts AS p ON p.author_id = u.id;
```

|     User     | Post Title |  Post Content   | Post Date  |
| :----------: | :--------: | :-------------: | :--------: |
| Cagri Dursun |   Hello    | Hello Folksie!~ | 2021-10-21 |

---

```sql
SELECT u.display_name AS "User", p.post_title AS "Post Title", c.content as "Comment"
FROM "user" AS u
         LEFT JOIN comments c ON u.id = c.author_id
         INNER JOIN posts p ON c.post_id = p.id
```

|     User     | Post Title |    Comment    |
| :----------: | :--------: | :-----------: |
| Ugurcan Dede |   Hello    | Hi Kod Gemisi |

</details>

## [Ödev 2](https://github.com/Folksdev-camp/folksdev-ugurcandede/commit/761b611194f62bf00269ca399be43f1ec9c36a9b)

Projeye dummy `CRUD` api oluşturup, validasyonları hazırlama.

- POST` Metod İsteği

```json
{
  "name": "Blade",
  "year": 1998,
  "imdbScore": 7.1,
  "genre": ["action", "horror"]
}
```

## [Ödev 1](https://github.com/Folksdev-camp/folksdev-ugurcandede/commit/d862b9106133b364bf86ba610215381b7dbac322)

Blog projesi oluşturma ve GitHub'a yükleme.

## Lisans

Bu repo [Ugurcan Dede](https://github.com/ugurcandede) tarafından bootcamp sürecinde oluşturulmuştur. `WTFPL` lisansına
sahiptir.
