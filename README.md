[![](./img/logo.png "FolksDev & Kod Gemisi")](https://github.com/ugurcandede/)

# FolksDev & Kod Gemisi SpringBoot Bootcamp

Bootcamp süresince geliştirilen spring boot projesine ait repodur.

## Gereksinimler

- Maven
- Docker
- Java 11

## Kullanılan Teknolojiler

- Java 11
- Kotlin 1.5.31
- Spring Boot
- Spring Data JPA
- PostgreSQL
- H2 In memory database
- Flyway
- Hamcrest
- OpenAPI documentation
- Hateoas
- Docker
- Docker compose
- JUnit 5

## Projenin Çalıştırılması

Projeyi çalıştırmanın 2 yolu vardır.

### Docker Compose

1. Projeyi cihazınıza kopyalayın: `git clone https://github.com/ugurcandede/FolksDevBootcamp`
2. Proje ana dizinine gidin: `cd FolksDevBootcamp`
3. `docker build -t bootcamp-blog .` komutu ile docker image oluşturun
4. `docker-compose up` komutu ile containerları çalıştırın.

> `http://localhost:9091` adresinde API,
>
> `http://localhost:9092` adresinde ise PostgreSQL çalışacaktır

### Maven

1. Projeyi cihazınıza kopyalayın: `git clone https://github.com/ugurcandede/FolksDevBootcamp`
2. Proje ana dizinine gidin: `cd FolksDevBootcamp`
3. `mvn clean install` ile çalıştırılabilir jar dosyasını oluşturun.
4. `mvn spring-boot:run` ile projeyi çalıştırın.

> `http://localhost:9091` adresinde API,
>
> `http://localhost:9092` adresinde ise PostgreSQL çalışacaktır

## HTTP İstek Yapısı

|      Controller       | Metot  |            Adres            |                  Açıklama                   |
| :-------------------: | :----: | :-------------------------: | :-----------------------------------------: |
|  **UserController**   |  GET   |   localhost:8080/v1/user    |        Bütün kullanıcıları listeler         |
|                       |  GET   |  localhost:8080/v1/user/0   |        ID 0 olan kullanıcıyı getirir        |
|                       |  POST  |   localhost:8080/v1/user    |             Kullanıcı oluşturur             |
|                       |  PUT   |  localhost:8080/v1/user/0   |       ID 0 olan kullanıcıyı günceller       |
|                       | DELETE |  localhost:8080/v1/user/0   |         ID 0 olan kullanıcıyı siler         |
|  **PostController**   |  GET   |   localhost:8080/v1/post    |         Bütün gönderileri listeler          |
|                       |  GET   |  localhost:8080/v1/post/0   |         ID 0 olan gönderiyi getirir         |
|                       |  POST  |  localhost:8080/v1/post/0   | ID 0 olan kullanıcıya ait gönderi oluşturur |
|                       |  PUT   |  localhost:8080/v1/post/0   |        ID 0 olan gönderiyi günceller        |
|                       | DELETE |  localhost:8080/v1/post/0   |          ID 0 olan gönderiyi siler          |
| **CommentController** |  GET   |  localhost:8080/v1/comment  |          Bütün yorumları listeler           |
|                       |  GET   | localhost:8080/v1/comment/0 |          ID 0 olan yorumu getirir           |
|                       |  POST  |  localhost:8080/v1/comment  |               Yorum oluşturur               |
|                       |  PUT   | localhost:8080/v1/comment/0 |         ID 0 olan yorumu günceller          |
|                       | DELETE | localhost:8080/v1/comment/0 |           ID 0 olan yorumu siler            |

## Commit Geçmişi

Ödevler ve commit geçmişi [COMMITS](https://github.com/ugurcandede/FolksDevBootcamp/COMMITS.md) dosyası içindedir.

## Lisans

Bu repo [Ugurcan Dede](https://github.com/ugurcandede) tarafından bootcamp sürecinde
oluşturulmuştur. [GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/) lisansına sahiptir.
