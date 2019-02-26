<#import "parts/common.ftl"as c>
<@c.page>
    <#if msg??>
        <div class="w3-panel w3-display-container w3-round w3-margin w3-light-green w3-card-4">
            <span class="w3-button w3-display-topright w3-hover-light-green" onclick="this.parentElement.style.display='none'">X</span>
            <h3>${msg}</h3>
        </div>
    </#if>
    <#if dlt??>
        <div class="w3-panel w3-display-container w3-round w3-margin w3-red w3-card-4">
            <span class="w3-button w3-display-topright w3-hover-red" onclick="this.parentElement.style.display='none'">X</span>
            <h3>${dlt}</h3>
        </div>
    </#if>
<div class="w3-light-grey w3-round w3-margin w3-card-4">
    <div class="w3-container w3-light-blue w3-center  w3-round">
        <h2>List of users</h2>
    </div>
    <div class="w3-container w3-center w3-margin-left w3-margin-right">
        <div>
            <table class="w3-table w3-center">
                <thread>
                    <tr>
                        <th class="w3-col m2 w3-center w3-large">Name</th>
                        <th class="w3-col m3 w3-center w3-large">Role</th>
                        <th class="w3-col m3 w3-center w3-large">Commands</th>
                    </tr>
                </thread>
                <#list users as user>
                <tr>
                    <td class="w3-col m2 w3-center">${user.username}</td>
                    <td class="w3-col m3 w3-center"><#list user.roles as role>${role}<#sep>, </#list></td>
                    <td class="w3-col m3 w3-center">
                        <table class="w3-table">
                            <tr>
                                <td class="w3-center w3-col m3">
                        <button class="w3-btn w3-round-large w3-green" onclick="location.href='/users/${user.id}'">Edit</button>
                                </td>
                                <td class="w3-align-left w3-col m3">
                                    <form method="post" action="/users/${user.id}/delete">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                        <button type="submit" class="w3-btn w3-round-large w3-red">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                </#list>
            </table>
        </div>
    </div>
</div>

<div class="w3-container w3-dark-grey w3-right-align w3-padding">
    <button class="w3-btn w3-round-large w3-hover-light-blue" onclick="location.href='/main'">Back to main</button>
</div>
</@c.page>