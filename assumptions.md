## Assumptions

* For best picture service, I have assumed that the CSV will be imported by another service into the database.
For example Azure Data Factory has a tool that reads CSVs and map them to a database.

* For toprated service, I have assumed that movies meta info(except than rate) will be updated in the DB by a consumer that will read the messages that is 
raised by movie CRUD service(that is not implemented in this challenge).

