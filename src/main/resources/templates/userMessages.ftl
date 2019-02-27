<#import "parts/common.ftl"as c>
<@c.page>
    <div class="w3-round w3-margin w3-light-grey w3-card-4">
        <div class="w3-container w3-light-blue w3-center w3-round">
            <h2>
                My messages
            </h2>
        </div>
        <#include "parts/messageEdit.ftl">
        <#include "parts/messageList.ftl">
    </div>
    <div class="w3-container w3-dark-grey w3-right-align w3-padding">
        <button class="w3-btn w3-round-large w3-hover-light-blue" onclick="location.href='/main'">Back to main</button>
    </div>
</@c.page>
