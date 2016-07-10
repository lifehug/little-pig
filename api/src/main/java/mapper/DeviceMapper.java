package com.etherfuse.server.mapper;

import com.etherfuse.server.core.Device;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceMapper implements ResultSetMapper<Device> {
  public Device map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    return new Device(r.getString("mac"), r.getLong("ip_addr"), r.getInt("src_prt"), r.getDate("discovered"), r.getDate("last_seen"), r.getString("vendor"), r.getString("os_details"), r.getString("name"), r.getString("hostname"));
  }
}