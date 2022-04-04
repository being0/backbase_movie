## How to scale

### App servers
By Adding a load balancer behind the app servers and adding more machines, the app servers can be easily 
scaled, there is no bottleneck in the app servers. 

As load grows, it is better to separate the Kafka consumers in toprated service into another component. 
Consumer and API have different non-functional requirements and they shouldn't affect each other(better to be isolated) 

### Databases
In the database side, the only OLTP database is Cassandra. 
The other apps use in-memory cache and they don't forward user requests to the database.  
Cassandra can be scaled by adding more servers to it as we grow. The transactions have been partitioned based on userId.

The database on toprated service will receive background loads from Kafka consumers.
To reduce load on this Postgres DB we can do the following action:
* Combine multiple messages and calculate the rates and counts and then send one request to the DB 

### Kafka
Kafka selected because it is high available component and can be scaled easily. It is not a bottleneck, 
However it should be closely monitored and tuned.