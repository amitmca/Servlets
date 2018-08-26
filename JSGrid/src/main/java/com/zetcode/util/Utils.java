package com.zetcode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

import com.google.common.base.Splitter;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Utils {

    public static DataSource getDataSource() {

    	ConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        
    	  ((MysqlDataSource) dataSource).setUser("root");
	      ((MysqlDataSource) dataSource).setPassword("");
	      ((MysqlDataSource) dataSource).setServerName("localhost");
	      ((MysqlDataSource) dataSource).setPort(3306);
	      ((MysqlDataSource) dataSource).setDatabaseName("jdbc");
        
        return (DataSource) dataSource;
    }

    public static Map<String, String> getParameterMap(HttpServletRequest request) {

        BufferedReader br = null;
        Map<String, String> dataMap = null;

        try {

            InputStreamReader reader = new InputStreamReader(
                    request.getInputStream());
            br = new BufferedReader(reader);

            String data = br.readLine();

            dataMap = Splitter.on('&')
                    .trimResults()
                    .withKeyValueSeparator(
                            Splitter.on('=')
                            .limit(2)
                            .trimResults())
                    .split(data);

            return dataMap;
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Utils.class.getName()).log(Level.WARNING, null, ex);
                }
            }
        }

        return dataMap;
    }
}
