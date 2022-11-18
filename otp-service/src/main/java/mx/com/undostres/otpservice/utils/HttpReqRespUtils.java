package mx.com.undostres.otpservice.utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;
import org.json.JSONArray;

public class HttpReqRespUtils {

    public static String geoip_country_code_by_name() throws IOException{
        URL urlObject = new URL("https://ipinfo.io/"+HttpReqRespUtils.getPublicIP());
        URLConnection conn = urlObject.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine, output = "";
        String countryCode = "";
        while ((inputLine = in.readLine()) != null) {
            output += inputLine;
        }
        in.close();
        output = "["+output+"]";
        JSONArray array = new JSONArray(output);
        for(int i=0;i<array.length();i++) {
            JSONObject object = array.getJSONObject(i);
            countryCode = object.getString("country");
//            System.out.println(object.getString("country"));
        }
        return countryCode;
    }

    public static String getPublicIP() throws IOException{
        Document doc = Jsoup.connect("http://www.checkip.org").get();
        return doc.getElementById("yourip").select("h1").first().select("span").text();
    }
}
