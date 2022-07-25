package com.example.mission1;

import com.example.mission1.dao.LocationDAO;
import com.example.mission1.dto.LocationDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/locationHistoryList")
public class LocationHistoryList extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocationDAO locationDAO = new LocationDAO();

        List<LocationDTO> list = locationDAO.selectLocationHistory();

        request.setAttribute("list", list);
        request.getRequestDispatcher("WEB-INF/history.jsp").forward(request, response);
    }
}
