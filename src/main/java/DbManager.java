import dto.FileInfoDto;
import dto.LineDto;

import java.sql.*;

public class DbManager {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/statistics_db";
    static final String USER = "postgres";
    static final String PASS = "Armagedon12";

    public void AddFile(FileInfoDto fileInfo) {
        Connection conn = null;
        Statement stmt = null;

        String sqlFile = String.format("insert into file_info (file_location, " +
                        "file_name, longest_word, shortest_word, length, " +
                        "average_word_length) values ('%s', '%s', '%s', '%s', %s, %s)",
                fileInfo.getLocation(),
                fileInfo.getName(),
                fileInfo.getLongestWord(),
                fileInfo.getShortestWord(),
                fileInfo.getLength(),
                fileInfo.getAverageWordLength());

        String sqlLine = "insert into line_info (file_id, " +
                "\"content\", longest_word, shortest_word," +
                "length, average_word_length) " +
                "values(?, ?, ?, ?, ?, ?)";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement statementFile = conn.prepareStatement(sqlFile,
                    Statement.RETURN_GENERATED_KEYS);
            statementFile.execute();

            ResultSet rs = statementFile.getGeneratedKeys();
            int key = 0;
            if (rs.next()) {
                key = rs.getInt(1);
            }


            PreparedStatement statementLine = conn.prepareStatement(sqlLine);

            int count = 0;

            for (LineDto line : fileInfo.getLines()) {
                statementLine.setInt(1, key);
                statementLine.setString(2, (line.getContent()));
                statementLine.setString(3, line.getLongestWord());
                statementLine.setString(4, line.getShortestWord());
                statementLine.setInt(5, line.getLength());
                statementLine.setInt(6, line.getAverageWordLength());

                statementLine.addBatch();
                count++;

                if (count % 100 == 0 || count == fileInfo.getLines().size()) {
                    statementLine.executeBatch();
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
