$(document).ready(function(){
    showParticipants();
    $("#postParticipantBtn").click(postParticipant);

});

// determine when to show participants
function showParticipants(){
    var typeRadio = $("#typeRadio");
    var conversationParticipants = $("#conversationParticipants");
    typeRadio.change(function(){
        if ($("#typePublic").checked){
            // hide participants if conversation is public
            conversationParticipants.hide();
        } else {
            // show participants if conversation is private
            conversationParticipants.show();
        }
    });
}

// handle postParticipant
function postParticipant() {
    $.post("/conversations/private", {
        'conversationParticipant':$("#conversationParticipant").val(),
        'conversationTitle': $("#conversationTitle").val(),
    }
        , function(data, status, somethingelse) {
            console.log(data);
            //console.log(data.getParameter("participants"));
            console.log(somethingelse);
            // $.each(data, function (key, value) {
            //
            //     $('#subFolder').append('<option value="' + key + '">"' + value + '"</option>');
            // })
        }
    )
}


