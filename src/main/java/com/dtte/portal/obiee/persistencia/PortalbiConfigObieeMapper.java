package com.dtte.portal.obiee.persistencia;

import org.mybatis.cdi.Mapper;

@Mapper
public interface PortalbiConfigObieeMapper {
	PortalbiConfigobiee getConfig();
}
