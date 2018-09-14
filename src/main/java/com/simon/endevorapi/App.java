package com.simon.endevorapi;

import java.io.*;
import org.json.*;
import java.net.*;
import java.util.Scanner;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Just warming up" );
        String var = "http://httpbin.org/get"; 
        String httpOut = callHttp(var);
        System.out.println(httpOut);
    }
    
    
    static String callHttp(String var) throws IOException {
    	URL url;
			url = new URL(var);		// Set URL to call
			HttpURLConnection con = (HttpURLConnection) url.openConnection();  // Open connection to URL
			con.setRequestMethod("GET");		// Set the HTTP method
			int httpStat = con.getResponseCode();		// This is what does the actual call
			String httpResponse = ("HTTP Response: " + httpStat);  // Display the HTTP status
			if (httpStat != 200) {
				return "Bad call! " +httpStat;
			}
			Scanner scan = new Scanner(url.openStream());
			String str = new String();   // Initialise str
			while (scan.hasNext())
				str += scan.nextLine();
			scan.close();
			
			System.out.println(str);
			
			JSONObject obj = new JSONObject(str);		// Build JSON object. If fails return a message
/*			if (! obj.getString("status").equals("OK"))
				return "JSON object build fell over"; */
			String o = "This is the origin: " + obj.getString("origin");
			System.out.println(o);
/*			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) !=null) {
				content.append(inputLine);
			}
			in.close();
			System.out.println(inputLine); */
			return httpResponse;
			
		}
    	
    	
    }
    



/*	println "This is the curl"
def respCurl = "curl -v http://httpbin.org/get".execute().text
	println respCurl 
def slurper = new JsonSlurper()
def jsonResult = slurper.parseText(respCurl)
println "This is the host: " + jsonResult.headers.Host
println "And this is the origin: " + jsonResult.origin
def origin = jsonResult.origin
println "here is it again " + origin
println "Ending now see you later." */