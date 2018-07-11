package com.leysoft.configuration;

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
	
	@Bean
	public TransportClientFactoryBean transportClientFactoryBean() {
		return new TransportClientFactoryBean();
	}
	
	@Bean
	public Client client(TransportClientFactoryBean clientFactory) throws Exception {
		clientFactory.setClusterName(clusterName);
		clientFactory.setClusterNodes(clusterNodes);
		return clientFactory.getObject();
	}
	
	@Bean
	public ElasticsearchOperations elasticsearchTemplate(Client client) {
		return new ElasticsearchTemplate(client);
	}
}
