package mouseportation.graph;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {

	public Neo4jConfig() {
		setBasePackage("mouseguests.transportation");
	}

	@Bean
	public GraphDatabaseService graphDatabaseService() {
		return new GraphDatabaseFactory()
				.newEmbeddedDatabase("/tmp/mouseportation");
	}
	
}
