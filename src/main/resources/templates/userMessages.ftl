<#import "parts/common.ftl"as c>
<@c.page>
    <div class="w3-round w3-margin w3-light-grey w3-card-4">
        <div class="w3-container w3-light-blue w3-center w3-round">
            <h2>
                ${user.username}
            </h2>
        </div>
        <div class="w3-container w3-center w3-margin">
            <#if !isCurrentUser>
                <#if isSubscriber>
                    <button class="w3-btn w3-round-large w3-green w3-margin-bottom"
                            onclick="location.href='/users/unsubscribe/${user.id}'">Unsubscribe
                    </button>
                <#else>
                    <button class="w3-btn w3-round-large w3-green w3-margin-bottom"
                            onclick="location.href='/users/subscribe/${user.id}'">Subscribe
                    </button>
                </#if>
            </#if>
            <table>
                <tr>
                    <td class="w3-card-4 w3-margin w3-round-large w3-white ">
                        <div class="w3-container w3-padding w3-large">Subscriptions</div>
                        <div class="w3-container w3-padding w3-xlarge">
                            <a class="w3-text-blue"
                               href="/users/subscriptions/${user.id}/list">${subscriptionsCount}</a>
                        </div>
                    </td>
                    <td class="w3-card-4 w3-margin w3-round-large w3-white">
                        <div class="w3-container w3-padding w3-large">Subscribers</div>
                        <div class="w3-container w3-padding w3-xlarge">
                            <a class="w3-text-blue" href="/users/subscribers/${user.id}/list">${subscribersCount}</a>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <#include "parts/messageEdit.ftl">
        <#include "parts/messageList.ftl">
    </div>
    <div class="w3-container w3-dark-grey w3-right-align w3-padding">
        <button class="w3-btn w3-round-large w3-hover-light-blue" onclick="location.href='/main'">Back to main</button>
    </div>
</@c.page>
