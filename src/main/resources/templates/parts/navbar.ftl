<#import "login.ftl"as f>
<#include "security.ftl" >
<div class="w3-bar w3-dark-grey">
    <div class="w3-bar-item w3-large w3-text-hover-light-blue">Super app !</div>
    <a href="/" class="w3-bar-item w3-hover-light-blue w3-button w3-large">Home</a>
    <#if user??>
        <a href="/main" class="w3-bar-item w3-button w3-large w3-hover-light-blue">Messages</a>
        <a href="/user-messages/${currentUserId}" class="w3-bar-item w3-button w3-large w3-hover-light-blue">My messages</a>
    </#if>
    <#if isAdmin>
        <a href="/users" class="w3-bar-item w3-button w3-large w3-hover-light-blue">User list</a>
    </#if>
    <#if user??>
        <form action="/logout" method="post">
            <input type="submit" value="Sign Out" class="w3-bar-item w3-button w3-right w3-hover-red w3-large"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
        <#else>
            <button class="w3-bar-item w3-button w3-right w3-hover-green w3-large" onclick="location.href='/main'">Sign in</button>
    </#if>
    <div class="w3-bar-item w3-large w3-right w3-text-light-blue">${name}</div>
    <#if user??>
        <a href="/users/profile" class="w3-bar-item w3-button w3-right w3-large w3-hover-light-blue">Profile</a>
    </#if>
</div>