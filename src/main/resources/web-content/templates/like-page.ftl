<#import "common.ftl" as c>
<@c.page>
    <div class="d-flex w-25 h-75 flex-column m-auto align-items-center">

<#--        <div class="mb-3 d-flex w-100 justify-content-between">-->
<#--            <a href="/likes">see liked</a>-->
<#--            <button onclick="logout()" type="button" class="btn btn-dark"><i class="fas fa-sign-out-alt"></i></button>-->
<#--        </div>-->

        <div class="card w-100">
            <div class="card-body">
                <div class="row">
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <#if avatar??>
                            <img src=${avatar} alt="" class="mx-auto like-page-img rounded-circle">
                        <#else> <img src="https://robohash.org/68.186.255.198.png" alt="" class="mx-auto like-page-img rounded-circle">
                        </#if>
                        <h3 class="mb-0 text-truncated">${username}</h3>
                        <br>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button id ="dislikeButton" type="button" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Dislike</button>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button id ="likeButton" class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like</button>
                    </div>
                </div>
            </div>
        </div>
            <#if app_error != "">
                <div class="alert-danger"><p>${app_error}</p></div>
            </#if>
    </div>


    <script>

      listen(location.href);

      async function postAction(url, type) {
       try {
           const response = await fetch(url, {
           method: "POST",
           headers: {"Content-Type": 'application/json',},
           body: JSON.stringify({ type })
         });
           if (response.ok) {
               location.reload();
           }
       } catch (e) { console.error(e); }
      }

      function listen(url) {
        document.querySelector(".card").addEventListener('click', evt => {
          const { id } = evt.target;

            if(id === "likeButton") {
                postAction(url, "like");
                console.log("like");
            } else if(id === "dislikeButton") {
                postAction(url,"dislike");
                console.log("dislike");
            }

        });
      }
    </script>
</@c.page>