package com.company;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URI;


import java.awt.*;
import java.sql.*;
import java.util.Arrays;

public class SongServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try(Connection conn = DriverManager.getConnection("jdbc:h2:~/project_db;AUTO_SERVER=TRUE;INIT=runscript from '~/create.sql'")){

            StringBuilder htmlBuilder = new StringBuilder();

            Statement selectSongTable = conn.createStatement();

            ResultSet results = selectSongTable.executeQuery("Select * from SONGS");

            while(results.next()) {

                htmlBuilder.append("<tr>")
                        .append("<td>").append(results.getString("ARTIST")).append("</td>")
                        .append("<td>").append(results.getString("SONG_YEAR")).append("</td>")
                        .append("<td>").append(results.getString("ALBUM")).append("</td>")
                        .append("<td>").append(results.getString("TITLE")).append("</td>")
                        .append("</tr>");

            }

            String htmlData = "<html> <head></head> <body>" +
                    "<h1>Your songs</h1>" +
                    "<table cellspacing=\"50\">" +
                    "<tr>" +
                    "<th>Artist</th>" +
                    "<th>Year</th>" +
                    "<th>Album</th>" +
                    "<th>Title</th>" +
                    "</tr>" +
                    htmlBuilder.toString()
                    + "</table></body> </html>";

            resp.getWriter().write(htmlData);

        }catch (SQLException e){
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }


    static void startServer() {

        try {
            Server server = new Server(8080);

            ServletContextHandler context = new ServletContextHandler(
                    ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            context.setResourceBase(System.getProperty("java.io.tmpdir"));
            server.setHandler(context);

            context.addServlet(SongServlet.class, "/songs");
            server.start();

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:8080/songs"));
            }
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }


}

