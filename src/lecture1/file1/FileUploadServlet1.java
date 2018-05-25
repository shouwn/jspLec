package lecture1.file1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/file1/FileUploadServlet1")
public class FileUploadServlet1 extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String 업로드_폴더 = "/upload"; // 업로드된 파일을 저장할 폴더

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8"); // request parameter에 한글이 포함된 경우에 필수
        String 제목 = request.getParameter("title");

        Part filePart = request.getPart("upload1"); // 업로드된 파일 데이터
        String 파일명 = getFileName(filePart)  ;    // 파일명 구하기
        long 파일크기 = filePart.getSize();         // 파일 크기 구하기

        String 폴더경로 = request.getServletContext().getRealPath(업로드_폴더); // 폴더 경로 계산
        Path path = Paths.get(폴더경로, 파일명);                       // 저장할 파일의 경로 계산
        if (파일크기 > 0) saveFile(filePart, path);                    // 파일 저장하기
        String 다운로드URL = request.getContextPath() + 업로드_폴더 + "/" + 파일명; // 파일 다운로드 URL 계산

        response.setContentType("text/html; utf-8"); // 웹브라우저에 HTML 태그 출력 시작
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        writer.write("<title>Servlet 3.0 File Upload Example</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<h3>파일업로드 결과</h3>");
        writer.write("<div>제목: " + 제목 + "</div>");
        writer.write("<div>파일이름: " + 파일명 + "</div>");
        writer.write("<div>파일크기: " + 파일크기 + "</div>");
        if (파일크기 > 0) {
            writer.write("<div>저장된파일: " + path.toString() + "</div>");
            writer.write("<div>다운로드 링크: <a download href='" + 다운로드URL + "'>다운로드</a></div>");
        }
        writer.write("</body>");
        writer.write("</html>");
        writer.close(); // HTML 태그 출력 끝
    }

    private String getFileName(Part filePart) {
        for (String content : filePart.getHeader("content-disposition").split(";"))
            if (content.trim().startsWith("filename")) {
                String path = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                return new File(path).getName();
            }
        return null;
    }

    private void saveFile(Part filePart, Path path) throws IOException {
        InputStream inputStream = filePart.getInputStream();
        OutputStream outputStream = new FileOutputStream(path.toFile());
        try {
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1)
                outputStream.write(bytes, 0, read);
        } finally {
            if (outputStream != null) outputStream.close();
            if (inputStream != null) inputStream.close();
        }
    }
}

