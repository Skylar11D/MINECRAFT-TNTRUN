package org.skylar11d.minecraftp.tntrun.utilities.data;

import org.bukkit.Bukkit;
import org.skylar11d.minecraftp.tntrun.utilities.data.impl.IMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class MySQL implements IMySQL {

    private String database;
    private String host;
    private int port;
    private String username;
    private String password;

    private Connection connection;


    public MySQL(String database, String host, int port, String username, String password){
        this.database = database;
        this.host = host;
        this.port = port;
        this.username = "";
        this.password = "";


            synchronized (this){

                try {
                    if(getConnection() != null && !getConnection().isClosed())return;

                    setConnection(DriverManager.getConnection("jdbc:mysql//"+host+":"+port+"/"+database, username, password));

                    Class.forName("com.mysql.jdbc.Driver");
                    Bukkit.getLogger().log(Level.INFO, "§aCONNECTED TO MYSQL SERVER AND THE SPECIFIED DATABASE");
                    Bukkit.getLogger().log(Level.INFO, "Checking 'runners_data' table existence..");
                    PreparedStatement preparedStatement = getConnection().prepareStatement("CREATE TABLE runners_data IF NOT EXISTS" +
                            " (VICTORIES BIGINT(100), LOSES BIGINT(100), UUID VARCHAR(35))");
                    Bukkit.getLogger().log(Level.INFO, "database-name: "+database.toUpperCase());
                    Bukkit.getLogger().log(Level.INFO, "host: "+host+":"+port);
                } catch (ClassNotFoundException | SQLException e){e.printStackTrace();}

            }

    }

    @Override
    public Connection getConnection() {

        return connection;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
