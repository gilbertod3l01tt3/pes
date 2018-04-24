package com.dtte.portal.obiee.dao;

import java.sql.SQLException;
import java.util.List;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;

public interface ConfigOBIIEDAO {
	List<PORTALBI_CONFIGOBIEE> listAllConfigs() throws SQLException;

}
