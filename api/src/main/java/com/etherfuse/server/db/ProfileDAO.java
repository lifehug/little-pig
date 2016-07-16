package com.etherfuse.server.db;

import com.etherfuse.server.mapper.ProfileMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import com.etherfuse.server.core.Profile;
import java.util.List;

@RegisterMapper(ProfileMapper.class)
public interface ProfileDAO {

  @SqlQuery("select * from profile")
  List<Profile> findAll();

  @SqlQuery("select * from profile where id = :id")
  Profile findProfileByID(@Bind("id") int id);

  @SqlUpdate("insert into profile (id, firstname, lastname, email, network) values (:id, :firstname, :lastname, :email, :network)")
  int createProfile(@Bind("id") int id, @Bind("firstname") String firstname, @Bind("lastname") String lastname, @Bind("email") String email, @Bind("network") boolean network);

  @SqlUpdate("update profile set firstname = :firstname, lastname = :lastname, email = :email, network = :network where id = :id")
  int updateProfile(@Bind("id") int id, @Bind("firstname") String firstname, @Bind("lastname") String lastname, @Bind("email") String email, @Bind("network") boolean network);
}