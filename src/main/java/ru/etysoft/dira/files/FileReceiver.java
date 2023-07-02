package ru.etysoft.dira.files;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class FileReceiver {

   public static void init() throws IOException {
       ServerSocket serverSocket = null;
       System.out.println("puk yes1");
       try {
           serverSocket = new ServerSocket(4444);
           System.out.println("puk yes");
       } catch (IOException ex) {
           System.out.println("Can't setup server on this port number. ");
       }

       Socket socket = null;
       InputStream in = null;
       OutputStream out = null;

       try {
           socket = serverSocket.accept();
       } catch (IOException ex) {
           System.out.println("Can't accept client connection. ");
       }

       try {
           in = socket.getInputStream();
       } catch (IOException ex) {
           System.out.println("Can't get socket input stream. ");
       }

       try {
          File output = new File("filesBuffer/file.png");
           out = new FileOutputStream(output);
       } catch (FileNotFoundException ex) {
           System.out.println("File not found. ");
       }

       byte[] bytes = new byte[16*1024];

       int count;
       while ((count = in.read(bytes)) > 0) {
           out.write(bytes, 0, count);
       }

       out.close();
       in.close();
       socket.close();
       serverSocket.close();
   }

}