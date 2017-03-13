<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%@ page language="java" import="java.util.*,java.io.* " pageEncoding="UTF-8"%>
        <%
            request.setCharacterEncoding("utf-8");
            String fileName = request.getParameter("fileName");
            String path = request.getParameter("path");

            fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
            System.out.println(fileName);
            OutputStream o=response.getOutputStream();
            byte b[]=new byte[500];
            String   serverPath=request.getRealPath("\\")+path;
            System.out.println(serverPath);
            File fileLoad=new File(serverPath+fileName);
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition","attachment; filename="+fileName);
            long fileLength=fileLoad.length();
            String length1=String.valueOf(fileLength);
            response.setHeader("Content_Length",length1);
            FileInputStream in=new FileInputStream(fileLoad);
            int n;
            while((n=in.read(b))!=-1){
                o.write(b,0,n);
            }
            in.close();
            out.clear();
            out = pageContext.pushBody();
        %></title>
</head>
<body>

</body>
</html>
