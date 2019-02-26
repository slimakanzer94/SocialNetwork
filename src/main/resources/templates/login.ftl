<#import "parts/common.ftl"as c>
<#import "parts/login.ftl"as f>
<@c.page>
    <#if msg??>
        <div class="w3-panel w3-display-container w3-round w3-margin w3-light-green w3-card-4">
            <span class="w3-button w3-display-topright w3-hover-light-green" onclick="this.parentElement.style.display='none'">X</span>
            <h3>${msg}</h3>
        </div>
    </#if>
    <#if failed??>
        <div class="w3-panel w3-display-container w3-round w3-margin w3-red w3-card-4">
            <span class="w3-button w3-display-topright w3-hover-red" onclick="this.parentElement.style.display='none'">X</span>
            <h3>${failed}</h3>
        </div>
    </#if>
<div class="w3-light-grey w3-round w3-margin w3-card-4">
    <div class="w3-container w3-light-blue w3-center  w3-round">
        <h2>Welcome to my super app !</h2>
    </div>
    <div>
        <@f.login "/login" false/>
    </div>
    <div class="w3-container">
        <button class="w3-btn w3-round-large w3-green w3-margin-bottom" onclick="location.href='/registration'">Registration</button>
    </div>
</div>

<div class="w3-container w3-dark-grey w3-right-align w3-padding">
    <button class="w3-btn w3-round-large w3-hover-light-blue" onclick="location.href='/home'">Back to main</button>
</@c.page>