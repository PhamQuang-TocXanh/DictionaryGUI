package com.example.fxfxfxf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DictDatabase {
    /**
     * SQL connection.
     * @return
     */

    private Connection connect() {
        String url = "jdbc:sqlite:evdict.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * add a word to database.
     * @param word
     * @param pronunciation
     * @param meaning
     */
    public void addWord(String word, String pronunciation, String meaning) {
        //sql insert syntax
        String sql = "INSERT INTO evdict(word,pronunciation,meaning) VALUES(?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, word);
            pstmt.setString(2, pronunciation);
            pstmt.setString(3, meaning);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * find word meaning in database.
     * @param keyWord
     * @return
     */
    public String findWord(String keyWord){

        //sql select syntax
        String sql = "SELECT * FROM evdict WHERE word = ?";

        try {
            Connection conn = this.connect();

            PreparedStatement pstmt  = conn.prepareStatement(sql);

            pstmt.setString(1,keyWord );

            ResultSet rs    = pstmt.executeQuery();
            StringBuilder result = new StringBuilder();

            while (rs.next()) {

                result.append(rs.getString("word") +  "\r\n\t" + rs.getString("pronunciation")
                        + "\r\n\t" + rs.getString("meaning") + "\t");

            }
            return result.toString();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * Delete a word in database.
     * @param word
     */
    public void deleteWord(String word) {
        // sql delete syntax
        String sql = "DELETE FROM evdict WHERE word = ?";

        try {
            Connection conn = this.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, word);

            pstmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Edit word in database.
     * @param word
     * @param newMeaning
     */
    public void editWord(String word, String newPronunciation,String newMeaning) {
        // sql update syntax
        String sql = "UPDATE evdict SET pronunciation = ? , meaning = ? WHERE word = ?";
        try {
            Connection conn = this.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newPronunciation);
            pstmt.setString(2, newMeaning);
            pstmt.setString(3, word);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     */
    boolean isInDictionary(String word) {
        return findWord(word) != "";
    }


    /**
     *  Dictionary Searcher.
     * @param word
     * @return
     */
    public List<String> dictionarySearcher(String word) {
        //sql select syntax
        String sql = "SELECT * FROM evdict WHERE word LIKE ? ORDER BY word ASC";
        List<String> listwords = new ArrayList<String>();
        try {
            Connection conn = this.connect();

            PreparedStatement ps  = conn.prepareStatement(sql);

            ps.setString(1,word + "%");

            ResultSet rs    = ps.executeQuery();

            while (rs.next()) {

                listwords.add(rs.getString("word"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listwords;
    }
}
