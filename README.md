# Gym Microservices workshop

This is an extension of the gym microservices workshop developed a while ago. Now this implementation aims to improve the communication between services using tools
for async communication, such as Apache Kafka and RabbitMQ, the choice of where to use kafka and rabbit was taken based on the directives given by the professor.

Base microservices were developed by:
- Class Microservice: https://github.com/jucarata/Class-Management-Service
- Trainer Microservice: https://github.com/JesusD03/Trainers-Management-Service
- Equipment Microservice: https://github.com/JuanFCast/gym-equipment-service
- Members Microservice: https://github.com/CamiloBueno/microserviceMember

Taking this basic implementation, I added the necessary configuration to use Kafka, RabbitMQ, and Keycloak.
