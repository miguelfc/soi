package relationshipChecker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {

    public static void main(String[] args) {
        PropertyConfigurator.configure(Configuration.getConfigPath() + "/log4j.properties");

        Logger log = Logger.getLogger(Main.class.getName());
        Logger report = Logger.getLogger("reportLogger");

        log.info("Executing relationship checker...");

        try {
            Configuration.loadConfiguration(log);
        } catch (Exception e1) {
            log.error("The configuration file config/config.properties is missing.");
        }

        Map<Long, Long> totalCount = new TreeMap<>();

        Map<Long, Set<Long>> relationshipList = new TreeMap<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://" + Configuration.getDatabaseServer() + ";" +
                    "database=" + Configuration.getDatabaseName() + ";" +
                    "user=" + Configuration.getDatabaseUser() + ";" +
                    "password=" + Configuration.getDatabasePassword() + "";
            Connection con = DriverManager.getConnection(connectionUrl);
            log.info("Connected.");

            String sql = "Select DISTINCT ANodeCIID, BNodeCIID, ServiceCIID from dbo.CIRelationship";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Long childId = rs.getLong(2);
                Long parentId = rs.getLong(1);
                Set<Long> parentSet = relationshipList.get(childId);
                if (parentSet == null) {
                    parentSet = new HashSet<>();
                    relationshipList.put(childId, parentSet);
                }
                parentSet.add(parentId);
            }

            report.info("Id;HexId;Count");
            for (Long id : relationshipList.keySet()) {
                log.debug("Processing item  <" + id + "> (" + Long.toHexString(id) + ")...");
                Long count = calculate(id, relationshipList);
                totalCount.put(id, count);
                report.info(id + ";" + Long.toHexString(id) + ";" + count);
                log.debug("Total item count <" + id + "> (" + Long.toHexString(id) + "):  " + count);
            }

            List<Map.Entry<Long, Long>> entries = new ArrayList<Map.Entry<Long, Long>>(totalCount.entrySet());
            Collections.sort(entries, new Comparator<Map.Entry<Long, Long>>() {
                public int compare(
                        Map.Entry<Long, Long> entry1, Map.Entry<Long, Long> entry2) {
                    return entry2.getValue().compareTo(entry1.getValue());
                }
            });

            Integer i = 0;
            log.info("The Top 30 of repeated items on the tree are: ");
            log.info("\tID\tCount");
            for (Entry<Long, Long> item : entries) {
                log.info("\t" + item.getKey() + "\t" + item.getValue());
                i++;
                if (i >= 30) {
                    break;
                }
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            System.exit(0);
        }

        log.info("You can find the full report in the log directory, in the report.csv file.");
        log.info("Finished");
    }

    private static Long calculate(Long id, Map<Long, Set<Long>> list) {
        Long count = 1L;
        Set<Long> parents = list.get(id);

        if (parents != null) {
            Long parentCount = 0L;
            for (Long parent : parents) {
                parentCount += calculate(parent, list);
            }
            count = parentCount * count;
        }
        return count;
    }
}
