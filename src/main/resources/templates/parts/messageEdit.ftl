<div>
    <div class="w3-container w3-light-grey">
        <h3>Send message</h3>
    </div>
    <form enctype="multipart/form-data" method="post" class="w3-selection w3-light-grey w3-padding">
        <div class="w3-margin">
            <input type="text" name="text"  value="<#if message??>${message.text}</#if>" placeholder="Enter message" class="w3-input w3-border w3-round-large" style="width: 30%"/>
            <#if textError??>
            <div class="w3-text-red">
                ${textError}
            </div>
        </#if>
        </div>
        <div class="w3-margin">
        <input type="text" name="tag"  value="<#if message??>${message.tag}</#if>" placeholder="Tag" class="w3-input w3-border w3-round-large" style="width: 30%"/>
        <#if tagError??>
        <div class="w3-text-red">
            ${tagError}
        </div>
    </#if>
        </div>
        <div class="w3-margin">
            <input type="file" name="file" style="width: 30%">
        </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>"/>
    <button type="submit" class="w3-btn w3-light-blue w3-round-large w3-margin-bottom w3-margin-top">Send</button>
</form>
</div>