[![](./img/logo.png "FolksDev & Kod Gemisi")](https://github.com/ugurcandede/)

# FolksDev & Kod Gemisi SpringBoot Bootcamp

Bootcamp süresince geliştirilen spring boot projesine ait repodur.

## Ödev 1

Blog projesi oluşturma ve GitHub'a yükleme.

## [Ödev 2](https://github.com/Folksdev-camp/folksdev-ugurcandede/commit/761b611194f62bf00269ca399be43f1ec9c36a9b)

Projeye dummy `CRUD` api oluşturup, validasyonları hazırlama.

+ `POST` Metod İsteği

```json
{
  "name": "Blade",
  "year": 1998,
  "imdbScore": 7.1,
  "genre": ["action", "horror"]
}
```


## [Ödev 3](https://github.com/Folksdev-camp/folksdev-ugurcandede/commit/adbf59c670c8a2c897cb7cb2887bae8df5d0cdd0)

Projenize ait bir veritabanı oluşturun, blog ve yorumları çekebileceğiniz bir sorgu yazın. 
Yazdığınız sorgu kodunu projedeki `resource` kısmına SQL dosyası olarak ekleyin.

> resource'a veritabanı dump halinde eklendi.

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


## Lisans

Bu repo [Ugurcan Dede](https://github.com/ugurcandede) tarafından bootcamp sürecinde oluşturulmuştur. `WTFPL` lisansına
sahiptir.