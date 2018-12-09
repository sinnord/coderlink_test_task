package ru.sinnord.testtask.web;

import ru.sinnord.testtask.model.RowItem;
import ru.sinnord.testtask.repository.FileRepository;
import ru.sinnord.testtask.repository.InMemoryFileRepository;
import ru.sinnord.testtask.util.XmlUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Objects;

public class DownloadServlet extends HttpServlet {
    private FileRepository repository = InMemoryFileRepository.getInstance();


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //update values
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String element = parameterNames.nextElement();
            RowItem item = repository.getItem(Integer.parseInt(element));

            String newValue = request.getParameter(element);

            if (!Objects.equals(item.getValue(), newValue)) {
                item.setNewValue(newValue);
                repository.saveItem(item);
            }
        }

        String updatedContent = XmlUtil.updateContent(repository.getContent(), repository.getAllItems());

        response.setContentType("text/plain");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + repository.getFileName());

        OutputStream os = response.getOutputStream();
        os.write(updatedContent.getBytes());

        os.flush();
        os.close();
    }
}
