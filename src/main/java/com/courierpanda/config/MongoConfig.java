package com.courierpanda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.courierpanda.repository")
public class MongoConfig extends AbstractMongoClientConfiguration{

	@Value("${spring.data.mongodb.host}")
	private String host;
	@Value("${spring.data.mongodb.database}")
	private String database;
	
	 @Autowired
	    private MappingMongoConverter mongoConverter;
	 
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return database;
	}
	@Bean
	public MongoClient mongoClient() {
	       return MongoClients.create("mongodb://localhost:27017");
	   }
	@Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mongoConverter);
    }
	 
}
