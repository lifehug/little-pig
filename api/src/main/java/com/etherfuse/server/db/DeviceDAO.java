package com.etherfuse.server.db;

import com.etherfuse.server.mapper.DeviceMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import com.etherfuse.server.core.Device;
import java.util.List;

@RegisterMapper(DeviceMapper.class)
public interface DeviceDAO {

  @SqlQuery("select * from device")
  List<Device> findAll();

  @SqlQuery("select * from device where mac = :mac")
  Device findNameByMAC(@Bind("mac") String mac);

  @SqlQuery("select * from device where hostname = :hostname")
  Device findNameByHostname(@Bind("hostname") String hostname);

  @SqlQuery("select * from device where ip_addr = :ip_addr")
  Device findNameByIPAddress(@Bind("ip_addr") String ip_addr);

  @SqlQuery("select * from device where owner = :owner_id")
  List<Device> findDevicesByProfileId(@Bind("owner_id") int owner_id);

  @SqlUpdate("update device set owner = :id where hostname = :hostname")
  int assign(@Bind("id") int id, @Bind("hostname") String hostname);

}