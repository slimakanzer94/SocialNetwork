<#import "parts/common.ftl"as c>
<@c.page>
<div class="w3-light-grey w3-round w3-margin w3-card-4">
    <div class="w3-container w3-light-blue w3-center  w3-round">
        <h2>Edit user</h2>
    </div>
    <div>
        <form action="/users" method="post" class="w3-selection w3-light-grey w3-padding">
            <input type="hidden" value="${user.id}" name="userId" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <label>Name</label>
                <input type="text" value="${user.username}" name="username" placeholder="User name" class="w3-input w3-border w3-round-large" style="width: 30%"/>
            </div>
            <#list roles as role>
            <div class="form-group">
                <label>
                    <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")} class="w3-input w3-border w3-round-large" style="width: 30%" />${role}
                </label>
                </div>
            </#list>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom w3-margin-top">Save</button>
        </form>
    </div>
</div>

<div class="w3-container w3-dark-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large w3-hover-light-grey" onclick="location.href='/users'">Back to list of users</button>
</div>
</@c.page>