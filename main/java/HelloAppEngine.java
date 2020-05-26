import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@SuppressWarnings("serial")
public class HelloAppEngine extends HttpServlet {

	@Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
		try {
	  String course_name, course_id,faculty,description;
	  course_name = request.getParameter("course_name");
	  course_id = request.getParameter("course_id");
	  faculty = request.getParameter("faculty");
	  description = request.getParameter("description");
	response.setContentType("text/html; charset=utf-8");
    PrintWriter out = response.getWriter();
    out.println("<form>");
    out.println("<div>Course Name: <input type='text' name='course_name' required value="+course_name+"></div><br/>");
    out.println("<div>Course ID: <input type='text' name='course_id' value="+course_id+"><div><br/>");
    out.println("<div>Faculty:<input type='text'name ='faculty' value="+faculty+"></div></br>"); 
    out.println("<div>Academic Year: <select name='academic_year'>"
    		+ "<option value='2020'>2020</option>" + 
    		"<option value='2019'>2019</option>" + 
    		"<option value='2018'>2018</option>" + 
    		"<option value='2017'>2017</option>" + 
    		"<option value='2016'>2016</option>" + 
    		"<option value='2015'>2015</option>" + 
    		"<option value='2014'>2014</option>" + 
    		"</select>" + 
    		"</div><br/>");
       out.println("<div>Language: <select name='language'>"
    		+ "<option value='english'>English</option>" + 
    		" <option value='dutch'>Dutch</option>" + 
    		"</select></div><br/>");
       out.println("<div><input type = submit value = 'Submit'></div>");
       Map<String,String> data = new HashMap<String,String>();
       data.put("math", "201500553,Math,2019,5ETC,ITC");
       data.put("science", "201900005,Science,2020,37.2EC,BMC");
       data.put("communication","201901105,communication,2019,5ETC,MSC");
       data.put("ITC", "201500553,Math,2019,5ETC,ITC");
       data.put("BMC", "201900005,Science,2020,37.2EC,BMC");
       data.put("MSC","201901105,communication,2019,5ETC,MSC");
       data.put("201500553", "201500553,Math,2019,5ETC,ITC");
       data.put("201900005", "201900005,Science,2020,37.2EC,BMC");
       data.put("201901105","201901105,communication,2021,5ETC,MSC");
       String keyValue;
       boolean validatedData = dataValidation(course_name,course_id,faculty,data);
       if (validatedData)
    	   keyValue = "true";
       else
       		throw new NullPointerException();
       keyValue = data.get(course_name);
       String[] list =keyValue.split(",");
		 out.println("<table>"); 
		 out.println("<tr><th>Course ID</th><th>Course Name</th><th>Year</th><th>Credits</th><th>Faculty</th></tr>"); 
		 out.println("<tr><td>"+list[0]+"</td><td>"+list[1]+"</td>" +
		 "<td>"+list[2]+"</td><td>"+list[3]+"</td><td>"+list[4]+"</tr>");
		 out.println("</table>");
	   out.println("</form>");
       out.close();
		}
		catch(NullPointerException e) {
		    PrintWriter out = response.getWriter();
		    out.println("<div>no result found</div>");
		    out.close();
		}
	}
	public static boolean dataValidation(String name,String id, String faculty,Map<String,String> data) {
		if(!(name.isEmpty()) && !(id.isEmpty()) && !(faculty.isEmpty()))
			return (data.get(name).equals(data.get(id)) && data.get(id).equals(data.get(faculty)) && data.get(faculty).equals(data.get(name)));
			else if(!(name.isEmpty()) && !(id.isEmpty()))
			return(data.get(name).equals(data.get(id)));
			else if(!(name.isEmpty()) && !(faculty.isEmpty()))
			return (data.get(name).equals(data.get(faculty)));
			else if(!(name.isEmpty()) && id.isEmpty() && faculty.isEmpty())
			return true;
			else
			return false;
	}
}