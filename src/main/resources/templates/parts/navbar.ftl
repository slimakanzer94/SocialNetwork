<#include "security.ftl" >
<div class="w3-bar w3-dark-grey">
            <div class="w3-bar-item w3-large w3-text-hover-light-blue">Super app !</div>
            <a href="/" class="w3-bar-item w3-hover-light-blue w3-button w3-large">Home</a>
            <a href="/main" class="w3-bar-item w3-button w3-large w3-hover-light-blue">Messages</a>
    <#if isAdmin>
            <a href="/users" class="w3-bar-item w3-button w3-large w3-hover-light-blue">User list</a>
    </#if>
    <div class="w3-bar-item w3-large w3-right w3-text-light-blue" >${name}</div>
</div>