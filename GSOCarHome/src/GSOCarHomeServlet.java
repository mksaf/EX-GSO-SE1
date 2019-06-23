

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GSOCarHomeServlet
 */
@WebServlet("/GSOCarHomeServlet")
public class GSOCarHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Parkhausbesitzer parkhaus = new Parkhausbesitzer();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GSOCarHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getQueryString() == null) {
			System.out.println("Kein Befehl");
		} else {
			PrintWriter out = response.getWriter();

			String[] params = request.getQueryString().split("=");
			String command = params[0];
			String param = params[1];
			
			if("cmd".equals(command) && "sum".equals(param)) {
				response.setContentType("text/html");
				out.println(parkhaus.getEinnahmen());
				System.out.println("sum = " + parkhaus.getEinnahmen());
			} else if ("cmd".equals(command) && "avg".equals(param)) {
				response.setContentType("text/html");
				out.println(parkhaus.getEinnahmenAvg());
				System.out.println("avg = " + parkhaus.getEinnahmenAvg());
			} else if("cmd".equals(command) && "max".equals(param)) {
				response.setContentType("text/html");
				out.println(parkhaus.getMaxEinnahme());
				System.out.println("max = " + parkhaus.getMaxEinnahme());
			} else if ("cmd".equals(command) && "chart".equals(param)) {
				String parkdauer="";
				for(int j=1; j<5; j++) {
					parkdauer += " " + parkhaus.getAnzahlGeparkteMin(j * 5) + ",\n";
				}
				response.setContentType("text/plain");
				out.println("{\n" +
					 " \"data\": [\n" +
					 " 		{\n" +
					 " 			\"x\": [\n" +
					 " 				\"5_Min \",\n" +
					 " 				\"10_Min \",\n" +
					 " 				\"15_Min \",\n" +
					 " 				\"20_Min \",\n" +
					 " 				\"20+_Min \"\n" +
					 " 			],\n" +
					 " 			\"y\": [\n"
					 				+ parkdauer +
					 " 			" + parkhaus.getAnzahlGeparkteMin(25) +"\n"+
					 " 			],\n" +
					 " 			\"type\": \"bar\"\n" +
					 " 		}\n" +
					 " 	]\n" +
					 "}");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = getBody( request );
		System.out.println( body );
		String[] params = body.split(",");
		
		String event = params[0];
		System.out.println("Event=" + event);
		String ticket = params[5];
		String priceString = params[4];
		String dauer = params[3];
		Long geparktAm = Long.parseLong(params[2]);
		Integer nr = Integer.parseInt(params[1]);
		
		// Event = Enter OR Leave
		if(event.equals("enter")) {
			parkhaus.kundeHinzufuegen(new Kunde(nr, new Date(geparktAm * 1000), ticket));
		} else if(event.equals("leave")) {
			parkhaus.kundeVerlaesstParkhaus(parkhaus.kundenList.get(nr - 1), 
											new Date((geparktAm + Long.parseLong(dauer)) * 1000), 
											Long.parseLong(dauer), 
											Float.parseFloat(priceString) / 100); 		
		}
		
		if (!"_".equals(priceString)) {
			getApplication().setAttribute("nr", nr);
			getApplication().setAttribute("price", Float.parseFloat(priceString) / 100);
			getApplication().setAttribute("dauer", dauer );
			getApplication().setAttribute("sum", parkhaus.getEinnahmen());
			getApplication().setAttribute("avg", parkhaus.getEinnahmenAvg());
			getApplication().setAttribute("max", parkhaus.getMaxEinnahme());
			getApplication().setAttribute("time", parkhaus.getZeitSum());
			getApplication().setAttribute("cars", parkhaus.kundenList.size());		
			
			getApplication().setAttribute("p5", parkhaus.getAnzahlGeparkteMin(5));
			getApplication().setAttribute("p10", parkhaus.getAnzahlGeparkteMin(10));
			getApplication().setAttribute("p15", parkhaus.getAnzahlGeparkteMin(15));
			getApplication().setAttribute("p20", parkhaus.getAnzahlGeparkteMin(20));
			getApplication().setAttribute("p20p", parkhaus.getAnzahlGeparkteMin(25));
		}
		
		response.setContentType("text/html");
	}
	
	private ServletContext getApplication() {
		return getServletConfig().getServletContext();
	}

	private static String getBody(HttpServletRequest request) throws IOException {
		 
			
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		 
		 try {
			 InputStream inputStream = request.getInputStream();
			 if (inputStream != null) {
				 bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				 char[] charBuffer = new char[128];
				 int bytesRead = -1;
				 while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					 stringBuilder.append(charBuffer, 0, bytesRead);
				 }
			 } else {
				 stringBuilder.append("");
			 }
		 } finally {
			 if (bufferedReader != null) {
				 bufferedReader.close();
			 }
		 }
		return stringBuilder.toString();
	}
}
