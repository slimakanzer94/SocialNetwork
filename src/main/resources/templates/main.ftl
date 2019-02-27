<#import "parts/common.ftl"as c>
<#import "parts/login.ftl"as f>
<@c.page>
<div class="w3-round w3-light-grey w3-margin w3-card-4">
    <div class="w3-container w3-light-blue  w3-center  w3-round">
    <h2>
        Hello ${user.getUsername()} !
    </h2>
    </div>
    <#include "parts/messageEdit.ftl">
        <div class="w3-container w3-light-grey">
            <h3>Messages</h3>
        </div>
        <div>
            <form action="/filter" method="post" class="w3-selection  w3-light-grey w3-padding">
                <div class="form-group">
                    <input type="text" name="filter" placeholder="Enter message" class="w3-input w3-border w3-round-large" style="width: 30%"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <button type="submit" class="w3-btn w3-light-blue w3-round-large w3-margin-bottom w3-margin-top">Find</button>
                </div>
            </form>
        </div>
    <#include "parts/messageList.ftl">
    <div class="w3-container w3-right-align">
        <button class="w3-btn w3-light-blue w3-round-large w3-margin-bottom" onclick="location.href='/users'">List of users</button>
    </div>
</div>

<div class="w3-container w3-dark-grey w3-right-align w3-padding">
    <button class="w3-btn w3-round-large w3-hover-light-blue" onclick="location.href='/login'">Back to login</button>
</div>
</@c.page>
