<%@tag description="Page Layout" pageEncoding="UTF-8"%>
<%@attribute name="title"%>
<%@attribute name="custom_css" fragment="true"%>
<%@attribute name="page_content" fragment="true"%>
<%@attribute name="custom_script" fragment="true"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <jsp:include page="/WEB-INF/views/header.html" />
        <jsp:invoke fragment="custom_css" />
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/topbar.html" />
        <div class="container-fluid">
            <jsp:invoke fragment="page_content" />
        </div>
        <jsp:include page="/WEB-INF/views/footer.html" />
        <jsp:invoke fragment="custom_script" />
    </body>
</html>