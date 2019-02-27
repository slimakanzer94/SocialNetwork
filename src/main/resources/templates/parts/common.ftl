<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Hellow world!</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body class="w3-white">
    <div>
        <#include "navbar.ftl">
    </div>
    <#nested>
    </body>
</html>
</#macro>