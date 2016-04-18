package relationshipChecker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class Configuration {

    private static String configPath = "config";

    private static String databaseUser;
    private static String databasePassword;
    private static String databaseServer;
    private static String databaseName;
    private static String databaseInstance;

    public static String getConfigPath() {
        return configPath;
    }

    public static void setConfigPath(String configPath) {
        Configuration.configPath = configPath;
    }

    public static String getDatabaseUser() {
        return databaseUser;
    }

    public static void setDatabaseUser(String databaseUser) {
        Configuration.databaseUser = databaseUser;
    }

    public static String getDatabasePassword() {
        return databasePassword;
    }

    public static void setDatabasePassword(String databasePassword) {
        Configuration.databasePassword = databasePassword;
    }

    public static String getDatabaseServer() {
        return databaseServer;
    }

    public static void setDatabaseServer(String databaseServer) {
        Configuration.databaseServer = databaseServer;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static void setDatabaseName(String databaseName) {
        Configuration.databaseName = databaseName;
    }

    public static String getDatabaseInstance() {
        return databaseInstance;
    }

    public static void setDatabaseInstance(String databaseInstance) {
        Configuration.databaseInstance = databaseInstance;
    }

    public static void loadConfiguration(Logger log) throws Exception {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(Configuration.getConfigPath() + "/config.properties");
            // load a properties file
            prop.load(input);
            // get all configuration properties
            Configuration.setDatabaseUser(prop.getProperty("database.user"));
            Configuration.setDatabasePassword(prop.getProperty("database.password"));
            Configuration.setDatabaseServer(prop.getProperty("database.server"));
            Configuration.setDatabaseName(prop.getProperty("database.name"));
            Configuration.setDatabaseInstance(prop.getProperty("database.instance"));
            
        } catch (IOException ex) {
            log.error("An error occurred while loading the config.properties file. This execution will be aborted.", ex);
            log.error("Application Stopped.");
            System.exit(1);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
