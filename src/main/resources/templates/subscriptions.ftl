<#import "parts/common.ftl"as c>
<@c.page>
    <div class="w3-round w3-margin w3-light-grey w3-card-4">
        <div class="w3-container w3-light-blue w3-center w3-round">
            <h2>
                ${user.username}
            </h2>
        </div>
        <div class="w3-container w3-center w3-xlarge">
            <div>${type}</div>
            <ul class="w3-ul w3-text-light-blue">
                <#list users as user>
                    <li>
                        <a href="/user-messages/${user.id}">${user.username}</a>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
    <div class="w3-container w3-dark-grey w3-right-align w3-padding">
        <button class="w3-btn w3-round-large w3-hover-light-blue" onclick="location.href='/main'">Back to main</button>
    </div>
</@c.page>