import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import org.json.simple.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/OpenAPI8.do")
public class C02Test3b extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//한글깨짐 방지
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		//파라미터 받기
		String searchterm = req.getParameter("searchname");
		
		//URL 받기
		String API_KEY = "api_key=6d9a1c805402044b6538f08dc1dae9fa";
		String BASE_URL = "https://api.themoviedb.org/3";
		String API_URL = BASE_URL + "/discover/movie?sort_boy=popularity.desc&"+API_KEY;
		String IMG_URL = "https://image.tmdb.org/t/p/w500";
		String searchURL = BASE_URL + "/search/movie?" + API_KEY;
		
		if(searchterm!=null) {
			API_URL = searchURL + "&query="+ searchterm;
			
		} else 
			API_URL = BASE_URL + "/discover/movie?sort_boy=popularity.desc&"+API_KEY;
		//URL 객체 생성
		URL url = new URL(API_URL);
		
		//IO 스트림 생성
		InputStream in = url.openStream();
		OutputStream bout = new FileOutputStream("c://UPLOAD/test666.db");
		int data = 0;
		while(true) {
			data = in.read();
			if(data==-1)
				break;
			bout.write(data);
		}
		in.close();
		bout.close();
		
		//-----------------------------------------------
		
		
		InputStream newin = new FileInputStream("c:/UPLOAD/test666.db");
		BufferedReader tin = new BufferedReader(new InputStreamReader(newin));
		String tmp; //문자열 임시받기
		StringBuffer xmldata = new StringBuffer(); //API내용 저장용도
		
		while(true) {
			tmp=tin.readLine(); //한줄을 읽어 tmp에 넣는다
			if(tmp==null) //더이상 읽을 내용이 없다
				break;
			xmldata.append(tmp);
		}
		tin.close();
		//System.out.println("XML Data \n" + xmldata);
		
		JSONObject json = new JSONObject();
		json.put("data", xmldata);
		
		
		//클라이언트 브라우저에 전달
		resp.getWriter().print(json);
		
		//actor.jsp로 이동
		
		
		
	}
}
