package com.hackthon;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hackthon.filemonitor.LogMonitor;
import com.hackthon.interfaces.FileChangeListener;

/**
 * Servlet implementation class ActionServlet
 */
@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet implements FileChangeListener{
	private static final long serialVersionUID = 1L;
    public static LogMonitor obj; 
  
    PrintWriter out;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionServlet() {
        super();
       
        // TODO Auto-generated constructor stub
    }
     /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello servlet");
		obj =  new LogMonitor(ActionServlet.this);
		try {
			obj.monitorLogs();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 out = response.getWriter();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	@Override
	public void onFileContentChange(File file) throws IOException {
		if(fileSizeMap.containsKey(file.getName()))
		{	
			long length = file.length();
			long initial = fileSizeMap.get(file.getName());
			RandomAccessFile obj = new RandomAccessFile("C:/logs/log2.txt", "r");
			obj.seek(initial);
			String strText = "";
			System.out.println("Before entering to while");
			while((strText = obj.readLine())!=null)
			{
				System.out.println("In while");
				if(strText.contains("abc"))
				{
					System.out.println("true : " + strText);
					break;
				}
				else
				{
					System.out.println("fa+lse : " + strText);
				}
			}
			fileSizeMap.put(file.getName(), length);		
			obj.close();
		}
		else
		{
			fileSizeMap.put(file.getName(), file.length());
			
			RandomAccessFile obj = new RandomAccessFile("C:/logs/log2.txt", "r");
			String strText = "";
			while((strText = obj.readLine())!=null)
			{
				System.out.println("In while");
				if(strText.contains("abc"))
				{
					System.out.println("true : " + strText);
					break;
				}
				else
				{
					System.out.println("false : " + strText);
				}
			}
		}
	}

}
