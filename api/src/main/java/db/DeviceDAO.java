package com.etherfuse.server.db;

import com.etherfuse.server.mapper.DeviceMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(DeviceMapper.class)
public interface DeviceDAO {

  @SqlQuery("select * from device")
  List<Device> findAll();

  @SqlQuery("select * from device where mac = :mac")
  String findNameByMAC(@Bind("mac") String mac);

}