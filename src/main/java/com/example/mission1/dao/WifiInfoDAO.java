package com.example.mission1.dao;

import com.example.mission1.dto.WifiDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WifiInfoDAO {

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

    public void publicWifiInfoInsert(List<WifiDTO> list){
        PreparedStatement stmt = null;

        String sql = "INSERT INTO wifi_info VALUES(0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        connection();

        try {
            for (WifiDTO dto : list){
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, dto.getX_SWIFI_MGR_NO());
                stmt.setString(2, dto.getX_SWIFI_WRDOFC());
                stmt.setString(3, dto.getX_SWIFI_MAIN_NM());
                stmt.setString(4, dto.getX_SWIFI_ADRES1());
                stmt.setString(5, dto.getX_SWIFI_ADRES2());
                stmt.setString(6, dto.getX_SWIFI_INSTL_FLOOR());
                stmt.setString(7, dto.getX_SWIFI_INSTL_TY());
                stmt.setString(8, dto.getX_SWIFI_INSTL_MBY());
                stmt.setString(9, dto.getX_SWIFI_SVC_SE());
                stmt.setString(10, dto.getX_SWIFI_CMCWR());
                stmt.setString(11, dto.getX_SWIFI_CNSTC_YEAR());
                stmt.setString(12, dto.getX_SWIFI_INOUT_DOOR());
                stmt.setString(13, dto.getX_SWIFI_REMARS3());
                stmt.setDouble(14, dto.getLNT());
                stmt.setDouble(15, dto.getLAT());
                stmt.setString(16, dto.getWORK_DTTM());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("[INSERT 쿼리 오류]\n" + e.toString());
        }finally {
            close();
        }
    }

    public List<Map<String, String>> selectWifiInfo(double myLat, double myLnt){

        List<Map<String, String>> list = new ArrayList<>();

        PreparedStatement stmt = null;
        String sql = "SELECT *, " +
                "ROUND((" +
                "6371 * acos(" +
                "cos(radians(?))" +
                "* cos(radians(lat))" +
                "* cos(radians(lnt) - radians(?))" +
                "+ sin(radians(?))" +
                "* sin(radians(lat))" +
                ")), 4) as distance " +
                "from wifi_info " +
                "having distance <= 1 " +
                "order by distance " +
                "limit 20";

        connection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, myLat);
            stmt.setDouble(2, myLnt);
            stmt.setDouble(3, myLat);
            rs = stmt.executeQuery();

            while(rs.next()){
                Map<String, String> map = new HashMap<>();
                map.put("id", rs.getString("id"));
                map.put("mgrNo", rs.getString("mgr_no"));
                map.put("wrdofc", rs.getString("wrdofc"));
                map.put("mainNm", rs.getString("main_nm"));
                map.put("adres1", rs.getString("adres1"));
                map.put("adres2", rs.getString("adres2"));
                map.put("instlTy", rs.getString("instl_ty"));
                map.put("instlMby", rs.getString("instl_mby"));
                map.put("svcSe", rs.getString("svc_se"));
                map.put("cmcwr", rs.getString("cmcwr"));
                map.put("cnstcYear", rs.getString("cnstc_year"));
                map.put("inoutDoor", rs.getString("inout_door"));
                map.put("remars3", rs.getString("remars3"));
                map.put("lat", rs.getString("lat"));
                map.put("lnt", rs.getString("lnt"));
                map.put("workDttm", rs.getString("work_dttm"));
                map.put("distance", rs.getString("distance"));
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println("[SELECT 쿼리 오류]\n" + e.toString());
        }
        return list;
    }
}
