package ru.sinnord.testtask.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sinnord.testtask.model.RowItem;
import ru.sinnord.testtask.repository.FileRepository;
import ru.sinnord.testtask.repository.InMemoryFileRepository;
import ru.sinnord.testtask.util.XmlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,    // 10 MB
        maxFileSize = 1024 * 1024 * 50,        // 50 MB
        maxRequestSize = 1024 * 1024 * 100)    // 100 MB
public class EditFileServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(EditFileServlet.class);

    private static final String UPLOAD_DIR = "uploads";

    private FileRepository repository = InMemoryFileRepository.getInstance();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        String fileName = null;
        for (Part part : request.getParts()) {
            fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
        }

        log.info("File uploaded: {}", fileName);

        String content = new String(Files.readAllBytes(Paths.get(uploadFilePath + File.separator + fileName)));
        repository.saveFile(fileName, content);
        response.sendRedirect("edit");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("get all items");

        List<RowItem> items = XmlUtil.parseXml(repository.getContent());
        req.setAttribute("items", items);
        req.getRequestDispatcher("/editForm.jsp").forward(req, resp);
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}
