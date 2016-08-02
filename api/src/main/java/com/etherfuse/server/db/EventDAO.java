package com.etherfuse.server.db;

import com.etherfuse.server.mapper.EventMapper;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import com.etherfuse.server.core.Event;
import java.util.List;
import java.sql.Timestamp;

@RegisterMapper(EventMapper.class)
public interface EventDAO {

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, p.lastname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr or i.ip_src = d.ip_addr left join profile as p on d.owner = p.id where e.timestamp between :timeStr and :timeEnd order by e.timestamp")
  List<Event> findAll(@Bind("timeStr") Timestamp timeStr, @Bind("timeEnd") Timestamp timeEnd);

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, p.lastname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr or i.ip_src = d.ip_addr left join profile as p on d.owner = p.id where i.sid = :sid and i.cid = :cid order by e.timestamp")
  Event findEventByID(@Bind("sid") Long sid, @Bind("cid") Long cid);

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, p.lastname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr or i.ip_src = d.ip_addr left join profile as p on d.owner = p.id where d.hostname = :hostname and e.timestamp between :timeStr and :timeEnd order by e.timestamp")
  List<Event> findEventsByDevice(@Bind("hostname") String hostname, @Bind("timeStr") Timestamp timeStr, @Bind("timeEnd") Timestamp timeEnd);

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, p.lastname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr or i.ip_src = d.ip_addr left join profile as p on d.owner = p.id where p.id = :id and e.timestamp between :timeStr and :timeEnd order by e.timestamp")
  List<Event> findEventsByProfile(@Bind("id") int id, @Bind("timeStr") Timestamp timeStr, @Bind("timeEnd") Timestamp timeEnd);

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, p.lastname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr or i.ip_src = d.ip_addr left join profile as p on d.owner = p.id and e.timestamp between :timeStr and :timeEnd order by e.timestamp limit :start,:end")
  List<Event> findAllWithPaging(@Bind("start") int start, @Bind("end") int end, @Bind("timeStr") Timestamp timeStr, @Bind("timeEnd") Timestamp timeEnd);

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, p.lastname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr or i.ip_src = d.ip_addr left join profile as p on d.owner = p.id where i.sid = :sid and i.cid = :cid order by e.timestamp limit :start,:end")
  Event findEventByIDWithPaging(@Bind("sid") Long sid, @Bind("cid") Long cid, @Bind("start") int start, @Bind("end") int end);

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, p.lastname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr or i.ip_src = d.ip_addr left join profile as p on d.owner = p.id where d.hostname = :hostname and e.timestamp between :timeStr and :timeEnd order by e.timestamp limit :start,:end")
  List<Event> findEventsByDeviceWithPaging(@Bind("hostname") String hostname, @Bind("start") int start, @Bind("end") int end, @Bind("timeStr") Timestamp timeStr, @Bind("timeEnd") Timestamp timeEnd);

  @SqlQuery("select p.id, i.sid, i.cid, i.ip_src, i.ip_dst, p.firstname, p.lastname, d.hostname, e.timestamp from event as e left join iphdr as i on i.sid = e.sid and i.cid = e.cid left join device as d on i.ip_dst = d.ip_addr or i.ip_src = d.ip_addr left join profile as p on d.owner = p.id where p.id = :id and e.timestamp between :timeStr and :timeEnd order by e.timestamp limit :start,:end")
  List<Event> findEventsByProfileWithPaging(@Bind("id") int id, @Bind("start") int start, @Bind("end") int end, @Bind("timeStr") Timestamp timeStr, @Bind("timeEnd") Timestamp timeEnd);

}