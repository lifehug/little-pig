package com.etherfuse.server.mapper;

import com.etherfuse.server.core.Profile;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileMapper implements ResultSetMapper<Profile> {
  public Profile map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    return new Profile(r.getInt("id"), r.getString("firstname"), r.getString("lastname"), r.getString("email"));
  }
}