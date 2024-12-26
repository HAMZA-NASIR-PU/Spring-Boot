```sql
CREATE DATABASE `jpql-set`;
```

```sql
INSERT INTO Library (id, name, location, establishedDate) VALUES
(1, 'Central Library', 'Downtown', '1990-03-15'),
(2, 'West End Library', 'West City', '1985-07-20'),
(3, 'East Side Library', 'East City', '2000-09-05');
```

```sql
INSERT INTO Member (id, first_name, last_name, email, location, library_id) VALUES
(1, 'Alice', 'Johnson', 'alice.johnson@example.com', 'Downtown', 1),
(2, 'Bob', 'Smith', 'bob.smith@example.com', 'West City', 2),
(3, 'Charlie', 'Brown', 'charlie.brown@example.com', 'East City', 3),
(4, 'Diana', 'Prince', 'diana.prince@example.com', 'Downtown', 1),
(5, 'Eve', 'Adams', 'eve.adams@example.com', 'East City', 3);
```

```sql
INSERT INTO Book (id, title, author, genre, library_id) VALUES
(1, 'Harry Potter and the Sorcerer\'s Stone', 'J.K. Rowling', 'Fantasy', 1),
(2, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 1),
(3, 'To Kill a Mockingbird', 'Harper Lee', 'Fiction', 2),
(4, '1984', 'George Orwell', 'Dystopian', 2),
(5, 'The Catcher in the Rye', 'J.D. Salinger', 'Fiction', 3),
(6, 'Pride and Prejudice', 'Jane Austen', 'Romance', 3);
```

```sql
-- Inserting books with fines based on overdue period (10 rupees per day)

-- Book returned on time (no fine)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (1, '2024-01-05', '2024-01-12', 1, 1, TRUE, '2024-01-12', 0.00);

-- Book overdue by 3 days (fine: 3 * 10 = 30 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (2, '2024-01-15', '2024-01-22', 2, 2, TRUE, '2024-01-25', 30.00);

-- Book overdue by 5 days (fine: 5 * 10 = 50 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (3, '2024-02-03', '2024-02-10', 3, 3, FALSE, NULL, 0.00);

-- Book overdue by 7 days (fine: 7 * 10 = 70 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (4, '2024-02-20', '2024-02-27', 4, 4, FALSE, NULL, 0.00);

-- Book returned late by 4 days (fine: 4 * 10 = 40 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (5, '2024-03-01', '2024-03-08', 5, 5, TRUE, '2024-03-12', 40.00);

-- Book overdue by 5 days (fine: 5 * 10 = 50 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (6, '2024-03-10', '2024-03-17', 1, 6, TRUE, '2024-03-22', 50.00);

-- Book overdue by 10 days (fine: 10 * 10 = 100 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (7, '2024-03-25', '2024-04-01', 2, 1, TRUE, '2024-04-11', 100.00);

-- Book overdue by 3 days (fine: 3 * 10 = 30 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (8, '2024-04-02', '2024-04-09', 3, 2, TRUE, '2024-04-12', 30.00);

-- Book overdue by 8 days (fine: 8 * 10 = 80 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (9, '2024-04-18', '2024-04-25', 4, 3, TRUE, '2024-05-03', 80.00);

-- Book overdue by 6 days (fine: 6 * 10 = 60 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (10, '2024-04-25', '2024-05-02', 5, 4, FALSE, NULL, 0.00);

-- Book overdue by 4 days (fine: 4 * 10 = 40 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (11, '2024-05-05', '2024-05-12', 1, 5, FALSE, NULL, 0.00);

-- Book overdue by 2 days (fine: 2 * 10 = 20 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (12, '2024-05-15', '2024-05-22', 2, 6, FALSE, NULL, 0.00);

-- Book overdue by 5 days (fine: 5 * 10 = 50 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (13, '2024-05-25', '2024-06-01', 3, 1, FALSE, NULL, 0.00);

-- Book overdue by 3 days (fine: 3 * 10 = 30 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (14, '2024-06-01', '2024-06-08', 4, 2, FALSE, NULL, 0.00);

-- Book overdue by 1 day (fine: 1 * 10 = 10 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (15, '2024-06-10', '2024-06-17', 5, 3, FALSE, NULL, 0.00);

-- Book returned late by 5 days (fine: 5 * 10 = 50 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (16, '2024-06-20', '2024-06-27', 1, 4, TRUE, '2024-07-02', 50.00);

-- Book overdue by 2 days (fine: 2 * 10 = 20 rupees)
INSERT INTO borrow_transaction (id, borrow_date, return_date, member_id, book_id, is_returned, actual_return_date, fine_amount)
VALUES (17, '2024-07-01', '2024-07-08', 2, 5, TRUE, '2024-07-05', 20.00);
```


https://www.alachisoft.com/resources/docs/ncache-5-0/prog-guide/hibernate-first-level.html#:~:text=Hibernate's%201st%20level%20caching%20module,maintained%20in%20the%20session%20cache.

## Hibernate First Level Cache

`Hibernate's 1st level caching module provides session level in the process caching. Whenever a session is created, a session cache is associated with it. When an object is loaded from the database, a copy of the object is maintained in the session cache. If the object is to be fetched again in the same session, the cached copy of the object is returned. In a particular transaction, if the same object is updated multiple times, it is only updated in session cache. At committing transaction, only the final state of object is persisted in the database thus avoiding repetitive update calls.`


Hello everyone, today I am going to teach you how Hibernate first-level cache works.

Consider following entities:

```java
package com.jpql.questions.JPQL.Questions.Set.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String location;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    public Member() {
    }
    /* Getters and Setters */
}
```

```java
package com.jpql.questions.JPQL.Questions.Set.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private LocalDate establishedDate;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> members;

    public Library() {
    }

    /* Getters and Setters */
}
```

Now consider following sql data in mysql tables:

```sql
INSERT INTO Library (id, name, location, establishedDate) VALUES
(1, 'Central Library', 'Downtown', '1990-03-15'),
(2, 'West End Library', 'West City', '1985-07-20'),
(3, 'East Side Library', 'East City', '2000-09-05');
```

```sql
INSERT INTO Member (id, first_name, last_name, email, location, library_id) VALUES
(1, 'Alice', 'Johnson', 'alice.johnson@example.com', 'Downtown', 1),
(2, 'Bob', 'Smith', 'bob.smith@example.com', 'West City', 2),
(3, 'Charlie', 'Brown', 'charlie.brown@example.com', 'East City', 3),
(4, 'Diana', 'Prince', 'diana.prince@example.com', 'Downtown', 1),
(5, 'Eve', 'Adams', 'eve.adams@example.com', 'East City', 3);
```

The next step is executing the following jpql query:

```java
String jpql2 = """
                    SELECT m\s
                    FROM Member m\s
                    """;
			TypedQuery<Member> query2 = entityManager.createQuery(jpql2, Member.class);
			List<Member> members = query2.getResultList();
```

Also in application.properties:

```xml
spring.jpa.properties.hibernate.format_sql=true
```

When you run the specified JPQL query, you will se following in your console:

```ignorelang
Hibernate: 
    select
        m1_0.id,
        m1_0.email,
        m1_0.first_name,
        m1_0.last_name,
        m1_0.library_id,
        m1_0.location 
    from
        member m1_0
Hibernate: 
    select
        l1_0.id,
        l1_0.established_date,
        l1_0.location,
        l1_0.name 
    from
        library l1_0 
    where
        l1_0.id=?
Hibernate: 
    select
        l1_0.id,
        l1_0.established_date,
        l1_0.location,
        l1_0.name 
    from
        library l1_0 
    where
        l1_0.id=?
Hibernate: 
    select
        l1_0.id,
        l1_0.established_date,
        l1_0.location,
        l1_0.name 
    from
        library l1_0 
    where
        l1_0.id=?
```

You see that hibernate generate 1 query for `member` table and 3 for `library` table. There are 3 libraries and 5 members in database. Each member has 
associated library. `ManyToOne` annotation use `FetchType.EAGER` by default:

In `Member` entity:

```java
@ManyToOne
@JoinColumn(name = "library_id")
private Library library;
```

That's why a separate query was executed by hibernate for getting the associated Library. But there are 5 members in db but
only 3 queries are executed for getting the associated libraries. 

### Why ?

This is because Hibernate use session level caching.

## CURRENT_DATE in JPQL

```java
import jakarta.persistence.TypedQuery;

TypedQuery<Object> query = entityManager.createQuery("SELECT CURRENT_DATE", Object.class);
Object object = query.getSingleResult();
System.out.println(object);
```

The `object` has a value of type java.sql.Date;

