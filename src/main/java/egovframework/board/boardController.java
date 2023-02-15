package egovframework.board;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.example.sample.service.ckSerivce;
import egovframework.vo.ckVO;

@Controller
public class boardController {
	
	@Resource(name="ckService")
	private ckSerivce ckservice;
	
	@RequestMapping(value="write.do")
	public String main() {
		return "write";
	}
	
	@RequestMapping(value="fileupload.do")
	public void fileupload(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiFile
			,@RequestParam MultipartFile upload) throws Exception {
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		try {
			String fileName = upload.getOriginalFilename();
			System.out.println(":::::::::::"+fileName+"::::::::::::::::::::");
			byte[] bytes = upload.getBytes();
			
			String path = "C:\\cR\\eGovFrameDev-3.8.0-64bit\\workspace\\ckeditor\\src\\main\\webapp\\resources\\ckimage\\";
			String ckUploadPath = path+fileName;
			File foler = new File(path); //생성
			System.out.println("path :::::::::::::::::: " + path);
			
			if(!foler.exists()) {
				try {
					foler.mkdir();
				}catch(Exception e) {
					e.getStackTrace();
				}
			}
			
			out = new FileOutputStream(new File(ckUploadPath));
			out.write(bytes);
			out.flush();
			
			String callback = request.getParameter("CKEditorFuncNum");
			printWriter = response.getWriter();
	    	String fileUrl = "/ckImgSubmit.do?fileName=" + fileName; // 작성화면
	    	
	    	// 업로드시 메시지 출력
	    	printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
	    	printWriter.flush();
	    	
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
    		try {
    		if(out != null) { out.close(); }
    		if(printWriter != null) { printWriter.close(); }
    	} catch(Exception e) { e.printStackTrace(); }
    	}
    	return;
		
	}
	@RequestMapping(value="/ckImgSubmit.do")
    public void ckSubmit( @RequestParam(value="fileName") String fileName
    		, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    	
    	//서버에 저장된 이미지 경로
    	String path = "C:\\cR\\eGovFrameDev-3.8.0-64bit\\workspace\\ckeditor\\src\\main\\webapp\\resources\\ckimage\\";	// 저장된 이미지 경로
    	System.out.println("path::::::::::::::::"+path);
    	String sDirPath = path + fileName;
    	
    	File imgFile = new File(sDirPath);
    	
    	//사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
    	if(imgFile.isFile()){
    		byte[] buf = new byte[1024];
    		int readByte = 0;
    		int length = 0;
    		byte[] imgBuf = null;
    		
    		FileInputStream fileInputStream = null;
    		ByteArrayOutputStream outputStream = null;
    		ServletOutputStream out = null;
    		
    		try{
    			fileInputStream = new FileInputStream(imgFile);
    			outputStream = new ByteArrayOutputStream();
    			out = response.getOutputStream();
    			
    			while((readByte = fileInputStream.read(buf)) != -1){
    				outputStream.write(buf, 0, readByte); 
    			}
    			
    			imgBuf = outputStream.toByteArray();
    			length = imgBuf.length;
    			out.write(imgBuf, 0, length);
    			out.flush();
    			System.out.println(fileName);
    			
    		}catch(IOException e){
    			e.printStackTrace();
    		}finally {
    			outputStream.close();
    			fileInputStream.close();
    			out.close();
    			}
    		}
    }
	
	
	@RequestMapping(value="save.do")
	public String save(ckVO vo) {
		String con = vo.getContent();
		System.out.println("===================================="+con);
		con = con.replace("&lt;", "<");
		con = con.replace("&gt", ">");
		con = con.replace("&amp;lt", "<");
		con = con.replace("&amp;gt;", ">");
		con = con.replace("&amp;nbsp;", " ");
		con = con.replace("&amp;amp;", "&");
		con = con.replace("&quot;&quot;", "\"\"");
		con = con.replace("&quot;", "\"");
		con = con.replace(";","\n");
		vo.setContent(con);
		System.out.println("===================================="+con);
		ckservice.insert(vo);
		
		
		
		
		
		
		return "redirect:/list.do";
	}
	
	@RequestMapping(value="list.do")
	public String list(ckVO vo,Model model) {
		List<?> list1 = ckservice.getList(vo);
		model.addAttribute("list",list1);
		return "list";
	}
	
	@RequestMapping(value="content.do")
	public String content(ckVO vo, Model model) {
		ckVO content = ckservice.getContent(vo);
		String title = content.getTitle();
		int no = content.getNo();
		String con = content.getContent();
		System.out.println(title);
		System.out.println(con);
		System.out.println(no);
		model.addAttribute("list", content);
		return "content";
	}
	
	@RequestMapping(value="edit.do")
	public String edid(ckVO vo, Model model) {
		ckVO content = ckservice.getContent(vo);
		model.addAttribute("list", content);
		return "edit";
	}
	
	@RequestMapping(value="editSave.do")
	public String editSave(ckVO vo, Model model) {
		String con = vo.getContent();
		con = con.replace("&lt;", "<");
		con = con.replace("&gt", ">");
		con = con.replace("&amp;lt", "<");
		con = con.replace("&amp;gt;", ">");
		con = con.replace("&amp;nbsp;", " ");
		con = con.replace("&amp;amp;", "&");
		con = con.replace("&quot;&quot;", "\"\"");
		con = con.replace("&quot;", "\"");
		con = con.replace(";","\n");
		vo.setContent(con);
		ckservice.editCon(vo);
		int no = vo.getNo();
		return "redirect:/content.do?no="+no;
	}

}






























