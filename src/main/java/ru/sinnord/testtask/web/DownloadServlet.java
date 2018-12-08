package ru.sinnord.testtask.web;

import ru.sinnord.testtask.repository.FileRepository;
import ru.sinnord.testtask.repository.InMemoryFileRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {
    private FileRepository repository = InMemoryFileRepository.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
