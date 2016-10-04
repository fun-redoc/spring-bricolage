package com.rsh.project.repository;

import com.rsh.project.domain.Period;
import com.rsh.project.domain.Project;
import com.rsh.project.domain.ProjectUserAssignment;
import com.rsh.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class ProjectRestRepository {

	private final JdbcTemplate jdbc;

	@Autowired
	public ProjectRestRepository(JdbcTemplate jdbc){
		this.jdbc = jdbc;
	}

    @SuppressWarnings("unused")
	public Project findById(Long id) {
        String sql = "SELECT p.id as pid, a.id as aid, u.id as uid, * FROM PROJECT as p, PROJECT_USER_ASSIGNMENT as a, USER as u where p.id = ? and p.id = a.project_id and a.user_id = u.id";
		return jdbc.queryForObject(
				sql,
				new Object[]{id},
                (ResultSet rs, int rowNum)  -> {
		                Project p= new Project();
                        p.setId(rs.getLong("pid"));
                        p.setName(rs.getString("name"));
                        Calendar cs = Calendar.getInstance();
                        cs.setTime(rs.getDate("start_date"));
                        p.setStartDate(cs);
                        Calendar ce = Calendar.getInstance();
                        ce.setTime(rs.getDate("end_date"));
                        p.setEndDate(ce);
                        p.setDescription(rs.getString("description"));
                        User u = User.builder()
                                .email(rs.getString("email"))
                                .firstName(rs.getString("first_name"))
                                .lastName(rs.getString("last_name"))
                                .id(rs.getLong("uid"))
                                .build();
                        ProjectUserAssignment pa = ProjectUserAssignment.builder()
                                    .id(rs.getLong("aid"))
                                    .user(u)
                                    //.project(p)
                                    .periods(getPeriods(rs.getLong("aid")))
                                    .build();

                        List<ProjectUserAssignment> pas = new ArrayList<>();
                        pas.add(pa);
                        p.setProjectUserAssignmentList(pas);
                        return p;
				}
        );
    }

    private List<Period> getPeriods(Long id) {
        String sql = "select start, end from period where project_user_id = ?";
        return jdbc.query(sql,
            new Object[] {id},
            (ResultSet rs, int i) -> {
                Calendar cs = Calendar.getInstance();
                cs.setTime(rs.getDate("start"));
                Calendar ce = Calendar.getInstance();
                ce.setTime(rs.getDate("end"));
                return Period.builder()
                        .start(cs)
                        .end(ce)
                        .build();
        });
    }

	@SuppressWarnings("unused")
	public List<Project> findAll() {
		String sql = "select * from project order by start_date";

        return jdbc.query(sql, (rs, i) -> {
            Project p =new Project( );
            p.setId(rs.getLong("id"));
            p.setName(rs.getString("name"));
            Calendar cs = Calendar.getInstance();
            cs.setTime(rs.getDate("start_date"));
            p.setStartDate(cs);
            Calendar ce = Calendar.getInstance();
            ce.setTime(rs.getDate("end_date"));
            p.setEndDate(ce);
            p.setDescription(rs.getString("description"));
            p.setProjectUserAssignmentList(new ArrayList<>());
            return p;
        });
	}
}