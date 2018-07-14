# Spring Boot con Elasticsearch

Proyecto spring boot que se integra con [elasticsearch](https://www.elastic.co/products/elasticsearch) usando spring-data, realizando implementación de ElasticsearchCrudRepository y personalizando querys con @Query, aspectos a tener en cuenta:

1. [Descargar] (https://www.elastic.co/downloads/past-releases/elasticsearch-5-3-2) elasticsearch comprimido según convenga.

2. Descomentar **cluster-name** en *elasticsearch-5.3.2/config/elasticsearch.yml*:
```
# ---------------------------------- Cluster -----------------------------------
#
# Use a descriptive name for your cluster:
#
cluster.name: my-application
#
```
3. Correr el proyecto.