package testpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Directions extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Directions() { super(); }

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		writer.println("doget");
		
		String testZipcode = "528704";
		String testRegion = "sg";
		calculateRoute(testZipcode, testRegion, request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		writer.println("dopost");
		
		String testZipcode = "528704";
		String testRegion = "sg";
		calculateRoute(testZipcode, testRegion, request, response);
	}
	
	private static String calculateRoute(String finish, String region, HttpServletRequest request, HttpServletResponse response){
		String result = "";
        String urlString = "https://maps.googleapis.com/maps/api/directions/xml?sensor=false&origin=529944&destination=528704&key=AIzaSyBu32ZzK6qNtqyUnzDK96HN1HYe4-n81dU";
       
        try {
            PrintWriter writer = response.getWriter();
    		writer.println("calculate route");
    		
			response.sendRedirect(urlString);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
       try {
            URL urlGoogleDirService = new URL(urlString);
            HttpURLConnection urlGoogleDirCon = (HttpURLConnection)urlGoogleDirService.openConnection();

            urlGoogleDirCon.setAllowUserInteraction( false );
            urlGoogleDirCon.setDoInput( true );
            urlGoogleDirCon.setDoOutput( false );
            urlGoogleDirCon.setUseCaches( true );
            urlGoogleDirCon.setRequestMethod("GET");
            urlGoogleDirCon.connect();

            DocumentBuilderFactory factoryDir = DocumentBuilderFactory.newInstance();
            DocumentBuilder parserDirInfo = factoryDir.newDocumentBuilder();
            Document docDir = parserDirInfo.parse(urlGoogleDirCon.getInputStream());

            urlGoogleDirCon.disconnect();
            result = docDir.toString();
            
        	return result;
        	
        } catch(Exception e) {
            System.out.println(e);
            return null;
        } 
    }
}