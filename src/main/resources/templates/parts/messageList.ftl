<#include "security.ftl" >
<div class="w3-light-grey">
    <#list messages as message>
        <div class="w3-card-4 w3-round-large w3-margin" style="width:20%">
            <div class="w3-container">
                <#if message.filename??>
                    <img src="/img/${message.filename}">
                </#if>
            </div>
            <div class="w3-container w3-round-large w3-white w3-padding">
                <span> ${message.text}</span><br/>
                <i>#${message.tag}</i>
            </div>
            <div class="w3-container w3-round-large w3-light-grey w3-padding">
                <a class="w3-button w3-hover-light-blue w3-round-large" href="/user-messages/${message.autor.id}">${message.autor.username}</a>
                <#if message.autor.id == currentUserId>
                    <a class="w3-button w3-hover-light-blue w3-round-large"
                       href="/user-messages/${message.autor.id}?message=${message.id}">Edit</a>
                </#if>
            </div>
        </div>
    </#list>
</div>