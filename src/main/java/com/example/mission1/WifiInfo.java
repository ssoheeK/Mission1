package com.example.mission1;

import com.example.mission1.dao.WifiInfoDAO;
import com.example.mission1.dto.WifiDTO;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/wifiInfo")
public class WifiInfo extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // 페이징 끝 자리까지 데이터 저장
        int endIndex = 1000;
        int total = getPublicWifiList(1, endIndex);

        // 전체 갯수 가져온 페이징 끝 자리까지 계속 요청
        int requestCount = (int) Math.ceil((double) total / 1000) - 1;

        while(requestCount > 0){
            if(requestCount == 1) {
                getPublicWifiList(endIndex + 1, total);
            } else {
                getPublicWifiList(endIndex + 1, endIndex + 1000);
            }
            endIndex += 1000;
            requestCount--;
        }

        request.setCharacterEncoding("UTF-8");
        request.setAttribute("total", total);
        request.getRequestDispatcher("WEB-INF/load-wifi.jsp").forward(request, response);

    }

    private int getPublicWifiList(int startIndex, int endIndex) throws IOException {

        String uri = "http://openapi.seoul.go.kr:8088";
        String authKey = "77414862427468673437754c72646f";
        String type = "json";
        String serviceName = "TbPublicWifiInfo";
        String start = String.valueOf(startIndex);
        String end = String.valueOf(endIndex);

        String encodeType = "UTF-8";

        StringBuilder urlBuilder = new StringBuilder(uri);
        urlBuilder.append("/" +  URLEncoder.encode(authKey,encodeType) );
        urlBuilder.append("/" +  URLEncoder.encode(type,encodeType) );
        urlBuilder.append("/" + URLEncoder.encode(serviceName,encodeType));
        urlBuilder.append("/" + URLEncoder.encode(start,encodeType));
        urlBuilder.append("/" + URLEncoder.encode(end,encodeType));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader br;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옴
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        conn.disconnect();

        int total = parseJson(sb.toString());

        return total;
    }

    private int parseJson(String jsonData){

        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(jsonData);
        JsonObject wifiInfoObject = (JsonObject) object.get("TbPublicWifiInfo");
        JsonElement total = wifiInfoObject.get("list_total_count");
        JsonArray rows = (JsonArray) wifiInfoObject.get("row");

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<WifiDTO>>(){}.getType();
        List<WifiDTO> list = gson.fromJson(rows.toString(), listType);

        // DB에 데이터 저장
        WifiInfoDAO wifiInfoDAO = new WifiInfoDAO();
        wifiInfoDAO.publicWifiInfoInsert(list);

        return Integer.parseInt(String.valueOf(total));
    }
}
