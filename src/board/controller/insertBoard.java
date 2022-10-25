package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.BoardVO;

/**
 * Servlet implementation class insertBoard
 */
@WebServlet("/insertBoard.do")
public class insertBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		BoardVO bVO = new BoardVO();
		bVO.setTitle(request.getParameter("title"));
		bVO.setContent(request.getParameter("content"));
			
//		System.out.println(bVO);
//		System.out.println("컨트롤러 : " + bVO.toString());
		
//		BoardService service = new BoardService();
//		int result = service.insertBoard(bVO);	
		
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int  maxSize = 1024*1024*10; // 10Mbyte로 전송파일 용량 제한
			
			String root = request.getSession().getServletContext().getRealPath("/");
			String savePath = root + "resources/img/contentImg";
			
			File f = new File(savePath);
			
			if(!f.exists()) {
				f.mkdirs();
			}
			
			MultipartRequest multi = new MultipartRequest(request, savePath, maxSize,"euc-kr",new DefaultFileRenamePolicy());
			
		} 
		
		request.getRequestDispatcher("WEB-INF/views/boardList.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
