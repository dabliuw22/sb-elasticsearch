package com.leysoft.configuration;

import java.util.Properties;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.leysoft.respository"})
public class ElasticsearchConfiguration {
	
	@Value(value = "${spring.data.elasticsearch.cluster-name}")
	private String clusterName;
	
	@Value(value = "${spring.data.elasticsearch.cluster-nodes}")
	private String clusterNodes;
	
	@Value(value = "${spring.data.elasticsearch.properties.transport.tcp.connect_timeout}")
	private String timeOut;
	
	@Bean
	public TransportClientFactoryBean transportClientFactoryBean() {
		return new TransportClientFactoryBean();
	}
	
	@Bean
	public Client client(TransportClientFactoryBean factory) throws Exception {
		factory.setClusterName(clusterName);
		factory.setClusterNodes(clusterNodes);
		factory.setProperties(createProperties());
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	@Bean
	public ElasticsearchOperations elasticsearchTemplate(Client client) {
		return new ElasticsearchTemplate(client);
	}
	
	private Properties createProperties() {
		Properties properties = new Properties();
		properties.put("cluster.name", clusterName);
		properties.put("transport.tcp.connect_timeout", timeOut);
		return properties;
	}
}
