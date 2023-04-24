# spring-shop

## 使用技術

### front

HTML, CSS JAVASCRIPT, BootStrap, Thymeleaf

### back

Spring Boot, Spring security

### database

MySQL

## api

| function          | method | uri                    |
|-------------------|--------|------------------------|
| main page         | GET    | /                      |
| Login page        | GET    | /login                 |
| signup page       | GET    | /signup                |
| signup            | POST   | /signup                |
| item page         | GET    | /items/{itemId}        |
| item add page     | GET    | /items/add             |
| item add          | POST   | /items/add             |
| item update form  | GET    | /items/{itemId}/update |
| item update form  | POST   | /items/{itemId}/update |
| item delete       | DELETE | /items/{itemId}        |
| cart page         | GET    | /carts                 |
| cart item add     | POST   | /carts/{itemId}        |
| cart item delete  | POST   | /carts/delete          |
| order list page   | GET    | /orders                |
| order detail page | GET    | /orders/{orderId}      |
| order add         | POST   | /orders                |
| order delete      | DELETE | /orders/{orderId}      |

## ERD

![img.png](img.png)

## 予定
validation, messageSource