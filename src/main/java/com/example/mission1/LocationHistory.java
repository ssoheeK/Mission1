package com.example.mission1;

import com.example.mission1.dao.LocationDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@WebServlet("/locationHistory")
public class LocationHistory extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");

        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        String currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_DATE_TIME);

        // DB 저장
        LocationDAO locationDAO = new LocationDAO();
        locationDAO.insertLocationHistory(Double.parseDouble(lat), Double.parseDouble(lnt), currentTime);

    }
}
