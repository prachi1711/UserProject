
$(document).ready(function(){
    $("#doneBtn").click(function(){
    	var userPost = $('#userPost').val();
        var userName = $('#userName').val();
    	var userPosts = [];    	
    	var userText = {};    	       	    	
       	userPosts.push(userPost);
       	userText["userName"] = userName;
       	userText["userPost"] = userPosts;       	
        $.ajax({
            headers: { 
        	   'Accept': 'application/json',
        	   'Content-Type': 'application/json' 
        	},
            type: "POST",
            url: "userPost",
            data: JSON.stringify(userText),            
            dataType: "json",
    	    success: function(response){        	    	
    	    	if (response.status == "SUCCESS"){    
    	    		debugger;
    	    		userText = response.result.userPost;
    	    		$.each(userText, function(idx, elem){
    	    			$('table#postTbl').append('<tr><td class="comment">'+elem.userPost +'</td></tr>');
    	    		});   
        	        $('#userPost').val('');  
    	    	} else {
    	    		$('#outputTxt').html(response.status);    	    		
    	    	}    	          	
            },    	
    	    error: function(e){      	    	
    	        alert('Error: ' + e);    	
    	    }    	
    	 });    	
    });              
});