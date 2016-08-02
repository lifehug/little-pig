package com.etherfuse.server.mapper;

import com.etherfuse.server.core.Event;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements ResultSetMapper<Event> {
  public Event map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    return new Event(r.getInt("id"), r.getLong("ip_src"), r.getLong("ip_dst"), r.getString("firstname"), r.getString("lastname"), r.getString("hostname"), r.getTimestamp("timestamp"), r.getLong("sid"), r.getLong("cid"));
  }
}