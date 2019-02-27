<#macro login path isRegisterForm>
    <form action="${path}" method="post" class="w3-selection w3-light-grey w3-padding">
        <div class="form-group">
            <label>Name</label>
            <input type="text" name="username" value="<#if user??>${user.username}</#if>" placeholder="User name" class="w3-input w3-border w3-round-large" style="width: 30%"/>
        </div>
        <#if usernameError??>
            <div class="w3-text-red">
                ${usernameError}
            </div>
        </#if>
        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" placeholder="Password" class="w3-input w3-border w3-round-large" style="width: 30%"/>
        </div>
        <#if passwordError??>
            <div class="w3-text-red">
                ${passwordError}
            </div>
        </#if>
        <#if isRegisterForm>
        <div class="form-group">
            <label>Confirm password</label>
            <input type="password" name="password2" placeholder="Confirm password" class="w3-input w3-border w3-round-large" style="width: 30%"/>
        </div>
            <#if password2Error??>
                <div class="w3-text-red">
                    ${password2Error}
                </div>
            </#if>
        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" placeholder="some@.some" value="<#if user??>${user.email}</#if>" class="w3-input w3-border w3-round-large" style="width: 30%"/>
        </div>
            <#if emailError??>
                <div class="w3-text-red">
                    ${emailError}
                </div>
            </#if>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom w3-margin-top">Sign in</button>
    </form>
</#macro>

<#macro logout >
    <form action="/logout" method="post">
        <input type="submit" value="Sign Out" class="w3-btn w3-round-large w3-hover-red w3-margin-bottom"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</#macro>