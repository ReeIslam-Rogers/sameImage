package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class Config {

    private final Logger logger = LoggerFactory.getLogger(Config.class);

    private final ObjectMapper mapper;
    private final InputStream configFile;
    private final JsonNode jsonNode;

    private Config() {
        mapper = new ObjectMapper();
        configFile = getClass().getClassLoader().getResourceAsStream("application.json");
        try {
            jsonNode = mapper.readValue(configFile, JsonNode.class);
        } catch (IOException e) {
            logger.error("Unable to read config map");
            throw new RuntimeException(e);
        }
    }

    public static final ReadConfig CONFIG = new ReadConfig(){

        private final Config c = new Config();

        @Override
        public String get(String name) {
            return readValue(name, c.jsonNode);
        }

        private String readValue(String name, JsonNode node){
            int index = name.indexOf('.');
            if(index > 0) {
                String key = name.substring(0, index);
                String rest = name.substring(index + 1);
                return readValue(rest, node.get(key));
            } else {
                return node.get(name).toString();
            }
        }
    };

}
