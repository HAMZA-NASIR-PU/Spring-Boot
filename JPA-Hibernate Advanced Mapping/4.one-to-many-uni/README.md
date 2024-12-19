

`org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.example.demo.entity.Instructor.courses: could not initialize proxy - no Session`
1. First solution for above exception is to use FetchType.EAGER

https://stackoverflow.com/questions/17431312/what-is-the-difference-between-join-and-join-fetch-when-using-jpa-and-hibernate


https://stackoverflow.com/questions/5816417/how-to-properly-express-jpql-join-fetch-with-where-clause-as-jpa-2-criteriaq