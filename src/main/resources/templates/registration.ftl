<#import "parts/common.ftl"as c>
<#import "parts/login.ftl"as f>
<@c.page>
    <#if msg??>
<div class="w3-panel w3-display-container w3-round w3-margin w3-red w3-card-4">
    <span class="w3-button w3-display-topright w3-hover-red" onclick="this.parentElement.style.display='none'">X</span>
    <h3>${msg}</h3>
</div>
    </#if>
<div class="w3-light-grey w3-round w3-margin w3-card-4">
    <div class="w3-container w3-light-blue w3-round">
        <h3>Add new user</h3>
    </div>
    <div>
        <@f.login path="/registration" isRegisterForm=true/>
    </div>
</div>
<div class="w3-container w3-dark-grey w3-right-align w3-padding">
    <button class="w3-btn w3-round-large w3-hover-light-blue" onclick="location.href='/login'">Back to login</button>
</div>
</@c.page>