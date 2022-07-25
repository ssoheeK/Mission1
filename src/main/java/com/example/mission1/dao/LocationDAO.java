package com.example.mission1.dao;

import com.example.mission1.dto.LocationDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {
    Connection conn;
    Statement stmt = null;
    ResultSet rs = null;

    public void connection(){
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/public_wifi?serverTimezone=UTC";
        String user = "root";
        String password = "thehd123";

        try{
            Class.forName(driverName);
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e){
            System.out.println("[로드 오류]\n" + e.toString());
        } catch (SQLException e){
            // DB 접속 정보 오류
            System.out.println("[연결 오류]\n" + e.toString());
        }

    }

    public void close(){
        try {
            if(stmt != null){
                stmt.close();
            }

            if(rs != null){
                rs.close();
            }

            if(conn != null){
                conn.close();
            }
        } catch (SQLException e){
            System.out.println("[닫기 오류]\n" + e.toString());
        }
    }

    public void insertLocationHistory(double lat, double lnt, String currentTime){
        PreparedStatement stmt = null;

        String sql = "INSERT INTO location_history VALUES(0,?,?,?);";
        connection();

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, lat);
            stmt.setDouble(2, lnt);
            stmt.setString(3, currentTime);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("[INSERT 쿼리 오류]\n" + e.toString());
        }finally {
            close();
        }
    }

    public List<LocationDTO> selectLocationHistory(){

        List<LocationDTO> list = new ArrayList<>();

        PreparedStatement stmt = null;

        String sql = "SELECT * FROM location_history;";
        connection();

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()){
                LocationDTO dto = new LocationDTO();
                dto.setId(Integer.parseInt(rs.getString("id")));
                dto.setX(Double.parseDouble(rs.getString("x")));
                dto.setY(Double.parseDouble(rs.getString("y")));
                dto.setInquiryTime(rs.getString("inquiry_time"));
                list.add(dto);
            }
        } catch (SQLException e) {
            System.out.println("[SELECT 쿼리 오류]\n" + e.toString());
        } finally {
            close();
        }

        return list;
    }


}
