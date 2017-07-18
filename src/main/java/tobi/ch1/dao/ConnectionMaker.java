package tobi.ch1.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	Connection makeConnection() throws ClassNotFoundException, SQLException;
}
