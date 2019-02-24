<#import "parts/common.ftl"as c>
<#import "parts/login.ftl"as f>
<@c.page>
<div class="w3-light-grey w3-round w3-margin w3-card-4">
    <div class="w3-container w3-light-blue w3-center  w3-round">
        <h2>Welcome to my super app !</h2>
    </div>
    <div>
        <@f.login "/login" />
    </div>
    <div class="w3-container">
        <button class="w3-btn w3-round-large w3-green w3-margin-bottom" onclick="location.href='/registration'">Registration</button>
    </div>
</div>

<div class="w3-container w3-dark-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large w3-hover-light-grey" onclick="location.href='/home'">Back to main</button>
</@c.page>