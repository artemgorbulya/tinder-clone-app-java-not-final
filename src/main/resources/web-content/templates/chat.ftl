<#import "common.ftl" as c>
<@c.page>
    <div class="container h-100">
        <div class="row">
            <div class="chat-main col-6 offset-3">
                <div class="col-md-12 chat-header">
                    <div class="row header-one text-white p-1">
                        <div class="col-md-6 name pl-2">
                            <i class="fa fa-comment"></i>
                            <h6 class="ml-1 mb-0">${profileName}</h6>
                        </div>
                        <div class="col-md-6 options text-right pr-0">
                            <i class="fa fa-window-minimize hide-chat-box hover text-center pt-1"></i>
                            <p class="arrow-up mb-0">
                                <i class="fa fa-arrow-up text-center pt-1"></i>
                            </p>
                            <i onclick ="location.replace('/likes')" class="fa fa-times hover text-center pt-1"></i>
                        </div>
                    </div>
                    <div class="row header-two w-100">
                        <div class="col-md-6 options-left pl-1">
                            <i class="fa fa-video-camera mr-3"></i>
                            <i class="fa fa-user-plus"></i>
                        </div>
                        <div class="col-md-6 options-right text-right pr-2">
                            <i class="fa fa-cog"></i>
                        </div>
                    </div>
                </div>
                <div class="chat-content">
                    <div id="chatbox" class="col-md-12 chats pt-3 pl-2 pr-3 pb-3">
                        <#if emptyChat>
                            <p class="text-center pt-lg-5 pb-1 pl-2 pr-2 m-0 rounded">
                                ${emptyChatMessage}
                            </p>
                        <#else>
                            <#list chat>
                                <ul>
                                    <#items as message>
                                        <#if !message.isSentByUser()>
                                            <li class="receive-msg float-left mb-2">
                                                <div class="sender-img">
                                                    <img src="${message.getSenderAvatar()}" class="float-left">
                                                </div>
                                                <div class="receive-msg-desc float-left ml-2">
                                                    <p class="bg-white m-0 pt-1 pb-1 pl-2 pr-2 rounded">
                                                        ${message.getText()}
                                                    </p>
                                                    <#if message.getDaysSinceSent() gt 0>
                                                        <span class="receive-msg-time">${message.getSenderName()}, ${message.getDatetime()}</span>
                                                    <#elseif message.getMinutesSinceSent() gt 59>
                                                        <span class="receive-msg-time">${message.getSenderName()}, ${message.getHoursSinceSent()}h ago</span>
                                                    <#else> <span class="receive-msg-time">${message.getSenderName()}</span>
                                                    </#if>
                                                </div>
                                            </li>
                                        <#else>
                                            <li class="send-msg float-right mb-2">
                                                <p class="pt-1 pb-1 pl-2 pr-2 m-0 rounded">${message.getText()}</p>
                                                <#if message.getDaysSinceSent() gt 0>
                                                    <span class="send-msg-time">${message.getDatetime()}</span>
                                                <#elseif message.getMinutesSinceSent() gt 59>
                                                    <span class="send-msg-time">${message.getHoursSinceSent()}h ago</span>
                                                </#if>
                                            </li>
                                        </#if>
                                    </#items>
                                </ul>
                            </#list>
                        </#if>
                    </div>
                    <div class="col-md-12 p-2 msg-box border border-primary">
                        <div class="input-wrapper">
                            <input id="msgInput" name="message" type="text" class="border-0" placeholder=" Send message" />
                        </div>
                        <button id="sendMsg" class="btn btn-info">
                            <i class="fa fa-paper-plane send-icon" aria-hidden="true"></i>
                        </button>
                    </div>
                    </div>
                </div>
            </div>
        </div>

    <script>
      listen();

      async function sendMessage(text) {
        try {
          return await fetch(location.href, {
            method: "POST",
            headers: {"Content-Type": 'application/json',},
            body: JSON.stringify({text})
          })
        } catch (e) {console.error(e)}
      }

      function listen() {
        const chatbox = document.querySelector("#chatbox");
        chatbox.scrollTop = chatbox.scrollHeight - chatbox.clientHeight;
        const msgbox = document.querySelector(".msg-box");
        const input = document.querySelector("#msgInput");

        msgbox.addEventListener("keyup", evt => {
          evt.preventDefault();
          if(evt.code === "Enter" && evt.target.id === "msgInput" && input.value!== "") {
            sendMessage(input.value).then(r => {
              if(r.ok) {
                input.value="";
                location.reload();
              }
            });
          }
        });

        msgbox.addEventListener('click', evt => {
          if(evt.target.id === "sendMsg" && input.value!== "") {
            sendMessage(input.value).then(r => {
              if(r.ok) {
                input.value="";
                location.reload();
              }
            });
          }
        })
      }
    </script>
</@c.page>