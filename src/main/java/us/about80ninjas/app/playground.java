package us.about80ninjas.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class playground {

	private final static String USER_AGENT = "Mozilla/5.0";
	private final static String REQUEST_METHOD = "GET";
	
	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://jordan:1195a045431d87150e1eb18ee9c8d27b8f@192.168.1.200:8080/job/SpringBootApp/build?token=mPj6hYZ5YeDy3C58AHzpz6nsr");
			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(REQUEST_METHOD);
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty(key, value);
			int responseCode = con.getResponseCode();
			System.out.println("\nSending " + REQUEST_METHOD + " request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			con.connect();
			
			con.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
