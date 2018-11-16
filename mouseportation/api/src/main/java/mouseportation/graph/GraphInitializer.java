package mouseportation.graph;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;

@Component
public class GraphInitializer implements InitializingBean {

	private Logger logger = LoggerFactory.getLogger(GraphInitializer.class);
	
	private Neo4jTemplate neo4j;

	@Autowired
	public GraphInitializer(Neo4jTemplate neo4j) {
		this.neo4j = neo4j;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		long nodeCount = countNodes();

		if (nodeCount == 0) {
			logger.info("Graph is empty. Initializing with fresh data");
			ClassPathResource cypherResource = new ClassPathResource("mouseportation/data/places-and-routes.cypher");
			InputStream is = cypherResource.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			HashMap<String, Object> noParams = new HashMap<String, Object>();
			while (reader.ready()) {
				String line = reader.readLine();
				if (line.contains("CREATE")) {
					neo4j.query(line, noParams);
				}
			}
			is.close();
		} else {
			logger.info("Graph already initialized");
		}
	}

	private long countNodes() {
		Result<Map<String, Object>> result = neo4j.query("start n=node(*) return count(n) as node_count", new HashMap<String, Object>());
		return (long) result.single().get("node_count");
	}

}
