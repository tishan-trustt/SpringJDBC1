package com.tishan.repository;

import com.tishan.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepo {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student student){
        String sql = "insert into students (rollNo,name,marks) values (?,?,?)";
        int r = jdbc.update(sql,student.getRollNo(),student.getName(),student.getMarks());
        System.out.printf("%d Student Added to repo",r);
        System.out.println();
    }

    public List<Student> findAll() {

        String sql = "select * from students";
        RowMapper<Student> rowMapper = new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

                Student student = new Student();
                student.setRollNo(rs.getInt("rollNo"));
                student.setName(rs.getString("name"));
                student.setMarks(rs.getInt("marks"));
                return student;
            }
        };

        return jdbc.query(sql,rowMapper);
    }
}
