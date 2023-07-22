package org.skylar11d.minecraftp.tntrun.utilities.data.impl;

import java.sql.Connection;

public interface IMySQL {

    Connection getConnection();
    void setConnection(Connection connection);

}
