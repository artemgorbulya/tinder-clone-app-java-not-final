<#import "common.ftl" as c>
<@c.page>
<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody id="likedProfiles">
                            <#list profiles as p>
                            <tr data-id="${p.getId()}">
                                <td data-id="${p.getId()}" width="10">
                                    <div data-id="${p.getId()}" class="avatar-img">
                                        <img data-id="${p.getId()}" class="img-circle" src=${p.getAvatar()} />
                                    </div>
                                </td>
                                <td data-id="${p.getId()}" class="align-middle">
                                    ${p.getFullname()}
                                </td>
                                <td data-id="${p.getId()}" class="align-middle">
                                    Last Login:  ${p.getLastLogin()} <br>
<#--                                    <#if p.getDaysFromLastVisit() != 0 >-->
<#--                                        <small class="text-muted">${p.getDaysFromLastVisit()} days ago</small>-->
<#--                                    </#if>-->
                                </td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <script>
            listen();
            function listen() {
            const table = document.querySelector("#likedProfiles");
            table.addEventListener('click', evt => {
            const profileID = evt.target.getAttribute("data-id");
            location.replace("/messages/" + profileID);
        });
        }
    </script>
</@c.page>