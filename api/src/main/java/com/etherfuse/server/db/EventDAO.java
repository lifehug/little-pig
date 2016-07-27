package com.etherfuse.server.db;

import com.etherfuse.server.mapper.EventMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import com.etherfuse.server.core.Event;
import java.util.List;

@RegisterMapper(EventMapper.class)
public interface EventDAO {

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr left join profile as p on d.owner = p.id")
  List<Event> findAll();

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr left join profile as p on d.owner = p.id where i.sid = :sid and i.cid = :cid")
  Event findEventByID(@Bind("sid") Long sid, @Bind("cid") Long cid);

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr left join profile as p on d.owner = p.id where d.hostname = :hostname")
  List<Event> findEventsByDevice(@Bind("hostname") String hostname);

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr left join profile as p on d.owner = p.id where p.id = :id")
  List<Event> findEventsByProfile(@Bind("id")  int id);

}