function activeTabFlow(e,t){$(".flow-step-"+e).removeClass("step-active");$(".flow-step-"+t).addClass("step-active")}function showEndMessage(e,t,n){$("#main-content").hide();$("#div-end-message").show();$("#end-message").text(e);activeTabFlow(t,n)}$(document).ready(function(){function n(){r(1);$(".flow-step").click(function(n){if($(this).attr("data-flow-next")!==undefined){e=$(this).attr("data-flow-next");activeTabFlow(parseInt(e)-1,parseInt(e))}if($(this).attr("data-flow-back")!==undefined){t=$(this).attr("data-flow-back");activeTabFlow(parseInt(t)+1,parseInt(t))}})}function r(e){$(".flow-step-"+e).addClass("step-active")}var e;var t;(function(){if($(".breadcrumb-load").val()!==undefined){var e=$(".breadcrumb-load").val();r(e)}else{n()}})()})